# Final

Objetivo
Desarrollar un sistema de gestión para una biblioteca digital utilizando Java, aplicando los conceptos y técnicas
aprendidos en el curso de Programación Avanzada.

Requisitos del Sistema

1. Diseño de Clases
   Crear un diagrama de clases UML que represente la estructura del sistema.
   Implementar las clases necesarias utilizando herencia, polimorfismo y abstracción.
   Incluir al menos una interfaz y una clase abstracta en el diseño.

2. Funcionalidades Básicas
   Gestión de usuarios (agregar, modificar, eliminar).
   Gestión de libros digitales (agregar, modificar, eliminar, buscar).
   Sistema de préstamos y devoluciones de libros digitales.
   Adicional: Generación de reportes (libros más solicitados, usuarios con más préstamos, etc).

3. Manejo de Errores
   Implementar un sistema de manejo de excepciones para todas las operaciones críticas.
   Utilizar excepciones personalizadas cuando sea apropiado.

4. Genéricos y Colecciones
   Utilizar colecciones genéricas (ArrayList, HashMap, etc) para almacenar y manipular datos.
   Adicional: Implementar un método genérico útil para el sistema.

5. Persistencia de Datos
   Utilizar JDBC para conectar al sistema a una base de datos SQL.
   Implementar operaciones CRUD (Create, Read, Update, Delete) para las entidades principales.

6. Patrones de Diseño
   Implementar el patrón Singleton para la conexión a la base de datos.
   Opcional: Incluir el uso de DAO y DTO.
   Opcional: Utilizar un patrón de diseño adicional (por ejemplo, Observer, Factory, o Strategy).

7. Interfaz de Usuario
   Crear una interfaz de consola para interactuar con el sistema.
   Implementar un menú principal con opciones para acceder a todas las funcionalidades.

Entregables

Codigo fuente completo.
Diagrama de clases UML.
Script SQL para

# Descripcion general del sistema

Sistema de administracion de libreia digital, el cual permite la gestion de usuarios, libros digitales, prestamos y
devoluciones de libros digitales.
Adicionalmente, muestra la cantidad total de estudiantes en el sistema, la cantidad total de libros, la cantidad de
préstamos pendientes, el libro más popular y el usuario con más préstamos en la historia de préstamos.

# Patrones de diseño utilizados

1. Singleton: Se utilizó para la conexión a la base de datos, de esta manera se garantiza que solo exista una instancia
   de la conexión a la base de datos en todo el sistema.
2. Repository: Se utilizó para encapsular la lógica de acceso a la base de datos, permitiendo que el sistema pueda
   interactuar con la base de datos sin tener que conocer los detalles de implementación de la conexión a la base de
   datos.
3. Factory: El patrón Factory se utiliza para encapsular la lógica de creación de objetos, en este contexto, de
   diferentes estrategias de base de datos. Nos beneficia al reducir el acoplamiento y mejorar la escalabilidad del
   sistema, permitiendo la fácil adición de soporte para nuevas bases de datos sin alterar el código existente.
4. Strategy: Se utilizó para definir una familia de algoritmos, encapsular cada uno de ellos y hacerlos intercambiables.
   En este caso, se utilizó para definir diferentes estrategias de base de datos y permitir que el sistema pueda cambiar
   de una base de datos a otra en tiempo de ejecución.

![entidades](https://github.com/user-attachments/assets/e0fa0993-10a5-4f04-a609-dfbfa6c4e94b)

![ddbb](https://github.com/user-attachments/assets/a1369103-2851-4ceb-9fe1-2207d17ba07b)

![creacion tablas](https://github.com/user-attachments/assets/ebfc9a44-8091-4ea3-b082-29a6946f1653)

# Instrucciones para ejecutar el sistema

1. Clonar el repositorio
2. Crear una base de datos en MySQL con el nombre `s0d33library`
3. Abrir el proyecto en un IDE de Java
4. Configurar las credenciales de la base de datos en el
   archivo `src/infrastructure/dbConnections/MysqlDbConnection.java`
5. La base de datos Mysql solo se creará a modo de ejemplo para demostracion de patrones como Startegy y Factory, luego
   el programa utilizara la base de datos Sqlite que ya se encuentra incluida en la carpeta Resources
6. Ejecutar el programa.

## Credenciales de Administrador (cualquiera de estos dos usuarios):

Username: jd123456
Password: 123456

Username: graceadams
Password: 123456
