/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa2_aplicacion;
import com.proyectohotel.capa3_dominio.entidades.Cliente;
import com.proyectohotel.capa3_dominio.entidades.Habitacion;
import com.proyectohotel.capa3_dominio.entidades.ReservaHabitacion;
import com.proyectohotel.capa4_persistencia.JDBC.GestorJDBC;
import com.proyectohotel.capa4_persistencia.jdbc_postgre.GestorJDBCPostgre;
import com.proyectohotel.capa4_persistencia.jdbc_postgre.ReservaDAOPostgre;
import java.util.List;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author josel
 */
public class RegistroHospedajeService {
    /* 
    Author : Jose
    */
    GestorJDBC gestorJDBC;
    ReservaDAOPostgre reservaDAO;
    public RegistroHospedajeService(){
        gestorJDBC = new GestorJDBCPostgre();
        reservaDAO = new ReservaDAOPostgre(gestorJDBC);
        
    }
    
    /* 
    Author :  Marco
    */
    public Cliente buscarCliente(String documentoIdentidad) throws Exception{
         gestorJDBC.abrirConexion();
         Cliente cliente = reservaDAO.buscarCliente(documentoIdentidad);
         gestorJDBC.cerrarConexion(); 
          return cliente;
    }
     /* 
     Author : Wilmer
    */
     /* 
     Author : Wilmer
    */
    public List<Habitacion> mostrarHabitaciones() throws Exception{
        gestorJDBC.abrirConexion();
        List reserva = reservaDAO.listarHabitaciones();
        gestorJDBC.cerrarConexion();
        return reserva;
    }
    public Map mostrarTotalDeHabitacionesDeEstado() throws Exception{
        gestorJDBC.abrirConexion();
        Map datos = new HashMap();
        datos  = reservaDAO.mostrarTotalDeHabitacionesDeEstado();
        gestorJDBC.cerrarConexion();
        return datos;
    }
    //requiere la tabla en el capa presentacion obligatoriamente
    /* 
     Author : Guillermo
    */
    public int registrarHabitacion(ReservaHabitacion reservaHabitacion){
        return 0;
    }
     /* 
     Author : Aldo
    */
     public ReservaHabitacion cerrarEstadiaCliente(String documentoIdentidad){
        return null;
    }
  
}
