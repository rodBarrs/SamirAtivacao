package com.samirAtivacao.banco;

import java.sql.Connection;
import java.sql.DriverManager;
 
public class Prueba {
 
   public static void main ( String args[] ){
 
      Connection connection = null;
 
      try{
         connection = DriverManager.getConnection( "jdbc:sqlite:db.sqlitejava" );
         if ( connection != null ){
            System.out.println("Conexión exitosa!");
         }
      }
      catch ( Exception ex ) {
         System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
         System.out.println("Error en la conexión");
      }
   }
 
}
