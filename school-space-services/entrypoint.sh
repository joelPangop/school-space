#!/bin/sh

echo "⌛ Attente que MySQL soit prêt..."

until mysqladmin ping -h "mysql" -u "root" -p"Abc123..." --silent; do
  echo "⏳ MySQL n'est pas encore prêt. Nouvelle tentative dans 5s..."
  sleep 5
done

echo "✅ MySQL est prêt. Lancement de l'application Spring Boot..."
exec java -jar app.jar
