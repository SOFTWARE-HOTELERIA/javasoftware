/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa3dominio;

import java.sql.Date;


/**
 *
 * @author josel
 */
public class ReservaHabitacion {
      private String codigoReserva;
    private Date fechaIngreso;
    private Date fechaSalida;
    private int dias;
    private float pagoDeCliente;
    private String codigoCliente;
    private String numeroHabitacion;
    private Cliente cliente;
    private Habitacion habitacion;
  
    //constructor solo con atributos de reserva , no tomo en cuenta las clases , definir si es necesario
    public ReservaHabitacion(String codigoReserva, Date fechaIngreso, Date fechaSalida, int dias, float pagoDeCliente, String codigoCliente, String numeroHabitacion) {
        this.codigoReserva = codigoReserva;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.dias = dias;
        this.pagoDeCliente = pagoDeCliente;
        this.codigoCliente = codigoCliente;
        this.numeroHabitacion = numeroHabitacion;
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

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public float getPagoDeCliente() {
        return pagoDeCliente;
    }

    public void setPagoDeCliente(float pagoDeCliente) {
        this.pagoDeCliente = pagoDeCliente;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(String numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
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
        //=> total_dias * costo_habitacion
         return 0;
     }
     public int totalDeDiasHospedado(){
         //=> fecha_inicial | fecha_final  => diferencia entre dias 
         return 0;
     }
      public void validadDocumentoIdentidad(String documentoIdentidad){
          if(documentoIdentidad == null){
              
          }
     }
//     public List listarAño(int añonInicial,)
      
    public boolean validarEstadoHabitacion(String estado){
        if(estado.equals("DISPONIBLE")){
            return true;
        }else{
            return false;
        }
    }
    public boolean validadNumeroHabitacion(){
//        return null;
        return false;
    }
}
