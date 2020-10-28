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
public class Habitacion {
    private String numeroHabitacion;
    private String estado;  
    private int numeroDePiso;
    private TipoHabitacion tipoHabitacion;

 
    //crear tabla piso
     public Habitacion(String numeroHabitacion,String estado,int pisoId){
        this.numeroHabitacion=numeroHabitacion;
        this.estado=estado;
        this.numeroDePiso=pisoId;
    }
     public Habitacion(){}
    public int getNumeroDePiso() {
        return numeroDePiso;
    }

    public void setNumeroDePiso(int numeroDePiso) {
        this.numeroDePiso = numeroDePiso;
    }
    public String getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(String numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
   public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }
    

   
}
