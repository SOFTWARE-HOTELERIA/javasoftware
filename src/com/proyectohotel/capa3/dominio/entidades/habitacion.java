package com.proyectohotel.capa3.dominio.entidades;

public class habitacion {
    private int numerohabitacion;
    private boolean estado;  
    private String tipohabitacion;
    private int pisoId;

    public static final String ESTADO_DISPONIBLE="DISPONIBLE";
    public static final String ESTADO_NODISPONIBLE="NO DISPONIBLE";
    
    
    public habitacion(int numerohabitacion, boolean estado, String tipohabitacion, int pisoId) {
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
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
    
        //REGLAS DE NEGOCIO
    public boolean estadoDisponibleParaLaHabitacion(){
        return !estado && tipohabitacion.equals(ESTADO_DISPONIBLE);
    
    
    
    
    }
    
}
