/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa4_persistencia.jdbc_postgre;
//import com.proyectohotel.capa4_persistencia.jdbc.

import com.proyectohotel.capa3_dominio.entidades.RegistroDeHabitacion;
import com.proyectohotel.capa4_persistencia.JDBC.GestorJDBC;


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
}
