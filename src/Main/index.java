/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import DatabaseConfig.Conexion; //get package datbaseconfig
import presentancion.VistaSuma;
import controller.ControllerSuma;

/**
 *
 * @author josel
 */
public class index {
    public static ControllerSuma controller;
    public static VistaSuma tb = new VistaSuma(); //null main index require instance
    public static Conexion conexion= new Conexion("localhost","sqlserver",1433,"bruno","bruno","CARNICERIA");
    public static void main(String[] args) {
    conexion.getDataConexion();
    controller = new ControllerSuma(tb);
    tb.setVisible(true);


 
        
        

    }
 
}
