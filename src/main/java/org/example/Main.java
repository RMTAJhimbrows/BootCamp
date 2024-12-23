package org.example;

import org.example.controllers.EmpleadoController;
import org.example.entities.Empleado;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmpleadoController empleadoController = new EmpleadoController();


        Empleado nuevoEmpleado = new Empleado(null, "Fulano", "Rodriguez", "Obrero de planta", 20000, LocalDate.of(1990,2,1));
        empleadoController.create(nuevoEmpleado);

        List<Empleado> todosLosEmpleados = empleadoController.findAll();
        for (Empleado empleado : todosLosEmpleados) {
            System.out.println(empleado);
        }

        Empleado actualizarEmpleado = empleadoController.findOne(6);
        actualizarEmpleado.setNombre("Laura");
        empleadoController.update(actualizarEmpleado);

        Integer id = 7;
        empleadoController.delete(id);


    }
}