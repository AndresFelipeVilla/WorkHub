# WorkHub - API de Gestión de Reservas de Espacios Coworking

## Descripción
WorkHub es una API RESTful diseñada para gestionar de manera eficiente las reservas en un espacio de coworking. Este sistema permite la administración de usuarios, espacios y reservas, además de generar informes sobre la ocupación de los espacios.

---

## Características principales

### 1. **Gestión de usuarios**
- Registro de usuarios.
- Autenticación mediante JWT.
- Asignación de roles (Administrador, Usuario, Invitado, Developer).

### 2. **Gestión de espacios**
- Creación, edición, eliminación y consulta de espacios disponibles.

### 3. **Gestión de reservas**
- Reservar espacios.
- Consultar reservas realizadas.
- Cancelar reservas existentes.

### 4. **Informes y estadísticas**
- Generación de reportes sobre la ocupación y uso de los espacios.

---

## Requisitos

- **Java 17**
- **Spring Boot**
- **Maven**
- **Docker** (opcional para despliegue)
- **Base de datos**: H2 (desarrollo), MySQL/PostgreSQL (producción)
- **Postman** o cualquier cliente API para pruebas

---

## Tecnologías principales

- **Spring Boot** (Framework principal)
- **Spring Security & JWT** (Autenticación y autorización)
- **JPA/Hibernate** (Manejo de persistencia)
- **Lombok** (Reducción de código repetitivo)
- **Swagger/OpenAPI** (Documentación de la API)

---

## Arquitectura del Proyecto

WorkHub sigue una arquitectura multicapa para garantizar modularidad y mantenimiento:

1. **Controladores (`controller`)**: Manejan las solicitudes HTTP.
2. **Servicios (`service`)**: Contienen la lógica de negocio.
3. **Repositorios (`repository`)**: Interactúan con la base de datos.
4. **DTOs (`dto`)**: Encapsulan y validan datos entre capas.
5. **Modelos (`model`)**: Representan las entidades del sistema.
6. **Configuraciones (`config`)**: Seguridad, Swagger y otras configuraciones.
7. **Excepciones (`exception`)**: Manejo centralizado de errores.

---

## Endpoints principales

### **Usuarios**
- `POST /usuarios/registro` → Registro de usuario.
- `POST /auth/login` → Inicio de sesión y obtención de JWT.

### **Espacios**
- `GET /espacios` → Consulta de espacios disponibles con filtros.
- `POST /espacios` → Creación de un espacio (Admin).
- `PUT /espacios/{id}` → Edición de un espacio (Admin).
- `DELETE /espacios/{id}` → Eliminación de un espacio (Admin).

### **Reservas**
- `POST /reservas` → Crear una reserva.
- `GET /reservas` → Consultar reservas del usuario autenticado.
- `DELETE /reservas/{id}` → Cancelar una reserva.

### **Informes**
- `GET /informes/ocupacion` → Obtener ocupación de espacios por rango de fechas.

---

## Instalación y ejecución

### **1. Clonar el repositorio**
```bash
$ git clone https://github.com/tu-usuario/workhub.git
$ cd workhub
```

### **2. Construcción y ejecución**
```bash
$ mvn clean install
$ mvn spring-boot:run
```

### **3. Acceso a la API**
- Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8082/swagger-ui.html)




