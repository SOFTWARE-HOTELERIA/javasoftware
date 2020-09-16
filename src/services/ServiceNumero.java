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

   @Override
    public boolean suma(int a,int b) {
        //conn.conexion.getConexion(); problema here we need static attribute
            Numero num = new Numero(a,b);
      try {
           String sql = "INSERT INTO numero(a,b,suma) values(?,?,?)";
             PreparedStatement query = index.conexion.getConexion().prepareStatement(sql);
             query.setInt(1,a);
             query.setInt(2,b);
             query.setInt(3,num.Suma());
            int res = query.executeUpdate(); //get 1 or 0
            if(res > 0){
              System.out.println("correct ");
               return true;
           }else{
               return false;
           }
       } catch (SQLException ex) {
           System.out.println(Color.RED + "Problema " + ex);
       }
        return true;
    }
    
}
