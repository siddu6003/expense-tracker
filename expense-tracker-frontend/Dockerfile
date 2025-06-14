# Build stage
FROM node:18 as build
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build

# CORS fix (optional: if needed, modify nginx conf)
EXPOSE 3000
CMD ["npm", "start"]