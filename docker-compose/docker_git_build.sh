echo "📥 Pull Git..."
git pull origin master

docker compose down

#!/bin/bash

# Récupère l'IP publique de l'instance EC2 depuis le Metadata service
PUBLIC_IP=$(curl -s http://169.254.169.254/latest/meta-data/public-ipv4)

# Chemin vers le dossier du frontend
CLIENT_DIR="../school-space-client"

# Build de l'image Docker avec l'URL backend injectée dynamiquement
echo "➡️  Public IP EC2: $PUBLIC_IP"
echo "➡️  Building schoolspaceclient with API URL: http://$PUBLIC_IP:8080"

docker build \
  --build-arg REACT_APP_API_URL=http://$PUBLIC_IP:8080 \
  -t schoolspaceclient \
  "$CLIENT_DIR"


echo "🧪 Build backend..."
cd school-space-services
./mvnw clean package -DskipTests || exit 1
cd ..

echo "🐳 Docker Compose..."
docker compose up --build

echo "✅ Fini !"