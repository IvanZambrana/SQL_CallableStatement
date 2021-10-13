
package t1_r4_callablestatement;

import java.sql.*;

/**
 *
 * @author Iván Zambrana Naranjo
 */
public class Conectar {
   
    //Declaracion de varialbels para la conexion
    private String url;
    private Connection conexion;
    
    //Carga del driver
    public Conectar(String sgdb, String server, String db, String user, String passw) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        if(sgdb.equalsIgnoreCase("mysql"))
        {
            url = "jdbc:" + sgdb + "://" + server + "/" + db + "?serverTimezone=UTC";
            this.conexion = DriverManager.getConnection(url, user, passw);
            System.out.println("La conexion con mysql ha sido un exito");
        }
        else if(sgdb.equalsIgnoreCase("oracle"))
        {
            url = "jdbc:" + sgdb + "://" + server + "/" + db + "?serverTimezone=UTC";
            this.conexion = DriverManager.getConnection(url, user, passw);
            System.out.println("La conexion con oracle ha sido un exito");
        }
        else    
        {
            System.out.println("-ERROR-Ningun SGBD elejido");
            System.out.println("Saliendo del programa...");
            System.exit(0);
        }
    }

    public Connection getConnection() 
    {
        return conexion;
    }
            
}
