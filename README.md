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
- `POST /workhub/auth/sign-up` → Registro de usuario.
- `POST /workhub/auth/log-in` → Inicio de sesión y obtención de JWT.
- `PUT /workhub/auth/updateRoles` → Edición de roles (Admin).

### **Espacios**
- `GET /workhub/spaces` → Consulta de espacios disponibles con filtros.
- `GET /workhub/spaces{id}` → Consulta un espacio en especifico.
- `POST /workhub/spaces/save` → Creación de un espacio (Admin).
- `PUT /workhub/spaces/update/{id}` → Edición de un espacio (Admin).
- `DELETE /workhub/espacios/delete/{id}` → Eliminación de un espacio (Admin).

### **Reservas**
- `POST /workhub/bookings/save` → Crear una reserva.
- `GET /workhub/bookings/{id}` → Consultar reservas del usuario autenticado.
- `GET /workhub/bookings/findAll` → Consultar reservas.
- `PUT /workhub/bookings/update/{id}` → Edicion de reserva.
- `DELETE /workhub/bookings/delete/{id}` → Cancelar una reserva.

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
- Swagger UI: [http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)




