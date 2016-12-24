/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import business.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author mohan
 */
public class QuestionDB {
public static int insert(Question question) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO question (QuestionID,StudyID,Question,AnswerType,Option1,Option2,Option3,Option4,Option5) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
        try {
            ps = connection.prepareStatement(query);
//            ps.setString(1, question.getQuestionID());
//            ps.setString(2, question.getStudyID());
//            ps.setString(3, question.getDescription());
//            ps.setString(4, question.getEmail());
//            ps.setString(5, question.getDateCreated());
//            ps.setString(6,  question.getImageURL());
//            ps.setInt(7, question.getRequestedParticipants());
//            ps.setString(8,  question.getStatus());
//            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    
}
