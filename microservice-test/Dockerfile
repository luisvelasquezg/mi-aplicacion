# Utilizamos una imagen base de OpenJDK para ejecutar la aplicación
#FROM openjdk:17-jre-slim
FROM openjdk:17-jdk-slim

# Establecemos el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el archivo JAR de la aplicación al contenedor
#COPY target/spring-boot-2-hello-world-1.0.2-SNAPSHOT.jar /app/spring-boot-2-hello-world-1.0.2-SNAPSHOT.jar
COPY target/microservice-test-0.0.1-SNAPSHOT.jar /app/microservice-test-0.0.1-SNAPSHOT.jar

# Exponemos el puerto en el que la aplicación Spring Boot escuchará las solicitudes
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot cuando el contenedor se inicie
CMD ["java", "-jar", "microservice-test-0.0.1-SNAPSHOT.jar"]