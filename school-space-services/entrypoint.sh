#!/bin/sh

echo "⌛ Attente que MySQL soit prêt..."

DB_HOST=${DB_HOST:-localhost}
DB_USER=${DB_USER:-root}
DB_PASSWORD=${DB_PASSWORD:-password}

until mysqladmin ping -h "$DB_HOST" -u "$DB_USER" -p"$DB_PASSWORD" --silent; do
  echo "⏳ MySQL ($DB_HOST) n'est pas encore prêt. Nouvelle tentative dans 5s..."
  sleep 5
done

echo "✅ MySQL est prêt. Lancement de l'application Spring Boot..."
#exec java -jar app.jar
exec java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 \
     -jar app.jar

