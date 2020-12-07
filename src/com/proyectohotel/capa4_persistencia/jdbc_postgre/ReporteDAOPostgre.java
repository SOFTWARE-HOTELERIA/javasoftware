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
import java.util.Date;
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
     public String reporteHospedaje(int anyoReporte){
         String query_reporteHospedaje="select * from ReporteporAño where extract (year from fecha_salida)='"+anyoReporte+"'";
           return query_reporteHospedaje;
       }
     
     public String reporteCliente(String Fecha1,String Fecha2){
        String query_reportCliente="select row_number() over(),cli.nombre,cli.apellido,cli.numeroidentidad,rh.fecha_entrada,rh.fecha_salida,rh.costo_final\n" +
                                        "from clientes cli inner join reservahabitacion rh\n" +
                                        "on cli.clientecodigo = rh.clientecodigo\n" +
                                        "where rh.fecha_salida between '"+Fecha1+"' and '"+Fecha2+"'";
        return query_reportCliente;
     }
      public int reporteTotalClientes(String Fecha1,String Fecha2)throws SQLException{
             int resultado=0;
             
                ResultSet re= gestorJDBC.ejecutarConsulta("select count(*) as total from reservahabitacion rh "+
                            "where rh.fecha_salida between '"+Fecha1+"' and '"+Fecha2+"'");
                
                 while( re.next()){
                     resultado=re.getInt("total");
                 }
                 
             return resultado;
         }
      public double reporteSumaCostoTotal(String Fecha1,String Fecha2)throws SQLException{
             double costoTotal=0;
                 ResultSet re= gestorJDBC.ejecutarConsulta("select sum(costo_final) as Costo_total from reservahabitacion rh\n"+
                            "where rh.fecha_salida between '"+Fecha1+"' and '"+Fecha2+"'");
                 
                 while( re.next()){
                     costoTotal=re.getInt("Costo_total");
                 }
                 
             return costoTotal;
         }
}