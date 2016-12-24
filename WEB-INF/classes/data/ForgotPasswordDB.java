/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mohan
 */
public class ForgotPasswordDB {
    public static String getUser(String token) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM forgotpassword "
                + "WHERE token = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, token);
            rs = ps.executeQuery();
            StringBuilder returnUser = new StringBuilder();
            if (rs.next()) {

                
                returnUser.append(rs.getString("email"));
                returnUser.append("::");
                returnUser.append(rs.getString("token"));
                returnUser.append("::");
                returnUser.append(rs.getString("expirationdate"));

            }
            return returnUser.toString();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }

    public static int addTempUser(String email, String token, String dateCreated) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO forgotpassword (email, token, expirationdate) "
                + "VALUES (?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
           
            ps.setString(1, email);
            ps.setString(2, token);
            ps.setString(3, dateCreated);
           
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int deleteUser(String token) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "DELETE FROM forgotpassword "
                + "WHERE token = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, token);

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }
}
