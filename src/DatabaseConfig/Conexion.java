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
            String url="jdbc:mysql://"+this.host+":"+this.port+"/"+this.database+"?useSSL=false";
            conexion = DriverManager.getConnection(url,this.user,this.password);
            if(conexion != null){
                System.out.println("conexion establecida");
            }
        }catch(SQLException e){
           System.out.println("error conexion verificar user and password: "+e);
        }
    }
    //problemas de conexion requiere identificar datbase
    public void getConextionPostgrest(){
         try{
            String url="jdbc:postgresql://"+this.host+":"+this.port+"/"+this.database;
            conexion = DriverManager.getConnection(url,this.user,this.password);
            if(conexion != null){
                System.out.println("conexion establecida");
            }
        }catch(SQLException e){
           System.out.println("error conexion verificar user and password: "+e);
        }
    }
    public void getConexionMariaDb(){
        try{
            String url="jdbc:mariadb://"+this.host+":"+this.port+"/"+this.database;
            conexion = DriverManager.getConnection(url,this.user,this.password);
            if(conexion != null){
                System.out.println("conexion establecida");
            }
        }catch(SQLException e){
           System.out.println("error conexion verificar user and password: "+e);
        }
    }
       public void getConexionSqlServer(){
        try{
            String url="jdbc:sqlserver://"+this.host+":"+this.port+";databaseName="+this.database;
            conexion = DriverManager.getConnection(url,this.user,this.password);
            if(conexion != null){
                System.out.println("conexion establecida");
            }
        }catch(SQLException e){
           System.out.println("error conexion verificar user and password: "+e);
        }
    }
    
}
