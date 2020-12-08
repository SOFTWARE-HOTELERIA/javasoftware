/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa2_aplicacion.servicios;

import com.proyectohotel.capa1_presentacion.util.JReport;
import com.proyectohotel.capa3_dominio.entidades.RegistroDeHabitacion;
import com.proyectohotel.capa4_persistencia.JDBC.GestorJDBC;
import com.proyectohotel.capa4_persistencia.jdbc_postgre.CierreDeEstadiaDAOPostgre;
import com.proyectohotel.capa4_persistencia.jdbc_postgre.GestorJDBCPostgre;
import com.proyectohotel.capa4_persistencia.jdbc_postgre.ReservaDAOPostgre;
import java.util.HashMap;


/**
 *
 * @author USER
 */
public class CierreDeEstadiaServicio {
    GestorJDBC gestorJDBC;
    ReservaDAOPostgre reservaDAO;
    private JReport reporte;
    CierreDeEstadiaDAOPostgre cierreDAO;
      public CierreDeEstadiaServicio(){
        gestorJDBC = new GestorJDBCPostgre();
        cierreDAO = new CierreDeEstadiaDAOPostgre(gestorJDBC);
    }
    public void boletaDeCierreEstadia(String numeroReserva) throws Exception {
        gestorJDBC.abrirConexion();
        String path_reporte = "\\javasoftware\\src\\com\\proyectohotel\\capa1_presentacion\\reportes\\finalizarEstadia.jrxml";
         HashMap hm=new HashMap();
        hm.put("numeroReserva",numeroReserva);
        reporte = new JReport(path_reporte);
         reporte.loadFile();
         reporte.managerReportParameter(hm,gestorJDBC.conexionReport());
        gestorJDBC.cerrarConexion();
    }
     public RegistroDeHabitacion listadoEstadiaCliente(int documentoIdentidad) throws Exception{
         gestorJDBC.abrirConexion();
         RegistroDeHabitacion registroHabitacion = cierreDAO.listadoDeEstadia(documentoIdentidad);
         gestorJDBC.cerrarConexion();
        return registroHabitacion;
    }
      public int actualizarDatosEstadia(RegistroDeHabitacion registroDeHabitacion) throws Exception{
         gestorJDBC.abrirConexion();
          registroDeHabitacion.finalizarEstadia();
          int diasHospedados = registroDeHabitacion.totalDeDiasHospedado();
          double totalPago = registroDeHabitacion.calcularCostoFinal();
           int resultado = cierreDAO.actualizarEstadiaCliente(registroDeHabitacion, diasHospedados, totalPago);
           gestorJDBC.cerrarConexion();
          return resultado;
    }
    
}
