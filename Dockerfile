FROM maven:3.9.6-eclipse-temurin-21

WORKDIR /app
COPY . .

CMD ["mvn", "clean", "test"]
