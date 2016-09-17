package AppPackage;


import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author home
 */
public class kiswahili {
    Connection conn=null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    //----------------------methods for kiswahili marks process checks-------------------------
    public void checkIfEdit(String admin_nos, String examType, String classx, String Term, String Year ){
        
        try{
            conn=connect.ConnecrDb();
            String sql = "select * from raw_marks where admin_nos='"+admin_nos+"' and exam_type ='"+examType+"' and "
                    + "class=? and term=? and year=? ";
            pst=conn.prepareStatement(sql);
            pst.setString(1, classx);
            pst.setString(2, Term);
            pst.setString(3, Year);
            rs=pst.executeQuery();
            if(rs.next()){
              edit="yes";
              //JOptionPane.showMessageDialog(null, edit);
            }else{
              edit="no"; 
              //JOptionPane.showMessageDialog(null, edit);
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
        
    }
    
    public void populateRawMarks( String fullName, String admin_nos, String house, String examType, String marks,
    String classx, String Term, String Year, String className){
        
        if(edit.equals("no")){
                  try{
                      
                  String sql = "insert into raw_marks (name,admin_nos,house,term,class,class_name,year,exam_type,kiswahili)values(?,?,?,?,?,?,?,?,?)";
                  conn=connect.ConnecrDb(); 
                  pst=conn.prepareStatement(sql);
                  pst.setString(1, fullName);
                  pst.setString(2, admin_nos);
                  pst.setString(3, house);
                  pst.setString(4, Term);
                  pst.setString(5, classx);
                  pst.setString(6, className);
                  pst.setString(7, Year);
                  pst.setString(8, examType);
                  pst.setString(9, marks);
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
               
                }else if(edit.equals("yes")){
                    try{
                      
                        String sql = "update raw_marks set kiswahili = ? where admin_nos = ? and term = ? and class = ? and class_name = ? and year = ? and exam_type = ? ";
                        conn=connect.ConnecrDb(); 
                        pst=conn.prepareStatement(sql);
                        pst.setString(1, marks);
                        pst.setString(2, admin_nos);
                        pst.setString(3, Term);
                        pst.setString(4, classx);
                        pst.setString(5, className);
                        pst.setString(6, Year);
                        pst.setString(7, examType);
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
        
    }
    
    public void prossFinalMark(String admin_nos,String classx, String Term, String Year){
        //process the final marks
        try{
                    
          conn=connect.ConnecrDb();
          String sql = "SELECT (select kiswahili from raw_marks where admin_nos = '"+admin_nos+"' and exam_type = 'EN' and term = '"+Term+"' and class = '"+classx+"' and year = '"+Year+"') AS 'EN', " +
"(select kiswahili from raw_marks where admin_nos = '"+admin_nos+"' and exam_type = 'MD' and term = '"+Term+"' and class = '"+classx+"' and year = '"+Year+"') AS 'MD', " +
"(select kiswahili from raw_marks where admin_nos = '"+admin_nos+"' and exam_type = 'ED' and term = '"+Term+"' and class = '"+classx+"' and year = '"+Year+"') AS 'ED' " +
"from raw_marks where admin_nos = '"+admin_nos+"' and term = '"+Term+"' and class = '"+classx+"' and year = '"+Year+"' limit 1 ";
         pst=conn.prepareStatement(sql);              
         rs=pst.executeQuery();
         
         if(rs.next()){
                String first_marks = rs.getString("EN");
                if(first_marks==null){
                    first_marks = "0";
                }
                                
                String mid_marks = rs.getString("MD");
                if(mid_marks==null){
                    mid_marks = "0";
                }
                
                String last_marks = rs.getString("ED");
                if(last_marks==null){
                    last_marks = "0";
                }
                //calculation of final marks                                
                float cats= (float) (Float.parseFloat(first_marks) + Float.parseFloat(mid_marks));
                
                //get and set marks calculation variables
                double varCat = Double.valueOf(catVar);
                double varExam = Double.valueOf(examVar);
                
                float mark1 = (float) (cats*varCat);
                float mark2 = (float) (Float.parseFloat(last_marks)*varExam);
                int final_marks = Math.round(mark1+mark2);
                mainFrame.txt_finalMarksKis.setText(""+final_marks);
                //JOptionPane.showMessageDialog(null, "First marks: "+first_marks+" Second marks: "+mid_marks+"  Final marks: "+final_marks);         
            }
            
        }catch(HeadlessException | ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }finally{
             try {
                   conn.close();
                   pst.close();
                   rs.close();
             } catch (SQLException ex) {
                 //JOptionPane.showMessageDialog(null, ex);
             }
        } 
    }
    
    public void checkFinalEdit(String admin_nos,String classx, String Term, String Year){
        
        try{
            conn=connect.ConnecrDb();
            String sql = "select * from final_marks where admin_nos='"+admin_nos+"' and "
                    + "class=? and term=? and year=? ";
            pst=conn.prepareStatement(sql);
            pst.setString(1, classx);
            pst.setString(2, Term);
            pst.setString(3, Year);
            rs=pst.executeQuery();
            if(rs.next()){
              final_edit="yes";
              //JOptionPane.showMessageDialog(null, final_edit);
            }else{
              final_edit="no"; 
              //JOptionPane.showMessageDialog(null, final_edit);
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
        
    }
    
    public void getGender(String admin_nos){
        try{
            
            conn=connect.ConnecrDb();
            String sql = "select * from students where admin_nos=? ";
            pst=conn.prepareStatement(sql);
            pst.setString(1, admin_nos);
            rs=pst.executeQuery();
            if(rs.next()){
              gender= rs.getString("gender");;
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
    
    public void populateFinalMarks( String fullName, String admin_nos, String house, String classx, String Term,
            String Year, String className ){
        
        if(final_edit.equals("no")){
                  try{
                      
                  String sql = "insert into final_marks (name,admin_nos,house,term,class,class_name,year,kiswahili,kiswahili_g,gender,kis_rem)values(?,?,?,?,?,?,?,?,?,?,?)";
                  conn=connect.ConnecrDb(); 
                  pst=conn.prepareStatement(sql);
                  pst.setString(1, fullName);
                  pst.setString(2, admin_nos);
                  pst.setString(3, house);
                  pst.setString(4, Term);
                  pst.setString(5, classx);
                  pst.setString(6, className);
                  pst.setString(7, Year);
                  pst.setString(8, mainFrame.txt_finalMarksKis.getText());
                  pst.setString(10, gender);
                  int avg_m = Integer.parseInt(mainFrame.txt_finalMarksKis.getText());
                  
                  if (avg_m <=29.5){
                    pst.setString(9, "E");   
                    pst.setString(11, "Put in more effort.");
                  }else if((avg_m >=29.6) && (avg_m <=34.5) ){
                    pst.setString(9, "D-"); 
                    pst.setString(11, "Must work hard.");
                  }else if((avg_m >=34.6) && (avg_m <=39.5) ){
                    pst.setString(9, "D");  
                    pst.setString(11, "You need to work.");
                  }else if((avg_m >=39.6) && (avg_m <=44.5) ){
                    pst.setString(9, "D+"); 
                    pst.setString(11, "Work hard.");
                  }else if((avg_m >=44.6) && (avg_m <=49.5) ){
                    pst.setString(9, "C-");
                    pst.setString(11, "Work hard.");
                  }else if((avg_m >=49.6) && (avg_m <=54.5) ){
                    pst.setString(9, "C"); 
                    pst.setString(11, "You have potential.");
                  }else if((avg_m >=54.6) && (avg_m <=59.5) ){
                    pst.setString(9, "C+");  
                    pst.setString(11, "Average.");
                  }else if((avg_m >=59.6) && (avg_m <=64.5) ){
                    pst.setString(9, "B-");   
                    pst.setString(11, "Fair.");
                  }else if((avg_m >=64.6) && (avg_m <=69.5) ){
                    pst.setString(9, "B");  
                    pst.setString(11, "Aim higher.");
                  }else if((avg_m >=69.6) && (avg_m <=74.5) ){
                    pst.setString(9, "B+"); 
                    pst.setString(11, "Very good.");
                  }else if((avg_m >=74.6) && (avg_m <=79.5) ){
                    pst.setString(9, "A-");  
                    pst.setString(11, "Very good.");
                  }else if(avg_m >=79.6){
                    pst.setString(9, "A");  
                    pst.setString(11, "Excellent!.");
                  }
                  
                  pst.execute();
                  mainFrame.txt_finalMarksKis.setText("");
                  
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
               
                }else if(final_edit.equals("yes")){
                    try{
                      
                        String sql = "update final_marks set kiswahili = ?, kiswahili_g = ?, gender = ?, kis_rem = ? where admin_nos = ? and term = ? and class = ? and class_name = ? and year = ? ";
                        conn=connect.ConnecrDb(); 
                        pst=conn.prepareStatement(sql);
                        pst.setString(1, mainFrame.txt_finalMarksKis.getText());
                        pst.setString(3, gender);
                        pst.setString(5, admin_nos);
                        pst.setString(6, Term);
                        pst.setString(7, classx);
                        pst.setString(8, className);
                        pst.setString(9, Year);
                        int avg_m = Integer.parseInt(mainFrame.txt_finalMarksKis.getText());
                  
                        if (avg_m <=29.5){
                            pst.setString(2, "E");  
                            pst.setString(4, "Put in more effort.");
                          }else if((avg_m >=29.6) && (avg_m <=34.5) ){
                            pst.setString(2, "D-");  
                            pst.setString(4, "Must work hard.");
                          }else if((avg_m >=34.6) && (avg_m <=39.5) ){
                            pst.setString(2, "D");
                            pst.setString(4, "You need to work.");
                          }else if((avg_m >=39.6) && (avg_m <=44.5) ){
                            pst.setString(2, "D+"); 
                            pst.setString(4, "Work hard.");
                          }else if((avg_m >=44.6) && (avg_m <=49.5) ){
                            pst.setString(2, "C-");
                            pst.setString(4, "Work hard.");
                          }else if((avg_m >=49.6) && (avg_m <=54.5) ){
                            pst.setString(2, "C"); 
                            pst.setString(4, "You have potential.");
                          }else if((avg_m >=54.6) && (avg_m <=59.5) ){
                            pst.setString(2, "C+"); 
                            pst.setString(4, "Average.");
                          }else if((avg_m >=59.6) && (avg_m <=64.5) ){
                            pst.setString(2, "B-"); 
                            pst.setString(4, "Fair.");
                          }else if((avg_m >=64.6) && (avg_m <=69.5) ){
                            pst.setString(2, "B"); 
                            pst.setString(4, "Aim higher.");
                          }else if((avg_m >=69.6) && (avg_m <=74.5) ){
                            pst.setString(2, "B+"); 
                            pst.setString(4, "Very good.");
                          }else if((avg_m >=74.6) && (avg_m <=79.5) ){
                            pst.setString(2, "A-");
                            pst.setString(4, "Very good.");
                          }else if(avg_m >=79.6){
                            pst.setString(2, "A");
                            pst.setString(4, "Excellent!.");
                          }
                        
                        pst.execute();
                        mainFrame.txt_finalMarksKis.setText("");

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
    
    public void updateFinalMarkTally(String admin_nos, String Term, String classx, String Year){
        try{
            String sql = "update final_marks set total = ?, avarage = ?, grade = ? where admin_nos = ? and term = ? and class = ? and year = ? ";
            conn=connect.ConnecrDb(); 
            pst=conn.prepareStatement(sql);
            pst.setString(1, ""+total_marks);
            pst.setString(2, ""+avg);
            pst.setString(3, mean_grade);
            pst.setString(4, admin_nos);
            pst.setString(5, Term);
            pst.setString(6, classx);
            pst.setString(7, Year);
            
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
        
    };
    //<-------------calculates final tally for all subjects-------------------->
    public void getFinalTotalMarks(String admin_nos, String Term, String classx, String Year){
        try{
            
            String sql = "select sum( case when english is null then 0 else english end + case when kiswahili is null then 0 else kiswahili end " +
"+ case when maths is null then 0 else maths end + case when physics is null then 0 else physics end " +
"+ case when biology is null then 0 else biology end + case when chemistry is null then 0 else chemistry end " +
"+ case when geography is null then 0 else geography end + case when histroy is null then 0 else histroy end " +
"+ case when cre is null then 0 else cre end + case when agriculture is null then 0 else agriculture end " +
"+ case when buisness_studies is null then 0 else buisness_studies end ) as 'total' " +
" from final_marks where admin_nos = ? and term = ? and class = ? and year = ?";
                        conn=connect.ConnecrDb(); 
                        pst=conn.prepareStatement(sql);
                        pst.setString(1, admin_nos);
                        pst.setString(2, Term);
                        pst.setString(3, classx);
                        pst.setString(4, Year);
                        rs=pst.executeQuery();
         
                        if(rs.next()){
                            //total marks
                           String total_marksx = rs.getString("total");
                           total_marks = Double.parseDouble(total_marksx);
                           
                           int classy = Integer.parseInt(classx);
                           int subject = 0;
                           if(classy<=2){
                               subject= 11; 
                           }else if(classy>=3 ){
                               subject= 8;
                           }
                           //AVG marks
                           double avgx = total_marks/subject;
                           DecimalFormat df = new DecimalFormat ("#.##");
                         
                           avg = Double.valueOf(df.format(avgx));
                           
                         if (avg <= 29.59){
                          mean_grade = "E";   
                        }else if((avg >=29.60) && (avg <=34.59) ){
                          mean_grade = "D-";   
                        }else if((avg >=34.60) && (avg <=39.59) ){
                          mean_grade = "D";   
                        }else if((avg >=39.60) && (avg <=44.59) ){
                          mean_grade = "D+"; 
                        }else if((avg >=44.60) && (avg <=49.59) ){
                          mean_grade = "C-";
                        }else if((avg >=49.60) && (avg <=54.59) ){
                          mean_grade = "C";  
                        }else if((avg >=54.60) && (avg <=59.59) ){
                          mean_grade = "C+";   
                        }else if((avg >=59.60) && (avg <=64.59) ){
                          mean_grade = "B-";   
                        }else if((avg >=64.60) && (avg <=69.59) ){
                          mean_grade = "B";   
                        }else if((avg >=69.60) && (avg <=74.59) ){
                          mean_grade = "B+";   
                        }else if((avg >=74.60) && (avg <=79.59) ){
                          mean_grade = "A-";   
                        }else if(avg >=79.60){
                          mean_grade = "A";   
                        }
                           
                       //JOptionPane.showMessageDialog(null, "Total marks: "+total_marks+" Avg marks: "+avg+" Mean grade: "+mean_grade);
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
    
    //<-------------rank classes perfomace table processes-------------------->
    public void checkRankTbl(String Term, String classx, String Year, String className){
        
        try{
            conn=connect.ConnecrDb();
            String sql = "select * from rank where class='"+classx+"' and class_name ='"+className+"' and "
                    + "term='"+Term+"' and year='"+Year+"' ";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
              edit_rank="yes";
              //JOptionPane.showMessageDialog(null, edit);
            }else{
              edit_rank="no"; 
              //JOptionPane.showMessageDialog(null, edit);
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
        
    }
    
    public void populateRankTbl(String Term, String classx, String Year, String className){
        
        rankClassCalc(Term,classx,Year,className);
        rankFormCalc(Term,classx,Year);
        
         if(edit_rank.equals("no")){
            try{
                String sql = "insert into rank (term,class,class_name,year,c_avarage,f_avarage,c_grade,f_grade)"
                        + "values(?,?,?,?,?,?,?,?)";
                  conn=connect.ConnecrDb(); 
                  pst=conn.prepareStatement(sql);
                  pst.setString(1, Term);
                  pst.setString(2, classx);
                  pst.setString(3, className);
                  pst.setString(4, Year);
                  pst.setString(5, ""+c_avg);
                  pst.setString(6, ""+f_avg);
                  
                  if (c_avg <=29.5){
                            pst.setString(7, "E");   
                          }else if((c_avg >=29.6) && (c_avg <=34.5) ){
                            pst.setString(7, "D-");   
                          }else if((c_avg >=34.6) && (c_avg <=39.5) ){
                            pst.setString(7, "D");   
                          }else if((c_avg >=39.6) && (c_avg <=44.5) ){
                            pst.setString(7, "D+"); 
                          }else if((c_avg >=44.6) && (c_avg <=49.5) ){
                            pst.setString(7, "C-");
                          }else if((c_avg >=49.6) && (c_avg <=54.5) ){
                            pst.setString(7, "C");  
                          }else if((c_avg >=54.6) && (c_avg <=59.5) ){
                            pst.setString(7, "C+");   
                          }else if((c_avg >=59.6) && (c_avg <=64.5) ){
                            pst.setString(7, "B-");   
                          }else if((c_avg >=64.6) && (c_avg <=69.5) ){
                            pst.setString(7, "B");   
                          }else if((c_avg>=69.6) && (c_avg <=74.5) ){
                            pst.setString(7, "B+");   
                          }else if((c_avg >=74.6) && (c_avg <=79.5) ){
                            pst.setString(7, "A-");   
                          }else if(c_avg >=79.6){
                            pst.setString(7, "A");   
                          }
                  
                  if (f_avg <=29.5){
                            pst.setString(8, "E");   
                          }else if((f_avg >=29.6) && (f_avg <=34.5) ){
                            pst.setString(8, "D-");   
                          }else if((f_avg >=34.6) && (f_avg <=39.5) ){
                            pst.setString(8, "D");   
                          }else if((f_avg >=39.6) && (f_avg <=44.5) ){
                            pst.setString(8, "D+"); 
                          }else if((f_avg >=44.6) && (f_avg <=49.5) ){
                            pst.setString(8, "C-");
                          }else if((f_avg >=49.6) && (f_avg <=54.5) ){
                            pst.setString(8, "C");  
                          }else if((f_avg >=54.6) && (f_avg <=59.5) ){
                            pst.setString(8, "C+");   
                          }else if((f_avg >=59.6) && (f_avg <=64.5) ){
                            pst.setString(8, "B-");   
                          }else if((f_avg >=64.6) && (f_avg <=69.5) ){
                            pst.setString(8, "B");   
                          }else if((f_avg>=69.6) && (f_avg <=74.5) ){
                            pst.setString(8, "B+");   
                          }else if((f_avg >=74.6) && (f_avg <=79.5) ){
                            pst.setString(8, "A-");   
                          }else if(f_avg >=79.6){
                            pst.setString(8, "A");   
                          }
                  
                  
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
            
         }else if(edit_rank.equals("yes")){
             
              try{
                String sql = "update rank set term=?,class=?,class_name=?,year=?,c_avarage=?,"
                        + "f_avarage=?,c_grade=?,f_grade=? where class='"+classx+"' and class_name ='"+className+"' and "
                    + "term='"+Term+"' and year='"+Year+"' " ;
                  conn=connect.ConnecrDb(); 
                  pst=conn.prepareStatement(sql);
                  pst.setString(1, Term);
                  pst.setString(2, classx);
                  pst.setString(3, className);
                  pst.setString(4, Year);
                  pst.setString(5, ""+c_avg);
                  pst.setString(6, ""+f_avg);
                  
                  if (c_avg <=29.5){
                            pst.setString(7, "E");   
                          }else if((c_avg >=29.6) && (c_avg <=34.5) ){
                            pst.setString(7, "D-");   
                          }else if((c_avg >=34.6) && (c_avg <=39.5) ){
                            pst.setString(7, "D");   
                          }else if((c_avg >=39.6) && (c_avg <=44.5) ){
                            pst.setString(7, "D+"); 
                          }else if((c_avg >=44.6) && (c_avg <=49.5) ){
                            pst.setString(7, "C-");
                          }else if((c_avg >=49.6) && (c_avg <=54.5) ){
                            pst.setString(7, "C");  
                          }else if((c_avg >=54.6) && (c_avg <=59.5) ){
                            pst.setString(7, "C+");   
                          }else if((c_avg >=59.6) && (c_avg <=64.5) ){
                            pst.setString(7, "B-");   
                          }else if((c_avg >=64.6) && (c_avg <=69.5) ){
                            pst.setString(7, "B");   
                          }else if((c_avg>=69.6) && (c_avg <=74.5) ){
                            pst.setString(7, "B+");   
                          }else if((c_avg >=74.6) && (c_avg <=79.5) ){
                            pst.setString(7, "A-");   
                          }else if(c_avg >=79.6){
                            pst.setString(7, "A");   
                          }
                  
                  if (f_avg <=29.5){
                            pst.setString(8, "E");   
                          }else if((f_avg >=29.6) && (f_avg <=34.5) ){
                            pst.setString(8, "D-");   
                          }else if((f_avg >=34.6) && (f_avg <=39.5) ){
                            pst.setString(8, "D");   
                          }else if((f_avg >=39.6) && (f_avg <=44.5) ){
                            pst.setString(8, "D+"); 
                          }else if((f_avg >=44.6) && (f_avg <=49.5) ){
                            pst.setString(8, "C-");
                          }else if((f_avg >=49.6) && (f_avg <=54.5) ){
                            pst.setString(8, "C");  
                          }else if((f_avg >=54.6) && (f_avg <=59.5) ){
                            pst.setString(8, "C+");   
                          }else if((f_avg >=59.6) && (f_avg <=64.5) ){
                            pst.setString(8, "B-");   
                          }else if((f_avg >=64.6) && (f_avg <=69.5) ){
                            pst.setString(8, "B");   
                          }else if((f_avg>=69.6) && (f_avg <=74.5) ){
                            pst.setString(8, "B+");   
                          }else if((f_avg >=74.6) && (f_avg <=79.5) ){
                            pst.setString(8, "A-");   
                          }else if(f_avg >=79.6){
                            pst.setString(8, "A");   
                          }
                  
                  
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
    }
    
    public void rankClassCalc(String Term, String classx, String Year, String className){
        try{
            conn=connect.ConnecrDb();
            String sql = "select count(id), sum(total), avg(total) from final_marks where "
                    + "class='"+classx+"' and class_name ='"+className+"' and "
                    + "term='"+Term+"' and year='"+Year+"' ";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
              double Cmean= Double.parseDouble(rs.getString("avg(total)"));
              
              int classy = Integer.parseInt(classx);
                           int subject = 0;
                           if(classy<=2){
                               subject= 11; 
                           }else if(classy>=3 ){
                               subject= 8;
                           }
              //class AVG marks
             double avgx = Cmean/subject;              
             DecimalFormat df = new DecimalFormat ("#.##");
             c_avg = Double.valueOf(df.format(avgx));
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
    
    public void rankFormCalc(String Term, String classx, String Year){
        try{
            conn=connect.ConnecrDb();
            String sql = "select count(id), sum(total), avg(total) from final_marks where "
                    + "class='"+classx+"' and term='"+Term+"' and year='"+Year+"' ";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
              double Fmean= Double.parseDouble(rs.getString("avg(total)"));
              
              int classy = Integer.parseInt(classx);
                           int subject = 0;
                           if(classy<=2){
                               subject= 11; 
                           }else if(classy>=3 ){
                               subject= 8;
                           }
              
              //form AVG marks
             double avgx = Fmean/subject;                
             DecimalFormat df = new DecimalFormat ("#.##");
             f_avg = Double.valueOf(df.format(avgx));
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
    

String catVar = login.lbl_catVar.getText();
String examVar = login.lbl_examVar.getText();
String user = "test";
String edit;
String final_edit;
String edit_rank;
String gender;
//---------------------------------for final results----------------------------
String mean_grade;
double avg;
double total_marks;
//---------------------------------for rank results----------------------------
double c_avg;
double f_avg;
    
}
