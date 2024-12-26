Estructura del proyecto

1. Entidades

Empleado: Representa la estructura de un empleado, con campos como:

id: Identificador único del empleado.

nombre: Nombre del empleado.

apellido: Apellido del empleado.

cargo: Posición o rol del empleado.

salario: Salario del empleado.

fechaInicio: Fecha en la que el empleado comenzó a trabajar.

Incluye métodos como toString, toUsuarioString y toAllUsersString para representaciones de datos personalizadas.

2. Persistencia

EmpleadoJPA: Clase de acceso a datos que utiliza JPA para interactuar con la base de datos. Contiene métodos como:

findAll: Recupera todos los empleados.

create: Persiste un nuevo empleado.

update: Actualiza los datos de un empleado.

findOne: Busca un empleado por ID.

delete: Elimina un empleado.

findByCargos: Busca empleados por su cargo.

3. Controladores

EmpleadoController: Actúa como intermediario entre la capa de persistencia y la aplicación de consola. Delega las operaciones CRUD a EmpleadoJPA.

4. Aplicación principal

Contiene lógica de interacción con el usuario mediante consola. Permite seleccionar opciones y procesar datos de empleados.
