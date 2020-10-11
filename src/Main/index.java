/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Config.Conexion; //get package datbaseconfig

import controller.ControllerSuma;

/**
 *
 * @author josel
 */
public class index {
    public static ControllerSuma controller;
       public static Conexion conexion= new Conexion("localhost","sqlserver",1433,"bruno","bruno","CARNICERIA");
    public static void main(String[] args) {
    conexion.getDataConexion();
  
 
               

    }
 
}
