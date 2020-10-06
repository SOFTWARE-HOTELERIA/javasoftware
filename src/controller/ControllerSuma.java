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
import DAO.NumeroDao;
import presentancion.VistaSuma;

/**
 *
 * @author josel
 */
public class ControllerSuma implements ActionListener{
    
   private final VistaSuma view;
   private Numero num;
   private final NumeroDao servicenum=new NumeroDao();
   public static String mensaje;
    public ControllerSuma(VistaSuma view) {
        this.view = view;
        VistaSuma.Sumar.addActionListener(this);
    }
    public void showMessage(String mensaje){
         VistaSuma.txtMensaje.setText(mensaje);
    }
   @Override
     public void actionPerformed(ActionEvent e) {
        if(VistaSuma.Sumar == e.getSource()){
     
              if(VistaSuma.txt1.getText().equals("") || view.txt2.getText().equals("")){
                 VistaSuma.txtMensaje.setText("falta completar");
             }else{
                  try {
                    int num1 = Integer.parseInt(this.view.txt1.getText());
                    int num2 = Integer.parseInt(this.view.txt2.getText());
                    num = new Numero(num1,num2);
                    boolean res = num.create(); //DEFINE INT
                    if(res){
                        showMessage("Nice");
                    }else{
                        showMessage("Ingresar enteros");
                    }
                  }catch (NumberFormatException ef) {
                     showMessage("Ingresar enteros");
                }

              }
         }  
         
     }
  
}
