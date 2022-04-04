FROM openjdk:17-alpine
COPY . /app
WORKDIR /app
RUN gradlew :bootJar
WORKDIR /app/build/libs
RUN mv *.jar app.jar
EXPOSE 8080
CMD ["java","-jar","app.jar"]
