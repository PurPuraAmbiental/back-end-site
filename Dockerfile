FROM openjdk:23-slim AS builder

RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src/ ./src/
RUN mvn clean package -DskipTests

FROM openjdk:23-slim

ENV CATALINA_HOME=/opt/tomcat
ENV PATH="$CATALINA_HOME/bin:$PATH"

RUN apt-get update && apt-get install -y curl && \
    curl -fsSL https://downloads.apache.org/tomcat/tomcat-10/v10.1.34/bin/apache-tomcat-10.1.34.tar.gz -o /tmp/tomcat.tar.gz && \
    mkdir -p "$CATALINA_HOME" && \
    tar -xzf /tmp/tomcat.tar.gz -C "$CATALINA_HOME" --strip-components=1 && \
    rm /tmp/tomcat.tar.gz && \
    rm -rf /var/lib/apt/lists/*

RUN rm -rf $CATALINA_HOME/webapps/*

COPY --from=builder /app/target/*.war $CATALINA_HOME/webapps/ROOT.war

COPY start.sh /start.sh
RUN chmod +x /start.sh

EXPOSE 8080

CMD ["/start.sh"]
