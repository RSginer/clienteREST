/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rubén
 */
@Entity
@Table(name = "plazasAparcamiento")
@NamedQueries({
    @NamedQuery(name = "plazasAparcamiento.findAll", query = "SELECT p "
            + " FROM PlazaAparcamiento p ORDER BY p.id")})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class PlazaAparcamiento implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @XmlTransient
    @OneToOne(mappedBy = "plazaAparcamiento")
    private Empleado empleado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
