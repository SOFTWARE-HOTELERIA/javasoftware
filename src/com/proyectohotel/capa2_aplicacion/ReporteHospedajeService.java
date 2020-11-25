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
import com.proyectohotel.capa4_persistencia.jdbc_postgre.ReporteDAOPostgre;
import com.proyectohotel.capa4_persistencia.jdbc_postgre.ReservaDAOPostgre;
import java.sql.Date;
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
    public RegistroDeHabitacion reporteCliente(String Fecha1,String Fecha2,String query_reporteCliente) throws Exception{
        gestorJDBC.abrirConexion();
        JasperDesign jdesign= JRXmlLoader.load("");            
            JRDesignQuery updaQuery_reporteCliente=new JRDesignQuery();
            updaQuery_reporteCliente.setText(query_reporteCliente);
            jdesign.setQuery(updaQuery_reporteCliente);
            JasperReport jreport=JasperCompileManager.compileReport(jdesign);
            HashMap hm=new HashMap();
            int totalClientes= reporteDAO.getTotalClientes(Fecha1, Fecha2);
            double SumaCostoTotal= reporteDAO.getSumaCostoTotal(Fecha1, Fecha2);
            hm.put("contador", String.valueOf(totalClientes));
            hm.put("suma",String.valueOf(SumaCostoTotal)); 
            return null;
    }
    public void reporteCliente2(Date Fecha1,Date Fecha2)throws Exception{
        
        JasperDesign jdesign= JRXmlLoader.load("C:\\Users\\USER\\Desktop\\nuevo\\javasoftware\\src\\reportes\\reportCliente.jrxml");
        String query_reporteCliente= reporteDAO.reporteCliente2(Fecha1, Fecha2);
        JRDesignQuery updaQuery_reporteCliente=new JRDesignQuery();
        updaQuery_reporteCliente.setText(query_reporteCliente);
        jdesign.setQuery(updaQuery_reporteCliente);
        JasperReport jreport=JasperCompileManager.compileReport(jdesign);
       // HashMap hm=new HashMap();
      //  int totalClientes= reporteDAO.getTotalClientes(Fecha1, Fecha2);
    //    double SumaCostoTotal= reporteDAO.getSumaCostoTotal(Fecha1, Fecha2);
       // hm.put("contador", String.valueOf(totalClientes));
      //  hm.put("suma",String.valueOf(SumaCostoTotal)); 
         JasperPrint jprint=JasperFillManager.fillReport(jreport,null,gestorJDBC.conexionReport());
         JasperViewer.viewReport(jprint);
    }
    /* 
         Author : Bruno
    */
     public RegistroDeHabitacion reporteHospedaje(){
           return null;
     }
    
}