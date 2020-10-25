/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa4_persistencia.jdbc_postgre;
import com.proyectohotel.capa3dominio.Cliente;
import com.proyectohotel.capa3dominio.Habitacion;
import com.proyectohotel.capa3dominio.ReservaHabitacion;
import com.proyectohotel.capa4_persistencia.JDBC.GestorJDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    //IMPLEMENTAR LOGICA PARA MOSTRAR TOTAL HABITACIONES DISPONIBLES Y NO DISPONIBLES
     /* 
       Author : Wilmer
    */
    //solo listar
    public List<ReservaHabitacion> listarHabitaciones() throws SQLException{
        //posible implementacion aqui mismo de logica de 2 querys 
        ArrayList<ReservaHabitacion> reserva = new ArrayList();
         ResultSet resultado_habitaciones;
        String sql ="select nhabitacion,estado,nivelId from habitacion";
         resultado_habitaciones = gestorJDBC.ejecutarConsulta(sql);
        while(resultado_habitaciones.next()){
            ReservaHabitacion reservaHabitacion = new ReservaHabitacion();
            Habitacion habitacion = new Habitacion(resultado_habitaciones.getString("estado"),resultado_habitaciones.getInt("nivelId"));
            reservaHabitacion.setNumeroHabitacion(resultado_habitaciones.getString("nhabitacion"));
            reservaHabitacion.setHabitacion(habitacion);
            reserva.add(reservaHabitacion);
        }
        return reserva;
    }
    public Map mostrarTotalDeHabitacionesDeEstado() throws SQLException{
        PreparedStatement q1 = null;
        PreparedStatement q2 = null;
        ResultSet re1=null;
        ResultSet re2=null;
       Map data = new HashMap();
        String ocupadas= "select count(*) as ocupadas from habitacion where estado='OCUPADO'";
        String disponibles = "select count(*) as disponibles from habitacion where estado='DISPONIBLE'";
         String [] output = {"ocupadas","disponibles"};
          PreparedStatement[] pres ={q1,q2};
          ResultSet [] res = {re1,re2};
        String [] sqlquerys={ocupadas,disponibles};
         for(int i=0;i<pres.length;i++){
                  res[i] = gestorJDBC.ejecutarConsulta(sqlquerys[i]);
                  if(res[i].next()){
                      data.put(output[i], res[i].getString(output[i]));
                  }
                  
        }
         return data;
   }
     /* 
       Author : Aldo 
    */
    public ReservaHabitacion cerrarEstadiaCliente(String documentoIdentidad){
        return null;
    }
    /* 
       Author : Guillermo 
    */
    public int registrarHabitacion(ReservaHabitacion reservaHabitacion){
        return 0;
    }
}
