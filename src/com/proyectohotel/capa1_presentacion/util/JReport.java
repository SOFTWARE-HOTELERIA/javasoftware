/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa1_presentacion.util;
import com.proyectohotel.capa4_persistencia.JDBC.GestorJDBC;
import com.proyectohotel.capa4_persistencia.jdbc_postgre.GestorJDBCPostgre;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author josel
 */
public class JReport {
    //ESTA CLASE TRABAJAR CON LOS REPORTES , SOLO CREANDO FUNCIONES RECURSIVAS 
    private String path;
    private String sql;
    GestorJDBC gestorJDBC;
    public JReport(String path,String sql){
        gestorJDBC = new GestorJDBCPostgre();
        this.path=path;
        this.sql=sql;
    }
    //parte 1 -- cargar archivo
    public JasperDesign loadFile() throws Exception{  
        JasperDesign jdesign= JRXmlLoader.load(this.path);
        return jdesign;
    }
    // parte 2 - establecer sentencia en jreport
    public void loadSentence() throws Exception{
        System.out.println("cargar sentencia" + this.sql);
         JRDesignQuery jrDesignQuery=new JRDesignQuery();
         jrDesignQuery.setText(this.sql);
         loadFile().setQuery(jrDesignQuery);
    }
    // parte 3 -- compilar reporte al cargar el archivo con la sentencia
    public JasperReport compilarReporte() throws Exception{
        System.out.println("compilar report" + this.path);
        JasperReport jreport=JasperCompileManager.compileReport(loadFile());
        return jreport;
    }
    // parte 4 -- administrar reporte final con parametro
    public void managerReportParameter(Map parameter) throws JRException,Exception{
        JasperPrint jprint=JasperFillManager.fillReport(compilarReporte(),parameter,gestorJDBC.conexionReport());
        JasperViewer.viewReport(jprint);
    }
      // parte 4 -- administrar reporte final sin parametro
    public void managerReport() throws JRException,Exception{
        System.out.println("manage rreport");
         JasperPrint jprint=JasperFillManager.fillReport(compilarReporte(),null,gestorJDBC.conexionReport());
        JasperViewer.viewReport(jprint);
    }
  
}
