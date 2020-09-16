package DatabaseConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author josel
 */
public class Conexion {
    private final String host;
    private final String password;
    private final String user;
    private final String database;
    private final int port;
    Connection conexion=null;
    public Conexion(String h, int por,String u,String pass,String db){
        this.host=h;
        this.port=por;
        this.user=u;
        this.password=pass;
        this.database=db;
    }
    public void getConexionMysql(){
        try{
            String url="jdbc:mysql://"+this.host+":"+this.port+"?useSSL=false";
            conexion = DriverManager.getConnection(url,this.user,this.password);
            if(conexion != null){
                System.out.println("conexion establecida");
            }
        }catch(SQLException e){
           System.out.println("error conexion verificar user and password: "+e);
        }
    }


     public void getConexionpostgres(){
     
            String url="jdbc:postgresql://"+this.host+":"+this.port+"/"+this.database;
            try {
                conexion = DriverManager.getConnection(url,this.user,this.password);
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(conexion != null){
                System.out.println("conexion establecida");
            }
        }
    public void getMariadB() {
           try{
            String url="jdbc:mariadb://"+this.host+":"+this.port+"/"+this.database;
            conexion = DriverManager.getConnection(url,this.user,this.password);
            if(conexion != null){
                System.out.println("Conexion Exitosa");
            }
        }catch(SQLException e){
           System.out.println("error conexion verificar user and password: "+e);
        }

     }
   

    }

    
    

