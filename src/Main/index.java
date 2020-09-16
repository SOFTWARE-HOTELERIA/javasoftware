/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import DatabaseConfig.Conexion;

/**
 *
 * @author josel
 */
public class index {
    public static void main(String[] args) {

        Conexion conexion = new Conexion("localhost", 3306, "root", "marco","mariadb");
       conexion.getMariadB();
 
        
        

    }
    
}
