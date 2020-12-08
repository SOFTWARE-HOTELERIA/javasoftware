/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa4_persistencia.jdbc_postgre;

import com.proyectohotel.capa3_dominio.entidades.Cliente;
import com.proyectohotel.capa3_dominio.entidades.Habitacion;
import com.proyectohotel.capa3_dominio.entidades.RegistroDeHabitacion;
import com.proyectohotel.capa3_dominio.entidades.TipoHabitacion;
import com.proyectohotel.capa4_persistencia.JDBC.GestorJDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class CierreDeEstadiaDAOPostgre {
     GestorJDBC gestorJDBC;
     ReservaDAOPostgre reservaDAO;
    public CierreDeEstadiaDAOPostgre(GestorJDBC gestorJDBC){
        this.gestorJDBC=gestorJDBC;
        reservaDAO = new ReservaDAOPostgre(gestorJDBC);
    }
     public int actualizarEstadiaCliente(RegistroDeHabitacion registroHabitacion,int dias,double costo_final) throws SQLException{ 
         String sql="update reservahabitacion set costo_final=?,dias=?,fecha_salida=? where numeroreserva=?";
         PreparedStatement sentencia = gestorJDBC.prepararSentencia(sql);
         sentencia.setDouble(1,costo_final);
         sentencia.setInt(2,dias);
         sentencia.setDate(3,registroHabitacion.getFechaSalida());
         sentencia.setString(4,registroHabitacion.getNumeroReserva());
         int resultado = sentencia.executeUpdate();
         if(resultado == 1){
             reservaDAO.actualizarEstadoHabitacion(registroHabitacion.getHabitacion());
         }
         return resultado;
    }
         /* 
       Author por :Jose
    */
      public String cerrarEstadiaCliente(String numeroReserva) throws SQLException{
          System.out.println("persistence " + numeroReserva);
          String sql="select numeroreserva,dias,fecha_entrada,fecha_salida,"
                  + "costo,costo_final,descripcion,nombre,apellido,pisoId,numeroidentidad "
                  + "from estadiafinalizada where numeroreserva='"+numeroReserva+"'";
          return sql;
      }
     
    
     
    public RegistroDeHabitacion listadoDeEstadia(int documentoIdentidad) throws SQLException{
        RegistroDeHabitacion registroHabitacion=null; 
        ResultSet resultado_habitaciones;
        String sentenciaEstadia="select * from estadiaFinalizada where numeroIdentidad='"+documentoIdentidad+"'";
        resultado_habitaciones = gestorJDBC.ejecutarConsulta(sentenciaEstadia);
          while(resultado_habitaciones.next()){
              TipoHabitacion tipoHabitacion = new TipoHabitacion();
              tipoHabitacion.setDescripcion(resultado_habitaciones.getString("descripcion"));
              tipoHabitacion.setCosto(resultado_habitaciones.getDouble("costo"));
              Habitacion habitacion = new Habitacion();
              habitacion.setNumeroHabitacion(resultado_habitaciones.getString("nhabitacion"));
              habitacion.setNumeroDePiso(resultado_habitaciones.getInt("pisoid")); 
              habitacion.setNumeroHabitacion(resultado_habitaciones.getString("nhabitacion"));
              habitacion.setTipoHabitacion(tipoHabitacion);
              Cliente cliente = new Cliente();
              cliente.setCodigocliente(resultado_habitaciones.getString("clientecodigo"));
              cliente.setNombre(resultado_habitaciones.getString("nombre"));
              cliente.setApellido(resultado_habitaciones.getString("apellido"));
              cliente.setNumeroIdentidad(resultado_habitaciones.getString("numeroidentidad"));
              registroHabitacion= new RegistroDeHabitacion();
              registroHabitacion.setNumeroReserva(resultado_habitaciones.getString("numeroreserva"));
              registroHabitacion.setFechaIngreso(resultado_habitaciones.getDate("fecha_entrada"));
              registroHabitacion.setCliente(cliente);
              registroHabitacion.setHabitacion(habitacion);
          }
         resultado_habitaciones.close();
        return registroHabitacion;
    }
}
