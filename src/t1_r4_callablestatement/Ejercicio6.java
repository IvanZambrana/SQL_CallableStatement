
package t1_r4_callablestatement;


import com.mysql.cj.jdbc.CallableStatement;
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
        int codAlumno, codCurso, numCursos;
        String sql, mesCurso, calification;
        PreparedStatement insercion = null;
        Statement exConsulta = null;
        ResultSet resultado = null;
        CallableStatement llamada = null;
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
           //java.sql.DatabaseMetaData datos = conexion.getMetaData();
           
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
                        //matricula_alumno
                        System.out.println("-EJECUTAR PROCEDIMIENTO matricula_alumno-");
                        System.out.println("Inserte codigo de alumno: ");
                        codAlumno = sc.nextInt();
                        
                        sql = "{ call matricula_alumno(?, ?) }";
                        llamada = (CallableStatement) conexion.prepareCall(sql);
                        llamada.registerOutParameter(2, java.sql.Types.INTEGER);
                        llamada.setInt(1, codAlumno);
                        
                        llamada.execute();
                        numCursos = llamada.getInt(2);
                        
                        if(numCursos != 0)
                            System.out.println("Numero de asignaturas que cursa: " + numCursos);
                        else
                            System.out.println("El alumno no cursa ninguna asignatura.");
                        System.out.println("------------------------------");
                        break;


                    case 2:
                        System.out.println("-EJECUTAR PROCEDIMIENTO fecha_comienzo-");
                       System.out.println("Inserte codigo del curso: ");
                        codCurso = sc.nextInt();
                        
                        sql = "{ call fecha_comienzo(?, ?) }";
                        llamada = (CallableStatement) conexion.prepareCall(sql);
                        llamada.registerOutParameter(2, java.sql.Types.VARCHAR);
                        llamada.setInt(1, codCurso);
                        
                        llamada.execute();
                        mesCurso = llamada.getString(2);
                        
                        if(mesCurso != null)
                            System.out.println("El curso comienza en " + mesCurso);
                        else
                            System.out.println("El curso seleccionado no tiene fecha de comienzo.");
                        System.out.println("------------------------------");
                        break;

                    case 3:
                        System.out.println("-EJECUTAR FUNCION calificacion-");
                        System.out.println("Inserte codigo del curso: ");
                        codCurso = sc.nextInt();
                        System.out.println("Inserte codigo de alumno: ");
                        codAlumno = sc.nextInt();
                        
                        sql = "{? = call calificacion(?, ?)}";
                        llamada = (CallableStatement) conexion.prepareCall(sql);
                        //llamada.registerOutParameter(1, java.sql.Types.VARCHAR);
                        llamada.setInt(2, codCurso);
                        llamada.setInt(3, codAlumno);
                        llamada.registerOutParameter(1, java.sql.Types.VARCHAR);
                        llamada.execute();
                        calification = llamada.getString(1);
                        
                        System.out.println(calification);
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
