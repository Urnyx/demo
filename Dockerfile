# Usa la imagen de OpenJDK como base
FROM openjdk:17

# Crea y Establece el directorio de trabajo en la aplicación si no se especifica un workdir.
RUN mkdir -p /home/app

# Copia el archivo JAR de la aplicación al contenedor
COPY ./target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que la aplicación está escuchando
EXPOSE 8080

# Comando para ejecutar la aplicación al iniciar el contenedor
CMD ["java", "-jar", "app.jar"]
