package data;

import business.Question;
import business.User;
import business.Study;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
/**
 * 
 * @author mohan
 *
 */
public class StudyDB {
    
    public static int addStudy(Study study) {
        java.sql.Date DateCreated=new java.sql.Date(new java.util.Date().getTime());
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        int count = 0;
        int count1 = 0;
        String query
                = "INSERT INTO study(StudyID,StudyName,Description,UserName,DateCreated,ImageURL,ReqParticipants,ActParticipants,SStatus)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
        String query1
                ="INSERT INTO question(StudyID,Question,AnswerType,Option1,Option2,Option3,Option4,Option5,QuestionID,Noofanswers)"+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
        try {
            ps = connection.prepareStatement(query);
            ps1 = connection.prepareStatement(query1);
            
            ps.setString(1, study.getStudyCode());
            ps.setString(2, study.getStudyName());
            ps.setString(3, study.getDescription());
            ps.setString(4, study.getEmail());
            ps.setDate(5, DateCreated);
            ps.setString(6,  study.getImageURL());
            ps.setInt(7, study.getRequestedParticipants());
            ps.setInt(8, study.getNumOfParitipants());
            ps.setString(9, study.getStatus());
            
            ps1.setString(1,  study.getStudyCode());
            ps1.setString(2, study.getQuestion());
            ps1.setString(3, study.getAnswerType());
            System.out.println("ps is" +ps);
           
            ArrayList<String> optionsList = study.getAnswer();
            for(int i=0;i<optionsList.size();i++)
            {
               
                ps1.setString(4+i, optionsList.get(i));
            }
            for(int i=0 ;i<5-optionsList.size();i++)
            {
                ps1.setString(4+optionsList.size()+i, null);
            }
            Random rand = new Random();
            //int x=optionsList.size();
            String questionId = rand.nextInt((1000 - 100) + 1) + 100+"";
            ps1.setString(9, questionId);
            ps1.setInt(10, optionsList.size());
            System.out.println("ps1 is" +ps1);
            count= ps.executeUpdate();
            count1 = ps1.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return count;
    }
    private static ArrayList<Study> studyList =  new ArrayList<>();

    public static Study getStudy(String studyCode) throws IOException {
	ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query =
                "SELECT S.StudyID,S.StudyName,S.Description,S.Username,S.DateCreated,S.ReqParticipants,"
                + "S.ActParticipants,S.SStatus,Q.Question,Q.AnswerType,Q.Noofanswers,Q.Option1,Q.Option2,Q.Option3,"
                + "Q.Option4,Q.Option5,Q.QuestionID FROM study S,question Q WHERE S.StudyID=Q.StudyID AND S.StudyID=?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, studyCode);
            rs = ps.executeQuery();
            Study study  = null;
            ArrayList<String> tempList = new ArrayList();

            if (rs.next()) {
                study = new Study();
                study.setStudyCode(rs.getString("StudyID"));
                study.setStudyName(rs.getString("StudyName"));
                study.setDescription(rs.getString("Description"));
                study.setEmail(rs.getString("UserName"));
                study.setDateCreated(rs.getString("DateCreated"));
                study.setRequestedParticipants(rs.getInt("ReqParticipants"));
                study.setNumOfParitipants(rs.getInt("ActParticipants"));
                study.setStatus(rs.getString("Sstatus"));
                study.setQuestion(rs.getString("Question"));
                study.setAnswerType(rs.getString("AnswerType"));
                 int noofanswers=Integer.parseInt(rs.getString("Noofanswers"));
                  for(int i=12;i<12+noofanswers;i++){
                    tempList.add(rs.getString(i));
                }
                study.setAnswer(tempList);
            }
            return study;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static Study getStudyEdit(String studyCode, String userEmail) throws IOException {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM study "
                + "WHERE StudyID = ? AND Username=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, studyCode);
            ps.setString(2, userEmail);
            rs = ps.executeQuery();
            Study study  = null;
            if (rs.next()) {
                study = new Study();
                study.setStudyCode(rs.getString("StudyID"));
                study.setStudyName(rs.getString("StudyName"));
                study.setDescription(rs.getString("Description"));
                study.setEmail(rs.getString("UserName"));
                study.setDateCreated(rs.getString("DateCreated"));
                study.setRequestedParticipants(rs.getInt("ReqParticipants"));
                study.setNumOfParitipants(rs.getInt("ActParticipants"));
                study.setStatus(rs.getString("Sstatus"));
            }
            return study;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<Study> getStudies() throws IOException {
	ArrayList<Study> totalStudyList = new ArrayList<>();
	totalStudyList = (ArrayList<Study>) studyList.clone();
	return totalStudyList;
    }

    public static ArrayList<Study> getStudies(String email) throws IOException {
    
        ArrayList<Study> studyListEmail = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM study "
                + "WHERE Username=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            Study study  = null;
            while (rs.next()) {
                study = new Study();
                study.setStudyCode(rs.getString("StudyID"));
                study.setStudyName(rs.getString("StudyName"));
                study.setDescription(rs.getString("Description"));
                study.setEmail(rs.getString("UserName"));
                study.setDateCreated(rs.getString("DateCreated"));
                study.setImageURL(rs.getString("ImageURL"));
                study.setRequestedParticipants(rs.getInt("ReqParticipants"));
                study.setNumOfParitipants(rs.getInt("ActParticipants"));
                study.setStatus(rs.getString("Sstatus"));
                
                studyListEmail.add(study);
            }
            
            return studyListEmail;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<Study> getStudiesStart() throws IOException {

        ArrayList<Study> studiesList = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;

        String query = "SELECT S.StudyID,S.StudyName,S.Description,S.Username,S.DateCreated,S.ReqParticipants,S.ActParticipants,S.SStatus,Q.Question,Q.QuestionID FROM study S,question Q WHERE S.StudyID=Q.StudyID AND S.SStatus='Start'";
        try {
            ps = connection.prepareStatement(query);
            System.out.println(ps);
        
            rs = ps.executeQuery();
                       
            Study study  = null;
            while (rs.next()) {
                study = new Study();
                study.setStudyCode(rs.getString("StudyID"));
                study.setStudyName(rs.getString("StudyName"));
                study.setDescription(rs.getString("Description"));
                study.setEmail(rs.getString("UserName"));
                study.setQuestion(rs.getString("Question"));
                study.setDateCreated(rs.getString("DateCreated"));
                study.setRequestedParticipants(rs.getInt("ReqParticipants"));
                study.setNumOfParitipants(rs.getInt("ActParticipants"));
                study.setStatus(rs.getString("Sstatus"));
                study.setQuestionID(rs.getString("QuestionID"));
                
                studiesList.add(study);
            }
            
            return studiesList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    public static boolean editStudy(String studyCode, String email, Study studyEdit){
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        int count = 0;
        int count1 = 0;
        String query= "UPDATE study SET StudyName=?,ReqParticipants=?,Description=? WHERE StudyID=?";
        String query1="UPDATE question SET Question=?,AnswerType=?,Noofanswers=?,Option1=?,Option2=?,Option3=?,Option4=?,Option5=? WHERE StudyID=?";
        ArrayList<String> answersList = studyEdit.getAnswer();
        int noofanswers = answersList.size();
        
        try {
            ps = connection.prepareStatement(query);
            ps1 = connection.prepareStatement(query1);
            
            ps.setString(1,studyEdit.getStudyName());
            ps.setInt(2,studyEdit.getRequestedParticipants());
            ps.setString(3,studyEdit.getDescription());
            ps.setString(4, studyEdit.getStudyCode());
            
            ps1.setString(1,studyEdit.getQuestion());
            ps1.setString(2,studyEdit.getAnswerType());
            ps1.setInt(3,noofanswers);
            
            
            
           for(int i=0;i<answersList.size();i++)
           {
               
                ps1.setString(4+i, answersList.get(i));
           }
           for(int i=0 ;i<5-answersList.size();i++)
           {
               
                ps1.setString(4+answersList.size()+i, "");
           }
           ps1.setString(9,studyCode);
        
           System.out.println("ps is" +ps);
           count= ps.executeUpdate();
           count1= ps1.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
         if(count==1 && count1 ==1){
                return true;
            }else{
                return false;
            }
        } 
	
    public static boolean updateStatus(String studyCode, String email, String status) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        int count = 0;
        String query
                = "UPDATE study SET SStatus=? WHERE StudyID=? AND Username=? "; 
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1,status);
            ps.setString(2, studyCode);
            ps.setString(3, email);
            System.out.println("ps is" +ps);
            
            count= ps.executeUpdate();
           
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
         if(count==1){
                return true;
            }else{
                return false;
            }
        }
	public static boolean incrementParticipantCount(String studyCode){
		for(Study study: studyList){
			if(study.getStudyCode().equals(studyCode)){
				System.out.println("inc the participant count");
				study.setNumOfParitipants(study.getNumOfParitipants() + 1);
				return true;
			}
			
		}
		return false;
	}
	public static int getTotalParticipantsCount(String email){
		int i = 0;
		for(Study study: studyList){
			if(study.getEmail().equals(email)){
				i += study.getNumOfParitipants();
			}
		}
		
		return i;
	}
	
	public static boolean deleteStudy(String studyCode) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        int count=0;

        String query = "DELETE FROM study "
                + "WHERE StudyID=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, studyCode);
            count=ps.executeUpdate();
            if(count==1){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
           return false;
        } finally {
          
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }      }
} 