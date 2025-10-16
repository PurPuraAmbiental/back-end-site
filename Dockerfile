# Build com Maven + JDK 23
FROM openjdk:23-slim AS builder

# Instalar Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copiar o POM e baixar dependências
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar o código-fonte e compilar
COPY src/ ./src/
RUN mvn package -DskipTests

# Runtime com Tomcat + JDK 23
FROM openjdk:23-slim

# Instalar Tomcat manualmente
ENV CATALINA_HOME=/opt/tomcat
ENV PATH="$CATALINA_HOME/bin:$PATH"
RUN apt-get update && apt-get install -y curl && \
    curl -fsSL https://downloads.apache.org/tomcat/tomcat-9/v9.0.97/bin/apache-tomcat-9.0.97.tar.gz -o /tmp/tomcat.tar.gz && \
    mkdir -p "$CATALINA_HOME" && \
    tar -xzf /tmp/tomcat.tar.gz -C "$CATALINA_HOME" --strip-components=1 && \
    rm /tmp/tomcat.tar.gz && \
    rm -rf /var/lib/apt/lists/*

# Remover apps padrão
RUN rm -rf $CATALINA_HOME/webapps/*

# Copiar o WAR gerado
COPY --from=builder /app/target/*.war $CATALINA_HOME/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
