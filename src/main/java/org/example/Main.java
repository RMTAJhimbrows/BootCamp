package org.example;

import org.example.controllers.EmpleadoController;
import org.example.entities.Empleado;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Instancia del controlador para gestionar las operaciones relacionadas con empleados
        EmpleadoController empleadoController = new EmpleadoController();
        Scanner scanner = new Scanner(System.in);

        // Declaración de variables necesarias para la lógica del programa
        LocalDate fechaInicio = null;
        double salario = 0.00;
        String nombre = null;
        String apellido = null;
        String cargo = null;
        int idEmpleado;
        int opcion;
        List<Empleado> todosLosEmpleados; // Lista para manejar los empleados

        // Menú principal
        System.out.println("==============================================");
        System.out.println(" 1.- Crear un nuevo empleado. \n 2.- Listar Empleados. \n 3.- Actualizar datos de empleado. \n 4.- Eliminar empleado. \n 5.- Listado por cargos");
        System.out.println("==============================================");
        System.out.println(" ");


        while (true){
            System.out.println("==============================================");
            System.out.println("Elija una opcion: ");
            System.out.println("==============================================");
            opcion = scanner.nextInt();
            if (opcion >= 1 && opcion <= 5) {
                break;
            } else {
                System.out.println("Opcion inválido. Por favor, elija una opcion");
            }
        }

        scanner.nextLine();

        // Switch para ejecutar la funcionalidad correspondiente segun la opcion seleccionada
        switch (opcion){
            case 1:
                // Crea un nuevo empleado
                nombre = obtenerEntradaValida(scanner, "Ingresar nombre: ");
                apellido = obtenerEntradaValida(scanner, "Ingresar apellido: ");
                cargo = obtenerEntradaValida(scanner, "Ingresar cargo: ");
                salario = obtenerSalarioValido(scanner, "Ingresar Salario"); // Valida que el saario sea un numero valido
                scanner.nextLine();
                fechaInicio = obtenerFechaValido(scanner, "Ingrear fecha en formato YYY-MM-DD"); // valida que la fecha sea válida


                System.out.println("================== CARGANDO ==================");
                // Crea un empleado con los datos ingresados.
                Empleado nuevoEmpleado = new Empleado(null, nombre, apellido, cargo, salario, fechaInicio);
                empleadoController.create(nuevoEmpleado); // Llama al controlador para guardar el nuevo empleado

                // Confirma que se ha registrado de manera exitosa
                System.out.println("==============================================");
                System.out.println("Se ha registrado exitosamente:");
                System.out.println("==============================================");
                System.out.println("Empleado: " + nuevoEmpleado.toUsuarioString());
                System.out.println("==============================================");
                break;

            case 2:
                // Listar todos los empleados registrados en la base de datos
                System.out.println("================== CARGANDO ==================");
                todosLosEmpleados = empleadoController.findAll(); // Recupera la lista de empleados
                System.out.println("====== LISTADO DE EMPLEADOS REGISTRADOS ======");
                for (Empleado empleado : todosLosEmpleados) {
                    System.out.println(empleado.toAllUsersString()); // Muestra los datos de cada empleado
                }
                break;

            case 3:
                // Actualizar los datos de un empleado existente
                System.out.println("================== CARGANDO ==================");
                todosLosEmpleados = empleadoController.findAll();
                System.out.println("Empleado a modificar");

                System.out.println("============ LISTADO DE EMPLEADOS ============");
                for (Empleado empleado : todosLosEmpleados) {
                    System.out.println(empleado.toString());
                }
                idEmpleado = validarIdEmpleado(scanner, "Ingrese el id del Empleado"); // Solicita y valida el ID del empleado a modificar

                System.out.println("================== CARGANDO ==================");
                Empleado actualizarEmpleado = empleadoController.findOne(idEmpleado); // Busca al empleado por ID

                // Solicita y valida los nuevos datos del empleado
                nombre = obtenerEntradaValida(scanner, "Nombre a modificar");
                apellido = obtenerEntradaValida(scanner, "Apellido a modificar");
                cargo = obtenerEntradaValida(scanner, "Cargo a modificar");
                salario = obtenerSalarioValido(scanner, "Salario a modificar: ");
                scanner.nextLine();
                fechaInicio = obtenerFechaValido(scanner, "Fecha a modificar");

                // Actualiza los datos del empleado
                actualizarEmpleado.setNombre(nombre);
                actualizarEmpleado.setApellido(apellido);
                actualizarEmpleado.setCargo(cargo);
                actualizarEmpleado.setSalario(salario);
                actualizarEmpleado.setFechaInicio(fechaInicio);

                empleadoController.update(actualizarEmpleado); // Llama al controlador para actualizar los datos

                // Lista todos los empleados despues de la actualización
                todosLosEmpleados = empleadoController.findAll();
                System.out.println("============ LISTADO DE EMPLEADOS ============");
                for (Empleado empleado : todosLosEmpleados) {
                    System.out.println(empleado.toAllUsersString());
                }
                break;

            case 4:
                // Eliminar un empleado
                todosLosEmpleados = empleadoController.findAll();
                System.out.println("============ LISTADO DE EMPLEADOS ============");

                for (Empleado empleado : todosLosEmpleados) {
                    System.out.println(empleado.toString());
                }
                idEmpleado = validarIdEmpleado(scanner, "Ingresar el Id del empleado para eliminar"); // Solicita el Id del empleado a aliminar
                System.out.println("================== CARGANDO ==================");
                empleadoController.delete(idEmpleado); // Elimina al empleado de la base de datos
                System.out.println("Se ha eliminido exitosamente");
                // Lista todos los empleados despues de la eliminacion
                todosLosEmpleados = empleadoController.findAll();
                System.out.println("====== LISTADO DE EMPLEADOS REGISTRADOS ======");
                for (Empleado empleado : todosLosEmpleados) {
                    System.out.println(empleado.toAllUsersString());
                }
                break;

            case 5:
                // Listar empleados por cargo
                cargo = obtenerEntradaValida(scanner, "Ingrese cargo"); // Solicita el cargo para filtrar empleados
                System.out.println("================== CARGANDO ==================");
                List<Empleado> empleadosPorCargo = empleadoController.findByCargos(cargo); // Busca empleados por cargo
                if (empleadosPorCargo.isEmpty()){
                    System.err.println("No se encontraron empleados con el cargo especificado."); // Mensaje si no hay resultados
                }else {
                    System.out.println("======= LISTADO DE EMPLEADOS POR CARGO =======");
                    for (Empleado empleado : empleadosPorCargo) {
                        System.out.println(empleado.toAllUsersString());
                    }
                }
                break;
        }
        scanner.close();
    }

    /**
     * Este método se encarga de validar la entrada de texto.
     *
     * @param scanner El objeto Scanner se utiliza para capturar la entrada del usuario.
     * @param mensaje El mensaje que se muestra en pantalla para solicitar la entrada del usuario.
     * @return La entrada válida del usuario.
     */
    private static String obtenerEntradaValida(Scanner scanner, String mensaje){
        String entrada;
        while (true){
            System.out.println(mensaje);
            entrada = scanner.nextLine();
            if (entrada != null && !entrada.trim().isEmpty() && entrada.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
                return entrada;
            } else {
                System.err.println("Entrada no es válida.");
            }
        }
    }

    /**
     * Este método se encarga de validar el salario.
     *
     * @param scanner El objeto Scanner se utiliza para capturar la entrada del usuario.
     * @param mensaje El mensaje que se muestra en pantalla para solicitar la entrada del usuario.
     * @return La entradaSalario válida del usuario.
     */
    private static double obtenerSalarioValido(Scanner scanner, String mensaje){
        double entradaSalario = 0;
        while (true){
            System.out.println(mensaje);
            try {
                entradaSalario = scanner.nextDouble();
                if (entradaSalario > 0){
                    return entradaSalario;
                } else {
                    System.err.println("El salario debe ser mayor a 0. Intente nuevamente");
                }
            } catch (Exception e){
                System.err.println("Entrada inválida debe ser un numero para el salario. Intente nuevamente");
                scanner.nextLine();
            }
        }
    }

    /**
     * Este método se encarga de validar la fecha.
     *
     * @param scanner El objeto Scanner se utiliza para capturar la entrada del usuario.
     * @param mensaje El mensaje que se muestra en pantalla para solicitar la entrada del usuario.
     * @return La fecha válida del usuario.
     */
    private static LocalDate obtenerFechaValido(Scanner scanner, String mensaje){
        LocalDate fecha = null;
        while (true){
            System.out.println(mensaje);
            String entradaFecha = scanner.nextLine();
            try {
                fecha = LocalDate.parse(entradaFecha, DateTimeFormatter.ISO_LOCAL_DATE);
                return fecha;
            } catch (DateTimeParseException e){
                System.err.println("Formato de fecha inválido. Intente nuevamente.");
            }
        }
    }

    /**
     * Este método se encarga de validar el IdEmpleado.
     *
     * @param scanner El objeto Scanner se utiliza para capturar la entrada del usuario.
     * @param mensaje El mensaje que se muestra en pantalla para solicitar la entrada del usuario.
     * @return El idEmpleado válida del usuario.
     */
    private static int validarIdEmpleado(Scanner scanner, String mensaje){
        int idEmpleado = -1;
        while(true){
            System.out.println(mensaje);
            try {
                idEmpleado = Integer.parseInt(scanner.nextLine());
                if (idEmpleado > 0){
                    return idEmpleado;
                } else {
                    System.err.println("El ID debe ser un numero positivo entero. Intentalo nuevamente.");
                }
            } catch (NumberFormatException e){
                System.err.println("Entrada inválida. debe ingresar un numero entero par el ID.");
            }
        }
    }
}