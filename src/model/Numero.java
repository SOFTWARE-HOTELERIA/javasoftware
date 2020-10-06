/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author josel
 */
import DAO.NumeroDao;

public class Numero {
    private int a;
    private int b;
    
    public Numero(int a,int b){
        this.a=a;
        this.b=b;
    }
    
    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
   
   
    //obtiene a la par dos funciones
    //una vez obtenido las dos funciones trabaja en etapas 
    
    //CREATE
    public boolean create(){
      try{
           return CRUD().insert(this);
      }catch(Exception ex){
          System.out.println(ex);
      }
      return false;
    }
    //READ
    //public void findId(){
     //   return CRUD()
    //}
    //UPDATE
    
    //DELETE
    
    public NumeroDao CRUD(){
        return new NumeroDao();
    }
 
}
