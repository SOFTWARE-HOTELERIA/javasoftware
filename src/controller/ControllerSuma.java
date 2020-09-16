/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DatabaseConfig.Conexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Numero;
import services.ServiceNumero;
import view.VistaSuma;

/**
 *
 * @author josel
 */
public class ControllerSuma implements ActionListener{
    
   private VistaSuma view;
   private Numero num;
   private ServiceNumero servicenum=new ServiceNumero();
   public static String mensaje;
    public ControllerSuma(VistaSuma view) {
        this.view = view;
        this.view.Sumar.addActionListener(this);
    }
       public void showMessage(String mensaje){
         this.view.txtMensaje.setText(mensaje);
    }
     public void actionPerformed(ActionEvent e) {
         if(VistaSuma.Sumar == e.getSource()){
              if(view.txt1.getText().equals("") || view.txt2.getText().equals("")){
                 this.view.txtMensaje.setText("falta completar");
             }else{
                    int num1 = Integer.parseInt(view.txt1.getText());
                    int num2 = Integer.parseInt(view.txt2.getText());
                    boolean output = servicenum.suma(num1,num2);
                    if(output){
                        showMessage("Registrado Correctamente");
                    }else{
                           showMessage("Problema al registrar");
                    }
              }
         }
     }
  
}
