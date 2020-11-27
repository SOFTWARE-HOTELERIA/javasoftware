/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa2_aplicacion;
//import com.proyectohotel.capa3dominio
import com.proyectohotel.capa3_dominio.entidades.RegistroDeHabitacion;
import com.proyectohotel.capa4_persistencia.JDBC.GestorJDBC;
import com.proyectohotel.capa4_persistencia.jdbc_postgre.GestorJDBCPostgre;
import com.proyectohotel.capa4_persistencia.jdbc_postgre.ReservaDAOPostgre;

/**
 *
 * @author josel
 */
public class ReporteHospedajeService {
     /* 
    Author : Jose
    */
    GestorJDBC gestorJDBC;
    ReservaDAOPostgre reservaDAO;
    public ReporteHospedajeService(){
        gestorJDBC = new GestorJDBCPostgre();
        //reservaDAO = new ReservaDAOPostgre(gestorJDBC);
        
    }
    /* 
         Author : Jhonatan
    */
    //falta funcion
    
    /* 
         Author : Bruno
    */
     public RegistroDeHabitacion reporteHospedaje(){
           return null;
     }
    
}