package org.example.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.entities.Empleado;

import java.util.List;

public class EmpleadoJPA {

    public List<Empleado> findAll() {
        EntityManager entityManager = ConfigJPA.getEntityManager();
        try {
            TypedQuery<Empleado> query = entityManager.createQuery("SELECT p FROM empleados p", Empleado.class);
            return query.getResultList();
        }finally {
            entityManager.close();
        }
    }

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

    public Empleado findOne(Integer id) {
        EntityManager entityManager = ConfigJPA.getEntityManager();
        try {
            return entityManager.find(Empleado.class, id);
        } finally {
            entityManager.close();
        }



    }

    public void delete(Integer id) {
        EntityManager entityManager = ConfigJPA.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Empleado empleado = entityManager.find(Empleado.class, id);
            if (empleado != null){
                entityManager.remove(empleado);
            } else {
                System.out.println("El id " + id + "No existe.");
            }
            entityManager.getTransaction().commit();
        }finally {
            entityManager.close();
        }
    }
}
