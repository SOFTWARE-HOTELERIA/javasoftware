
package com.proyectohotel.capa3.dominio.entidades;


public class Habitacion {
    private int numerohabitacion;
    private String estado;  
    private String tipohabitacion;
    private int pisoId;

    public Habitacion(int numerohabitacion, String estado, String tipohabitacion, int pisoId) {
        this.numerohabitacion = numerohabitacion;
        this.estado = estado;
        this.tipohabitacion = tipohabitacion;
        this.pisoId = pisoId;
    }

    public int getNumerohabitacion() {
        return numerohabitacion;
    }

    public void setNumerohabitacion(int numerohabitacion) {
        this.numerohabitacion = numerohabitacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipohabitacion() {
        return tipohabitacion;
    }

    public void setTipohabitacion(String tipohabitacion) {
        this.tipohabitacion = tipohabitacion;
    }

    public int getPisoId() {
        return pisoId;
    }

    public void setPisoId(int pisoId) {
        this.pisoId = pisoId;
    }
    
    
    
}
