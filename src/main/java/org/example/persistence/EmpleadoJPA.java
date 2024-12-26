package org.example.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.entities.Empleado;

import java.util.List;
/**
 * Clase de acceso a datos para la entidad Empleado utilizando JPA.
 * Proporciona métodos CRUD (Crear, Leer, Actualizar, Eliminar) y consultas específicas.
 */
public class EmpleadoJPA {

    /**
     * Recupera una lista de todos los empleados almacenados en la base de datos.
     *
     * @return Lista de empleados.
     */
    public List<Empleado> findAll() {
        EntityManager entityManager = ConfigJPA.getEntityManager();
        try {
            TypedQuery<Empleado> query = entityManager.createQuery("SELECT p FROM empleados p", Empleado.class);
            return query.getResultList();
        }finally {
            entityManager.close();
        }
    }

    /**
     * Persiste un nuevo empleado en la base de datos.
     *
     * @param nuevoEmpleado El empleado a crear.
     */
    public void create(Empleado nuevoEmpleado) {
        EntityManager entityManager = ConfigJPA.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(nuevoEmpleado);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }

    }

    /**
     * Actualiza los datos de un empleado existente en la base de datos.
     *
     * @param actualizarEmpleado El empleado con los datos actualizados.
     */
    public void update(Empleado actualizarEmpleado) {
        EntityManager entityManager = ConfigJPA.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(actualizarEmpleado);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    /**
     * Busca un empleado por su identificador único.
     *
     * @param id El identificador del empleado.
     * @return El empleado encontrado o null si no existe.
     */
    public Empleado findOne(Integer id) {
        EntityManager entityManager = ConfigJPA.getEntityManager();
        try {
            return entityManager.find(Empleado.class, id);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Elimina un empleado de la base de datos.
     *
     * @param id El identificador del empleado a eliminar.
     */
    public void delete(Integer id) {
        EntityManager entityManager = ConfigJPA.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Empleado empleado = entityManager.find(Empleado.class, id);
            if (empleado != null){
                entityManager.remove(empleado);
            } else {
                System.err.println("El id " + id + "No existe.");
            }
            entityManager.getTransaction().commit();
        }finally {
            entityManager.close();
        }
    }

    /**
     * Busca empleados por el cargo especificado.
     *
     * @param cargo El cargo a buscar.
     * @return Lista de empleados que tienen el cargo especificado.
     */
    public List<Empleado> findByCargos(String cargo) {
        EntityManager entityManager = ConfigJPA.getEntityManager();
        try {
            TypedQuery<Empleado> query = entityManager.createQuery("SELECT e FROM empleados e WHERE e.cargo = :cargo", Empleado.class);
            query.setParameter("cargo", cargo);
            return query.getResultList();
        }finally {
            entityManager.close();
        }
    }
}
