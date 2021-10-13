
package t1_r4_callablestatement;

import com.mysql.cj.jdbc.DatabaseMetaData;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Iván Zambrana Naranjo
 */
public class Ejercicio6 {

    public static void main(String[] args) {
            
            
        //Creamos escaner y declaramos variables para el menu
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        
        //Variables útiles para el menú
        
        PreparedStatement insercion = null;
        Statement exConsulta = null;
        ResultSet resultado = null;
        
        int option, row;
        
        try
        {
            
            //Pedimos pautas necesarias para la conexión a la base de datos
            System.out.println("inserta sgbd: ");
            String sgdb = sc.nextLine();
            System.out.println("inserta servidor: ");
            String server = sc.nextLine();
            System.out.println("inserta base de datos: ");
            String db = sc.nextLine();
            System.out.println("inserta usuario: ");
            String user = sc.nextLine();
            System.out.println("inserta contraseña: ");
            String passw = sc.nextLine();

           //Realizamos la conexión
           Connection conexion = null;
           Conectar con = new Conectar(sgdb, server, db, user, passw);
           conexion = con.getConnection();
           java.sql.DatabaseMetaData datos = conexion.getMetaData();
           
            //Creacion del menu
            while(!exit) {
                //Mostrar opciones
                System.out.println("\n\n\n----------------------------------------");
                System.out.println("------MENU SQL(CALLABLE STATEMENT)------");
                System.out.println("----------------------------------------");
                System.out.println("1. Ejecutar procedimiento matricula_alumno.");
                System.out.println("2. Ejecutar procedimiento fecha_comienzo.");
                System.out.println("3. Ejecutar funcion calificacion.");
                System.out.println("4. Salir.");


                //Captura opcion insertada por teclado
                System.out.println("Elija una opcion: ");
                option = sc.nextInt();

                //Manejo de opciones
                switch(option){
                    case 1:
                        System.out.println("-EJECUTAR PROCEDIMIENTO matricula_alumno-");
                        
                        System.out.println("------------------------------");
                        break;


                    case 2:
                        System.out.println("-EJECUTAR PROCEDIMIENTO fecha_comienzo-");
                       
                        System.out.println("------------------------------");
                        break;

                    case 3:
                        System.out.println("-EJECUTAR FUNCION calificacion-");
                        
                        

                        System.out.println("------------------------------");
                        break;

                    case 4:
                           
                        System.out.println("Saliendo...");
                        exit=true;
                        break;
                    default:
                        System.out.println("Elija un numero entre 1 y 4.");

                }
            }
        } catch (ClassNotFoundException | SQLException cnfsql) {cnfsql.printStackTrace();}
    }
    
}
