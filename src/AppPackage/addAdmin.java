package AppPackage;


import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author home
 */
public class addAdmin extends javax.swing.JFrame {
    Connection conn=null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    /**
     * Creates new form addUser
     */
    public addAdmin() {
        initComponents();
           
    }
 
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn_saveUser = new javax.swing.JButton();
        txt_userPassword = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        txt_userFullName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txt_userUsername = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_retypePassword = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Add Administrator");
        setIconImage(Toolkit.getDefaultToolkit().getImage("icon\\favicon.png"));
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add administrator user", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel1.setEnabled(false);

        btn_saveUser.setBackground(new java.awt.Color(2, 176, 219));
        btn_saveUser.setText("Add system admin");
        btn_saveUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveUserActionPerformed(evt);
            }
        });

        txt_userPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_userPasswordKeyReleased(evt);
            }
        });

        jLabel2.setText("Password:");

        jLabel1.setText("Full name:");

        jLabel3.setText("Username:");

        jLabel5.setText("Re type password:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_saveUser)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_userUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_userFullName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(35, 35, 35)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_userPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_retypePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_userFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_userUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_userPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_retypePassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_saveUser, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 153, 255));
        jLabel4.setText("Please register a system administrator in order to loggin. ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(381, 312));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_saveUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveUserActionPerformed
        // add category:
        try{

            if(txt_userFullName.getText().isEmpty()){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Please fill in users full name!","Attention",JOptionPane.ERROR_MESSAGE);
            }else if(txt_userUsername.getText().isEmpty()){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Please fill in a username!","Attention",JOptionPane.ERROR_MESSAGE);
            }else if(txt_userPassword.getText().isEmpty()){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Please fill in a password!","Attention",JOptionPane.ERROR_MESSAGE);
            }else if(!txt_userPassword.getText().equals(txt_retypePassword.getText())){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "The passwords did not match!","Attention",JOptionPane.ERROR_MESSAGE);
                txt_userPassword.setText("");
                txt_retypePassword.setText("");
            }else{
                //check if book category is duplicated
                String sqlx = "select * from users where username=? ";
                conn=connect.ConnecrDb();
                pst = conn.prepareStatement(sqlx);
                pst.setString(1, txt_userUsername.getText());
                rs = pst.executeQuery();

                if(rs.next()){
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "The username '"+txt_userUsername.getText()+"' is already registered!","Attention",JOptionPane.ERROR_MESSAGE);
                }else{
                    //save class info to system
                    String sql = "insert into users (full_name,username,password,subject)values(?,?,?,?)";
                    conn=connect.ConnecrDb();
                    pst=conn.prepareStatement(sql);
                    pst.setString(1, txt_userFullName.getText());
                    pst.setString(2, txt_userUsername.getText());
                    pst.setString(3, txt_userPassword.getText());
                    pst.setString(4, "Admin");
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Administrator user added. Restart the programe to loggin.","Information",JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
            }

        }catch(HeadlessException | ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }finally {
            try {
                rs.close();
                conn.close();
                pst.close();
            } catch (SQLException ex) {
                //JOptionPane.showMessageDialog(null, ex);
            }
        }
        //insert book save event tot log
       
        
    }//GEN-LAST:event_btn_saveUserActionPerformed

    private void txt_userPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_userPasswordKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            
          try{

            if(txt_userFullName.getText().isEmpty()){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Please fill in users full name!","Attention",JOptionPane.ERROR_MESSAGE);
            }else if(txt_userUsername.getText().isEmpty()){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Please fill in a username!","Attention",JOptionPane.ERROR_MESSAGE);
            }else if(txt_userPassword.getText().isEmpty()){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Please fill in a password!","Attention",JOptionPane.ERROR_MESSAGE);
            }else{
                //check if book category is duplicated
                String sqlx = "select * from users where username=? ";
                conn=connect.ConnecrDb();
                pst = conn.prepareStatement(sqlx);
                pst.setString(1, txt_userUsername.getText());
                rs = pst.executeQuery();

                if(rs.next()){
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "The username '"+txt_userUsername.getText()+"' is already registered!","Attention",JOptionPane.ERROR_MESSAGE);
                }else{
                    //save class info to system
                    String sql = "insert into users (full_name,username,password,subject)values(?,?,?,?)";
                    conn=connect.ConnecrDb();
                    pst=conn.prepareStatement(sql);
                    pst.setString(1, txt_userFullName.getText());
                    pst.setString(2, txt_userUsername.getText());
                    pst.setString(3, txt_userPassword.getText());
                    pst.setString(4, "Admin");
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Admin user added. Restart please restart programe to loggin.","Information",JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
            }

        }catch(HeadlessException | ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }finally {
            try {
                rs.close();
                conn.close();
                pst.close();
            } catch (SQLException ex) {
                //JOptionPane.showMessageDialog(null, ex);
            }
        }  
            
        }
    }//GEN-LAST:event_txt_userPasswordKeyReleased

    
    
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
                 
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {                
                    new addAdmin().setVisible(true);               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_saveUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txt_retypePassword;
    private javax.swing.JTextField txt_userFullName;
    private javax.swing.JPasswordField txt_userPassword;
    private javax.swing.JTextField txt_userUsername;
    // End of variables declaration//GEN-END:variables
}