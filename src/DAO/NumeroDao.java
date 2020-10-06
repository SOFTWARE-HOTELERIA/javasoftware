/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Config.Color;
import DatabaseConfig.Conexion;
import model.Numero;
import Main.index;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Interfaces.INumeroDao;
/**
 *
 * @author josel
 */
//https://junit.org/junit4/javadoc/4.12/org/junit/Assert.html

public class NumeroDao implements INumeroDao{
//    private final String INSERT = "INSERT INTO numero(a,b) values(?,?)";
    //solo reuso esta funcion ... el modelo se encarga
    @Override
    public boolean insert(Numero num) {
        Conexion conexion= new Conexion("localhost","mysql",3306,"root","root","java");
        try {
             PreparedStatement query = conexion.getConexion().prepareStatement("INSERT INTO numero(a,b) values(?,?)");
             query.setInt(1,num.getA());
             query.setInt(2,num.getB());
             int res = query.executeUpdate(); 
             return (0 < res);
       } catch (SQLException ex) {
           System.out.println("Problema " + ex);
       }
        return false;
    }

  
    
}
