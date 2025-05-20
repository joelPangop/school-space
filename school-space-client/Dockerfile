# Étape 1 : build de l'app React
FROM node:20 AS build
WORKDIR /app
# Cache dependencies
COPY package.json package-lock.json ./
RUN npm ci

COPY . .

# Build the app
RUN npm run build

# Injecter la variable REACT_APP_API_URL dans le build
ARG REACT_APP_API_URL
ENV REACT_APP_API_URL=$REACT_APP_API_URL

RUN npm install
RUN npm run build

# ---------- Stage 2: Serve with Nginx ----------
FROM nginx:alpine

# Étape 2 : serveur web statique avec Nginx
FROM nginx:alpine
COPY --from=build /app/build /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]