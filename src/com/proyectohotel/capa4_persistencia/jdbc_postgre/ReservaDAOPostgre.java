/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa4_persistencia.jdbc_postgre;
import com.proyectohotel.capa3_dominio.entidades.Cliente;
import com.proyectohotel.capa3_dominio.entidades.Habitacion;
import com.proyectohotel.capa3_dominio.entidades.RegistroDeHabitacion;
import com.proyectohotel.capa3_dominio.entidades.TipoDocumento;
import com.proyectohotel.capa3_dominio.entidades.TipoHabitacion;
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
     /* 
       Author : Marco , Arreglado por :Jose
    */
     
    public Cliente buscarCliente(String documentoIdentidad) throws SQLException{
         Cliente cliente = null;
        ResultSet resultado_cliente;
        String sentenciaSQL;
        sentenciaSQL ="SELECT c.clientecodigo,c.nombre,c.apellido,c.telefono,td.descripcion,c.correo,c.nacionalidad "
                + "FROM clientes c left JOIN tipo_documento td on c.documentoId =td.documentoId "
                + "where c.numeroIdentidad = '" + documentoIdentidad+"'";
        System.out.println(cliente);
        resultado_cliente =gestorJDBC.ejecutarConsulta(sentenciaSQL);
        if(resultado_cliente.next()){
        cliente = new Cliente();
        cliente.setCodigocliente(resultado_cliente.getString("clientecodigo"));
        cliente.setNombre(resultado_cliente.getString("nombre"));
        cliente.setApellido(resultado_cliente.getString("apellido"));
        cliente.setTelefono(resultado_cliente.getString("telefono"));
        TipoDocumento tipoDocumento = new TipoDocumento();
        tipoDocumento.setDescripcion(resultado_cliente.getString("descripcion"));
        cliente.setCorreo(resultado_cliente.getString("correo"));
        cliente.setNacionalidad(resultado_cliente.getString("nacionalidad"));
         cliente.setTipoDocumento(tipoDocumento);
     }
        resultado_cliente.close();
        return cliente;
    }
    //tomar el codigo anterior de los registros
    public String obtenerCodigoAnterior() throws SQLException{
    String codigoReal=null;
    String sql="select max(numeroreserva) as codigo from reservahabitacion";
    ResultSet resultado_verificar= gestorJDBC.ejecutarConsulta(sql);
    if(resultado_verificar.next()){
      String codigo=resultado_verificar.getString("codigo");
      long id=Long.parseLong(codigo.substring(3,codigo.length()));
      id++;
      codigoReal = "TZ-"+id; //TZ-2
    }
    return codigoReal;
}
    
    //REGISTRAR RESERVHABITACION
    public int registrarHabitacionCliente(RegistroDeHabitacion registroHabitacion) throws SQLException{
       String sql="insert into reservahabitacion(numeroreserva,clientecodigo,habitacionnum,fecha_entrada) "
               + "values(?,?,?,?)";
       PreparedStatement prestatement_registro=gestorJDBC.prepararSentencia(sql);
         prestatement_registro.setString(1,registroHabitacion.getNumeroReserva());
       prestatement_registro.setString(2,registroHabitacion.getCliente().getCodigocliente());
       prestatement_registro.setString(3,registroHabitacion.getHabitacion().getNumeroHabitacion());
       prestatement_registro.setDate(4,registroHabitacion.getFechaIngreso());
       int resultado=prestatement_registro.executeUpdate();
       if(resultado == 1){
           actualizarEstadoHabitacion(registroHabitacion.getHabitacion());
       }
       return resultado;
    }
    //READ RESERVAHABITACION CLIENTE
    public RegistroDeHabitacion readRegistroHabitacion(String codigoCliente) throws SQLException{
        RegistroDeHabitacion registroHabitacion=null;
        ResultSet resultado_registro;
        String sql="select * from estadiafinalizada where clientecodigo='"+codigoCliente+"'";
        resultado_registro = gestorJDBC.ejecutarConsulta(sql);
        while(resultado_registro.next()){
            registroHabitacion = new RegistroDeHabitacion();
            registroHabitacion.setFechaIngreso(resultado_registro.getDate("fecha_entrada"));
            Cliente cliente = new Cliente();
            cliente.setNombre(resultado_registro.getString("nombre"));
            cliente.setApellido(resultado_registro.getString("apellido"));
            Habitacion habitacion = new Habitacion();
            habitacion.setNumeroHabitacion(resultado_registro.getString("nhabitacion"));
            habitacion.setNumeroDePiso(resultado_registro.getInt("pisoid"));
            TipoHabitacion tipoHabitacion = new TipoHabitacion();
            tipoHabitacion.setDescripcion(resultado_registro.getString("descripcion"));
            tipoHabitacion.setCosto(resultado_registro.getDouble("costo"));
            habitacion.setTipoHabitacion(tipoHabitacion);
            registroHabitacion.setCliente(cliente);
            registroHabitacion.setHabitacion(habitacion);
        }
        resultado_registro.close();
        return registroHabitacion;
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
               sentenciaListado  ="select h.nhabitacion as nhabitacion,estado,h.nivelId from habitacion as h " 
                  + "inner join tipo_habitacion as t on h.tipohabitacionid=t.nhabitacion "
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
     public Map mostrarTotalDeHabitacionesDeEstado(String tipoHabitacion) throws SQLException{
        PreparedStatement q1 = null;
        PreparedStatement q2 = null;
       ResultSet re1=null,re2=null;
       Map data = new HashMap();
         String ocupadas="";
        String disponibles="";
        String costo="";
       if(tipoHabitacion==null){
              disponibles= "select count(*) as disponibles from habitacion h " +
                "inner join tipo_habitacion t on h.tipohabitacionid=t.nhabitacion " +
                "where estado='DISPONIBLE'";
               ocupadas= "select count(*) as ocupadas from habitacion h " +
                "inner join tipo_habitacion t on h.tipohabitacionid=t.nhabitacion " +
                "where estado='OCUPADO'";
               
       }else{
            disponibles= "select count(*) as disponibles from habitacion h " +
                "inner join tipo_habitacion t on h.tipohabitacionid=t.nhabitacion " +
                "where estado='DISPONIBLE' and descripcion='"+tipoHabitacion+"'";
            ocupadas= "select count(*) as ocupadas from habitacion h " +
                "inner join tipo_habitacion t on h.tipohabitacionid=t.nhabitacion " +
                "where estado='OCUPADO' and descripcion='"+tipoHabitacion+"'";
       }
         String [] output = {"disponibles","ocupadas"};
          PreparedStatement[] pres ={q1,q2};
          ResultSet [] res = {re1,re2};
        String [] sqlquerys={disponibles,ocupadas};
         for(int i=0;i<output.length;i++){
                  res[i] = gestorJDBC.ejecutarConsulta(sqlquerys[i]);
                  if(res[i].next()){
                      data.put(output[i], res[i].getString(output[i]));
                  }
        }
         return data;
         //{totalAcumuladoCOsto=10,totalPersonas=20}
   }
     public double costoTipoDeHabitacion(String tipoDeHabitacion) throws SQLException{
         ResultSet resultado_tipo;
         double costoHabitacion=0;
         String sql="SELECT costo from tipo_habitacion where descripcion='"+tipoDeHabitacion+"'";
         resultado_tipo = gestorJDBC.ejecutarConsulta(sql);
         if(resultado_tipo.next()){
             costoHabitacion = resultado_tipo.getDouble("costo");
         }
         return costoHabitacion;
     }
     public int actualizarEstadoHabitacion(Habitacion habitacion) throws SQLException{
         String sql="UPDATE HABITACION SET estado=? WHERE nhabitacion=?";
         PreparedStatement prestatement = gestorJDBC.prepararSentencia(sql);
          prestatement.setString(1,habitacion.getEstado());
          prestatement.setString(2,habitacion.getNumeroHabitacion());
          return prestatement.executeUpdate();
     }

  
}
