/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa4_persistencia.jdbc_postgre;
import com.proyectohotel.capa3_dominio.entidades.Cliente;
import com.proyectohotel.capa3_dominio.entidades.Habitacion;
import com.proyectohotel.capa3_dominio.entidades.RegistroDeHabitacion;
import com.proyectohotel.capa3_dominio.entidades.TipoHabitacion;

import com.proyectohotel.capa4_persistencia.JDBC.GestorJDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Date;
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
     /* 
       Author : Marco , Arreglado por :Jose
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
    public List<TipoHabitacion> listarTipoHabitaciones() throws SQLException{
         ArrayList<TipoHabitacion> registroHabitaciones = new ArrayList();
         ResultSet resultado_tipohabitacion;
         String sql="SELECT * FROM TIPO_HABITACION";
         resultado_tipohabitacion = gestorJDBC.ejecutarConsulta(sql);
         while(resultado_tipohabitacion.next()){
             TipoHabitacion tipoHabitacion = new TipoHabitacion();
            tipoHabitacion.setNumHabitacion(resultado_tipohabitacion.getString("nhabitacion"));
            tipoHabitacion.setDescripcion(resultado_tipohabitacion.getString("descripcion"));
            tipoHabitacion.setCosto(resultado_tipohabitacion.getDouble("costo"));
            registroHabitaciones.add(tipoHabitacion);
         }
         resultado_tipohabitacion.close();
         return registroHabitaciones;
    }
     /* 
       Author : Wilmer , Arreglado por :Jose
    */
     //solo listar
    public List<Habitacion> listarHabitaciones(String tipoHabitacion) throws SQLException{
        //posible implementacion aqui mismo de logica de 2 querys 
        ArrayList<Habitacion> registroHabitaciones = new ArrayList();
         ResultSet resultado_habitaciones;
         String sentenciaListado="";
         if(tipoHabitacion == null){
           sentenciaListado ="select nhabitacion,estado,nivelId from habitacion";
         }else{
               sentenciaListado  ="select h.nhabitacion,estado,h.nivelId from habitacion h " 
                  + "inner join tipo_habitacion t on h.tipohabitacionid=t.nhabitacion "
                  + "where descripcion='"+tipoHabitacion+"'";
         }
         resultado_habitaciones = gestorJDBC.ejecutarConsulta(sentenciaListado);
        while(resultado_habitaciones.next()){
            Habitacion habitacion = new Habitacion();
            habitacion.setNumeroHabitacion(resultado_habitaciones.getString("nhabitacion"));
            habitacion.setEstado(resultado_habitaciones.getString("estado"));
            habitacion.setNumeroDePiso(resultado_habitaciones.getInt("nivelId"));
            registroHabitaciones.add(habitacion);
        }
        resultado_habitaciones.close();
        return registroHabitaciones;
    }
     /* 
       Author : Wilmer ,Arreglado por :Jose
    */
     public Map mostrarTotalDeHabitacionesDeEstado() throws SQLException{
        PreparedStatement q1 = null;
        PreparedStatement q2 = null;
        ResultSet re1=null;
        ResultSet re2=null;
       Map data = new HashMap();
        String ocupadas= "select count(*) as ocupadas from habitacion where estado='OCUPADO'";
        String disponibles = "select count(*) as disponibles from habitacion where estado='DISPONIBLE'";
         String [] output = {"ocupadas","disponibles"};
          PreparedStatement[] pres ={q1,q2};
          ResultSet [] res = {re1,re2};
        String [] sqlquerys={ocupadas,disponibles};
         for(int i=0;i<output.length;i++){
                  res[i] = gestorJDBC.ejecutarConsulta(sqlquerys[i]);
                  if(res[i].next()){
                      data.put(output[i], res[i].getString(output[i]));
                  }
                  
        }
         return data;
         //{totalAcumuladoCOsto=10,totalPersonas=20}
   }
     //
      /* 
       Author : Jose
    */
     public int actualizarEstadiaCliente(RegistroDeHabitacion registroHabitacion,int dias,double costo_final) throws SQLException{ 
         String sql="update reservahabitacion set costo_final=?,dias=?,fecha_salida=? where clientecodigo=?";
         PreparedStatement sentencia = gestorJDBC.prepararSentencia(sql);
         sentencia.setDouble(1,costo_final);
         sentencia.setInt(2,dias);
         sentencia.setDate(3,registroHabitacion.getFechaSalida());
         sentencia.setString(4,registroHabitacion.getCliente().getCodigocliente());
         int resultado = sentencia.executeUpdate();
         if(resultado == 1){
             actualizarEstadoHabitacion(registroHabitacion.getHabitacion());
         }
         return resultado;
    }
       /* 
       Author por :Jose
    */
      public String cerrarEstadiaCliente(String documentoIdentidad) throws SQLException{
          String sql="select dias,fecha_entrada,fecha_salida,"
                  + "costo,costo_final,descripcion,nombre,apellido,pisoId,numeroidentidad "
                  + "from estadiafinalizada where numeroidentidad='"+documentoIdentidad+"'";
          return sql;
      }
     
      /* 
       Author por :Jose
    */
     public int actualizarEstadoHabitacion(Habitacion habitacion) throws SQLException{
         String sql="UPDATE HABITACION SET estado=? WHERE nhabitacion=?";
         PreparedStatement prestatement = gestorJDBC.prepararSentencia(sql);
          prestatement.setString(1,habitacion.getEstado());
          prestatement.setString(2,habitacion.getNumeroHabitacion());
          return prestatement.executeUpdate();
     }

     
     
    public RegistroDeHabitacion listadoDeEstadia(int documentoIdentidad) throws SQLException{
        RegistroDeHabitacion registroHabitacion=null; 
        ResultSet resultado_habitaciones;
        String sentenciaEstadia="select * from estadiaFinalizada where numeroIdentidad='"+documentoIdentidad+"'";
        resultado_habitaciones = gestorJDBC.ejecutarConsulta(sentenciaEstadia);
          while(resultado_habitaciones.next()){
              TipoHabitacion tipoHabitacion = new TipoHabitacion();
              tipoHabitacion.setDescripcion(resultado_habitaciones.getString("descripcion"));
              tipoHabitacion.setCosto(resultado_habitaciones.getDouble("costo"));
              Habitacion habitacion = new Habitacion();
              habitacion.setNumeroHabitacion(resultado_habitaciones.getString("nhabitacion"));
              habitacion.setNumeroDePiso(resultado_habitaciones.getInt("pisoid")); 
              habitacion.setNumeroHabitacion(resultado_habitaciones.getString("nhabitacion"));
              habitacion.setTipoHabitacion(tipoHabitacion);
              Cliente cliente = new Cliente();
              cliente.setCodigocliente(resultado_habitaciones.getString("clientecodigo"));
              cliente.setNombre(resultado_habitaciones.getString("nombre"));
              cliente.setApellido(resultado_habitaciones.getString("apellido"));
              cliente.setNumeroIdentidad(resultado_habitaciones.getString("numeroidentidad"));
              registroHabitacion= new RegistroDeHabitacion();
              registroHabitacion.setFechaIngreso(resultado_habitaciones.getDate("fecha_entrada"));
              registroHabitacion.setCliente(cliente);
              registroHabitacion.setHabitacion(habitacion);
          }
         resultado_habitaciones.close();
        return registroHabitacion;
    }
    /* 
       Author : Guillermo 
    */
    public int registrarHabitacion(RegistroDeHabitacion reservaHabitacion){
        return 0;
    }
}
