/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa4_persistencia.jdbc_postgre;
import com.proyectohotel.capa3dominio.Cliente;
import com.proyectohotel.capa3dominio.ReservaHabitacion;
import com.proyectohotel.capa4_persistencia.JDBC.GestorJDBC;
import java.util.List;
/**
 *
 * @author josel
 */
public class ReservaDAOPostgre {
      GestorJDBC gestorJDBC;
    public ReservaDAOPostgre(GestorJDBC gestorJDBC){
        this.gestorJDBC=gestorJDBC;
    }
       //ingresar logica en cada funcion con comentarios..
        //Recordar por procesos
    //NOTAS:
    //1.QUERYS DEFINIDAS EN EL PAQUETE => CONFIG/CONSULTAS.SQL
    //2.USAR ESAS QUERYS EN SU GESTOR DE BASE DE DATOS PORGEST PRIMERAMENTE
    //3.ADAPTAR ESA QUERY A CADA FUNCION CORRESPONDIENTE
     /* 
       Author : Marco
    */
    public Cliente buscarCliente(String documentoIdentidad){
        return null;
    }
     /* 
       Author : Wilmer
    */
    public List<ReservaHabitacion> buscarTipoHabitacion(){
        return null;
    }
     /* 
       Author : Guillermo 
    */
    public ReservaHabitacion cerrarEstadiaCliente(String documentoIdentidad){
        return null;
    }
    /* 
       Author : Aldo 
    */
    public int registrarHabitacion(ReservaHabitacion reservaHabitacion){
        return 0;
    }
}
