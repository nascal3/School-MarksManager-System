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
public class finalReportClass extends SwingWorker<Void, Void>{
    
    Connection conn=null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    public void genFinalReportClass(){
        
        try {
                    String year = String.valueOf(mainFrame.chooser_finalMarksYear.getValue());
                    String term = mainFrame.combo_finalMarksTerm.getSelectedItem().toString();
                    String classx = mainFrame.combo_classFinalMarks.getSelectedItem().toString();
                    String className = mainFrame.combo_classNameFinalMarks.getSelectedItem().toString(); 
                    
                    conn=connect.ConnecrDb();
                    JasperDesign jd = null;
                    if(classx.equals("1") && className.equalsIgnoreCase("Central")){
                        jd = JRXmlLoader.load("jxml\\final_marks_1_ctl.jrxml");
                    }else if (classx.equals("1") && className.equalsIgnoreCase("East")){
                        jd = JRXmlLoader.load("jxml\\final_marks_1_est.jrxml");
                    }else if (classx.equals("1") && className.equalsIgnoreCase("West")){
                        jd = JRXmlLoader.load("jxml\\final_marks_1_wst.jrxml");
                    }else if(classx.equals("2") && className.equalsIgnoreCase("Central")){
                        jd = JRXmlLoader.load("jxml\\final_marks_2_ctl.jrxml");
                    }else if (classx.equals("2") && className.equalsIgnoreCase("East")){
                        jd = JRXmlLoader.load("jxml\\final_marks_2_est.jrxml");
                    }else if (classx.equals("2") && className.equalsIgnoreCase("West")){
                        jd = JRXmlLoader.load("jxml\\final_marks_2_wst.jrxml");
                    }else if (classx.equals("3") && className.equalsIgnoreCase("Central")){
                        jd = JRXmlLoader.load("jxml\\final_marks_3_ctl.jrxml");
                    }else if (classx.equals("3") && className.equalsIgnoreCase("East")){
                        jd = JRXmlLoader.load("jxml\\final_marks_3_est.jrxml");
                    }else if (classx.equals("3") && className.equalsIgnoreCase("West")){
                        jd = JRXmlLoader.load("jxml\\final_marks_3_wst.jrxml");
                    }else if (classx.equals("4") && className.equalsIgnoreCase("Central")){
                        jd = JRXmlLoader.load("jxml\\final_marks_4_ctl.jrxml");
                    }else if (classx.equals("4") && className.equalsIgnoreCase("East")){
                        jd = JRXmlLoader.load("jxml\\final_marks_4_est.jrxml");
                    }else if (classx.equals("4") && className.equalsIgnoreCase("West")){
                        jd = JRXmlLoader.load("jxml\\final_marks_4_wst.jrxml");
                    }                   
                    //"C:\Users\home\Documents\NetBeansProjects\MarksManagement\src\AppPackage"
                    String sql = "select id, admin_nos as 'ADM', name as 'Name',house as 'H',"
                            + "english as 'ENG',english_g as 'ENG-G',kiswahili as  'KIS',kiswahili_g as 'KIS-G',"
                            + "maths as 'MAT',math_g as 'MAT-G',physics as 'PHY',physics_g as 'PHY-G',"
                            + "chemistry as 'CHE',chemistry_g as 'CHE-G',"
                            + "biology as 'BIO',biology_g as 'BIO-G',histroy as 'HIS',histroy_g as 'HIS-G',"
                            + "geography as 'GEO',geography_g as 'GEO-G',cre as 'CRE',cre_g as 'CRE-G',"
                            + "agriculture as 'AGR',agriculture_g as 'AGR-G',"
                            + "buisness_studies as 'BS',business_studies_g as 'BS-G',"
                            + "total as 'TOT',avarage as 'AV',grade as 'GD',term,class,year,class_name from "
                            + "final_marks where class ='"+classx+"' and term = '"+term+"' and year = '"+year+"' "
                            + "and class_name = '"+className+"' order by avarage desc";

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
        genFinalReportClass();
        return null;
    }
    protected void done() {
       mainFrame.showProgress.setIndeterminate(false);
       mainFrame.showProgress.setStringPainted(false);
       mainFrame.geneState.setVisible(false);
    }
    
}
