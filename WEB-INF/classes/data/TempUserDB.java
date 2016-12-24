/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import business.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author mohan
 */
public class TempUserDB {

    public static String getUser(String token) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM tempuser "
                + "WHERE token = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, token);
            rs = ps.executeQuery();
            StringBuilder returnUser = new StringBuilder();
            if (rs.next()) {

                returnUser.append(rs.getString("uname"));
                returnUser.append("::");
                returnUser.append(rs.getString("email"));
                returnUser.append("::");
                returnUser.append(rs.getString("password"));
                returnUser.append("::");
                returnUser.append(rs.getString("salt"));

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

    public static int addTempUser(String name, String email, String password, String dateCreated,
            String token, String salt) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO tempuser (uname, email, password, issuedate, token,salt) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, dateCreated);
            ps.setString(5, token);
            ps.setString(6, salt);

            System.out.println(ps);
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

        String query = "DELETE FROM tempuser "
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
