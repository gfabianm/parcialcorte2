# ===============================
# Etapa 1: Compilación del proyecto
# ===============================
FROM maven:3.9.6-amazoncorretto-21 AS build
WORKDIR /app

# Copiar los archivos del proyecto
COPY pom.xml .
COPY src ./src

# Compilar y empaquetar sin ejecutar tests
RUN mvn clean package -DskipTests

# ===============================
# Etapa 2: Ejecución de la app
# ===============================
FROM amazoncorretto:21-alpine-jdk
WORKDIR /app

# Copiar el JAR desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto del servidor
EXPOSE 8080

# Comando para iniciar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
