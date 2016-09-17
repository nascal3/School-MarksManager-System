package AppPackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author caleb Nasio
 */
import java.sql.*;
import javax.swing.*;
public class connect {
    
    Connection conn= null;
    
    public static Connection ConnecrDb() throws ClassNotFoundException{
        
     try{
     Class.forName("org.sqlite.JDBC");
     Connection conn = DriverManager.getConnection("jdbc:sqlite:MarksManager.db");
     return conn; 
     //"C:\Users\home\Documents\NetBeansProjects\MarksManagement\src\"
            
        }catch(SQLException e){
          JOptionPane.showMessageDialog(null, e);
          return null;
        }
        
    }   
    
}
