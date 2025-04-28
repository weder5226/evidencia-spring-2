# Proyecto Sistema de Ventas

Este es un sistema de ventas orientado a la gestión comercial de una empresa que fabrica y vende productos en general.  
La aplicación permite la administración de productos, usuarios, clientes y órdenes de venta, todo a través de una API REST desarrollada con tecnologías modernas en un entorno Java con Spring Boot.

---

## 🚀 Tecnologías utilizadas

- **Java 21** – Lenguaje de programación principal (última LTS)
- **Spring Boot 3+** – Framework para desarrollo backend
- **Spring Web** – Construcción de servicios RESTful
- **Spring Data JPA (Hibernate)** – Persistencia de datos
- **Spring Security** – Seguridad y control de autenticación
- **PostgreSQL** – Sistema de gestión de bases de datos relacional
- **Gradle** – Sistema de construcción del proyecto
- **OpenAPI 3 / Swagger UI** – Documentación de la API
- **Postman** – Pruebas de endpoints

---

## 🖥️ Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- [Java 21+](https://www.oracle.com/co/java/technologies/downloads/)
- [PostgreSQL 16+](https://www.postgresql.org/download/)
- [Gradle](https://gradle.org/install/) (o usar `./gradlew`)
- [Visual Studio Code](https://code.visualstudio.com/)
- Extensiones recomendadas (se te sugerirán al abrir VS Code):
  - Extension Pack for Java
  - Spring Boot Extension Pack
  - Gradle for Java

---

## ⚙️ Configuración del entorno

1. **Clona el repositorio**

```bash
git clone https://github.com/weder5226/evidencia-spring-2.git
cd evidencia-spring-2
```

2. **Crea una base de datos vacía en PostgreSQL**

Ejemplo usando consola de PostgreSQL:

```sql
CREATE DATABASE sistema_ventas;
```

3. **Edita el archivo *src/main/resources/application.properties***

Configura la conexión a tu base de datos:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/sistema_ventas
spring.datasource.username=<tu_usuario>
spring.datasource.password=<tu_contraseña>
```

Reemplaza los valores entre <> con tus credenciales reales.

4. **Abre el proyecto en VS Code**

VS Code te sugerirá instalar las extensiones necesarias (Java, Spring, Gradle). Acepta las recomendaciones.

5. **Actualiza las dependencias**

En la terminal de VS Code:

```bash
./gradlew build --refresh-dependencies
```

6. **Ejecuta la aplicación**

Ubica la clase principal (por ejemplo SistemaVentasApplication.java) y haz clic en “Run”, o ejecuta desde terminal:

```bash
./gradlew bootRun
```

7. **Accede a la aplicación**

El backend estará disponible en *http://localhost:8080*
La documentación estará disponible a través del navegador en *http://localhost:8080/swagger-ui*

---

## 🔐 Autenticación

Al iniciar la aplicación, se crea automáticamente un usuario administrador por defecto:

- Email: `admin@example.com`
- Contraseña: `123456`

Es obligatorio autenticarse con este usuario para poder consumir los endpoints protegidos y registrar nuevos usuarios.

---

## 📄 Documentación de la API

La API cuenta con documentación automática utilizando OpenAPI 3. Puedes acceder a la interfaz Swagger en: http://localhost:8080/swagger-ui. 

Desde ahí puedes probar los endpoints, revisar parámetros y ver respuestas posibles.

---

## 🧪 Pruebas

Se recomienda el uso de Postman para probar los diferentes endpoints. Puedes crear una colección y autenticarte usando el usuario administrador, luego enviar peticiones GET, POST, PUT, DELETE según las funcionalidades implementadas.

---

## 📌 Notas finales

La base de datos se actualiza automáticamente al iniciar la aplicación gracias a las bondades que ofrece spring mediante la definición de un esquema base.
