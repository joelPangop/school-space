# ---------- Stage 1: Build React App ----------
FROM node:20 AS build

WORKDIR /app

# Inject API URL (must come BEFORE build)
ARG REACT_APP_API_URL
ENV REACT_APP_API_URL=$REACT_APP_API_URL

# Install deps with cache
COPY package.json package-lock.json ./
RUN npm ci

# Copy the rest of the source
COPY . .

# 🔥 Supprimer le dossier build au cas où il existe
RUN rm -rf build

# ---------- Stage 2: Serve with Nginx ----------
FROM nginx:alpine

# Copy built files from previous stage
COPY --from=build /app/build /usr/share/nginx/html

# Build the app using the injected REACT_APP_API_URL
RUN REACT_APP_API_URL=$REACT_APP_API_URL npm run build

# Custom Nginx config (optional)
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
