FROM node:latest AS builder

WORKDIR /app

COPY package*.json ./

RUN npm ci

COPY . .

RUN npm rebuild esbuild

RUN npm run build

FROM nginx:latest

RUN rm -rf /usr/share/nginx/html/*

COPY --from=builder /app/dist /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
