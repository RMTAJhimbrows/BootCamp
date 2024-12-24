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
        boolean fechaValida = false;
        boolean salarioValido = false;
        double salario = 0.00;
        String nombre = null;
        String apellido = null;
        String cargo = null;
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
                while (nombre == null || nombre.trim().isEmpty()) {
                    System.out.println("Ingrese su nombre: ");
                    nombre = scanner.nextLine();
                    if (nombre == null || nombre.trim().isEmpty()){
                        System.out.println("El nombre no puede ser vacio ni ocntener solo espacios. Intenete nuevamente.");
                    }
                }

                while (apellido == null || apellido.trim().isEmpty()) {
                    System.out.println("Ingrese su apellido: ");
                    apellido = scanner.nextLine();
                    if (apellido == null || apellido.trim().isEmpty()){
                        System.out.println("El apellido no puede ser vacio ni obntener solo espacios. Intenete nuevamente.");
                    }
                }

                while (cargo == null || cargo.trim().isEmpty()) {
                    System.out.println("Ingrese su cargo: ");
                    cargo = scanner.nextLine();
                    if (cargo == null || cargo.trim().isEmpty()){
                        System.out.println("El cargo no puede ser vacio ni ocntener solo espacios. Intenete nuevamente.");
                    }
                }

                while (!salarioValido) {
                    System.out.println("Ingrese su salario: ");
                    try {
                        salario = scanner.nextDouble();
                        if (salario <= 0){
                            System.out.println("El salario debe ser un numero Positivo");
                        } else {
                            salarioValido = true;
                        }
                    } catch (Exception e){
                        System.out.println("Entrada inválida. Debe ingresar un numero para el salario");
                        scanner.nextLine();
                    }
                }
                scanner.nextLine();

                while (!fechaValida) {
                    System.out.println("Ingrese fecha en formato YYYY-MM-DD");
                    String fechaEntrada = scanner.nextLine();

                    try {
                        fechaInicio = LocalDate.parse(fechaEntrada, DateTimeFormatter.ISO_LOCAL_DATE);
                        fechaValida = true;
                    } catch (DateTimeParseException e){
                        System.out.println("Formato de fecha inválido");
                    }
                }
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
                Empleado actualizarEmpleado = empleadoController.findOne(6);
                actualizarEmpleado.setNombre("Carmen");
                actualizarEmpleado.setApellido("Perez");
                actualizarEmpleado.setCargo("Jefe");
                actualizarEmpleado.setSalario(23000);
                actualizarEmpleado.setFechaInicio(LocalDate.of(2022,12,3));

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
/*
        Empleado nuevoEmpleado = new Empleado(null, "Fulano", "Rodriguez", "Obrero de planta", 20000, LocalDate.of(1990,2,1));
        empleadoController.create(nuevoEmpleado);

        List<Empleado> todosLosEmpleados = empleadoController.findAll();
        for (Empleado empleado : todosLosEmpleados) {
            System.out.println(empleado);
        }

        Empleado actualizarEmpleado = empleadoController.findOne(6);
        actualizarEmpleado.setNombre("Carmen");
        actualizarEmpleado.setApellido("Perez");
        actualizarEmpleado.setCargo("Jefe");
        actualizarEmpleado.setSalario(23000);
        actualizarEmpleado.setFechaInicio(LocalDate.of(2022,12,3));


        empleadoController.update(actualizarEmpleado);

        Integer id = 7;
        empleadoController.delete(id);

*/
    }

}