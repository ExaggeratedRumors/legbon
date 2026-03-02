FROM openjdk:20-jdk-slim
RUN mkdir /app
COPY ./build/libs/legbon-server-all.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]