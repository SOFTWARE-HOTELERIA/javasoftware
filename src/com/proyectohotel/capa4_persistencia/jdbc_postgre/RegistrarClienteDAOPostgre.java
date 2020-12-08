/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa4_persistencia.jdbc_postgre;

import com.proyectohotel.capa3_dominio.entidades.Cliente;
import com.proyectohotel.capa3_dominio.entidades.TipoDocumento;
import com.proyectohotel.capa4_persistencia.JDBC.GestorJDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class RegistrarClienteDAOPostgre {
    GestorJDBC gestorJDBC;

    public RegistrarClienteDAOPostgre(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }
    
    public int registrarCliente(Cliente cliente) throws SQLException{
 String sql="INSERT INTO CLIENTES(clientecodigo,nombre,apellido,correo,telefono,documentoid,numeroIdentidad,nacionalidad) "
         + "values(?,?,?,?,?,?,?,?)";
  PreparedStatement prestatement = gestorJDBC.prepararSentencia(sql);
  prestatement.setString(1,cliente.getCodigocliente());
  prestatement.setString(2,cliente.getNombre());
  prestatement.setString(3,cliente.getApellido());
  prestatement.setString(4,cliente.getCorreo()); 
  prestatement.setString(5,cliente.getTelefono());
  prestatement.setInt(6,cliente.getTipoDocumento().getDocumentoId());
  prestatement.setString(7,cliente.getNumeroIdentidad());
  prestatement.setString(8,cliente.getNacionalidad());
  return prestatement.executeUpdate();
}


public int verificarNumeroDeIdentidad(String numeroIdentidad) throws SQLException{
 int verificar=0;
 String sql="SELECT COUNT(*) as existe from clientes where numeroidentidad='"+numeroIdentidad+"'";
 ResultSet resultado_verificar= gestorJDBC.ejecutarConsulta(sql);
 if(resultado_verificar.next()){
   verificar=resultado_verificar.getInt("existe");
 }
 return verificar;
}
public String obtenerCodigoAnterior() throws SQLException{
    String codigoReal=null;
    String sql="select max(clientecodigo) as codigo from clientes";
    ResultSet resultado_verificar= gestorJDBC.ejecutarConsulta(sql);
    if(resultado_verificar.next()){
      String codigo=resultado_verificar.getString("codigo");
      long id=Long.parseLong(codigo.substring(3,codigo.length()));
      id++;
      codigoReal = "PO0"+id; //PO7
    }
    return codigoReal;
}


public List<TipoDocumento> listarTipoDocumento() throws SQLException{
    ArrayList<TipoDocumento> listaTipoDocumento = new ArrayList();
  ResultSet resultado_tipoDocumento;  
  String sql="SELECT * FROM tipo_documento";
  resultado_tipoDocumento = gestorJDBC.ejecutarConsulta(sql);
  while(resultado_tipoDocumento.next()){
     TipoDocumento tipoDocumento = new TipoDocumento();
     tipoDocumento.setDescripcion(resultado_tipoDocumento.getString("descripcion"));
     tipoDocumento.setDocumentoId(resultado_tipoDocumento.getInt("documentoid"));  
     listaTipoDocumento.add(tipoDocumento);
  }
  return listaTipoDocumento;
    }
}
