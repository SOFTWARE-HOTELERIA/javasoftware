package DatabaseConfig;
import Interfaces.IConnection;
import Config.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josel
 */
public class Conexion implements IConnection{
    private final String host;
    private final String password;
    private final String user;
    private final String database;
     private final String type;
    private final int port;
    private String url="";
    Connection conexion=null;
    public Conexion(String h, String type,int por,String u,String pass,String db){
        this.host=h;
        this.type=type.toLowerCase();
        this.port=por;
        this.user=u;
        this.password=pass;
        this.database=db;
    }
    public void getDataConexion(){
        try{
            switch(this.type){
                case "mysql":
                    url="jdbc:"+this.type+"://"+this.host+":"+this.port+"/"+this.database+"?useSSL=false";
                    break;
                case "mariadb":
                case "postgresql":
                    url="jdbc:"+this.type+"://"+this.host+":"+this.port+"/"+this.database;
                    break;
                case "sqlserver":
                     url="jdbc:"+this.type+"://"+this.host+":"+this.port+";databaseName="+this.database;
                     break;
                default:
                    System.out.println(Color.RED + "Only Define mysql,mariadb,postgresql,sqlserver");
                    break;
            }
            conexion = DriverManager.getConnection(url,this.user,this.password);
            if(conexion != null){
                System.out.println(Color.GREEN + "Connection Database ==> "+ this.type);
                System.out.println(Color.PURPLE + "Connection port ==> "+ this.port);
                System.out.println("Conexion establecida");
            }
        }catch(SQLException e){
           System.out.println("Error de Conexion : "+e);
        }
    }

    //export connection object
    public Connection getConexion(){
       return conexion;
    }
}

    

    
    


