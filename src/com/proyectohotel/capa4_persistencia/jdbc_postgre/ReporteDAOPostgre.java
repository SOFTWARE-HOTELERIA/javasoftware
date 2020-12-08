/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa4_persistencia.jdbc_postgre;
//import com.proyectohotel.capa4_persistencia.jdbc.


import com.proyectohotel.capa4_persistencia.JDBC.GestorJDBC;
import java.util.Date;
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
      public int reporteTotalClientes(Date Fecha1,Date Fecha2)throws SQLException{
             int resultado=0;
             
                ResultSet re= gestorJDBC.ejecutarConsulta("select count(*) as total from reservahabitacion rh "+
                            "where rh.fecha_salida between '"+Fecha1+"' and '"+Fecha2+"'");
                
                 while( re.next()){
                     resultado=re.getInt("total");
                 }
                 
             return resultado;
         }
      public double reporteSumaCostoTotal(Date Fecha1,Date Fecha2)throws SQLException{
             double costoTotal=0;
                 ResultSet re= gestorJDBC.ejecutarConsulta("select sum(costo_final) as Costo_total from reservahabitacion rh\n"+
                            "where rh.fecha_salida between '"+Fecha1+"' and '"+Fecha2+"'");
                 
                 while( re.next()){
                     costoTotal=re.getInt("Costo_total");
                 }
                 
             return costoTotal;
         }
}