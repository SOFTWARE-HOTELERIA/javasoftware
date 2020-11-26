/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa2_aplicacion;
//import com.proyectohotel.capa3dominio

import Config.Conexion;
import Config.variablesGlobales;
import com.proyectohotel.capa3_dominio.entidades.RegistroDeHabitacion;
import com.proyectohotel.capa4_persistencia.JDBC.GestorJDBC;
import com.proyectohotel.capa4_persistencia.jdbc_postgre.GestorJDBCPostgre;
import com.proyectohotel.capa4_persistencia.jdbc_postgre.ReporteDAOPostgre;
import com.proyectohotel.capa4_persistencia.jdbc_postgre.ReservaDAOPostgre;
import java.util.Date;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

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
    ReporteDAOPostgre reporteDAO;
    public ReporteHospedajeService(){
        gestorJDBC = new GestorJDBCPostgre();
        //reservaDAO = new ReservaDAOPostgre(gestorJDBC);
        reporteDAO=new ReporteDAOPostgre(gestorJDBC);
        
        
    }
    /* 
         Author : Jhonatan 
    */
    //falta funcion
    public void reporteCliente(String Fecha1,String Fecha2)throws Exception{
        gestorJDBC.abrirConexion();
        String ruta2=variablesGlobales.path+"\\javasoftware\\src\\reportes\\reportCliente.jrxml";
        JasperDesign jdesign= JRXmlLoader.load(ruta2);
        String query_reporteCliente= reporteDAO.reporteCliente(Fecha1, Fecha2);
        JRDesignQuery updaQuery_reporteCliente=new JRDesignQuery();
        updaQuery_reporteCliente.setText(query_reporteCliente);
        jdesign.setQuery(updaQuery_reporteCliente);
        JasperReport jreport=JasperCompileManager.compileReport(jdesign);
        HashMap hm=new HashMap();
        int totalClientes= reporteDAO.reporteTotalClientes(Fecha1, Fecha2);
        double SumaCostoTotal= reporteDAO.reporteSumaCostoTotal(Fecha1, Fecha2);
        hm.put("conteoCliente", String.valueOf(totalClientes));
        hm.put("sumaCostoTotal",String.valueOf(SumaCostoTotal)); 
         JasperPrint jprint=JasperFillManager.fillReport(jreport,hm,gestorJDBC.conexionReport());
         JasperViewer.viewReport(jprint);
         gestorJDBC.cerrarConexion();
    }
    /* 
         Author : Bruno
    */
     public RegistroDeHabitacion reporteHospedaje(){
           return null;
     }
    
}