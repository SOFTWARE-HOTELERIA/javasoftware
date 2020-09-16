/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import DatabaseConfig.Conexion; //get package datbaseconfig
import model.Numero;
import view.VistaSuma;
import controller.ControllerSuma;
import java.sql.Connection;
/**
 *
 * @author josel
 */
public class index {
    public static ControllerSuma controller;
    public static VistaSuma tb = new VistaSuma(); //null main index require instance
    public static Conexion conexion= new Conexion("localhost","mysql",3306,"root","root","java");
    public static void main(String[] args) {
        controller = new ControllerSuma(tb);
        tb.setVisible(true);
    }
    
}
