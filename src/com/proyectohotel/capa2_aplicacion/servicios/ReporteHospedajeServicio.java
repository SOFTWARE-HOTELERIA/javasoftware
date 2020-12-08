/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa2_aplicacion.servicios;
//import com.proyectohotel.capa3dominio

import com.proyectohotel.capa1_presentacion.util.JReport;
import com.proyectohotel.capa4_persistencia.JDBC.GestorJDBC;
import com.proyectohotel.capa4_persistencia.jdbc_postgre.GestorJDBCPostgre;
import com.proyectohotel.capa4_persistencia.jdbc_postgre.ReporteDAOPostgre;
import java.util.Date;
import java.util.HashMap;



/**
 *
 * @author josel
 */
public class ReporteHospedajeServicio {
     /* 
    Author : Jose
    */
    GestorJDBC gestorJDBC;
    ReporteDAOPostgre reporteDAO;
    public ReporteHospedajeServicio(){
        gestorJDBC = new GestorJDBCPostgre();
        reporteDAO=new ReporteDAOPostgre(gestorJDBC);
        
        
    }
    /* 
         Author : Jhonatan 
    */
    //falta funcion
    public void reporteCliente(Date fechaEntrada,Date fechaSalida)throws Exception{
        gestorJDBC.abrirConexion();
        String path_reporte="\\javasoftware\\src\\com\\proyectohotel\\capa1_presentacion\\reportes\\reportCliente.jrxml";
        HashMap hm=new HashMap();
        int totalClientes= reporteDAO.reporteTotalClientes(fechaEntrada, fechaSalida);
        double SumaCostoTotal= reporteDAO.reporteSumaCostoTotal(fechaEntrada, fechaSalida);
        hm.put("conteoCliente", String.valueOf(totalClientes));
        hm.put("sumaCostoTotal",String.valueOf(SumaCostoTotal)); 
        hm.put("fechaEntrada",fechaEntrada);
        hm.put("fechaFinal",fechaSalida);
        JReport reporte = new JReport(path_reporte);
        reporte.loadFile();
        reporte.managerReportParameter(hm,gestorJDBC.conexionReport());
        gestorJDBC.cerrarConexion();
    }
    /* 
         Author : Bruno
    */
     public void reporteHospedaje(int anyoReporte)throws Exception{
        gestorJDBC.abrirConexion();
        String path_reporte="\\javasoftware\\src\\com\\proyectohotel\\capa1_presentacion\\reportes\\ReportGraficodeEstadiaTerminadaPorAño.jrxml";
        HashMap hm=new HashMap();
        hm.put("anyo",anyoReporte);
        JReport reporte = new JReport(path_reporte);
        reporte.loadFile();
        reporte.managerReportParameter(hm,gestorJDBC.conexionReport());
         gestorJDBC.cerrarConexion();
     }
    
}