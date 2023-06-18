# Test de dockerizacion

## Dockerizar  

- Crear un archivo dockerfile
- Crear un archivo docker compose


1. Compilar la aplicacion `./mvnw clean package -DskipTest`
2. Crear el dockerfile

    ```dockerfile
    FROM (imagen de la que parte para crear)
    COPY (ubicacion del jar) (nuevo nombre del jar)
    MAINTAINER (persona / encargado de mantener el contenedor)
    ENTRYPOINT ["java", "-jar", "ms-curso.jar"]
    ```

    ```dockerfile
    FROM openjdk:11-jdk-slim
    COPY target/ms-curso-0.0.1-SNAPSHOT.jar ms-curso.jar
    ENTRYPOINT ["java", "-jar", "ms-curso.jar"]
    ```
3. Crear el archivo docker-compose.yml

    ```yml
    version: VERSION

    services:
        ms-curso:
            container_name: NOMBRE DLE CONTENEDOTR
            image: IMAGEN DE LA QUE QUE SE CONSTRUYE EL CONTENEDOR
            build: PATH DEL DOCKERFILE
            ports:
            - puerto_host:puerto_contenedor
            - 8080 se refiere al puerto del host. Es el puerto que puedes utilizar para acceder a tu aplicación desde el host.
            - 8083 se refiere al puerto del contenedor. Es el puerto en el que la aplicación dentro del contenedor está escuchando.
            environment:
            - vARIABLES DE ENTORNO
    ```

    ```yml
    version: '1.0'

    services:
        ms-curso:
            container_name: ms-curso
            image: ms-curso:latest
            build: .
            ports:
            - 8083:8083
            environment:
            - DATABASE_URL=jdbc:mysql://localhost:3306/colegio?serverTimezone=GMT-5
            - DATABASE_USERNAME=root
            - DATABASE_PASSWORD=duckCop1502
    ```
4. Compilar nuevamente por si añadiste algo `./mvnw clean package -DskipTest`
5. Ejecutar el docker build `docker-compose build nombre_app` | En vez de poner el nombre dle servicio tambien puedes ejecutar en lote (variois services)

6. subir la imagen
   1. logearse
      1. docker login
      2. usuario
      3. password
   2. Crear el repositorio
   3. Etiqueta tu imagen con el nombre de usuario de Docker Hub y el nombre del repositorio
      1. `docker tag <nombre_imagen_local> <nombre_usuario_dockerhub>/<nombre_repositorio>:<tag>`
      2. docker tag ms-curso:1.0.0 iryps/prueba-ms-curso:1.0.0
   4. Sube la imagen etiquetada a Docker Hub utilizando el siguiente comando:
      1. `docker push <nombre_usuario_dockerhub>/<nombre_repositorio>:<tag>`
      2. docker push iryps/prueba-ms-curso:1.0.0
7.  Crear azure app services
    1.  Crear una app, definir lso demas atributos
8.  Centor de implementacion
    1.  Definir la configuracion yml de docker compose
9.  Esperar y probar al API


## Crear el workflow

1. crear el direrctorio `.github/workflows`
2. crear un yaml para el workflow
   ```yml
   name: Create JAR and list JARs

    on:
    push:
        branches:
        - main

    jobs:
    create-jar:
        runs-on: ubuntu-latest
        permissions:
      contents: read
      packages: write
        steps:
        - name: Check out the repository
            uses: actions/checkout@v3

        - name: Create the JAR
            run: ./mvnw clean package -DskipTests

        - name: List the JARs - funcionará chan chan channnnnn
            run: ls target/
   ```
3. Deebes añadir docker secrets si te encuentras con al sigueinte peroblematica
   1. Tienes variables de entorno que funcionan con el .env en local
    ```yml
    name: Create JAR and list JARs

    on:
    push:
        branches:
        - master

    jobs:
    create-jar:
        runs-on: ubuntu-latest

        steps:
        - name: Check out the repository
            uses: actions/checkout@v3

        - name: Set up JDK 11
            uses: actions/setup-java@v3
            with:
            java-version: '11'
            distribution: 'temurin'

        - name: Build with Maven
            env:
                DATABASE_URL: ${{ secrets.DATABASE_URL }}
                DATABASE_USERNAME: ${{ secrets.DATABASE_USERNAME }}
                DATABASE_PASSWORD: ${{ secrets.DATABASE_PASSWORD }}
            run: mvn -q -B package --file pom.xml

        - name: List the JARs - funcionará chan chan channnnnn
            run: ls target/
    ```
   2. a