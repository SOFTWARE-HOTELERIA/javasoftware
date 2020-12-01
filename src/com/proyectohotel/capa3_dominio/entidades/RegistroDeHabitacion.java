/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa3_dominio.entidades;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.lang.Math;

/**
 *
 * @author josel
 */
public class RegistroDeHabitacion {
    private String codigoReserva;
    private Date fechaIngreso;
    private Date fechaSalida;
    private Cliente cliente;
    private Habitacion habitacion;
    Calendar cal = Calendar.getInstance();
   public static final String ESTADO_DISPONIBLE = "DISPONIBLE";
    public static final String ESTADO_OCUPADO = "OCUPADO";
    //constructor solo con atributos de reserva , no tomo en cuenta las clases , definir si es necesario
    public RegistroDeHabitacion(Date fechaIngreso,Date fechaSalida){
        this.fechaIngreso=fechaIngreso;
        this.fechaSalida=fechaSalida;
    }
            
    public RegistroDeHabitacion(String codigoReserva, Date fechaIngreso, Date fechaSalida, int dias, float pagoDeCliente, String codigoCliente) {
        this.codigoReserva = codigoReserva;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
    }
     public RegistroDeHabitacion(){
        
    }
    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }
    //reglas de negocio
     public double calcularCostoFinal(){
       return  totalDeDiasHospedado()*habitacion.getTipoHabitacion().getCosto();
     }
//     fechaIngreso.getDay()
     public int totalDeDiasHospedado(){
         //only work with dias , no trabaja meses ni años :/
        return Math.abs(getDayNumberOld(getFechaIngreso()) - getDayNumberOld(getFechaSalida()));
     }
     public void registrarHabitacion(){
          setFechaIngreso(Date.valueOf(LocalDate.now()));
          habitacion.setEstado(ESTADO_OCUPADO);
     }
     public void finalizarEstadia(){
        habitacion.setEstado(ESTADO_DISPONIBLE);
        setFechaSalida(Date.valueOf(LocalDate.now()));
     }
     public static int getDayNumberOld(Date date) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal.get(Calendar.DAY_OF_MONTH);
      }
  

}
