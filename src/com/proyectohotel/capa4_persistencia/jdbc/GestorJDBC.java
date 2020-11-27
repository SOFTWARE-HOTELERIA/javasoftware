/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa4_persistencia.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Bruno
 */
public abstract class GestorJDBC {
    protected Connection con;
    public abstract void abrirConexion() throws Exception;
    public void cerrarConexion() throws Exception{
        con.close();
    }    
    public void iniciarTransaccion() throws Exception{
        con.setAutoCommit(false);
    }
    public void terminarTransaccion() throws Exception{
        con.commit();
        con.setAutoCommit(true);
        con.close();
    }
    public void cancelarTransaccion() throws Exception{
        con.rollback();
        con.setAutoCommit(true);
        con.close();
    } 
    
    public PreparedStatement prepararSentencia(String sql) throws SQLException{
        return con.prepareStatement(sql);
    }
    
    public ResultSet ejecutarConsulta(String sql) throws SQLException{
        Statement sentencia;
        ResultSet resultado;
        sentencia = con.createStatement();
        resultado = sentencia.executeQuery(sql);
        return resultado;
    }
    public Connection conexionReport(){
        return con;
    }
}
