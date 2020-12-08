/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa1_presentacion.util;
import java.sql.Connection;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.util.Map;

/**
 *
 * @author josel
 */
public class JReport {
    //ESTA CLASE TRABAJAR CON LOS REPORTES , SOLO CREANDO FUNCIONES RECURSIVAS 
    private String path;
      public final String pathRelative="C:\\Users\\USER\\Desktop\\practica";
    public JReport(String path){
        this.path=pathRelative+path;
    }
    //parte 1 -- cargar archivo
    public JasperDesign loadFile() throws Exception{  
        JasperDesign jdesign= JRXmlLoader.load(this.path);
        return jdesign;
    }
    // parte 3 -- compilar reporte al cargar el archivo con la sentencia
    public JasperReport compilarReporte() throws Exception{
        JasperReport jreport=JasperCompileManager.compileReport(loadFile());
        return jreport;
    }
    // parte 4 -- administrar reporte final con parametro
    public void managerReportParameter(Map parameter,Connection con) throws Exception{
        JasperPrint jprint=JasperFillManager.fillReport(compilarReporte(),parameter,con);
        JasperViewer.viewReport(jprint);
    }
      // parte 4 -- administrar reporte final sin parametro
    public void managerReport(Connection con) throws Exception{
         JasperPrint jprint=JasperFillManager.fillReport(compilarReporte(),null,con);
        JasperViewer.viewReport(jprint);
    }
  
}
