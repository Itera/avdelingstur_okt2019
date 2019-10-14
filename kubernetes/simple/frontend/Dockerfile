FROM node:12-alpine

WORKDIR /usr/src/appv

COPY package.json ./

RUN npm install

COPY server.js ./

EXPOSE 80

CMD [ "node", "server.js" ]