FROM openjdk:8-jdk-alpine
COPY . /app
WORKDIR /app
RUN /app/gradlew -v
CMD ["/app/gradlew", "bootRun"]