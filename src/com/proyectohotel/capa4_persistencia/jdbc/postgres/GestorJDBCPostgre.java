/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectohotel.capa4_persistencia.jdbc.GestorJDBCPostgre;

import com.proyectohotel.capa4_persistencia.JDBC.GestorJDBC;
import java.awt.Color;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author josel
 */
public class GestorJDBCPostgre extends GestorJDBC{
    public void abrirConexion() throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/biblioteca";
            DriverManager.getConnection(url, "postgres", "postgres");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(Color.RED + "Problema de Conexion");
            throw new Exception("Error en la conexion con la base de datos, consulte con el administrador.");
        }
         
        
    }
}
