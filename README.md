# Práctica 2 - CRUD

![PUCMMM-logo](https://i.imgur.com/9eEIci9.png)

Segunda práctica realizada para la asignatura **Programación Web (ISC-415)** perteneciente a la carrera **Ingeniería de Sistemas y Computación** de la **Pontificia Universidad Católica Madre y Maestra (PUCMM)** en el ciclo **Mayo-Agosto 2018**.

## Objetivo general

Crear una aplicación web utilizando SparkJava que permita ejecutar las tareas especificadas en la sección [Tareas requeridas](#tareas-requeridas).

## Tecnologías requeridas

- Java SE
- Gradle
- SparkJava
- FreeMarker

## Otras tecnologías utilizadas

- Bootstrap 4
- Font Awesome 5

## Modelo de datos
Para esta aplicación es requerido utilizar una colección estática de Estudiantes, para lo cual es necesaria la construcción de la clase Estudiante como parte del modelo de datos, y aquí se muestra su estructura:
```java
class Estudiante { 
  int matricula;
  String nombre;
  String apellido;
  String telefono;
}
```

## Tareas requeridas

- [X] **Listar estudiantes** - Mostrar un listado de todos los estudiantes que están en la colección estática.
- [X] **Agregar estudiante** - Mostrar un formulario de registro de estudiante, y añadir el estudiante a la colección estática.
- [X] **Ver estudiante** - Mostrar la información de un estudiante en específico.
- [X] **Editar estudiante** - Mostrar un formulario de edición para el estudiante especificado, y guardar los cambios realizados en el estudiante que está en la colección estática.
- [X] **Borrar estudiante** - Mostrar una confirmación de borrado de un estudiante especificado, y eliminar al estudiante de la colección estática.