package AppPackage;


import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
public class login extends javax.swing.JFrame {
    Connection conn=null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    /**
     * Creates new form login
     */
    public login() {
        initComponents();
        txt_type.setVisible(false);
        lbl_catVar.setVisible(false);
        lbl_examVar.setVisible(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_loginUsername = new javax.swing.JTextField();
        txt_loginPassword = new javax.swing.JPasswordField();
        btn_login = new javax.swing.JButton();
        txt_type = new javax.swing.JTextField();
        lbl_catVar = new javax.swing.JLabel();
        lbl_examVar = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("NDURURUNO SEC MARKS MANAGEMENT");
        setIconImage(Toolkit.getDefaultToolkit().getImage("icon\\task.png"));
        setResizable(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/keys.png"))); // NOI18N

        jLabel2.setText("Username:");

        jLabel3.setText("Password:");

        txt_loginPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_loginPasswordKeyReleased(evt);
            }
        });

        btn_login.setBackground(new java.awt.Color(2, 176, 219));
        btn_login.setText("Login");
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });

        txt_type.setEditable(false);

        lbl_catVar.setText("catVar");

        lbl_examVar.setText("examVar");

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_loginUsername)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_catVar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_examVar)
                                .addGap(27, 27, 27)
                                .addComponent(txt_type, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txt_loginPassword))
                        .addGap(8, 8, 8))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_loginUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_loginPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_login, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_catVar)
                            .addComponent(lbl_examVar)
                            .addComponent(txt_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(386, 217));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
        // TODO add your handling code here:
        try{
            conn=connect.ConnecrDb();
            String sql = "select * from users where username=? and password=? ";
            pst=conn.prepareStatement(sql);
            pst.setString(1, txt_loginUsername.getText());
            pst.setString(2, txt_loginPassword.getText());
            
            rs=pst.executeQuery();
            if(rs.next()){
                String username = rs.getString("username");
                String user = rs.getString("subject");
                txt_type.setText(user);
                success = "yes";
                               
                dispose();
                new mainFrame().setVisible(true);           
                
            }else{
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Username or Password did not match!","Authentication Error!",JOptionPane.ERROR_MESSAGE);
                failLoginLog(txt_loginUsername.getText(),txt_loginPassword.getText() );
                success = "no";       
            }
            
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }finally {
            try {
                conn.close();
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                //JOptionPane.showMessageDialog(null, ex);
            }
       }
         selectLog();
    }//GEN-LAST:event_btn_loginActionPerformed

    private void txt_loginPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_loginPasswordKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            
          try{
            conn=connect.ConnecrDb();
            String sql = "select * from users where username=? and password=? ";
            pst=conn.prepareStatement(sql);
            pst.setString(1, txt_loginUsername.getText());
            pst.setString(2, txt_loginPassword.getText());
            
            rs=pst.executeQuery();
            if(rs.next()){
                String username = rs.getString("username");
                String user = rs.getString("subject");
                txt_type.setText(user);
                success = "yes";
                      
                dispose();
                new mainFrame().setVisible(true);           
                
            }else{
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Username or Password did not match!.","Authentication Error!",JOptionPane.ERROR_MESSAGE);
                success = "no";
                      
            }
            
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }finally {
            try {
                conn.close();
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                //JOptionPane.showMessageDialog(null, ex);
            }
       }  
         selectLog();
          
        }
    }//GEN-LAST:event_txt_loginPasswordKeyReleased

    public void selectLog(){
        
        if(success.equals("yes")){
            successLoginLog(txt_loginUsername.getText());
            setExamVar();
        }else if(success.equals("no")){
            failLoginLog(txt_loginUsername.getText(),txt_loginPassword.getText());
        }
        
    }
    
    public void setExamVar(){
        
        try {
          conn=connect.ConnecrDb();
          String sql = "select * from exam_variables ";
         pst=conn.prepareStatement(sql);
                            
         rs=pst.executeQuery();
         if(rs.next()){
             String catVar = rs.getString("cat_var");
             String examVar = rs.getString("exam_var");
             
             lbl_catVar.setText(catVar);
             lbl_examVar.setText(examVar);
             
         }         
        
      } catch (SQLException | ClassNotFoundException ex) {
          JOptionPane.showMessageDialog(null, ex+" fourth errox");
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
    
    public void failLoginLog(String person, String username){
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal2 = Calendar.getInstance();
        String Currdate = (dateFormat2.format(cal2.getTime())); //2014-08-06
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String CurrtimeStamp = (dateFormat.format(cal.getTime())); //2014-08-06 16:00:22
        
       try{
            //save member entry event 
           String logit = "insert into system_log (date,event_date,user,event)values(?,?,?,?)"; 
           conn=connect.ConnecrDb(); 
           pst=conn.prepareStatement(logit);
           pst.setString(1, Currdate);
           pst.setString(2, CurrtimeStamp);
           pst.setString(3, "N/A");
           pst.setString(4, "Failed login with username '"+person+"' and password '"+username+"' into database.");
           pst.execute();
           
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
    
    public void successLoginLog(String person){
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal2 = Calendar.getInstance();
        String Currdate = (dateFormat2.format(cal2.getTime())); //2014-08-06
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String CurrtimeStamp = (dateFormat.format(cal.getTime())); //2014-08-06 16:00:22
        
       try{
            //save member entry event 
           String logit = "insert into system_log (date,event_date,user,event)values(?,?,?,?)"; 
           conn=connect.ConnecrDb(); 
           pst=conn.prepareStatement(logit);
           pst.setString(1, Currdate);
           pst.setString(2, CurrtimeStamp);
           pst.setString(3, person);
           pst.setString(4, "Login by username '"+person+"' into system.");
           pst.execute();
           
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
       
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
                 //UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {                
                    new login().setVisible(true);                
            }
        });
    }

    String success;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_login;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JToolBar jToolBar1;
    public static javax.swing.JLabel lbl_catVar;
    public static javax.swing.JLabel lbl_examVar;
    private javax.swing.JPasswordField txt_loginPassword;
    public static javax.swing.JTextField txt_loginUsername;
    public static javax.swing.JTextField txt_type;
    // End of variables declaration//GEN-END:variables
}