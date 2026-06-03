# Sistema de Agenda Odontológica Virtual

¡Bienvenido al repositorio de la Agenda Dental Virtual! Este es un proyecto interactivo desarrollado en **Java** utilizando la biblioteca gráfica **Swing**. El sistema simula una plataforma multiusuario donde se gestionan pacientes, citas clínicas y la disponibilidad de horarios por día de forma autónoma.

Este software fue desarrollado para cumplir con los requerimientos de la Actividad Formativa 2 de la materia Desarrollo de Sistemas.

---

## Características del Proyecto

* **Programación Orientada a Objetos (POO):** Modelado completo de entidades reales mediante las clases `Paciente`, `Cita` y `Main`.
* **Interfaz Gráfica de Usuario (GUI):** Ventana interactiva construida con Java Swing para una navegación amigable.
* **Calendario Dinámico de Disponibilidad:** Sistema inteligente que cruza *Días y Horas* mediante estructuras de datos (`HashMap`).
* **Bloqueo de Horarios en Tiempo Real:** Si un horario ya está ocupado por otro odontólogo, el sistema deshabilita el botón de agendamiento para evitar la duplicidad de citas.

---

## Conceptos de POO Aplicados

1.  **Clases y Atributos:** Definición de plantillas estructurales con propiedades privadas (`private`).
2.  **Constructores:** Inicialización parametrizada de objetos en la memoria al registrar pacientes y citas.
3.  **Encapsulamiento:** Métodos de acceso (*Getters*) para proteger y comunicar los datos entre clases de forma segura.
4.  **Asociación entre Objetos:** La clase `Cita` contiene un atributo del tipo `Paciente`, permitiendo que la cita conozca toda la información del usuario asignado.

---

## Estructura del Repositorio

El código fuente está organizado dentro de la carpeta raíz de la siguiente manera:

* `src/Paciente.java`: Clase plantilla con los datos personales e historial clínico del paciente.
* `src/Cita.java`: Clase encargada de procesar las fechas, horas, tratamientos y estados de la consulta.
* `src/Main.java`: El motor de la aplicación que arranca la interfaz gráfica (Front) y maneja las reglas de negocio (Controlador).

---

## Requisitos e Instalación

Para ejecutar este proyecto de forma local en tu computadora necesitas:

1.  Contar con el **Java Development Kit (JDK)** versión 17 o superior.
2.  Un IDE compatible como **IntelliJ IDEA**, **Eclipse** o **NetBeans**.

### Instrucciones para correr el programa:
1. Clona este repositorio en tu máquina local.
2. Abre la carpeta del proyecto desde tu IDE favorito.
3. Dirígete al archivo `src/Main.java`.
4. Haz clic derecho y selecciona **Run 'Main.main()'** o presiona el botón verde de reproducción (Play).

---

## Datos Generales del Estudiante
Nombre: Jazmin Rodriguez Zamarron

Carrera:Ingeniería en Desarrollo de Software

Materia: Programación Orientada a Objetos

Fecha de entrega: Junio 2026
