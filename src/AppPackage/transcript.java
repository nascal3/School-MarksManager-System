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
public class transcript extends SwingWorker<Void, Void>{
    Connection conn=null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
        String year = String.valueOf(mainFrame.chooser_YearTrans.getValue());
        String term = mainFrame.combo_marksTrans.getSelectedItem().toString();
        String classx = mainFrame.combo_classTrans.getSelectedItem().toString();
        String className = mainFrame.combo_classNameTrans.getSelectedItem().toString();
    
    //create and populate the table range
    public void createRange(String classx, String term, String year,String className){
        try{
            conn=connect.ConnecrDb();
            String sql = "CREATE TABLE range AS SELECT admin_nos,name,avarage,"
                    + "(select count(admin_nos) from final_marks where year = '"+year+"' and class = '"+classx+"' and term = '"+term+"' ) as 'pop' " +
                      "from final_marks where year = '"+year+"' and class = '"+classx+"' and term = '"+term+"' order by avarage desc;";
            pst=conn.prepareStatement(sql);
            pst.execute();           
            
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
    }
    
    //create and populate the rangeL_Cpos
    public void createRangeL_Cpos(String classx, String term, String year,String className){
        if(term.equals("1")){
            term = "3";
            int yearx = Integer.parseInt(year)-1;
            year = String.valueOf(yearx);
            int classxy = Integer.parseInt(classx)-1;
            classx = String.valueOf(classxy);
        }else if(term.equals("2")){
            term = "1";
        }else if(term.equals("3")){
            term = "2";
        }
        
        
        try{
            conn=connect.ConnecrDb();
            String sql = "CREATE TABLE rangeL_Cpos AS SELECT admin_nos,name,avarage "
                         +"from final_marks where year = '"+year+"' and class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' order by avarage desc;";
            pst=conn.prepareStatement(sql);
            pst.execute();           
            
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
    }
    
    //create and populate the rangeF_Cpos
    public void createRangeL_Fpos(String classx, String term, String year,String className){
        if(term.equals("1")){
            term = "3";
            int yearx = Integer.parseInt(year)-1;
            year = String.valueOf(yearx);
            int classxy = Integer.parseInt(classx)-1;
            classx = String.valueOf(classxy);
        }else if(term.equals("2")){
            term = "1";
        }else if(term.equals("3")){
            term = "2";
        }
        
        
        try{
            conn=connect.ConnecrDb();
            String sql = "CREATE TABLE rangeL_Fpos AS SELECT admin_nos,name,avarage "
                         +"from final_marks where year = '"+year+"' and class = '"+classx+"' and term = '"+term+"' order by avarage desc;";
            pst=conn.prepareStatement(sql);
            pst.execute();           
            
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
    }
    
    //create transcripts
    public void generateTranscript(String classx, String term, String year,String className){
         
         // students analysis report:
        try {
                    conn=connect.ConnecrDb();
                    int classy = Integer.parseInt(classx);
                           JasperDesign jd = null;
                           if(classy<=2){
                               jd = JRXmlLoader.load("jxml\\transcripts.jrxml");
                           }else if(classy>=3 ){
                               jd = JRXmlLoader.load("jxml\\transcripts2.jrxml");
                           }
                                                        
                    //"C:\Users\home\Documents\NetBeansProjects\MarksManagement\src\AppPackage"
                    String sql = "select *,\n" +
"(select raw_marks.english from raw_marks where exam_type = 'EN' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'eng_CAT1',\n" +
"(select raw_marks.english from raw_marks where exam_type = 'MD' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'eng_CAT2',\n" +
"(select raw_marks.english from raw_marks where exam_type = 'ED' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'eng_END',\n" +
"\n" +
"(select raw_marks.kiswahili from raw_marks where exam_type = 'EN' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'kis_CAT1',\n" +
"(select raw_marks.kiswahili from raw_marks where exam_type = 'MD' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'kis_CAT2',\n" +
"(select raw_marks.kiswahili from raw_marks where exam_type = 'ED' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'kis_END',\n" +
"\n" +
"(select raw_marks.maths from raw_marks where exam_type = 'EN' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'math_CAT1',\n" +
"(select raw_marks.maths from raw_marks where exam_type = 'MD' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'math_CAT2',\n" +
"(select raw_marks.maths from raw_marks where exam_type = 'ED' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'math_END',\n" +
"\n" +
"(select raw_marks.physics from raw_marks where exam_type = 'EN' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'phy_CAT1',\n" +
"(select raw_marks.physics from raw_marks where exam_type = 'MD' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'phy_CAT2',\n" +
"(select raw_marks.physics from raw_marks where exam_type = 'ED' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'phy_END',\n" +
"\n" +
"(select raw_marks.biology from raw_marks where exam_type = 'EN' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'bio_CAT1',\n" +
"(select raw_marks.biology from raw_marks where exam_type = 'MD' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'bio_CAT2',\n" +
"(select raw_marks.biology from raw_marks where exam_type = 'ED' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'bio_END',\n" +
"\n" +
"(select raw_marks.chemistry from raw_marks where exam_type = 'EN' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'chem_CAT1',\n" +
"(select raw_marks.chemistry from raw_marks where exam_type = 'MD' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'chem_CAT2',\n" +
"(select raw_marks.chemistry from raw_marks where exam_type = 'ED' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'chem_END',\n" +
"\n" +
"(select raw_marks.geography from raw_marks where exam_type = 'EN' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'geo_CAT1',\n" +
"(select raw_marks.geography from raw_marks where exam_type = 'MD' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'geo_CAT2',\n" +
"(select raw_marks.geography from raw_marks where exam_type = 'ED' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'geo_END',\n" +
"\n" +
"(select raw_marks.histroy from raw_marks where exam_type = 'EN' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'his_CAT1',\n" +
"(select raw_marks.histroy from raw_marks where exam_type = 'MD' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'his_CAT2',\n" +
"(select raw_marks.histroy from raw_marks where exam_type = 'ED' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'his_END',\n" +
"\n" +
"(select raw_marks.cre from raw_marks where exam_type = 'EN' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'cre_CAT1',\n" +
"(select raw_marks.cre from raw_marks where exam_type = 'MD' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'cre_CAT2',\n" +
"(select raw_marks.cre from raw_marks where exam_type = 'ED' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'cre_END',\n" +
"\n" +
"(select raw_marks.agriculture from raw_marks where exam_type = 'EN' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'agri_CAT1',\n" +
"(select raw_marks.agriculture from raw_marks where exam_type = 'MD' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'agri_CAT2',\n" +
"(select raw_marks.agriculture from raw_marks where exam_type = 'ED' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'agri_END',\n" +
"\n" +
"(select raw_marks.buisness_studies from raw_marks where exam_type = 'EN' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'bs_CAT1',\n" +
"(select raw_marks.buisness_studies from raw_marks where exam_type = 'MD' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'bs_CAT2',\n" +
"(select raw_marks.buisness_studies from raw_marks where exam_type = 'ED' and raw_marks.[admin_nos] = final_marks.[admin_nos] and raw_marks.[class] = final_marks.[class] and raw_marks.[class_name] = final_marks.[class_name] and raw_marks.[term] = final_marks.[term] and raw_marks.[year] = final_marks.[year]) as 'bs_END',\n" +
                            
"(select avg(total) from final_marks where class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"') as 'c_avg',\n" +
"(select rank.[c_grade] from rank where rank.[class]=final_marks.[class] and rank.[class_name]=final_marks.[class_name] and rank.[year]=final_marks.[year]) as 'c_grade',\n" +
"(select rank.[f_avarage] from rank where rank.[class]=final_marks.[class] and rank.[year]=final_marks.[year]) as 'f_avg',\n" +
"(select rank.[f_grade] from rank where rank.[class]=final_marks.[class] and rank.[year]=final_marks.[year]) as 'f_grade',\n" +
"(select students.[kcpe_marks] from students where students.[admin_nos]=final_marks.[admin_nos]) as 'kcpe_marks'," +
"(select students.[pic] from students where students.[admin_nos]=final_marks.[admin_nos]) as 'pic'," +                            
                            
"(select COUNT(id) from final_marks where class = '"+classx+"' and class_name = '"+className+"' and term = '"+term+"' and year = '"+year+"') as 'pop' \n" +
" from final_marks where final_marks.class = '"+classx+"' and final_marks.class_name = '"+className+"' and final_marks.term = '"+term+"' and final_marks.year = '"+year+"' order by avarage desc\n" +
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

                //rs.close();
                conn.close();
                //pst.close();

            } catch (SQLException ex) {
                //JOptionPane.showMessageDialog(null, ex);
            }
        }
        //make report for report generation
        //reportGenerationLog();
           
     }
    
    //Destroy and delete table range
    public void dropRange(){
        try{
            conn=connect.ConnecrDb();
            String sql = "DROP TABLE IF EXISTS range";
            pst=conn.prepareStatement(sql);
            pst.execute();           
            
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
    }
    
    //Destroy and delete table rangeL_Fpos
    public void dropRangeL_Fpos(){
        try{
            conn=connect.ConnecrDb();
            String sql = "DROP TABLE IF EXISTS rangeL_Fpos";
            pst=conn.prepareStatement(sql);
            pst.execute();           
            
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
    }
    
    //Destroy and delete table rangeL_Fpos
    public void dropRangeL_Cpos(){
        try{
            conn=connect.ConnecrDb();
            String sql = "DROP TABLE IF EXISTS rangeL_Cpos";
            pst=conn.prepareStatement(sql);
            pst.execute();           
            
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
    }

    
    @Override
    protected Void doInBackground() throws Exception {
       //Destroy and delete table range
        dropRange();
        //Destroy and delete table rangeL_Cpos
        dropRangeL_Cpos();
        //Destroy and delete table rangeL_Fpos
        dropRangeL_Fpos();
        //create and populate the table range
        createRange(classx, term, year, className);
        //create and populate the rangeL_Cpos
        createRangeL_Cpos(classx, term, year, className);
        //create and populate the rangeF_Fpos
        createRangeL_Fpos(classx, term, year, className);        
        //create transcripts
        generateTranscript(classx, term, year, className);             
        return null; 
    }    
    protected void done() {
       mainFrame.showProgress.setIndeterminate(false);
       mainFrame.showProgress.setStringPainted(false);
       mainFrame.geneState.setVisible(false);
    }
}
