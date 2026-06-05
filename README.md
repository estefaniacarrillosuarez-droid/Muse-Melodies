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

### 4. Compilar y Ejecutar proyecto
**Desde la Terminal:** Navega hasta la raíz del proyecto y ejecuta:

```
mvn spring-boot:run
```

Al arrancar, Hibernate generará de forma automática las tablas `artistas`, `canciones`, `generos` y la tabla intermedia `artista_genero` en tu base de datos gracias a la propiedad `spring.jpa.hibernate.ddl-auto=update`.

### 5. Cómo probar los Endpoints protegidos (POST, PUT, DELETE)
Dado que la API cuenta con un filtro de seguridad (`ApiKeyFilter`), si intentas crear, actualizar o eliminar un recurso sin autenticarte, el servidor rechazará la petición con un error `401 Unauthorized`.

Para identificarte correctamente, debes incluir la siguiente cabecera HTTP en cada petición de escritura:

* **Clave (Header Key):** `X-API-KEY`
* **Valor (Header Value):** `musemelodies123`

A continuación se detalla cómo configurarlo en las principales herramientas:

#### 1. En Postman 
1. Selecciona el método de la petición (`POST`, `PUT` o `DELETE`) e introduce la URL (ej. `http://localhost:8080/api/v1/artistas`).
2. Justo debajo de la barra de direcciones, haz clic en la pestaña **Headers** (Cabeceras).
3. En la columna **Key**, escribe: `X-API-KEY`.
4. En la columna **Value**, escribe: `musemelodies123`.
5. *(Opcional)* Si vas a enviar datos en un `POST` o `PUT`, recuerda ir a la pestaña **Body**, seleccionar **raw** y cambiar el desplegable de la derecha a **JSON** para pegar tu objeto.
6. Haz clic en **Send**.

---

#### 2. En Thunder Client (VS Code) 
1. Abre una nueva petición en Thunder Client seleccionando el método correspondiente e introduciendo la URL del endpoint.
2. En las pestañas inferiores de la petición, haz clic en **Headers**.
3. Verás una tabla con dos columnas principales: *Name* y *Value*.
4. En el campo **Name**, escribe: `X-API-KEY`.
5. En el campo **Value**, escribe: `musemelodies123`.
6. Al igual que en Postman, si necesitas enviar datos, ve a la pestaña **Body**, asegúrate de que esté seleccionado **JSON** y escribe tu código.
7. Haz clic en el botón **Send**.

## Estructura del proyecto

El proyecto sigue una arquitectura por capas para separar responsabilidades y mantener un código limpio y escalable.

La estrctura utilizada es: 

* Controller -> Gestiona las peticiones HTTP y las respuestas de la API.
* Service -> Contiene la lógica de negocio de la aplicación.
* Repository -> Gestiona el acceso a la base de datos mediante Spring Data JPA.
* Model -> Define las entidades JPA y las relaciones entre ellas.
* Security -> Gestiona la autenticación mediante API Key.
* Exception -> Contiene el manejo global de excepciones mediante @ControllerAdvice.

Esta arquitectura facilita el mantenimiento del proyecto, la reutilización del código y una mejor separación de responsabilidades.

## Endpoints principales

### Base URL

http://localhost:8080

---

## Artistas

| Método | Endpoint |
|---|---|
| GET | /api/v1/artistas |
| GET | /api/v1/artistas/{id} |
| GET | /api/v1/artistas/buscar |
| POST | /api/v1/artistas |
| PUT | /api/v1/artistas/{id} |
| DELETE | /api/v1/artistas/{id} |

## Relaciones entre entidades

La aplicación implementa distintas relaciones JPA entre las entidades del sistema.

Relaciones implementadas:

* Un artista puede tener muchas canciones (@OneToMany).
* Una canción pertenece a un único artista (@ManyToOne).
* Un artista puede tener varios géneros y un género puede pertenecer a varios artistas (@ManyToMany).

---

## Interfaz web

El proyecto incluye una interfaz web desarrollada con:

* Consultar artistas
* Buscar y filtrar información
* Consultar canciones y género

Para utilizar la interfaz: 

