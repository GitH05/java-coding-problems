package com.dhawal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dhawal.db.MyConnection;
import com.dhawal.modal.User;

public class UserDao {
    public static boolean isExist(String email) throws SQLException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select email from users");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String mail = rs.getString(1);
            if (mail.equals(email)) {
                return true;
            }
        }
        return false;
    }

    public static int saveUser(User user) throws SQLException {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("INSERT INTO users (name, email) VALUES (?, ?)");
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());

        return ps.executeUpdate();
    }

}
