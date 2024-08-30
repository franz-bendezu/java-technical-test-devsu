

# Examen Técnico Devsu

Este repositorio contiene dos microservicios: `account-service` y `client-service`. A continuación, se detallan las instrucciones para configurar y ejecutar estos servicios.

## Estructura del Proyecto

```
.
├── account-service/ ( Microservicio de Cuentas - Movimientos )
├── client-service/ ( Microservicio de Cliente- Persona )
├── Devsu Bank.postman_collection.json ( Colección de Postman )
├── docker-compose.dev.yml ( Archivo de configuración de Docker Compose para desarrollo )
├── docker-compose.yml ( Archivo de configuración de Docker Compose para producción )
└── BaseDatos.sql ( Script de creación de base de datos )
```

## Requisitos

- Docker
- Docker Compose
- Java 21
- Maven

## Configuración

### Variables de Entorno

Asegúrese de configurar las siguientes variables de entorno en los archivos [`application.properties`]( /account-service/src/main/resources/application.properties) de cada microservicio:

#### [`account-service/src/main/resources/application.properties`](/account-service/src/main/resources/application.properties)

```properties
spring.datasource.username=${SPRING_DATASOURCE_USERNAME:test}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD:test}
spring.datasource.url=jdbc:mysql://${SPRING_DATASOURCE_HOST:localhost}:${SPRING_DATASOURCE_PORT:3306}/${SPRING_DATASOURCE_DB:ms_account}
spring.flyway.enabled=${SPRING_FLYWAY_ENABLED:false}
spring.kafka.bootstrap-servers=${SPRING_KAFKA_BOOTSTRAP_SERVERS:localhost:29092}
```

#### [`client-service/src/main/resources/application.properties`](/client-service/src/main/resources/application.properties)

```properties
spring.datasource.username=${SPRING_DATASOURCE_USERNAME:test}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD:test}
spring.datasource.url=jdbc:postgresql://${SPRING_DATASOURCE_HOST:localhost}:${SPRING_DATASOURCE_PORT:5432}/${SPRING_DATASOURCE_DB:ms_client}
spring.flyway.enabled=${SPRING_FLYWAY_ENABLED:false}
spring.kafka.bootstrap-servers=${SPRING_KAFKA_BOOTSTRAP_SERVERS:localhost:29092}
```

## Ejecución

### Usando Docker Compose

Para levantar los microservicios y sus dependencias, ejecute el siguiente comando:

```sh
docker-compose up --build
```

### Usando Maven

Para compilar y ejecutar cada microservicio individualmente, siga los siguientes pasos:

#### [`account-service`] ("/account-service")

1. Navegue al directorio [`account-service`]("/account-service"):

   ```sh
   cd account-service
   ```

2. Compile y ejecute el servicio:
   ```sh
   ./mvnw spring-boot:run
   ```

#### [`client-service`]("/client-service")

1. Navegue al directorio [`client-service`]("/client-service"):

   ```sh
   cd client-service
   ```

2. Compile y ejecute el servicio:
   ```sh
   ./mvnw spring-boot:run
   ```

## Pruebas

### Postman

Se incluye una colección de Postman en el archivo `Devsu Bank.postman_collection.json` para probar los endpoints de los microservicios.

### JUnit

Para ejecutar las pruebas unitarias, navegue al directorio del microservicio correspondiente y ejecute:

```sh
./mvnw test
```

## Migraciones de Base de Datos

### Para desarollo local
Las migraciones de base de datos se encuentran en los directorios [`src/main/resources/db/migration`]
[`microservcio-cuenta`](/account-service/src/main/resources/db/migration) y [`microservicio-cliente`](/client-service/src/main/resources/db/migration) para cada microservicio. Para ejecutarlas, asegúrese de que la variable [`spring.flyway.enabled`]
['Editar en microservicio-cuenta'](account-service/src/main/resources/application.properties) y 
['Editar en microservicio-cliente'](client-service/src/main/resources/application.properties)
esté configurada en [`true`]

### Para producción (docker-compose)

Las migraciones de base de datos se ejecutan automáticamente al iniciar los contenedores de Docker
 siempre y cuando la variable [`SPRING_FLYWAY_ENABLED`] esté configurada en [`true`] en los archivos [`docker-compose.yml`](docker-compose.yml) para cada microservicio.

## Contacto

Para cualquier consulta, por favor contacte al equipo de Devsu.

```

```
