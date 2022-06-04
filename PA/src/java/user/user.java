/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author cagatay
 */
@Named(value = "user")
@SessionScoped
public class user implements Serializable {

  private String CID,STC,TTC,SPASSWORD,TPASSWORD,takenclass,notver,studentNo;
  private int midterm,finals;
  private String newmail,newnumber,newpass;
  
  String a1,a2,a3,a4,a5,a6,a7,a8;

    public String getNewmail() {
        return newmail;
    }

    public void setNewmail(String newmail) {
        this.newmail = newmail;
    }

    public String getNewnumber() {
        return newnumber;
    }

    public void setNewnumber(String newnumber) {
        this.newnumber = newnumber;
    }

    public String getNewpass() {
        return newpass;
    }

    public void setNewpass(String newpass) {
        this.newpass = newpass;
    }

    public String getA8() {
        return a8;
    }

    public void setA8(String a8) {
        this.a8 = a8;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getA3() {
        return a3;
    }

    public void setA3(String a3) {
        this.a3 = a3;
    }

    public String getA4() {
        return a4;
    }

    public void setA4(String a4) {
        this.a4 = a4;
    }

    public String getA5() {
        return a5;
    }

    public void setA5(String a5) {
        this.a5 = a5;
    }

    public String getA6() {
        return a6;
    }

    public void setA6(String a6) {
        this.a6 = a6;
    }

    public String getA7() {
        return a7;
    }

    public void setA7(String a7) {
        this.a7 = a7;
    }

   
  

  
  private ResultSet rs2;
  
  
    public user() 
    {
    }
    Connection conn;
    public Connection baglan() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            System.out.println("baglanti basarili");
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/otomat");
        } catch (Exception e) {
            System.out.println("baglantida sorun var");
        }
        return conn;
    }
    
   
    public ResultSet tdersler() throws SQLException{
    
        if (conn == null) {
            System.out.println("veritabani bagli degil. baglaniyorum.");
            baglan();
        }
        try {
            String sql="SELECT DERSLER.CID, DERSLER.CLASSNAME FROM DERSLER WHERE DERSLER.TTC='" + TTC + "'";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(rs);
            return resultSet1;

        } finally {

        }
    
    }
    
    public String deleteThings()throws SQLException{
    
    if (conn == null) {
            System.out.println("veritabani bagli degil. baglaniyorum.");
            baglan();
        }
        try {
        String sql="DELETE FROM LOGINS WHERE STC='"+STC+"'";
        String sq2="DELETE FROM student WHERE STC='"+STC+"'";
        String sq3="DELETE FROM takenclass WHERE STC='"+STC+"'";
        
                PreparedStatement ps2 = conn.prepareStatement(sq2);                
                ps2.executeUpdate();
                PreparedStatement ps3 = conn.prepareStatement(sq3);                
                ps3.executeUpdate();
                PreparedStatement ps = conn.prepareStatement(sql);                
                ps.executeUpdate();
      
     

        } catch(Exception se) {
            return "youcantdothat.xhtml";

        }
      return "index.xhtml";  
    }
    public String biktim()throws SQLException{
    
    if (conn == null) {
            System.out.println("veritabani bagli degil. baglaniyorum.");
            baglan();
        }
        try {
        String sql="UPDATE logins set password= '"+newpass+"' WHERE STC='"+STC+"'";
        PreparedStatement ps = conn.prepareStatement(sql);                
                ps.executeUpdate();
      
     

        } catch(Exception se) {
            return "youcantdothat.xhtml";

        }
      return "passok.xhtml";  
    }
    
   
    public void ogrenci() throws SQLException{
        String[] off = new String[7];
                
                
        if (conn == null) {
            System.out.println("veritabani bagli degil. baglaniyorum.");
            baglan();
        }
        try {
        String sql="select studentname,studentsurname,studentno,logins.study,gpa,email,phonenumber,password from logins,student where student.stc=logins.stc and student.stc ='"+STC+"'";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
           
        while (rs.next()){
                    
                       a1=rs.getString(1);
                       a2=rs.getString(2);
                       a3=rs.getString(3);
                       a4=rs.getString(4);
                       a5=rs.getString(5);
                       a6=rs.getString(6);
                       a7=rs.getString(7);
                       a8=rs.getString(8);
                       
                       
                       
                    
}
        
            
            
            
            

        } finally {

        }
    
    }
    
    
    public String buttonClick(String cid) {
       setCID(cid);
   return "students.xhtml";
        
        
    }
    public String buttonClick2(String cid) {
       setCID(cid);
   return "seeinfo.xhtml";
        
    }
    
     public ResultSet seeinfo  () throws SQLException{
      if (conn == null) {
            System.out.println("veritabani bagli degil. baglaniyorum.");
            baglan();
        }
         
        try {
            String sql="SELECT CID,COUNT(CID) as ty,AVG(MIDTERM) as e1, AVG(FINAL) as e2 FROM TAKENCLASS GROUP BY TAKENCLASS.CID HAVING TAKENCLASS.CID ='"+CID+"'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(rs);
            return resultSet1;
            
            
        } finally {

        
     }
      
     }
    
    
    public String notVer (String sno) throws SQLException{    
       // studentNo = sno;
       String takenclassSTC="";
        if (conn == null) {
            System.out.println("veritabani bagli degil. baglaniyorum.");
            baglan();
        }
        try {
            
            String sql;
           
                sql="SELECT STUDENT.STC FROM STUDENT WHERE STUDENTNO = '"+sno+"'";
                
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            // ögrencinin studentnosundan stc sini buluyoruz ki sqlde manipüle edebilek
            while(rs.next()){
                takenclassSTC = rs.getString(1);
            }
                
                String sql2;
                String veri= CID + takenclassSTC;
                sql2="UPDATE TAKENCLASS SET midterm="+midterm+ ",FINAL=" + finals+"WHERE TAKENCLASS.VERIFICATION ='"+veri+"'";
                PreparedStatement ps = conn.prepareStatement(sql2);                
                ps.executeUpdate();
            }
          
         
        catch(Exception se) {
            return "notverYANLIS.xhtml";

        }
      return null;
    
    }
    
     public ResultSet students1() throws SQLException{
                
        if (conn == null) {
            System.out.println("veritabani bagli degil. baglaniyorum.");
            baglan();
        }
         
        try {
            String sql="SELECT LOGINS.STUDENTNAME, LOGINS.STUDENTSURNAME, STUDENT.STUDENTNO, TAKENCLASS.MIDTERM, TAKENCLASS.FINAL FROM STUDENT,LOGINS INNER JOIN TAKENCLASS ON TAKENCLASS.STC=LOGINS.STC WHERE STUDENT.STC = LOGINS.STC AND TAKENCLASS.MIDTERM IS NULL AND TAKENCLASS.FINAL IS NULL AND TAKENCLASS.CID= '"+CID+"' ORDER BY  STUDENTNO asc";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(rs);
            return resultSet1;
            
        } finally {

        
     }
     }
    
   
    
    public String dersAl() throws SQLException{
        Statement stmt = null;
        String[] arrSplit = takenclass.split(",");
        if (conn == null) {
            System.out.println("veritabani bagli degil. baglaniyorum.");
            baglan();
        }
        try {
            
            for(int i=0; i<arrSplit.length;i++){
            String Verification = arrSplit[i]+STC;
            String sql;
           
                sql="INSERT INTO TAKENCLASS VALUES( '" + arrSplit[i] + "','" + STC + "',null,null,null,'" + Verification + "')";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            
           }
            
            

        }  catch(Exception se) {
            return "wrongclass.xhtml";

        }
      
            return "rightclass.xhtml";
        }
    
    
    //DERS GÖSTERME
    public ResultSet dersler() throws SQLException{
    
  
        if (conn == null) {
            System.out.println("veritabani bagli degil. baglaniyorum.");
            baglan();
        }
        try {
            
            PreparedStatement ps = conn.prepareStatement("SELECT CID ,  CLASSNAME , TEACHER.TEACHERNAME  FROM DERSLER,TEACHER WHERE DERSLER.STUDY=  (SELECT STUDY  FROM LOGINS WHERE STC= '" + STC + "') AND DERSLER.TTC=TEACHER.TTC");
            
            ResultSet rs = ps.executeQuery();
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(rs);
            return resultSet1;

        } finally {

        }
    
    }
    
    //alınan dersleri gösterme
    public ResultSet dersGor() throws SQLException{
    
        System.out.println(STC);
        if (conn == null) {
            System.out.println("veritabani bagli degil. baglaniyorum.");
            baglan();
        }
        try {
            String sql="SELECT TAKENCLASS.CID, DERSLER.CLASSNAME, TAKENCLASS.MIDTERM,TAKENCLASS.\"FINAL\",TAKENCLASS.HARFNOTU FROM TAKENCLASS INNER JOIN DERSLER ON TAKENCLASS.CID=DERSLER.CID WHERE TAKENCLASS.STC = '"+ STC+ "' ORDER BY CID";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(rs);
            return resultSet1;

        } finally {

        }
    
    }
    
    
    public String verifyS(){
        Connection conn = null;
        Statement stmt = null;
        boolean verified = false;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            // open connection to DB
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/otomat");
           
            stmt= conn.createStatement();
            String sql;
           
            sql = "SELECT * FROM LOGINS WHERE STC= '" + STC + "' AND PASSWORD='" + SPASSWORD + "'";  
            
            ResultSet rs = stmt.executeQuery(sql);
            
                   
            if(rs.next())
                verified = true;
            
            rs.close();
            stmt.close();
            conn.close();
                    
        }
        catch(Exception se){          
            return "error.xhtml";
        
        }
        if (verified)
            return "logincorrect.xhtml";
        else 
            return "error.xhtml";
    }
    
    
    public String verifyT(){
        Connection conn = null;
        Statement stmt = null;
        boolean verified = false;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            // open connection to DB
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/otomat");
           
            stmt= conn.createStatement();
            String sql;
            sql = "SELECT TTC,PASSWORD FROM TEACHER WHERE TTC= '" + TTC + "' AND PASSWORD='" + TPASSWORD + "'";  
            ResultSet rs = stmt.executeQuery(sql);
                   
            if(rs.next())
                verified = true;
            
            rs.close();
            stmt.close();
            conn.close();
                    
        }
        catch(Exception se){          
            return "Terror.xhtml";
        
        }
        if (verified)
            return "Tlogincorrect.xhtml";
        else 
            return "Terror.xhtml";
    }
    
    
       
    
    

    public String getSTC() {
        return STC;
    }

    public void setSTC(String STC) {
        this.STC = STC;
    }

    public String getTTC() {
        return TTC;
    }

    public void setTTC(String TTC) {
        this.TTC = TTC;
    }

    public String getSPASSWORD() {
        return SPASSWORD;
    }

    public void setSPASSWORD(String SPASSWORD) {
        this.SPASSWORD = SPASSWORD;
    }

    public String getTPASSWORD() {
        return TPASSWORD;
    }

    public void setTPASSWORD(String TPASSWORD) {
        this.TPASSWORD = TPASSWORD;
    }

     public ResultSet getRs2() {
        return rs2;
    }

    public void setRs2(ResultSet rs2) {
        this.rs2 = rs2;
    }

    public String getTakenclass() {
        return takenclass;
    }

    public void setTakenclass(String takenclass) {
        this.takenclass = takenclass;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public int getMidterm() {
        return midterm;
    }

    public void setMidterm(int midterm) {
        this.midterm = midterm;
    }
    
 
    

    public int getFinals() {
        return finals;
    }

    public void setFinals(int finals) {
        this.finals = finals;
    }
 
    

    
}
    
    
    
    
