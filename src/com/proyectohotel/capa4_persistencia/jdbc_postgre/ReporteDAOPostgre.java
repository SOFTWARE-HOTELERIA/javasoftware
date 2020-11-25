/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa4_persistencia.jdbc_postgre;
//import com.proyectohotel.capa4_persistencia.jdbc.

import com.proyectohotel.capa3_dominio.entidades.Cliente;
import com.proyectohotel.capa3_dominio.entidades.Habitacion;
import com.proyectohotel.capa3_dominio.entidades.RegistroDeHabitacion;
import com.proyectohotel.capa3_dominio.entidades.TipoHabitacion;
import com.proyectohotel.capa4_persistencia.JDBC.GestorJDBC;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author josel
 */
public class ReporteDAOPostgre {
    GestorJDBC gestorJDBC;
    public ReporteDAOPostgre(GestorJDBC gestorJDBC){
        this.gestorJDBC=gestorJDBC;
    }
    //no definido el primer reporte
    //    public reporteCliente(String anyo){
    //        
    //    }
     public RegistroDeHabitacion reporteHospedaje(){
           return null;
       }
     public RegistroDeHabitacion reporteCliente(String Fecha1,String Fecha2,String query_reportCliente) throws SQLException{
      RegistroDeHabitacion registroDeHabitacion=null;
      ResultSet resultado=null;   
      query_reportCliente="select row_number() over(),cli.nombre,cli.apellido,cli.numeroidentidad,rh.fecha_entrada,rh.fecha_salida,rh.costo_final\n" +
                                        "from clientes cli inner join reservahabitacion rh\n" +
                                        "on cli.clientecodigo = rh.clientecodigo\n" +
                                        "where rh.fecha_salida between '"+Fecha1+"' and '"+Fecha2+"'";
      resultado=gestorJDBC.ejecutarConsulta(query_reportCliente);
      if(resultado.next()){
          Cliente cliente=new Cliente();
          cliente.setNombre(resultado.getString("nombre"));
          cliente.setApellido(resultado.getString("apellido"));
          cliente.setNumeroIdentidad(resultado.getString("numeroidentidad"));
          registroDeHabitacion=new RegistroDeHabitacion();
          registroDeHabitacion.setFechaIngreso(resultado.getDate("fecha_entrada"));
          registroDeHabitacion.setFechaSalida(resultado.getDate("fecha_salida"));
          TipoHabitacion tipoHabitacion=new TipoHabitacion();
          tipoHabitacion.setCosto(resultado.getDouble("costo_final"));        
      }
      resultado.close();
      return registroDeHabitacion;
     }
     public String reporteCliente2(Date Fecha1,Date Fecha2){
        String query_reportCliente="select row_number() over(),cli.nombre,cli.apellido,cli.numeroidentidad,rh.fecha_entrada,rh.fecha_salida,rh.costo_final\n" +
                                        "from clientes cli inner join reservahabitacion rh\n" +
                                        "on cli.clientecodigo = rh.clientecodigo\n" +
                                        "where rh.fecha_salida between '"+Fecha1+"' and '"+Fecha2+"'";
        return query_reportCliente;
     }
      public int getTotalClientes(String Fecha1,String Fecha2)throws SQLException{
             int resultado=0;
             try {
                PreparedStatement pre= gestorJDBC.prepararSentencia("select count(*) as total from reservahabitacion rh\n"+
                            "where rh.fecha_salida between '"+Fecha1+"' and '"+Fecha2+"'");
                ResultSet re= pre.executeQuery();
                 while( re.next()){
                     resultado=re.getInt("total");
                 }
                 
             } catch (Exception e) {
                 System.out.println("error"+e);
             }
             return resultado;
         }
      public double getSumaCostoTotal(String Fecha1,String Fecha2){
             double costoTotal=0;
             try {
                 PreparedStatement pre= gestorJDBC.prepararSentencia("select sum(costo_final) as Costo_total from reservahabitacion rh\n"+
                            "where rh.fecha_salida between '"+Fecha1+"' and '"+Fecha2+"'");
                 ResultSet re= pre.executeQuery();
                 while( re.next()){
                     costoTotal=re.getInt("Costo_total");
                 }
                 
             } catch (Exception e) {
                 System.out.println("error"+e);
             }
             return costoTotal;
         }
}