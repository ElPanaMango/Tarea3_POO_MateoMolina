# Tarea #3: Manejo de Excepciones, Archivos y MVC (POO)

Proyecto desarrollado para la materia de **Programación Orientada a Objetos** en la Universidad Politécnica Salesiana (UPS).

## Descripción del Proyecto
Aplicación en Java orientada al sorteo automatizado de partidos para una liga profesional (desde octavos de final hasta la gran final), integrando:
1. **Patrón Arquitectónico MVC** (Modelo - Vista - Controlador).
2. **Excepciones Personalizadas** (`NumeroEquiposInvalidoException` y `SorteoInvalidoException`).
3. **Algoritmo Recursivo** para el emparejamiento aleatorio de equipos por etapas.
4. **Serialización y Deserialización de Objetos** (`torneo.ser`) para la persistencia de datos.

---

## Estructura del Código (MVC)
El proyecto está dividido en los siguientes paquetes lógicos:
* `modelo`: Contiene la clase `Torneo` (con la lógica del sorteo recursivo), la clase `Equipo` y las excepciones personalizadas.
* `controlador`: Contiene la clase `TorneoControlador` que gestiona el flujo y los métodos de persistencia con archivos binarios.
* `vista`: Contiene la clase `ConsolaVista` con el menú interactivo para la ejecución por consola.

---

## Instrucciones de Ejecución
1. Clona el repositorio en tu máquina local o ábrelo directamente en **Visual Studio Code** (asegúrate de tener instalado el *Extension Pack for Java*).
2. Ejecuta la clase principal ubicada en `vista/ConsolaVista.java`.
3. Sigue las opciones del menú de la consola para inicializar el torneo, realizar los sorteos o guardar/cargar el estado mediante serialización.
