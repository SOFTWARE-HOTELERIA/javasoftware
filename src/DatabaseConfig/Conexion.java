package DatabaseConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author josel
 */
public class Conexion {
    String host;
    String password;
    String user;
    String database;
    int port;
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
            String url="jdbc:mysql://"+this.host+":"+this.port;
            conexion = DriverManager.getConnection(url,this.user,this.password);
            if(conexion != null){
                System.out.println("conexion establecida");
            }
        }catch(SQLException e){
           System.out.println("error conexion verificar user and password: "+e);
        }
    }
    
    
    
}
