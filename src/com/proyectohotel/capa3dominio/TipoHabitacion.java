/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa3dominio;

/**
 *
 * @author josel
 */
public class TipoHabitacion {
      String numHabitacion;
    String descripcion;
    String costo;

    public TipoHabitacion(String numHabitacion, String descripcion, String costo) {
        this.numHabitacion = numHabitacion;
        this.descripcion = descripcion;
        this.costo = costo;
    }

    public String getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(String numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }
}
