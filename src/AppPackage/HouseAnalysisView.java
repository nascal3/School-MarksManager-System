package AppPackage;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import net.proteanit.sql.DbUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author home
 */
public class HouseAnalysisView {
    Connection conn=null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    public void houseAnaView(String term,int classx,String year){
        
        try {                                                        
                    conn=connect.ConnecrDb();
                                  
                    String sql = "select DISTINCT CASE final_marks.[house] WHEN 'CC' THEN 'CC' ELSE 'CC' END as 'House',\n" +
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
"(select avg(total)/11 from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'M.MRK'\n" +
"from final_marks \n" +
"where final_marks.class = '"+classx+"' and final_marks.term = '"+term+"' and final_marks.year = '"+year+"' and house = 'CC'\n" +
"\n" +
"union\n" +
"\n" +
"select DISTINCT CASE final_marks.[house] WHEN 'OS' THEN 'OS' ELSE 'OS' END as 'House',\n" +
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
"(select avg(total)/11 from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'M.MRK'\n" +
"from final_marks \n" +
"where final_marks.class = '"+classx+"' and final_marks.term = '"+term+"' and final_marks.year = '"+year+"' and house = 'OS'\n" +
"\n" +
"UNION\n" +
"\n" +
"select DISTINCT CASE final_marks.[house] WHEN 'PE' THEN 'PE' ELSE 'PE' END as 'House',\n" +
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
"(select avg(total)/11 from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'M.MRK'\n" +
"from final_marks \n" +
"where final_marks.class = '"+classx+"' and final_marks.term = '"+term+"' and final_marks.year = '"+year+"' and house = 'PE'\n" +
"\n" +
"union\n" +
"\n" +
"select DISTINCT CASE final_marks.[house] WHEN 'EA' THEN 'EA' ELSE 'EA' END as 'House',\n" +
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
"(select avg(total)/11 from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'M.MRK'\n" +
"from final_marks \n" +
"where final_marks.class = '"+classx+"' and final_marks.term = '"+term+"' and final_marks.year = '"+year+"' and house = 'EA'\n" +
"\n" +
"union all\n" +
"\n" +
"select DISTINCT CASE final_marks.[house] WHEN 'EA' THEN 'TOTALS' ELSE 'TOTALS' END as 'House',\n" +
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
"(select avg(total)/11 from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'M.MRK'\n" +
"from final_marks \n" +
"where final_marks.class = '"+classx+"' and final_marks.term = '"+term+"' and final_marks.year = '"+year+"'\n" +
"";

                    pst=conn.prepareStatement(sql);
                    rs=pst.executeQuery();
                    mainFrame.tbl_houseAnaMarks.setModel(DbUtils.resultSetToTableModel(rs));
                    
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for(int x=0;x<16;x++){
         mainFrame.tbl_houseAnaMarks.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
         mainFrame.tbl_houseAnaMarks.getColumnModel().getColumn(x).setHeaderRenderer(centerRenderer);
        }
                              
        } catch(ClassNotFoundException | SQLException ex){
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
    
    public void houseAnaView2(String term,int classx,String year){
        
        try {                                                        
                    conn=connect.ConnecrDb();
                                  
                    String sql = "select DISTINCT CASE final_marks.[house] WHEN 'CC' THEN 'CC' ELSE 'CC' END as 'House',\n" +
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
"(select avg(total)/8 from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'CC') as 'M.MRK'\n" +
"from final_marks \n" +
"where final_marks.class = '"+classx+"' and final_marks.term = '"+term+"' and final_marks.year = '"+year+"' and house = 'CC'\n" +
"\n" +
"union\n" +
"\n" +
"select DISTINCT CASE final_marks.[house] WHEN 'OS' THEN 'OS' ELSE 'OS' END as 'House',\n" +
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
"(select avg(total)/8 from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'OS') as 'M.MRK'\n" +
"from final_marks \n" +
"where final_marks.class = '"+classx+"' and final_marks.term = '"+term+"' and final_marks.year = '"+year+"' and house = 'OS'\n" +
"\n" +
"UNION\n" +
"\n" +
"select DISTINCT CASE final_marks.[house] WHEN 'PE' THEN 'PE' ELSE 'PE' END as 'House',\n" +
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
"(select avg(total)/8 from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'PE') as 'M.MRK'\n" +
"from final_marks \n" +
"where final_marks.class = '"+classx+"' and final_marks.term = '"+term+"' and final_marks.year = '"+year+"' and house = 'PE'\n" +
"\n" +
"union\n" +
"\n" +
"select DISTINCT CASE final_marks.[house] WHEN 'EA' THEN 'EA' ELSE 'EA' END as 'House',\n" +
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
"(select avg(total)/8 from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"' and house = 'EA') as 'M.MRK'\n" +
"from final_marks \n" +
"where final_marks.class = '"+classx+"' and final_marks.term = '"+term+"' and final_marks.year = '"+year+"' and house = 'EA'\n" +
"\n" +
"union all\n" +
"\n" +
"select DISTINCT CASE final_marks.[house] WHEN 'EA' THEN 'TOTALS' ELSE 'TOTALS' END as 'House',\n" +
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
"(select avg(total)/8 from final_marks where class = '"+classx+"' and term = '"+term+"' and year = '"+year+"') as 'M.MRK'\n" +
"from final_marks \n" +
"where final_marks.class = '"+classx+"' and final_marks.term = '"+term+"' and final_marks.year = '"+year+"'\n" +
"";

                    pst=conn.prepareStatement(sql);
                    rs=pst.executeQuery();
                    mainFrame.tbl_houseAnaMarks.setModel(DbUtils.resultSetToTableModel(rs));
                    
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for(int x=0;x<16;x++){
         mainFrame.tbl_houseAnaMarks.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
         mainFrame.tbl_houseAnaMarks.getColumnModel().getColumn(x).setHeaderRenderer(centerRenderer);
        }
                              
        } catch(ClassNotFoundException | SQLException ex){
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
}
