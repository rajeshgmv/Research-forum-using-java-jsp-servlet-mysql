package data;

import java.util.ArrayList;


import business.Reports;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 
 * @author mohan
 * All the manipulations on the Reports takes place here
 */


	//private static ArrayList<Reports> reportsList = new ArrayList<>();


	
        
  public class ReportDB {     
  public  ReportDB() {
      
  }
      public static ArrayList<Reports> getUserReports(String eMail) {
          ArrayList<Reports> reports = new ArrayList<Reports>();
          ConnectionPool pool = ConnectionPool.getInstance();
          Connection connection = pool.getConnection();
          PreparedStatement ps = null;
          ResultSet rs=null;
          String query = "select q.Question,r.DateSubmitted,r.Status from reported r ,question q "
                  + " where UserName=? and q.StudyID=r.StudyID and q.QuestionID=r.QuestionID";
          try {
              ps = connection.prepareStatement(query);
              ps.setString(1,eMail);
              rs=ps.executeQuery();
              Reports report=null;
              while(rs.next()){
                  report=new Reports();
                  report.setQuestion(rs.getString(1));
                  report.setDateReported(rs.getString(2));
                  report.setStatus(rs.getString(3));
                  reports.add(report);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }

          return reports;
      }
  public void  addReport(Reports reportedqn){
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
         String query
                = "INSERT INTO reported (StudyID, QuestionId, Username, DateSubmitted,NumParticipants,Status)"
                + "VALUES (?, ?, ?, NOW(), ?,?)";
         try{
             
                        ps = connection.prepareStatement(query);
                        ps.setString(1, reportedqn.getStudyCode());
                        ps.setString(2, reportedqn.getQuestionID());
                        ps.setString(3, reportedqn.getUserName());
                        //ps.setDate(3,reportedqn.getDateReported());
                       ps.setString(4, reportedqn.getNumOfParitipants());
                        ps.setString(5, reportedqn.getStatus()); 
                        
                        ps.executeUpdate();
         }
         catch (SQLException e) {
            System.out.println(e);
         } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
  }
        public ArrayList<Reports> getAllReports(){
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
                ResultSet rs = null;

         String query="SELECT * from reported WHERE Status=?";
         try{
              ps = connection.prepareStatement(query);
              ps.setString(1, "Under review");
                rs = ps.executeQuery();
                ArrayList<Reports> reportedqnslist=new ArrayList<Reports>();
                while(rs.next()){
                    
                
                Reports reportedqn= new Reports();
              reportedqn.setQuestionID(rs.getString("QuestionID"));
              reportedqn.setStudyCode(rs.getString("StudyID"));
           //   reportedqn.setDateReported(rs.getDate("Date"));
              reportedqn.setNumOfParitipants(rs.getString("NumParticipants"));
            reportedqn.setStatus("Under review");
               reportedqnslist.add(reportedqn) ;
            
                }
              return reportedqnslist;

         } catch (SQLException e) {
            System.out.println(e);
            return null;
         } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }  
    }
        public static ArrayList<Reports> getPendingReports() {
          ArrayList<Reports> reports = new ArrayList<Reports>();
//public ArrayList<Reports> getPendingReports(){
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
          String query = "select r.UserName,r.StudyID,r.QuestionID,q.Question from reported r, question q  WHERE Status='Under review' AND q.QuestionID=r.QuestionID AND q.StudyID=r.StudyID ";
          try {
              ps = connection.prepareStatement(query);
              rs=ps.executeQuery();
              Reports report=null;
              while(rs.next()){
                  report=new Reports();
                  report.setUserName(rs.getString(1));
                  report.setStudyCode(rs.getString(2));
                  report.setQuestionID(rs.getString(3));
                   report.setQuestion(rs.getString(4));
                  reports.add(report);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }

          return reports;
      } 
        public static void updateStatus(String studyCode, String status, String questionID){
ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
         String query
                = "UPDATE reported SET Status=? WHERE StudyID=? AND QuestionID=?";
         try{
             
                        ps = connection.prepareStatement(query);
                        ps.setString(1, status);
                        ps.setString(2, studyCode);
                        ps.setString(3, questionID);
                        ps.executeUpdate();
         }
         catch (SQLException e) {
            System.out.println(e);
         } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
  }
}       