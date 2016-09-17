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
 * @author caleb nasio
 */
public class subjectAnalysisView {
    Connection conn=null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    public void subAnaView(String term,int classx,String className,String year,String subject_g,String subject){
        
        try {
                                                        
                    conn=connect.ConnecrDb();
                                  
                    String sql = "select CASE final_marks.gender WHEN 'male' THEN 'Boys' ELSE 'Boys' END as 'Gender', " +
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
"from final_marks INNER JOIN teachers ON teachers.class = final_marks.class \n" +
"where final_marks.class = '"+classx+"' and final_marks.class_name = '"+className+"' and final_marks.term = '"+term+"' and final_marks.year = '"+year+"' and final_marks."+subject+" <>'' group by teachers."+subject+"\n" +
"\n" +
"union\n" +
"\n" +
"select DISTINCT CASE final_marks.gender WHEN 'female' THEN 'Girls' ELSE 'Girls' END as 'Gender', " +
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
"from final_marks INNER JOIN teachers ON teachers.class = final_marks.class \n" +
"where final_marks.class = '"+classx+"' and final_marks.class_name = '"+className+"' and final_marks.term = '"+term+"' and final_marks.year = '"+year+"' and final_marks."+subject+" <>'' group by teachers."+subject+"\n" +
"\n" +
"union all\n" +
"\n" +
"select DISTINCT CASE final_marks.gender WHEN 'female' THEN 'Both' ELSE 'Both' END as 'Gender', " +
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
"from final_marks INNER JOIN teachers ON teachers.class = final_marks.class \n" +
"where final_marks.class = '"+classx+"' and final_marks.class_name = '"+className+"' and final_marks.term = '"+term+"' and final_marks.year = '"+year+"' and final_marks."+subject+" <>''\n" +
"";

                    pst=conn.prepareStatement(sql);
                    rs=pst.executeQuery();
                    mainFrame.tbl_anaMarks.setModel(DbUtils.resultSetToTableModel(rs));
                    
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for(int x=0;x<16;x++){
         mainFrame.tbl_anaMarks.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
         mainFrame.tbl_anaMarks.getColumnModel().getColumn(x).setHeaderRenderer(centerRenderer);
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
