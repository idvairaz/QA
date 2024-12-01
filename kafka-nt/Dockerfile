FROM maven:latest AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:17
WORKDIR /app
COPY --from=builder /app/target/kafka-unit-test-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
EXPOSE 9092
EXPOSE 9093
EXPOSE 9094
CMD ["java", "-jar", "app.jar"]