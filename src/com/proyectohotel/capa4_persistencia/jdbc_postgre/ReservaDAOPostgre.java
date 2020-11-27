/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa4_persistencia.jdbc_postgre;
import com.proyectohotel.capa3_dominio.entidades.Cliente;
import com.proyectohotel.capa3_dominio.entidades.Habitacion;
import com.proyectohotel.capa3_dominio.entidades.RegistroDeHabitacion;
import com.proyectohotel.capa4_persistencia.JDBC.GestorJDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
     /* 
       Author : Wilmer , Arreglado por :Jose
    */
     //solo listar
    public List<Habitacion> listarHabitaciones() throws SQLException{
        //posible implementacion aqui mismo de logica de 2 querys 
        ArrayList<Habitacion> registroHabitaciones = new ArrayList();
         ResultSet resultado_habitaciones;
        String sql ="select nhabitacion,estado,nivelId from habitacion";
         resultado_habitaciones = gestorJDBC.ejecutarConsulta(sql);
         System.out.println("persistencia here");
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
         for(int i=0;i<pres.length;i++){
                  res[i] = gestorJDBC.ejecutarConsulta(sqlquerys[i]);
                  if(res[i].next()){
                      data.put(output[i], res[i].getString(output[i]));
                  }
                  
        }
         return data;
         //{totalAcumuladoCOsto=10,totalPersonas=20}
   }
    
     
     
     //
     public int cerrarEstadiaCliente(String documentoIdentidad){
         // calcularCostoFinal()  => trabajar en capa aplicacion
         // totalDeDiasHospedado() ==> trabjar en capa aplicacion
         // establecerFechaDeReserva() ==>  trabjar en capa aplicacion
         
         //executeUpdate() => create , delete , update 
        return 0;
    }
  
    public RegistroDeHabitacion mostrarEstadiaCliente(String documentoIdentidad){
        return null;
    }
    /* 
       Author : Guillermo 
    */
    public int registrarHabitacion(RegistroDeHabitacion reservaHabitacion){
        return 0;
    }
}
