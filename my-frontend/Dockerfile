# Stage 1: Build Angular app
FROM node:20-alpine as build

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build --prod

# Stage 2: Nginx serve
FROM nginx:1.25-alpine

# Copy build output
COPY --from=build /app/dist/my-frontend /usr/share/nginx/html

# Copy nginx config
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Expose container port 90
EXPOSE 90

CMD ["nginx", "-g", "daemon off;", "-c", "/etc/nginx/nginx.conf"]
