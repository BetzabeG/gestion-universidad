# Sistema de Gestión de Estudiantes - API REST

Este proyecto es una **API REST** desarrollada en **Java** utilizando el framework **Spring Boot**. La API permite gestionar estudiantes en un sistema, proporcionando funcionalidades como la creación, actualización, obtención y eliminación de estudiantes.

## Características

La API incluye las siguientes funcionalidades principales:

1. **Obtener todos los estudiantes**  
   Endpoint: `GET /api/estudiantes`  
   Devuelve una lista de todos los estudiantes registrados en el sistema.

2. **Obtener un estudiante por ID**  
   Endpoint: `GET /api/estudiantes/{id}`  
   Devuelve los detalles de un estudiante específico basado en su ID.

3. **Crear un nuevo estudiante**  
   Endpoint: `POST /api/estudiantes`  
   Permite registrar un nuevo estudiante en el sistema.  
   - **Entrada:** Datos del estudiante en formato JSON.  
   - **Salida:** El estudiante creado con un código de estado `201 Created`.

4. **Actualizar un estudiante existente**  
   Endpoint: `PUT /api/estudiantes/{id}`  
   Permite actualizar los datos de un estudiante existente.  
   - **Entrada:** ID del estudiante y los nuevos datos en formato JSON.  
   - **Salida:** El estudiante actualizado con un código de estado `200 OK`.

5. **Eliminar un estudiante por ID**  
   Endpoint: `DELETE /api/estudiantes/{id}`  
   Permite eliminar un estudiante del sistema basado en su ID.  
   - **Salida:** Código de estado `204 No Content` si la operación es exitosa.

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.x**
- **Spring Web**: Para la creación de controladores REST.
- **Spring Context**: Para la gestión de dependencias.
- **Jakarta Annotations**: Para anotaciones como `@PostConstruct`.
- **ConcurrentHashMap**: Para simular un repositorio en memoria.
- **Maven**: Para la gestión de dependencias.

## Estructura del Proyecto

El proyecto está organizado en los siguientes paquetes:

- **`controllers`**: Contiene los controladores REST que manejan las solicitudes HTTP.
  - `EstudianteController`: Controlador principal para gestionar los endpoints relacionados con estudiantes.

- **`services`**: Contiene la lógica de negocio.
  - `IEstudianteService`: Interfaz que define los métodos del servicio.
  - `EstudianteServiceimpl`: Implementación de la interfaz del servicio.

- **`repository`**: Contiene la capa de acceso a datos.
  - `EstudianteRepository`: Repositorio en memoria para gestionar los datos de los estudiantes.

- **`dtos`**: Contiene los objetos de transferencia de datos (DTOs).
  - `EstudianteDTO`: Representa los datos que se envían y reciben a través de la API.

- **`models`**: Contiene las entidades del dominio.
  - `Estudiante`: Representa la entidad de un estudiante.

## Ejemplo de Uso

### Crear un Estudiante
**Solicitud:**
```http
POST /api/estudiantes
Content-Type: application/json

{
    "nombre": "Edson",
    "apellido": "Lucana",
    "email": "edsonlucana@gmail.com",
    "fechaNacimiento": "1998-03-25",
    "numeroInscripcion": "S003"
}
```
**Respuesta:**
```http
{
    "id": 2,
    "nombre": "Edson",
    "apellido": "Lucana",
    "email": "edsonlucana@gmail.com",
    "fechaNacimiento": "1998-03-25",
    "numeroInscripcion": "S003"
}
```
**Como Ejecutar este proyecto**
1. Clona este proyecto
```http
git clone https://github.com/BetzabeG/gestion-universidad.git
```
2. Navega al directorio de este proyecto en tu local
```http
cd SegundoProyecto
```
3. Compila y ejecuta el proyecto
```http
mvn clean install
```
```http
java -jar .\target\SegundoProyecto-0.0.1-SNAPSHOT.jar
``` 


