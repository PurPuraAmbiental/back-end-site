# ===== Build do WAR com Maven (JDK 23) =====
FROM openjdk:23-slim AS builder

# Instala Maven e dependências básicas
RUN apt-get update && \
    apt-get install -y maven curl git && \
    rm -rf /var/lib/apt/lists/*

# Diretório de trabalho
WORKDIR /app

# Copia pom.xml e baixa dependências offline
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia código-fonte e gera WAR
COPY src ./src
RUN mvn clean package -DskipTests

# ===== Imagem final com Tomcat 11 =====
FROM openjdk:23-slim

# Variáveis do Tomcat
ENV CATALINA_HOME=/opt/tomcat
ENV PATH="$CATALINA_HOME/bin:$PATH"

# Instala curl e baixa Tomcat 11
RUN apt-get update && apt-get install -y curl && \
    curl -fsSL https://dlcdn.apache.org/tomcat/tomcat-11/v11.0.13/bin/apache-tomcat-11.0.13.tar.gz -o /tmp/tomcat.tar.gz && \
    mkdir -p "$CATALINA_HOME" && \
    tar -xzf /tmp/tomcat.tar.gz -C "$CATALINA_HOME" --strip-components=1 && \
    rm /tmp/tomcat.tar.gz && \
    rm -rf /var/lib/apt/lists/*

# Limpa webapps padrão
RUN rm -rf $CATALINA_HOME/webapps/*

# Copia WAR do builder
COPY --from=builder /app/target/*.war $CATALINA_HOME/webapps/ROOT.war

# Script de start
COPY start.sh /start.sh
RUN chmod +x /start.sh

# Porta padrão
EXPOSE 8080

# Comando de start
CMD ["/start.sh"]
