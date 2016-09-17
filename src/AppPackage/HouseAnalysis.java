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
public class HouseAnalysis extends SwingWorker<Void, Void>  {
    Connection conn=null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
        String term = mainFrame.combo_marksTermAnaHouse.getSelectedItem().toString();
        String year = String.valueOf(mainFrame.chooser_marksYearAnaHouse.getValue());
        int classx = Integer.parseInt(mainFrame.combo_classAnaHouse.getSelectedItem().toString());
    
    //Destroy and delete table house_pos
    public void dropHouse_pos(){
        try{
            conn=connect.ConnecrDb();
            String sql = "DROP TABLE IF EXISTS house_pos";
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
    
    //create and populate the house_pos
    public void createHouse_pos_PE(int classx, String term, String year){
        String sql = null;
        if(classx<=2){
          sql = "CREATE TABLE house_pos AS \n" +
"select DISTINCT CASE final_marks.[house] WHEN 'PE' THEN 'PE' ELSE 'PE' END as 'House',\n" +
"(select class from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'class',\n" +
"(select year from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'year',\n" +
"(select term from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'term',\n" +
"(select count(id) from final_marks where grade = 'A' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'A',\n" +
"(select count(id) from final_marks where grade = 'A-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'A-',\n" +
"(select count(id) from final_marks where grade = 'B+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'B+',\n" +
"(select count(id) from final_marks where grade = 'B' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'B',\n" +
"(select count(id) from final_marks where grade = 'B-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'B-',\n" +
"(select count(id) from final_marks where grade = 'C+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'C+',\n" +
"(select count(id) from final_marks where grade = 'C' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'C',\n" +
"(select count(id) from final_marks where grade = 'C-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'C-',\n" +
"(select count(id) from final_marks where grade = 'D+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'D+',\n" +
"(select count(id) from final_marks where grade = 'D' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'D',\n" +
"(select count(id) from final_marks where grade = 'D-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'D-',\n" +
"(select count(id) from final_marks where grade = 'E' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'E',\n" +
"(select count(id) from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'TOT',\n" +
"(select sum(total) from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'TOT MRKS',\n" +
"(select avg(total)/11 from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'MMRK'\n" +
"from final_marks \n" +
"where final_marks.class = '"+classx+"' and final_marks.term = '"+term+"' and final_marks.year = '"+year+"' and house = 'PE' ";
        }else if(classx>=3 ){
          sql = "CREATE TABLE house_pos AS \n" +
"select DISTINCT CASE final_marks.[house] WHEN 'PE' THEN 'PE' ELSE 'PE' END as 'House',\n" +
"(select class from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'class',\n" +
"(select year from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'year',\n" +
"(select term from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'term',\n" +
"(select count(id) from final_marks where grade = 'A' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'A',\n" +
"(select count(id) from final_marks where grade = 'A-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'A-',\n" +
"(select count(id) from final_marks where grade = 'B+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'B+',\n" +
"(select count(id) from final_marks where grade = 'B' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'B',\n" +
"(select count(id) from final_marks where grade = 'B-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'B-',\n" +
"(select count(id) from final_marks where grade = 'C+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'C+',\n" +
"(select count(id) from final_marks where grade = 'C' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'C',\n" +
"(select count(id) from final_marks where grade = 'C-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'C-',\n" +
"(select count(id) from final_marks where grade = 'D+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'D+',\n" +
"(select count(id) from final_marks where grade = 'D' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'D',\n" +
"(select count(id) from final_marks where grade = 'D-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'D-',\n" +
"(select count(id) from final_marks where grade = 'E' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'E',\n" +
"(select count(id) from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'TOT',\n" +
"(select sum(total) from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'TOT MRKS',\n" +
"(select avg(total)/8 from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'MMRK'\n" +
"from final_marks \n" +
"where final_marks.class = '"+classx+"' and final_marks.term = '"+term+"' and final_marks.year = '"+year+"' and house = 'PE' ";
        }
        
        
        try{
            conn=connect.ConnecrDb();
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
    
    //populate the house_pos CC
    public void insertHouse_CC(int classx, String term, String year){
        String sql = null;
        if(classx<=2){
          sql = "INSERT INTO house_pos \n" +
"select DISTINCT CASE final_marks.[house] WHEN 'CC' THEN 'CC' ELSE 'CC' END as 'House',\n" +
"(select class from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'class',\n" +
"(select year from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'year',\n" +
"(select term from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'term',\n" +
"(select count(id) from final_marks where grade = 'A' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'A',\n" +
"(select count(id) from final_marks where grade = 'A-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'A-',\n" +
"(select count(id) from final_marks where grade = 'B+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'B+',\n" +
"(select count(id) from final_marks where grade = 'B' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'B',\n" +
"(select count(id) from final_marks where grade = 'B-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'B-',\n" +
"(select count(id) from final_marks where grade = 'C+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'C+',\n" +
"(select count(id) from final_marks where grade = 'C' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'C',\n" +
"(select count(id) from final_marks where grade = 'C-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'C-',\n" +
"(select count(id) from final_marks where grade = 'D+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'D+',\n" +
"(select count(id) from final_marks where grade = 'D' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'D',\n" +
"(select count(id) from final_marks where grade = 'D-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'D-',\n" +
"(select count(id) from final_marks where grade = 'E' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'E',\n" +
"(select count(id) from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'TOT',\n" +
"(select sum(total) from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'TOT MRKS',\n" +
"(select avg(total)/11 from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'MMRK'\n" +
"from final_marks \n" +
"where final_marks.class = '"+classx+"' and final_marks.term = '"+term+"' and final_marks.year = '"+year+"' and house = 'CC' ";
        }else if(classx>=3 ){
          sql = "INSERT INTO house_pos \n" +
"select DISTINCT CASE final_marks.[house] WHEN 'CC' THEN 'CC' ELSE 'CC' END as 'House',\n" +
"(select class from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'class',\n" +
"(select year from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'year',\n" +
"(select term from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'term',\n" +
"(select count(id) from final_marks where grade = 'A' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'A',\n" +
"(select count(id) from final_marks where grade = 'A-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'A-',\n" +
"(select count(id) from final_marks where grade = 'B+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'B+',\n" +
"(select count(id) from final_marks where grade = 'B' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'B',\n" +
"(select count(id) from final_marks where grade = 'B-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'B-',\n" +
"(select count(id) from final_marks where grade = 'C+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'C+',\n" +
"(select count(id) from final_marks where grade = 'C' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'C',\n" +
"(select count(id) from final_marks where grade = 'C-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'C-',\n" +
"(select count(id) from final_marks where grade = 'D+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'D+',\n" +
"(select count(id) from final_marks where grade = 'D' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'D',\n" +
"(select count(id) from final_marks where grade = 'D-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'D-',\n" +
"(select count(id) from final_marks where grade = 'E' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'E',\n" +
"(select count(id) from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'TOT',\n" +
"(select sum(total) from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'TOT MRKS',\n" +
"(select avg(total)/8 from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'MMRK'\n" +
"from final_marks \n" +
"where final_marks.class = '"+classx+"' and final_marks.term = '"+term+"' and final_marks.year = '"+year+"' and house = 'CC' ";
        }
        
        
        try{
            conn=connect.ConnecrDb();
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
    
    //populate the house_pos OS
    public void insertHouse_OS(int classx, String term, String year){
        String sql = null;
        if(classx<=2){
          sql = "INSERT INTO house_pos \n" +
"select DISTINCT CASE final_marks.[house] WHEN 'OS' THEN 'OS' ELSE 'OS' END as 'House',\n" +
"(select class from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'class',\n" +
"(select year from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'year',\n" +
"(select term from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'term',\n" +
"(select count(id) from final_marks where grade = 'A' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'A',\n" +
"(select count(id) from final_marks where grade = 'A-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'A-',\n" +
"(select count(id) from final_marks where grade = 'B+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'B+',\n" +
"(select count(id) from final_marks where grade = 'B' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'B',\n" +
"(select count(id) from final_marks where grade = 'B-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'B-',\n" +
"(select count(id) from final_marks where grade = 'C+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'C+',\n" +
"(select count(id) from final_marks where grade = 'C' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'C',\n" +
"(select count(id) from final_marks where grade = 'C-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'C-',\n" +
"(select count(id) from final_marks where grade = 'D+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'D+',\n" +
"(select count(id) from final_marks where grade = 'D' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'D',\n" +
"(select count(id) from final_marks where grade = 'D-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'D-',\n" +
"(select count(id) from final_marks where grade = 'E' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'E',\n" +
"(select count(id) from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'TOT',\n" +
"(select sum(total) from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'TOT MRKS',\n" +
"(select avg(total)/11 from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'MMRK'\n" +
"from final_marks \n" +
"where final_marks.class = '"+classx+"' and final_marks.term = '"+term+"' and final_marks.year = '"+year+"' and house = 'OS' ";
        }else if(classx>=3 ){
          sql = "INSERT INTO house_pos \n" +
"select DISTINCT CASE final_marks.[house] WHEN 'OS' THEN 'OS' ELSE 'OS' END as 'House',\n" +
"(select class from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'class',\n" +
"(select year from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'year',\n" +
"(select term from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'term',\n" +
"(select count(id) from final_marks where grade = 'A' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'A',\n" +
"(select count(id) from final_marks where grade = 'A-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'A-',\n" +
"(select count(id) from final_marks where grade = 'B+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'B+',\n" +
"(select count(id) from final_marks where grade = 'B' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'B',\n" +
"(select count(id) from final_marks where grade = 'B-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'B-',\n" +
"(select count(id) from final_marks where grade = 'C+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'C+',\n" +
"(select count(id) from final_marks where grade = 'C' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'C',\n" +
"(select count(id) from final_marks where grade = 'C-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'C-',\n" +
"(select count(id) from final_marks where grade = 'D+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'D+',\n" +
"(select count(id) from final_marks where grade = 'D' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'D',\n" +
"(select count(id) from final_marks where grade = 'D-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'D-',\n" +
"(select count(id) from final_marks where grade = 'E' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'E',\n" +
"(select count(id) from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'TOT',\n" +
"(select sum(total) from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'TOT MRKS',\n" +
"(select avg(total)/8 from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'MMRK'\n" +
"from final_marks \n" +
"where final_marks.class = '"+classx+"' and final_marks.term = '"+term+"' and final_marks.year = '"+year+"' and house = 'OS' ";
        }
        
        
        try{
            conn=connect.ConnecrDb();
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
    
    //populate the house_pos EA
    public void insertHouse_EA(int classx, String term, String year){
        String sql = null;
        if(classx<=2){
          sql = "INSERT INTO house_pos \n" +
"select DISTINCT CASE final_marks.[house] WHEN 'EA' THEN 'EA' ELSE 'EA' END as 'House',\n" +
"(select class from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'class',\n" +
"(select year from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'year',\n" +
"(select term from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'term',\n" +
"(select count(id) from final_marks where grade = 'A' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'A',\n" +
"(select count(id) from final_marks where grade = 'A-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'A-',\n" +
"(select count(id) from final_marks where grade = 'B+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'B+',\n" +
"(select count(id) from final_marks where grade = 'B' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'B',\n" +
"(select count(id) from final_marks where grade = 'B-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'B-',\n" +
"(select count(id) from final_marks where grade = 'C+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'C+',\n" +
"(select count(id) from final_marks where grade = 'C' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'C',\n" +
"(select count(id) from final_marks where grade = 'C-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'C-',\n" +
"(select count(id) from final_marks where grade = 'D+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'D+',\n" +
"(select count(id) from final_marks where grade = 'D' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'D',\n" +
"(select count(id) from final_marks where grade = 'D-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'D-',\n" +
"(select count(id) from final_marks where grade = 'E' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'E',\n" +
"(select count(id) from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'TOT',\n" +
"(select sum(total) from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'TOT MRKS',\n" +
"(select avg(total)/11 from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'MMRK'\n" +
"from final_marks \n" +
"where final_marks.class = '"+classx+"' and final_marks.term = '"+term+"' and final_marks.year = '"+year+"' and house = 'EA' ";
        }else if(classx>=3 ){
          sql = "INSERT INTO house_pos \n" +
"select DISTINCT CASE final_marks.[house] WHEN 'EA' THEN 'EA' ELSE 'EA' END as 'House',\n" +
"(select class from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'class',\n" +
"(select year from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'year',\n" +
"(select term from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'term',\n" +
"(select count(id) from final_marks where grade = 'A' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'A',\n" +
"(select count(id) from final_marks where grade = 'A-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'A-',\n" +
"(select count(id) from final_marks where grade = 'B+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'B+',\n" +
"(select count(id) from final_marks where grade = 'B' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'B',\n" +
"(select count(id) from final_marks where grade = 'B-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'B-',\n" +
"(select count(id) from final_marks where grade = 'C+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'C+',\n" +
"(select count(id) from final_marks where grade = 'C' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'C',\n" +
"(select count(id) from final_marks where grade = 'C-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'C-',\n" +
"(select count(id) from final_marks where grade = 'D+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'D+',\n" +
"(select count(id) from final_marks where grade = 'D' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'D',\n" +
"(select count(id) from final_marks where grade = 'D-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'D-',\n" +
"(select count(id) from final_marks where grade = 'E' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'E',\n" +
"(select count(id) from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'TOT',\n" +
"(select sum(total) from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'TOT MRKS',\n" +
"(select avg(total)/8 from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'MMRK'\n" +
"from final_marks \n" +
"where final_marks.class = '"+classx+"' and final_marks.term = '"+term+"' and final_marks.year = '"+year+"' and house = 'EA' ";
        }
        
        
        try{
            conn=connect.ConnecrDb();
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
    
    //populate the house_pos PE
    public void insertHouse_TOTALS(int classx, String term, String year){
        String sql = null;
        if(classx<=2){
          sql = "INSERT INTO house_pos \n" +
"select DISTINCT CASE final_marks.[house] WHEN 'EA' THEN 'TOTALS' ELSE 'TOTALS' END as 'House',\n" +
"(select class from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' ) as 'class',\n" +
"(select year from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' ) as 'year',\n" +
"(select term from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' ) as 'term',\n" +
"(select count(id) from final_marks where grade = 'A' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'A',\n" +
"(select count(id) from final_marks where grade = 'A-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'A-',\n" +
"(select count(id) from final_marks where grade = 'B+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'B+',\n" +
"(select count(id) from final_marks where grade = 'B' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'B',\n" +
"(select count(id) from final_marks where grade = 'B-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'B-',\n" +
"(select count(id) from final_marks where grade = 'C+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'C+',\n" +
"(select count(id) from final_marks where grade = 'C' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'C',\n" +
"(select count(id) from final_marks where grade = 'C-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'C-',\n" +
"(select count(id) from final_marks where grade = 'D+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'D+',\n" +
"(select count(id) from final_marks where grade = 'D' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'D',\n" +
"(select count(id) from final_marks where grade = 'D-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'D-',\n" +
"(select count(id) from final_marks where grade = 'E' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'E',\n" +
"(select count(id) from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'TOT',\n" +
"(select sum(total) from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'TOT MRKS',\n" +
"(select avg(total)/11 from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'MMRK'\n" +
"from final_marks \n" +
"where final_marks.class = '"+classx+"' and final_marks.term = '"+term+"' and final_marks.year = '"+year+"' ";
        }else if(classx>=3 ){
          sql = "INSERT INTO house_pos \n" +
"select DISTINCT CASE final_marks.[house] WHEN 'EA' THEN 'TOTALS' ELSE 'TOTALS' END as 'House',\n" +
"(select class from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' ) as 'class',\n" +
"(select year from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' ) as 'year',\n" +
"(select term from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' ) as 'term',\n" +
"(select count(id) from final_marks where grade = 'A' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'A',\n" +
"(select count(id) from final_marks where grade = 'A-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'A-',\n" +
"(select count(id) from final_marks where grade = 'B+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'B+',\n" +
"(select count(id) from final_marks where grade = 'B' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'B',\n" +
"(select count(id) from final_marks where grade = 'B-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'B-',\n" +
"(select count(id) from final_marks where grade = 'C+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'C+',\n" +
"(select count(id) from final_marks where grade = 'C' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'C',\n" +
"(select count(id) from final_marks where grade = 'C-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'C-',\n" +
"(select count(id) from final_marks where grade = 'D+' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'D+',\n" +
"(select count(id) from final_marks where grade = 'D' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'D',\n" +
"(select count(id) from final_marks where grade = 'D-' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'D-',\n" +
"(select count(id) from final_marks where grade = 'E' and class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'E',\n" +
"(select count(id) from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'TOT',\n" +
"(select sum(total) from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'TOT MRKS',\n" +
"(select avg(total)/8 from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'MMRK'\n" +
"from final_marks \n" +
"where final_marks.class = '"+classx+"' and final_marks.term = '"+term+"' and final_marks.year = '"+year+"' ";
        }
        
        
        try{
            conn=connect.ConnecrDb();
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
    
    //GENRATE REPORT
    public void houseAnaGen(String term,int classx,String year){
        
        try {                                                        
                    conn=connect.ConnecrDb();
                    
                           JasperDesign jd = JRXmlLoader.load("jxml\\House_Analysis.jrxml");
                                                                                   
                    //"C:\Users\home\Documents\NetBeansProjects\MarksManagement\src\AppPackage"
                                  
                    String sql = "SELECT *, 2 AS ordering FROM house_pos WHERE House = (SELECT House FROM house_pos where House = 'TOTALS')\n" +
"UNION\n" +
"SELECT *, 1 AS ordering FROM house_pos WHERE House != 'TOTALS'\n" +
"ORDER BY ordering, MMRK desc ";

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
        
    }

    @Override
    protected Void doInBackground() throws Exception {
        dropHouse_pos();
        createHouse_pos_PE(classx, term, year);
        insertHouse_OS(classx, term, year);
        insertHouse_CC(classx, term, year);
        insertHouse_EA(classx, term, year);
        insertHouse_TOTALS(classx, term, year);
        //GENERATE THE REPORT
        houseAnaGen(term, classx, year);
    return null; 
    }    
    protected void done() {
       mainFrame.showProgress.setIndeterminate(false);
       mainFrame.showProgress.setStringPainted(false);
       mainFrame.geneState.setVisible(false);
    }
        
}
