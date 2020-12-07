/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa2_aplicacion.servicios;

import Config.variablesGlobales;
import com.proyectohotel.capa4_persistencia.JDBC.GestorJDBC;
import com.proyectohotel.capa4_persistencia.jdbc_postgre.ReservaDAOPostgre;
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
 * @author USER
 */
public class CierreDeEstadiaServicio {
    GestorJDBC gestorJDBC;
    ReservaDAOPostgre reservaDAO;
    
    public void boletaDeCierreEstadia(String documentoIdentidad) throws Exception {
        gestorJDBC.abrirConexion();
        String ruta2 = variablesGlobales.path + "\\javasoftware\\src\\reportes\\finalizarEstadia.jrxml";
        JasperDesign jdesign = JRXmlLoader.load(ruta2);
        String query_boletaCliente = reservaDAO.cerrarEstadiaCliente(documentoIdentidad); //query
        JRDesignQuery updaQuery_reporteCliente = new JRDesignQuery();
        updaQuery_reporteCliente.setText(query_boletaCliente);
        jdesign.setQuery(updaQuery_reporteCliente);
        //
        JasperReport jreport = JasperCompileManager.compileReport(jdesign);
        JasperPrint jprint = JasperFillManager.fillReport(jreport, null,gestorJDBC.conexionReport());
        JasperViewer.viewReport(jprint);
        gestorJDBC.cerrarConexion();
    }
    
}
