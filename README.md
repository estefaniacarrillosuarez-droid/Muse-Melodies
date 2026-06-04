# MuseMelodies API

## Introducción

MuseMelodies API es una aplicación desarrollada con Spring Boot que permite gestionar artistas, canciones y géneros musicales mediante una API REST.

El proyecto implementa operaciones CRUD completas (crear, consultar, actualizar y eliminar), además de filtros personalizados, relaciones entre entidades y protección de endpoints mediante API Key.

La aplicación utiliza:

- Java + Spring Boot
- Spring Data JPA
- MYSQL
- Thunder Client para pruebas de endpoints
- Maven para gestión de dependencias

Además, se aplican buenas prácticas como:

- Arquitectura por capas (Controller, Service y Repository)
- Uso de ResponseEntity y códigos HTTP adecuados.
- Seguridad mediante API Key
- Relaciones entre entidades con JPA


## Cómo iniciar el proyecto

### 1. Requisitos previos

Es necesario tener instalado

- Java 17 o superior
- Maven
- MySQL Server
- MySQL Workbench (u otro gestor de base de datos)
- Visual Studio Code o IntelliJ
- Thunder Client

### 2. Configuración de la Base de Datos
La aplicación está configurada para conectarse a un servidor local de MySQL. 
1. Abre tu gestor de base de datos (phpMyAdmin, Workbench, etc.).
2. No es necesario crear la base de datos manualmente si tienes activada la propiedad `createDatabaseIfNotExist=true` en la URL de conexión.
3. Verifica el archivo `src/main/resources/application.properties` y modifica las credenciales según tu servidor local:

```properties
spring.datasource.username=tu_usuario_mysql
spring.datasource.password=tu_contraseña_mysql
```

### 3. Clonar o descargar el proyecto

Importar el proyecto en el IDE seleccionado.

### 4. Compilación y Ejecución
* **Desde la Terminal:** Navega hasta la raíz del proyecto y ejecuta:

```
mvn spring-boot:run
```

Al arrancar, Hibernate generará de forma automática las tablas `artistas`, `canciones`, `generos` y la tabla intermedia `artista_genero` en tu base de datos gracias a la propiedad `spring.jpa.hibernate.ddl-auto=update`.
