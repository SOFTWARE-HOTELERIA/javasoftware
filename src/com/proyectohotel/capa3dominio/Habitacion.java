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
public class Habitacion {
     private int numeroHabitacion;
    private String estado;  
    private String tipoHabitacion;
    private int pisoId;

    
    public Habitacion(int numeroHabitacion, String estado, String tipoHabitacion, int pisoId) {
        this.numeroHabitacion = numeroHabitacion;
        this.estado = estado;
        this.tipoHabitacion = tipoHabitacion;
        this.pisoId = pisoId;
    }
    
    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public int getPisoId() {
        return pisoId;
    }

    public void setPisoId(int pisoId) {
        this.pisoId = pisoId;
    }
}
