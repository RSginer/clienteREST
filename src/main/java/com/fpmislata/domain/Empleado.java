/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rubén
 */
@Entity
@Table(name = "empleados")
@NamedQueries({
    @NamedQuery(name = "empleados.findAll", query = "SELECT e "
            + " FROM Empleado e ORDER BY e.id")})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @ManyToOne
    @JoinColumn(name = "fkDepartamento")
    private Departamento departamento;


    @OneToOne
    @JoinColumn(name = "fkPlazaAparcamiento")
    private PlazaAparcamiento plazaAparcamiento;

    @ManyToMany
    @JoinTable(name = "Empleados_Proyectos",
            joinColumns = @JoinColumn(name = "id_empleado"),
            inverseJoinColumns = @JoinColumn(name = "id_proyecto"))
    private List<Proyecto> proyectos;

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public PlazaAparcamiento getPlazaAparcamiento() {
        return plazaAparcamiento;
    }

    public void setPlazaAparcamiento(PlazaAparcamiento plazaAparcamiento) {
        this.plazaAparcamiento = plazaAparcamiento;
    }

    public Empleado(int id, Departamento departamento) {
        this.id = id;
        this.departamento = departamento;
    }

    public Empleado() {
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

}
