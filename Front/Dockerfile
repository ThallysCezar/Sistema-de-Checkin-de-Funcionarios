FROM node:20-alpine AS build

WORKDIR /app

ARG API_URL=http://localhost:8081
ENV API_URL=${API_URL}

COPY package*.json ./
RUN npm ci

COPY . .

RUN echo "export const environment = { production: true, apiUrl: '${API_URL}' };" > src/environments/environment.prod.ts
RUN echo "Generated environment.prod.ts:" && cat src/environments/environment.prod.ts

RUN npm run build -- --configuration production

FROM nginx:alpine

WORKDIR /usr/share/nginx/html

COPY --from=build /app/dist/Front/browser .

COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
