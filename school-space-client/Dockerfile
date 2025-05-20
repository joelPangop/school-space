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

# Build the app using the injected REACT_APP_API_URL
RUN npm run build

# ---------- Stage 2: Serve with Nginx ----------
FROM nginx:alpine

# Copy built files from previous stage
COPY --from=build /app/build /usr/share/nginx/html

# Custom Nginx config (optional)
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
