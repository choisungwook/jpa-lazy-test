FROM node:lts-alpine as build-stage
ENV NODE_ENV=production
WORKDIR /app
COPY package*.json ./
RUN npm install --production
COPY . .
RUN npm run build

# production stage
FROM nginx:stable-alpine as production-stage
ARG nginx_conf=/etc/nginx/conf.d

COPY --from=build-stage /app/dist /usr/share/nginx/html
COPY ./nginx.conf $nginx_conf/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]