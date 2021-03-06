/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa2_aplicacion.servicios;
import com.proyectohotel.capa3_dominio.entidades.Cliente;
import com.proyectohotel.capa3_dominio.entidades.TipoHabitacion;
import com.proyectohotel.capa3_dominio.entidades.Habitacion;
import com.proyectohotel.capa3_dominio.entidades.RegistroDeHabitacion;
import com.proyectohotel.capa4_persistencia.JDBC.GestorJDBC;
import com.proyectohotel.capa4_persistencia.jdbc_postgre.GestorJDBCPostgre;
import com.proyectohotel.capa4_persistencia.jdbc_postgre.ReservaDAOPostgre;
import java.util.List;
import java.util.Map;


/**
 *
 * @author josel
 */
public class RegistroHospedajeServicio {
    /* 
    Author : Jose
    */
    GestorJDBC gestorJDBC;
    ReservaDAOPostgre reservaDAO;
    public RegistroHospedajeServicio(){
        gestorJDBC = new GestorJDBCPostgre();
        reservaDAO = new ReservaDAOPostgre(gestorJDBC);
    }
    
    /* 
    Author :  Marco
    */
    //buscar cliente
    public Cliente buscarCliente(String documentoIdentidad) throws Exception{
         gestorJDBC.abrirConexion();
         Cliente cliente = reservaDAO.buscarCliente(documentoIdentidad);
         gestorJDBC.cerrarConexion(); 
          return cliente;
          
    }
     //registrar habitacion
    public int registrarHabitacion(RegistroDeHabitacion registroDeHabitacion) throws Exception{
          gestorJDBC.abrirConexion();
          String codigo = reservaDAO.obtenerCodigoAnterior();
          registroDeHabitacion.setNumeroReserva(codigo);
          System.out.println(codigo);
          registroDeHabitacion.registrarHabitacion();
          int resultado_registro = reservaDAO.registrarHabitacionCliente(registroDeHabitacion);
          gestorJDBC.cerrarConexion(); 
          return resultado_registro;
    }
    
    //mostrar detalle de habitacion
     public RegistroDeHabitacion detalleReservaHabitacion(String codigoCliente) throws Exception{
        gestorJDBC.abrirConexion();
        RegistroDeHabitacion registroHabitacion = reservaDAO.readRegistroHabitacion(codigoCliente);
        gestorJDBC.cerrarConexion(); 
          return registroHabitacion;
    }
     /* 
     Author : Jose
    */
    public List<Habitacion> mostrarHabitaciones(String tipoHabitacion) throws Exception{
        gestorJDBC.abrirConexion();
        List reserva = reservaDAO.listarHabitaciones(tipoHabitacion);
        gestorJDBC.cerrarConexion();
        return reserva;
    }
     /* 
     Author : Jose
    */
    //mostrar acumuladores de total de habitaciones disponibles y no disponibles
    public Map mostrarTotalDeHabitacionesDeEstado(String tipoHabitacion) throws Exception{
        gestorJDBC.abrirConexion();
        Map datos  = reservaDAO.mostrarTotalDeHabitacionesDeEstado(tipoHabitacion);
        gestorJDBC.cerrarConexion();
        return datos;
    }
    
    public double mostrarCostoHabitacion(String tipoHabitacion) throws Exception{
        gestorJDBC.abrirConexion();
        double costo = reservaDAO.costoTipoDeHabitacion(tipoHabitacion);
         gestorJDBC.cerrarConexion();
        return costo;
    }
     /* 
     Author : Jose
    */
    public List<TipoHabitacion> mostrarTiposHabitaciones() throws Exception{
        gestorJDBC.abrirConexion();
        List tipoHabitaciones = reservaDAO.listarTipoHabitaciones();
         gestorJDBC.cerrarConexion();
        return tipoHabitaciones;
    }
  
}
