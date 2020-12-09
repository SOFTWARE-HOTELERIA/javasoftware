# JAVA - DESAROLLO DE UN SOFTWARE DE REGISTROS DEL HOTEL PEPITO


## CONTENIDO

### Configuracion del proyecto

Para instalar el proyecto , clone el repositorio

# good practices : 
https://www.codejava.net/coding/10-java-core-best-practices-every-java-programmer-should-know
# Documentacion del proyecto :
https://readthedocs.org/projects/hoteleria/
# Nombrar funciones o variables 
 - variable => firstName , nombre,codigo ,edad
 - funciones => isStudent , addPerson
 - clases => Habitacion , TipoHabitacion
 
 # Configuracion del software a la base de datos POSTGRES
  ```
    public void abrirConexion() throws Exception {
           try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/DATABASE";
            con = DriverManager.getConnection(url, "USUARIO", "PASSWORD");
        } catch (ClassNotFoundException | SQLException e) {
            throw new Exception("Error en la conexion con la base de datos, consulte con el administrador." + e);
        }
    }
  ```
 # Inicializacion de JasperReport:
  ruta de las librerias y plugins : https://drive.google.com/drive/folders/1Ef_LmvxmMfa5KUynqTor0GH-OW3m4z9q
  * 1)Descargar los jar de la carpeta "LIBRERIA" y "PLUGINS" e implementarlos
  * 2)Configurar la ruta de tu proyecto , dicha ruta esta almacenado el proyecto "javasoftware"
   ```
   public class JReport{
       public final String pathRelative="C:\\Users\\USER\\Desktop\\practica";
   }
 
```
  * 3)Ejecutar el programa y probar.



