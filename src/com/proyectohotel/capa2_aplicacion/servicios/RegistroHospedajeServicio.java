/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa2_aplicacion.servicios;
import Config.variablesGlobales;
import com.proyectohotel.capa3_dominio.entidades.Cliente;
import com.proyectohotel.capa3_dominio.entidades.TipoHabitacion;
import com.proyectohotel.capa3_dominio.entidades.Habitacion;
import com.proyectohotel.capa3_dominio.entidades.RegistroDeHabitacion;
import com.proyectohotel.capa4_persistencia.JDBC.GestorJDBC;
import com.proyectohotel.capa4_persistencia.jdbc_postgre.GestorJDBCPostgre;
import com.proyectohotel.capa4_persistencia.jdbc_postgre.ReservaDAOPostgre;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.HashMap;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JRException;
/**
 *
 * @author josel
 */
public class RegistroHospedajeServicio {
    /* 
    Author : Jose
    */
    GestorJDBC gestorJDBC;
    ReservaDAOPostgre reservaDAO;
    public RegistroHospedajeServicio(){
        gestorJDBC = new GestorJDBCPostgre();
        reservaDAO = new ReservaDAOPostgre(gestorJDBC);
    }
    
    /* 
    Author :  Marco
    */
    public Cliente buscarCliente(String documentoIdentidad) throws Exception{
         gestorJDBC.abrirConexion();
         Cliente cliente = reservaDAO.buscarCliente(documentoIdentidad);
         gestorJDBC.cerrarConexion(); 
          return cliente;
    }
     /* 
     Author : Jose
    */
    public List<Habitacion> mostrarHabitaciones(String tipoHabitacion) throws Exception{
        gestorJDBC.abrirConexion();
        List reserva = reservaDAO.listarHabitaciones(tipoHabitacion);
        gestorJDBC.cerrarConexion();
        return reserva;
    }
     /* 
     Author : Jose
    */
    public Map mostrarTotalDeHabitacionesDeEstado(String tipoHabitacion) throws Exception{
        gestorJDBC.abrirConexion();
        Map datos = new HashMap();
        datos  = reservaDAO.mostrarTotalDeHabitacionesDeEstado(tipoHabitacion);
        gestorJDBC.cerrarConexion();
        return datos;
    }
     /* 
     Author : Jose
    */
    public List<TipoHabitacion> mostrarTiposHabitaciones() throws Exception{
        gestorJDBC.abrirConexion();
        List tipoHabitaciones = reservaDAO.listarTipoHabitaciones();
         gestorJDBC.cerrarConexion();
        return tipoHabitaciones;
    }
    /* 
     Author : Jose
    */
    public int actualizarDatosEstadia(RegistroDeHabitacion registroDeHabitacion) throws Exception{
         gestorJDBC.abrirConexion();
          registroDeHabitacion.finalizarEstadia();
          int diasHospedados = registroDeHabitacion.totalDeDiasHospedado();
          double totalPago = registroDeHabitacion.calcularCostoFinal();
       int resultado = reservaDAO.actualizarEstadiaCliente(registroDeHabitacion, diasHospedados, totalPago);
             gestorJDBC.cerrarConexion();
          return resultado;
    }
     /* 
     Author : Jose
    */
    public void boletaDeCierreEstadia(String documentoIdentidad) throws Exception{
         gestorJDBC.abrirConexion();
        String ruta2=variablesGlobales.path+"\\javasoftware\\src\\reportes\\finalizarEstadia.jrxml";
        JasperDesign jdesign= JRXmlLoader.load(ruta2);
        String query_boletaCliente=reservaDAO.cerrarEstadiaCliente(documentoIdentidad); //query
        JRDesignQuery updaQuery_reporteCliente=new JRDesignQuery();
        updaQuery_reporteCliente.setText(query_boletaCliente);
        jdesign.setQuery(updaQuery_reporteCliente);
        //
        JasperReport jreport=JasperCompileManager.compileReport(jdesign);
         JasperPrint jprint=JasperFillManager.fillReport(jreport,null,gestorJDBC.conexionReport());
         JasperViewer.viewReport(jprint);
         gestorJDBC.cerrarConexion();
    }
     /* 
     Author : Jose
    */
     public RegistroDeHabitacion listadoEstadiaCliente(int documentoIdentidad) throws Exception{
         gestorJDBC.abrirConexion();
         RegistroDeHabitacion registroHabitacion = reservaDAO.listadoDeEstadia(documentoIdentidad);
         gestorJDBC.cerrarConexion();
        return registroHabitacion;
    }
  
}
