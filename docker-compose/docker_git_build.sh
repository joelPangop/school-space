echo "📥 Pull Git..."
git pull origin master

echo "🧪 Build backend..."
cd school-space-services
./mvnw clean package -DskipTests || exit 1
cd ..

echo "🐳 Docker Compose..."
docker compose down
docker compose up --build

echo "✅ Fini !"