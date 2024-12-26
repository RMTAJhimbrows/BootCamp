package org.example.controllers;

import org.example.entities.Empleado;
import org.example.persistence.EmpleadoJPA;

import java.util.List;

/**
 * Controlador para gestionar las operaciones relacionadas con empleados.
 * Actúa como intermediario entre la lógica de negocio y la capa de persistencia (EmpleadoJPA).
 */
public class EmpleadoController {
    // Instancia del acceso a datos (DAO) para la entidad Empleado.
    EmpleadoJPA empleadoJPA = new EmpleadoJPA();

    /**
     * Crea un nuevo empleado en la base de datos.
     *
     * @param nuevoEmpleado Objeto Empleado con los datos a guardar.
     */
    public void create(Empleado nuevoEmpleado){
        empleadoJPA.create(nuevoEmpleado);// Llama al método correspondiente en la capa de persistencia
    }
    public List<Empleado> findAll(){
        return empleadoJPA.findAll();
    }

    /**
     * Actualiza la información de un empleado existente.
     *
     * @param actualizarEmpleado Objeto Empleado con los datos actualizados.
     */
    public void update(Empleado actualizarEmpleado){
        empleadoJPA.update(actualizarEmpleado);
    }

    /**
     * Busca un empleado específico por su identificador.
     *
     * @param id Identificador único del empleado.
     * @return Objeto Empleado correspondiente al ID o null si no existe.
     */
    public Empleado findOne(Integer id) {
        return empleadoJPA.findOne(id);
    }

    /**
     * Elimina un empleado de la base de datos por su identificador.
     *
     * @param id Identificador único del empleado a eliminar.
     */
    public void delete(Integer id){
        empleadoJPA.delete(id);
    }

    /**
     * Busca empleados por un cargo específico.
     *
     * @param cargos El cargo a buscar.
     * @return Lista de empleados que tienen el cargo especificado.
     */
    public List<Empleado> findByCargos(String cargos){
        return empleadoJPA.findByCargos(cargos);
    }
}
