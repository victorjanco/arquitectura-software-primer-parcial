/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author victor janco
 */
public class Conexion {
    public static Connection connection;
    public static Statement statement;

    public static boolean conectar() {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            connection =DriverManager.getConnection("jdbc:mysql://127.0.0.1/campeonato", "root", "");
            statement = connection.createStatement();
            return true;
        } catch ( ClassNotFoundException e ) {
            System.err.println( "driver no encontrado" + e.getMessage() );
            return false;
        } catch ( SQLException e ) {
            System.err.println( "falla en la base de datos : " + e.getMessage() );
            return false;
        }
    }    
    
    public static boolean desconectar() {
        try {
            if ( connection != null ) {
                //System.out.println( "desconectado..." );
                if ( statement != null )
                    statement.close();
                connection.close();
            }
            return true;
        } catch ( SQLException e ) {
            System.err.println( "error al desconectar..." + e.getMessage() );
            return false;
        }
    }
    /*
     excuteQuery
     Este método permite ejecutar una consulta SELECT.
     Este tipo de consultas devuelven una tabla, que en Java se representa con
     objetos de clase ResultSet
     */
    public static ResultSet consultaSelect( String sql ) {
        try {
            statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch ( SQLException e ) {
            System.out.println( "error al ejecutar la consulta select..." + e.getMessage() + " "+e.getErrorCode());
            return null;
        }
    }
    
    /*EJECUTA UNA CONSULTA DE INSERCIONE EN LA BASE DE DATOS
     INSERT INTO VALUES */
    
    public int insertar(String sqlinsert){
        try {
           return statement.executeUpdate(sqlinsert); 
        }
        catch (Exception e){
           JOptionPane.showMessageDialog(null, e.getMessage(),"Insertar", JOptionPane.ERROR_MESSAGE); 
        }
        return 0;
    }
    /**
     * ejecuta una consulta de actualizacion en la base de datos
     * @param sqlupdate  es una consulta de actualizacion "update tabla set..."
     * @return el numero de registros que ha actualizado.
     */

   public int actualizar(String sqlupdate){
        try{
            return statement.executeUpdate(sqlupdate);
          
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Actualizar Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
        return 0; 
    }
      /**
     * ejecuta una consulta de eliminacion en la base de datos
     * @param sqldelete es una consulta de eliminacion "delete from tabla..."
     * @return el numero de registros que ha eliminado.
     */

    public int eliminar (String sqldelete){
        try {
            return statement.executeUpdate(sqldelete);
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Eliminar", JOptionPane.ERROR_MESSAGE);
        }
               return 0; 
    }
    
    
}
