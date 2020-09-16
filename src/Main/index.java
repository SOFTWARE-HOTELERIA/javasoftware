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
        //define host,port,user,password,database
        Conexion conexion = new Conexion("localhost",5432,"postgres","gordita","postgres");
        conexion.getConexionpostgres();
        
    }
    
}
