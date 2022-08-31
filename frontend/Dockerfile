FROM node:lts-alpine as builder

WORKDIR /user_app
RUN pwd
COPY /frontend/user/package*.json ./
RUN npm install

COPY /frontend/user ./
RUN npm run build

WORKDIR /admin_app
COPY /frontend/admin/package*.json ./
RUN npm install

COPY /frontend/admin ./
RUN npm run build


FROM nginx:stable-alpine
RUN rm -rf /etc/nginx/conf.d/default.conf
COPY /frontend/nginx/default.conf /etc/nginx/conf.d/default.conf
run sed -i "s/\r//g" /etc/nginx/conf.d/default.conf

RUN rm -rf /usr/share/nginx/html/*
COPY --from=builder /user_app/dist /usr/share/nginx/user
COPY --from=builder /admin_app/dist /usr/share/nginx/admin

EXPOSE 8080 8012
ENTRYPOINT ["nginx", "-g", "daemon off;"]