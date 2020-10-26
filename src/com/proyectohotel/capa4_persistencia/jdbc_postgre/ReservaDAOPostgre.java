/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa4_persistencia.jdbc_postgre;
import com.proyectohotel.capa3dominio.Cliente;
import com.proyectohotel.capa3dominio.ReservaHabitacion;
import com.proyectohotel.capa4_persistencia.JDBC.GestorJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author josel
 */
public class ReservaDAOPostgre {
      GestorJDBC gestorJDBC;
    public ReservaDAOPostgre(GestorJDBC gestorJDBC){
        this.gestorJDBC=gestorJDBC;
    }
       //ingresar logica en cada funcion con comentarios..
        //Recordar por procesos
    //NOTAS:
    //1.QUERYS DEFINIDAS EN EL PAQUETE => CONFIG/CONSULTAS.SQL
    //2.USAR ESAS QUERYS EN SU GESTOR DE BASE DE DATOS PORGEST PRIMERAMENTE
    //3.ADAPTAR ESA QUERY A CADA FUNCION CORRESPONDIENTE
     /* 
       Author : Marco
    */
    public Cliente buscarCliente(String documentoIdentidad) throws SQLException{
         Cliente cliente = null;
        ResultSet resultado_cliente;
        String sentenciaSQL;
        sentenciaSQL ="SELECT c.nombre,c.apellido,c.telefono,td.descripcion,c.correo ,c.nacionalidad "
                + "FROM clientes c left JOIN tipo_documento td on c.documentoId =td.documentoId "
                + "where c.numeroIdentidad = '" + documentoIdentidad+"'";
        resultado_cliente =gestorJDBC.ejecutarConsulta(sentenciaSQL);
        if(resultado_cliente.next()){
        cliente = new Cliente();
        cliente.setNombre(resultado_cliente.getString("nombre"));
        cliente.setApellido(resultado_cliente.getString("apellido"));
        cliente.setTelefono(resultado_cliente.getString("telefono"));
        cliente.setTipoDocumento(resultado_cliente.getString("Descripcion"));
        cliente.setCorreo(resultado_cliente.getString("correo"));
        cliente.setNacionalidad(resultado_cliente.getString("nacionalidad"));
         }
        resultado_cliente.close();
        return cliente;
    }
     /* 
       Author : Wilmer
    */
    public List<ReservaHabitacion> buscarTipoHabitacion(){
        return null;
    }
     /* 
       Author : Guillermo 
    */
    public ReservaHabitacion cerrarEstadiaCliente(String documentoIdentidad){
        return null;
    }
    /* 
       Author : Aldo 
    */
    public int registrarHabitacion(ReservaHabitacion reservaHabitacion){
        return 0;
    }
}
