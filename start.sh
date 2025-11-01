#!/usr/bin/env bash
set -e

# Porta padrão
PORT=${PORT:-8080}
SERVER_XML="$CATALINA_HOME/conf/server.xml"

# Substitui a porta 8080 pela fornecida pelo Render
sed -i "s/port=\"8080\"/port=\"${PORT}\"/g" "$SERVER_XML"

echo "✅ Iniciando Tomcat na porta ${PORT}"
exec catalina.sh run