package AppPackage;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author home
 */
public class studentAnalysis extends SwingWorker<Void, Void>{
    
    Connection conn=null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    public void generateReport(){
        
       try {
                    String admin_nos = mainFrame.txt_admnStudentResult.getText();
                                        
                    conn=connect.ConnecrDb();
                    JasperDesign jd = JRXmlLoader.load("jxml\\student_Analysis.jrxml");
                                    
                    //"C:\Users\home\Documents\NetBeansProjects\MarksManagement\src\"
                    String sql = "select final_marks.id,final_marks.name,final_marks.class_name,"
                            + "final_marks.admin_nos,final_marks.english,final_marks.english_g," +
                            "final_marks.kiswahili,final_marks.kiswahili_g,final_marks.maths,final_marks.math_g," +
                            "final_marks.physics,final_marks.physics_g,final_marks.biology,final_marks.biology_g," +
                            "final_marks.chemistry,final_marks.chemistry_g,final_marks.geography,final_marks.geography_g," +
                            "final_marks.histroy,final_marks.histroy_g,final_marks.agriculture,final_marks.agriculture_g, " +
                            "final_marks.buisness_studies,final_marks.business_studies_g,final_marks.cre,final_marks.cre_g, " +
                            "final_marks.class,final_marks.term,final_marks.year,final_marks.avarage,final_marks.grade, " +
                            "students.birth_cert from final_marks JOIN students ON final_marks.admin_nos = students.admin_nos " +
                            "where final_marks.admin_nos ='"+admin_nos+"' order by final_marks.class asc,final_marks.term asc, "
                            + "final_marks.year asc";

                    JRDesignQuery newQuery = new JRDesignQuery();
                    newQuery.setText(sql);
                    jd.setQuery(newQuery);
                    JasperReport jr = JasperCompileManager.compileReport(jd);
                    JasperPrint jp = JasperFillManager.fillReport(jr, null,conn);
                    JasperViewer.viewReport(jp,false);
             
        } catch(JRException | ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null, ex);
        }finally{
            try {

                rs.close();
                conn.close();
                pst.close();

            } catch (SQLException ex) {
                //JOptionPane.showMessageDialog(null, ex);
            }
        } 
        
        
        
        
    }

    @Override
    protected Void doInBackground() throws Exception {
        generateReport();
        return null;
    }
    protected void done() {
       mainFrame.showProgress.setIndeterminate(false);
       mainFrame.showProgress.setStringPainted(false);
       mainFrame.geneState.setVisible(false);
    }
    
}
