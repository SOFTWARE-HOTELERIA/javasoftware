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
        String sql="SELECT c.nombre,c.apellido,c.telefono,td.descripcion,c.correo ,c.nacionalidad \n" +
        "FROM clientes c left JOIN tipo_documento td on c.documentoId =td.documentoId\n" +
        "where c.numeroIdentidad = '65821548'";
        
        return null;
    }
     /* 
       Author : Wilmer
    */
    //solo listar
    public List<ReservaHabitacion> listarHabitaciones() throws SQLException{
         ArrayList<ReservaHabitacion> reserva = new ArrayList();
        String sql ="select nhabitacion,estado,nivelId from habitacion";
        PreparedStatement pre = gestorJDBC.prepararSentencia(sql);
        ResultSet re = pre.executeQuery();
        while(re.next()){
            ReservaHabitacion reservaHabitacion = new ReservaHabitacion();
            Habitacion habitacion = new Habitacion(re.getString("estado"),re.getInt("nivelId"));
            reservaHabitacion.setNumeroHabitacion(re.getString("nhabitacion"));
            reservaHabitacion.setHabitacion(habitacion);
            reserva.add(reservaHabitacion);
        }
        return reserva;
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
