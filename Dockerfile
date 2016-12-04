FROM node:argon

# Create app directory
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

# Install app dependencies
COPY package.json /usr/src/app/
RUN npm install

# Bundle app source
COPY app /usr/src/app/app
COPY index.js /usr/src/app/

EXPOSE 80
CMD [ "npm", "start" ]
