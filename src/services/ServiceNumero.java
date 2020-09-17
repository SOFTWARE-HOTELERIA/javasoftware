/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Config.Color;
import Interfaces.INumero;
import model.Numero;
import Main.index;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author josel
 */


public class ServiceNumero implements INumero{

    public boolean suma(Numero num) {
      
        try {
           String sql = "INSERT INTO numero(a,b,suma) values(?,?,?)";
             PreparedStatement query = index.conexion.getConexion().prepareStatement(sql);
             query.setInt(1,num.getA());
             query.setInt(2,num.getB());
             query.setInt(3,num.Suma());
             int res = query.executeUpdate(); //get 1 or 0
            return res > 0;
       } catch (SQLException ex) {
           System.out.println(Color.RED + "Problema " + ex);
       }
        return true;
    }
    
}
