/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa2_aplicacion;
import com.proyectohotel.capa3dominio.Cliente;
import com.proyectohotel.capa3dominio.ReservaHabitacion;
import com.proyectohotel.capa4_persistencia.JDBC.GestorJDBC;
import com.proyectohotel.capa4_persistencia.jdbc_postgre.GestorJDBCPostgre;
import com.proyectohotel.capa4_persistencia.jdbc_postgre.ReservaDAOPostgre;
import java.util.List;

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
    public List<ReservaHabitacion> buscarTipoHabitacion(){
        return null;
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
