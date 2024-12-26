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
        EmpleadoController empleadoController = new EmpleadoController();
        Scanner scanner = new Scanner(System.in);
        LocalDate fechaInicio = null;
        double salario = 0.00;
        String nombre = null;
        String apellido = null;
        String cargo = null;
        int usuario;
        int opcion;

        System.out.println("==============================================");
        System.out.println(" 1.- Crear un nuevo empleado. \n 2.- Listar Empleados. \n 3.- Actualizar datos de empleado. \n 4.- Eliminar empleado");
        System.out.println("==============================================");
        System.out.println(" ");


        while (true){
            System.out.println("==============================================");
            System.out.println("Elija una opcion: ");
            System.out.println("==============================================");
            opcion = scanner.nextInt();
            if (opcion >= 1 && opcion <= 4) {
                break;
            } else {
                System.out.println("Opcion inválido. Por favor, elija una opcion");
            }
        }
        scanner.nextLine();


        switch (opcion){
            case 1:
                nombre = obtenerEntradaValida(scanner, "Ingresar nombre: ");
                apellido = obtenerEntradaValida(scanner, "Ingresar apellido: ");
                cargo = obtenerEntradaValida(scanner, "Ingresar cargo: ");
                salario = obtenerSalarioValido(scanner, "Ingresar Salario");
                scanner.nextLine();
                fechaInicio = obtenerFechaValido(scanner, "Ingrear fecha en formato YYY-MM-DD");


                System.out.println("================== CARGANDO ==================");
                Empleado nuevoEmpleado = new Empleado(null, nombre, apellido, cargo, salario, fechaInicio);
                empleadoController.create(nuevoEmpleado);
                System.out.println("==============================================");
                System.out.println("Se ha registrado exitosamente:");
                System.out.println("==============================================");
                System.out.println("Empleado: " + nuevoEmpleado.toUsuarioString());
                System.out.println("==============================================");
                break;

            case 2:
                System.out.println("================== CARGANDO ==================");
                List<Empleado> todosLosEmpleados = empleadoController.findAll();
                System.out.println("====== LISTADO DE EMPLEADOS REGISTRADOS ======");
                for (Empleado empleado : todosLosEmpleados) {
                    System.out.println(empleado.toAllUsersString());
                }
                break;
            case 3:
                System.out.println("Usuario a modificar");
                usuario = scanner.nextInt();
                Empleado actualizarEmpleado = empleadoController.findOne(usuario);

                nombre = obtenerEntradaValida(scanner, "Nombre a modificar");
                apellido = obtenerEntradaValida(scanner, "Apellido a modificar");
                cargo = obtenerEntradaValida(scanner, "Cargo a modificar");
                salario = obtenerSalarioValido(scanner, "Salario a modificar: ");
                fechaInicio = obtenerFechaValido(scanner, "Fecha a modificar");

                actualizarEmpleado.setNombre(nombre);
                actualizarEmpleado.setApellido(apellido);
                actualizarEmpleado.setCargo(cargo);
                actualizarEmpleado.setSalario(salario);
                actualizarEmpleado.setFechaInicio(fechaInicio);

                empleadoController.update(actualizarEmpleado);
                break;
            case 4:
                Integer id = 7;
                empleadoController.delete(id);
                break;
            default:
                System.out.println("Opcion Inválida");
        }
        scanner.close();

    }

    private static String obtenerEntradaValida(Scanner scanner, String mensaje){
        String entrada;
        while (true){
            System.out.println(mensaje);
            entrada = scanner.nextLine();
            if (entrada != null && !entrada.trim().isEmpty() && entrada.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
                return entrada;
            } else {
                System.out.println("Entrada no es válida.");
            }
        }
    }

    private static double obtenerSalarioValido(Scanner scanner, String mensaje){
        double entradaSalario = 0;
        while (true){
            System.out.println(mensaje);
            try {
                entradaSalario = scanner.nextDouble();
                if (entradaSalario > 0){
                    return entradaSalario;
                } else {
                    System.out.println("El salario debe ser mayor a 0. Intente nuevamente");
                }
            } catch (Exception e){
                System.out.println("Entrada inválida debe ser un numero para el salario. Intente nuevamente");
                scanner.nextLine();
            }
        }
    }

    private static LocalDate obtenerFechaValido(Scanner scanner, String mensaje){
        LocalDate fecha = null;
        while (true){
            System.out.println(mensaje);
            String entradaFecha = scanner.nextLine();
            try {
                fecha = LocalDate.parse(entradaFecha, DateTimeFormatter.ISO_LOCAL_DATE);
                return fecha;
            } catch (DateTimeParseException e){
                System.out.println("Formato de fecha inválido. Intente nuevamente.");
            }
        }
    }
}