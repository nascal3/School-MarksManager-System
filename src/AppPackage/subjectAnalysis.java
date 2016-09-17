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
 * @author caleb nasio
 * 
 */


public class subjectAnalysis extends SwingWorker<Void, Void>{
    Connection conn=null;
    ResultSet rs = null;
    PreparedStatement pst = null;
       
        String term = mainFrame.combo_marksTermAna.getSelectedItem().toString();
        String year = String.valueOf(mainFrame.chooser_marksYearAna.getValue());
        String classx = mainFrame.combo_classAna.getSelectedItem().toString();
        String className = mainFrame.combo_classNameAna.getSelectedItem().toString();
        String subject = mainFrame.combo_subjectAna.getSelectedItem().toString();
        String subject_g = mainFrame.txt_subjectG.getText(); 

        
    public void subjectAnalysis(String term,String classx,String className,String year,String subject_g,String subject){
        
        try {
                                                        
                    conn=connect.ConnecrDb();
                    JasperDesign jd = null;
                    if(subject.equals("english")){
                        jd = JRXmlLoader.load("jxml\\eng_resultsAnalysis.jrxml");
                    }else if(subject.equals("kiswahili")){
                        jd = JRXmlLoader.load("jxml\\kis_resultsAnalysis.jrxml");
                    }else if(subject.equals("physics")){
                        jd = JRXmlLoader.load("jxml\\phy_resultsAnalysis.jrxml");
                    }else if(subject.equals("chemistry")){
                        jd = JRXmlLoader.load("jxml\\chem_resultsAnalysis.jrxml");
                    }else if(subject.equals("geography")){
                        jd = JRXmlLoader.load("jxml\\geo_resultsAnalysis.jrxml");
                    }else if(subject.equals("maths")){
                        jd = JRXmlLoader.load("jxml\\mat_resultsAnalysis.jrxml");
                    }else if(subject.equals("biology")){
                        jd = JRXmlLoader.load("jxml\\bio_resultsAnalysis.jrxml");
                    }else if(subject.equals("agriculture")){
                        jd = JRXmlLoader.load("jxml\\agr_resultsAnalysis.jrxml");
                    }else if(subject.equals("histroy")){
                        jd = JRXmlLoader.load("jxml\\his_resultsAnalysis.jrxml");
                    }else if(subject.equals("buisness_studies")){
                        jd = JRXmlLoader.load("jxml\\bs_resultsAnalysis.jrxml");
                    }else if(subject.equals("cre")){
                        jd = JRXmlLoader.load("jxml\\cre_resultsAnalysis.jrxml");
                    }                 
                    //"C:\Users\home\Documents\NetBeansProjects\MarksManagement\src\AppPackage"
                    String sql = "select CASE final_marks.gender WHEN 'male' THEN 'Boys' ELSE 'Boys' END as 'Gender',teachers."+subject+" as 'teachers', " +
"(select class from final_marks where class_name = '"+className+"' and class = '"+classx+"') as 'class', \n" +
"(select class_name from final_marks where class_name = '"+className+"' and class = '"+classx+"') as 'class_name', "+                        
"(select count(id) from final_marks where "+subject_g+" = 'A' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'male' and "+subject+" <>'') as 'A', \n" +
"(select count(id) from final_marks where "+subject_g+" = 'A-' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'male' and "+subject+" <>'') as 'A-',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'B+' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'male' and "+subject+" <>'') as 'B+',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'B' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'male' and "+subject+" <>'') as 'B',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'B-' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'male' and "+subject+" <>'') as 'B-',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'C+' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'male' and "+subject+" <>'') as 'C+',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'C' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'male' and "+subject+" <>'') as 'C',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'C-' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'male' and "+subject+" <>'') as 'C-',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'D+' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'male' and "+subject+" <>'') as 'D+',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'D' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'male' and "+subject+" <>'') as 'D',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'D-' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'male' and "+subject+" <>'') as 'D-',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'E' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'male' and "+subject+" <>'') as 'E',\n" +
"(select count(id) from final_marks where class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'male' and "+subject+" <>'') as 'TOT',\n" +
"(select sum("+subject+") from final_marks where class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'male' and "+subject+" <>'') as 'TOT MRKS',\n" +
"(select avg("+subject+") from final_marks where class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'male' and "+subject+" <>'') as 'M.MRK' \n" +
"from final_marks INNER JOIN teachers ON teachers.class = (select class from final_marks where class_name = '"+className+"' and class = '"+classx+"') \n" +
"where final_marks.class = '"+classx+"' and final_marks.class_name = '"+className+"' and final_marks.term = '"+term+"' and final_marks.year = '"+year+"' and final_marks."+subject+" <>'' group by teachers."+subject+"\n" +
"\n" +
"union\n" +
"\n" +
"select DISTINCT CASE final_marks.gender WHEN 'female' THEN 'Girls' ELSE 'Girls' END as 'Gender',teachers."+subject+" as 'teachers', " +
"(select class from final_marks where class_name = '"+className+"' and class = '"+classx+"') as 'class', \n" +
"(select class_name from final_marks where class_name = '"+className+"' and class = '"+classx+"') as 'class_name', "+                            
"(select count(id) from final_marks where "+subject_g+" = 'A' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'female' and "+subject+" <>'') as 'A', \n" +
"(select count(id) from final_marks where "+subject_g+" = 'A-' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'female' and "+subject+" <>'') as 'A-',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'B+' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'female' and "+subject+" <>'') as 'B+',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'B' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'female' and "+subject+" <>'') as 'B',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'B-' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'female' and "+subject+" <>'') as 'B-',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'C+' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'female' and "+subject+" <>'') as 'C+',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'C' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'female' and "+subject+" <>'') as 'C',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'C-' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'female' and "+subject+" <>'') as 'C-',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'D+' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'female' and "+subject+" <>'') as 'D+',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'D' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'female' and "+subject+" <>'') as 'D',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'D-' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'female' and "+subject+" <>'') as 'D-',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'E' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'female' and "+subject+" <>'') as 'E' ,\n" +
"(select count(id) from final_marks where class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'female' and "+subject+" <>'') as 'TOT',\n" +
"(select sum("+subject+") from final_marks where class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'female' and "+subject+" <>'') as 'TOT MRKS',\n" +
"(select avg("+subject+") from final_marks where class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and gender = 'female' and "+subject+" <>'') as 'M.MRK'   \n" +
"from final_marks INNER JOIN teachers ON  teachers.class = (select class from final_marks where class_name = '"+className+"' and class = '"+classx+"') \n" +
"where final_marks.class = '"+classx+"' and final_marks.class_name = '"+className+"' and final_marks.term = '"+term+"' and final_marks.year = '"+year+"' and final_marks."+subject+" <>'' group by teachers."+subject+"\n" +
"\n" +
"union all\n" +
"\n" +
"select DISTINCT CASE final_marks.gender WHEN 'female' THEN 'Both' ELSE 'Both' END as 'Gender',teachers."+subject+" as 'teachers', " +
"(select class from final_marks where class_name = '"+className+"' and class = '"+classx+"') as 'class', \n" +
"(select class_name from final_marks where class_name = '"+className+"' and class = '"+classx+"') as 'class_name', "+
"(select count(id) from final_marks where "+subject_g+" = 'A' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and "+subject+" <>'') as 'A', \n" +
"(select count(id) from final_marks where "+subject_g+" = 'A-' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and "+subject+" <>'') as 'A-',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'B+' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and "+subject+" <>'') as 'B+',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'B' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and "+subject+" <>'') as 'B',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'B-' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and "+subject+" <>'') as 'B-',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'C+' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and "+subject+" <>'') as 'C+',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'C' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and "+subject+" <>'') as 'C',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'C-' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and "+subject+" <>'') as 'C-',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'D+' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and "+subject+" <>'') as 'D+',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'D' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and "+subject+" <>'') as 'D',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'D-' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and "+subject+" <>'') as 'D-',\n" +
"(select count(id) from final_marks where "+subject_g+" = 'E' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and "+subject+" <>'') as 'E',\n" +
"(select count(id) from final_marks where class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and "+subject+" <>'') as 'TOT',\n" +
"(select sum("+subject+") from final_marks where class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and "+subject+" <>'') as 'TOT MRKS',\n" +
"(select avg("+subject+") from final_marks where class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"' and "+subject+" <>'') as 'M.MRK' \n" +
"from final_marks INNER JOIN teachers ON  teachers.class = (select class from final_marks where class_name = '"+className+"' and class = '"+classx+"') \n" +
"where final_marks.class = '"+classx+"' and final_marks.class_name = '"+className+"' and final_marks.term = '"+term+"' and final_marks.year = '"+year+"' and final_marks."+subject+" <>''\n" +
"";

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
        subjectAnalysis(term,classx,className,year,subject_g,subject);
        return null;
    }
    protected void done() {
       mainFrame.showProgress.setIndeterminate(false);
       mainFrame.showProgress.setStringPainted(false);
       mainFrame.geneState.setVisible(false);
    }
    
}
