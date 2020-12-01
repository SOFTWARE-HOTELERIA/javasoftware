/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa3_dominio.entidades;

/**
 *
 * @author josel
 */
public class TipoHabitacion {
    private String idTipoHabitacion;
    private String descripcion;
    private double costo;

     public TipoHabitacion() {
    }
    public String getNumHabitacion() {
        return idTipoHabitacion;
    }

    public void setNumHabitacion(String idTipoHabitacion) {
        this.idTipoHabitacion = idTipoHabitacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

}
