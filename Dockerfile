FROM openjdk:21-jdk-slim
WORKDIR /app
COPY app/build/libs/exchangerates.jar /app
EXPOSE 8080
CMD ["java", "-jar", "exchangerates.jar"]