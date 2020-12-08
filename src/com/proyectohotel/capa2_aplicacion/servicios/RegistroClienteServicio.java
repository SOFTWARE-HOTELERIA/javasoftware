/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa2_aplicacion.servicios;

import com.proyectohotel.capa3_dominio.entidades.Cliente;
import com.proyectohotel.capa3_dominio.entidades.TipoDocumento;
import com.proyectohotel.capa4_persistencia.JDBC.GestorJDBC;
import com.proyectohotel.capa4_persistencia.jdbc_postgre.GestorJDBCPostgre;
import com.proyectohotel.capa4_persistencia.jdbc_postgre.RegistrarClienteDAOPostgre;
import java.util.List;

/**
 *
 * @author USER
 */
public class RegistroClienteServicio {
    GestorJDBC gestorJDBC;
    RegistrarClienteDAOPostgre registroDAO;
      public RegistroClienteServicio(){
        gestorJDBC = new GestorJDBCPostgre();
        registroDAO = new RegistrarClienteDAOPostgre(gestorJDBC);
    }
    public int registrarCliente(Cliente cliente) throws Exception{
     gestorJDBC.abrirConexion();
     String codigoCliente = registroDAO.obtenerCodigoAnterior();
     cliente.setCodigocliente(codigoCliente);
     int resultado_registro = registroDAO.registrarCliente(cliente);
     gestorJDBC.cerrarConexion(); 
    return resultado_registro;
    }
    public int existeDocumentoidentidad(String numeroIdentidad) throws Exception{
      gestorJDBC.abrirConexion();
      int resultado_verificado = registroDAO.verificarNumeroDeIdentidad(numeroIdentidad);
     gestorJDBC.cerrarConexion(); 
    return resultado_verificado;
    }
    public List<TipoDocumento> listarTipoDocumento() throws Exception{
  gestorJDBC.abrirConexion();
    List tipoDocumento = registroDAO.listarTipoDocumento();
   gestorJDBC.cerrarConexion(); 
  return tipoDocumento;
}

}
