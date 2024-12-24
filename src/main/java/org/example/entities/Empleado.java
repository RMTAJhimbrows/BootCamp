package org.example.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "empleados")
public class Empleado {
    // nombre, apellido, cargo, salario y fecha de inicio.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String cargo;

    @Column
    private double salario;

    @Column
    private LocalDate fechaInicio;

    public Empleado() {
    }

    public Empleado(Integer id, String nombre, String apellido, String cargo, double salario, LocalDate fechaInicio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.salario = salario;
        this.fechaInicio = fechaInicio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", cargo='" + cargo + '\'' +
                ", salario=" + salario +
                ", fechaInicio=" + fechaInicio +
                '}';
    }

    public String toUsuarioString(){
        return "Nombre: " + nombre + "\n" +
                "Apellido: " + apellido + "\n" +
                "Cargo: " + cargo + "\n" +
                "Salario: " + salario + " €\n" +
                "Fecha de inicio: " + fechaInicio;
    }

    public String toAllUsersString() {
        return "Nombre: " + nombre  +
                " Apellido: " + apellido +
                " Cargo: " + cargo +
                " Salario: " + salario +
                " Fecha de inicio: " + fechaInicio;
    }
}
