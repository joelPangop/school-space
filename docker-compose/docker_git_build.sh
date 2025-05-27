#!/bin/bash

echo "📥 Pull Git..."
cd /home/ec2-user/school-space || exit 1
git pull origin master

echo "⛔️ Arrêt des conteneurs..."
docker-compose down

# Obtenir l'IP publique de l'EC2
PUBLIC_IP=$(curl -s http://169.254.169.254/latest/meta-data/public-ipv4)

# === Build du frontend React ===
CLIENT_DIR="./school-space-client"

echo "🌍 IP Publique EC2 : $PUBLIC_IP"
echo "🛠️ Build schoolspaceclient avec REACT_APP_API_URL=$PUBLIC_IP:8080"

docker build \
  --build-arg REACT_APP_API_URL=http://$PUBLIC_IP:8080 \
  -t schoolspaceclient \
  --no-cache \
  "$CLIENT_DIR" || exit 1

# === Build du backend Spring Boot ===
echo "🧪 Build backend..."
cd ./school-space-services || exit 1
chmod +x mvnw
./mvnw clean package -DskipTests || exit 1
cd ..

# === Docker Compose ===
echo "🐳 Lancement des conteneurs..."
docker-compose up

echo "✅ Déploiement terminé avec succès !"
