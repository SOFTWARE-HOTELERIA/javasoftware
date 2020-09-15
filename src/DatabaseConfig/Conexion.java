package DatabaseConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
    public Connection getConexion(){
        try{
            System.out.println(Color.GREEN + "Connection Database ==> "+ this.type);
            System.out.println(Color.PURPLE + "Port Connection ==> "+ this.port);
            //System.out.println("Conexion On ==> " + this.type+"\n Execute on Port ==>"+this.port);
            switch(this.type){
                case "mysql":
                    url="jdbc:"+this.type+"://"+this.host+":"+this.port+"/"+this.database+"?useSSL=false";
                    break;
                case "mariadb":
                    url="jdbc:"+this.type+"://"+this.host+":"+this.port+"/"+this.database;
                    break;
                case "postgres":
                    url="jdbc:"+this.type+"://"+this.host+":"+this.port+"/"+this.database;
                    break;
                case "sqlserver":
                     url="jdbc:"+this.type+"://"+this.host+":"+this.port+";databaseName="+this.database;
                     break;
                default:
                    System.out.println("Only Define mysql,mariadb,postgres,sqlserver");
                    break;
            }
            conexion = DriverManager.getConnection(url,this.user,this.password);
            if(conexion != null){
                System.out.println("conexion establecida");
                return conexion;
            }
        }catch(SQLException e){
           System.out.println("Error de Conexion : "+e);
        }
        return null;
    }
    
}
