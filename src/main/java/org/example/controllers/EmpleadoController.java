package org.example.controllers;

import org.example.entities.Empleado;
import org.example.persistence.EmpleadoJPA;

import java.util.List;

public class EmpleadoController {
    EmpleadoJPA empleadoJPA = new EmpleadoJPA();

    public void create(Empleado nuevoEmpleado){
        empleadoJPA.create(nuevoEmpleado);
    }
    public List<Empleado> findAll(){
        return empleadoJPA.findAll();
    }

    public void update(Empleado actualizarEmpleado){
        empleadoJPA.update(actualizarEmpleado);
    }

    public Empleado findOne(Integer id) {
        return empleadoJPA.findOne(id);
    }

    public void delete(Integer id){
        empleadoJPA.delete(id);
    }
}
