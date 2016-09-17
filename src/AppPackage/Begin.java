package AppPackage;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
//import javax.swing.UIManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author home
 */
public class Begin extends javax.swing.JFrame {
                  
    public static void main(String[] args) throws InterruptedException{  
        
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Windows".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//                 //UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
//            }
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
//            JOptionPane.showMessageDialog(null, ex);
//        }
        
         try
        {
          UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel");
         }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
        {
          JOptionPane.showMessageDialog(null, e);
        }
        //</editor-fold>
        
        Connection conn=null;
        ResultSet rs = null;
        PreparedStatement pst = null; 
        
        try{
            
            //check is admin user exists event 
           String logit = "select count(*) from users where subject= 'Admin' "; 
           conn=connect.ConnecrDb(); 
           pst=conn.prepareStatement(logit);
                      
           rs=pst.executeQuery();
            if(rs.next()){
                Thread.sleep(5000);
                String count = rs.getString("count(*)");
                int Count = Integer.parseInt(count);
                if(Count>0){
                   new login().setVisible(true);
                    
                    
                } else{
                    new addAdmin().setVisible(true); 
                }                  
                         
            }
           
       }catch(ClassNotFoundException | SQLException ex){
          JOptionPane.showMessageDialog(null, ex); 
       }finally {
            try {
                conn.close();
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                //JOptionPane.showMessageDialog(null, ex);
            }
      }  
        
                
    } 

}
