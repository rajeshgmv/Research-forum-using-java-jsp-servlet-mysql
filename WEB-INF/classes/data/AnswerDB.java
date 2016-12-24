package data;

import java.util.ArrayList;

import business.Answer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * 
 * @author mohan
 * Answers are currently being stored
 */
public class AnswerDB {
	private static ArrayList<Answer> answersList = new ArrayList<Answer>();
	
	
	public static boolean addAnswer(Answer answer){
		answersList.add(answer);
                
                ConnectionPool pool = ConnectionPool.getInstance();
                Connection connection = pool.getConnection();
                PreparedStatement ps = null;
                PreparedStatement ps1= null;
                PreparedStatement ps2= null;
                String query,query1,query2;
                int count=-1;
        
                query= "INSERT INTO answer(StudyID,UserName,Choice,DateSubmitted) VALUES (?,?,?,NOW())";
                query1="UPDATE user SET Participation=Participation+1,Coins=Coins+1 WHERE Username=?";
                query2="UPDATE study SET ActParticipants=ActParticipants+1 WHERE StudyID=?";
        try {
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(query);
            ps.setString(1, answer.getStudyCode());
            //ps.setString(2, answer.getQuestionId());
            ps.setString(2, answer.getEmail());
            ps.setString(3, answer.getChoice());
           
           count= ps.executeUpdate();
           if(count==1){
               count=-1;
//               ResultSet genKeys=ps.getGeneratedKeys();
//                if(genKeys.next()){
//                    key=genKeys.getInt(1);
//                }
           
           ps1 = connection.prepareStatement(query1);
           ps1.setString(1, answer.getEmail());
            
           count= ps1.executeUpdate();
           }
           if(count==1){
               count=-1;
//               ResultSet genKeys=ps.getGeneratedKeys();
//                if(genKeys.next()){
//                    key=genKeys.getInt(1);
//                }
           
           ps2 = connection.prepareStatement(query2);
           ps2.setString(1, answer.getStudyCode());
            
           count= ps2.executeUpdate();
           }
            
        } catch (SQLException e) {
            System.out.println(e);
            
        } finally {
            try {
                if(count==1)
                    connection.commit();
                else
                    connection.rollback();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            DBUtil.closePreparedStatement(ps);
            DBUtil.closePreparedStatement(ps1);
            DBUtil.closePreparedStatement(ps2);
            pool.freeConnection(connection);
        }


		return true;
	}
	
	public static ArrayList<Answer> getAllAnswers(){
		
		return answersList;
	}

}
