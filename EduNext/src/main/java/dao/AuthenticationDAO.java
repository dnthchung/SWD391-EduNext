/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import model.ResetPasswordLink;
import model.User;
import utils.DBContext;

/**
 *
 * @author tranh
 */
public class AuthenticationDAO {

    public static void main(String[] args) {
        AuthenticationDAO auth = new AuthenticationDAO();
        System.out.println(auth.getResetPasswordLinkByToken("ddddd-"));
    }

    public User login(String username, String password) {
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("SELECT * FROM [User] WHERE Usename = ? AND Password = ?")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return User.builder()
                        .userId(rs.getLong("UserID"))
                        .fullName(rs.getString("FullName"))
                        .useName(rs.getString("Usename"))
                        .password(rs.getString("Password"))
                        .dob(rs.getDate("DOB").toLocalDate())
                        .phoneNumber(rs.getString("PhoneNumber"))
                        .userRoleId(rs.getInt("UserRoleID"))
                        .userStatusId(rs.getInt("UserStatusID"))
                        .email(rs.getString("Email"))
                        .address(rs.getString("Address"))
                        .gender(rs.getInt("Gender"))
                        .departmentId(rs.getLong("DepartmentID"))
                        .note(rs.getString("Note"))
                        .build();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByEmail(String email) {
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("SELECT * FROM [User] WHERE Email = ?")) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return User.builder()
                        .userId(rs.getLong("UserID"))
                        .fullName(rs.getString("FullName"))
                        .useName(rs.getString("Usename"))
                        .password(rs.getString("Password"))
                        .dob(rs.getDate("DOB").toLocalDate())
                        .phoneNumber(rs.getString("PhoneNumber"))
                        .userRoleId(rs.getInt("UserRoleID"))
                        .userStatusId(rs.getInt("UserStatusID"))
                        .email(rs.getString("Email"))
                        .address(rs.getString("Address"))
                        .gender(rs.getInt("Gender"))
                        .departmentId(rs.getLong("DepartmentID"))
                        .note(rs.getString("Note"))
                        .build();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean saveResetPasswordToken(User user, String token) {
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("INSERT INTO ResetPasswordLink VALUES (?, ?, ?, ?);")) {
            preparedStatement.setString(1, token);
            preparedStatement.setLong(2, user.getUserId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setBoolean(4, false);
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ResetPasswordLink getResetPasswordLinkByToken(String token) {
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("SELECT * FROM [ResetPasswordLink] WHERE ResetPasswordID = ?")) {
            preparedStatement.setString(1, token);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return ResetPasswordLink.builder()
                        .resetPasswordId(rs.getString("ResetPasswordID"))
                        .userId(rs.getLong("UserID"))
                        .linkGeneratedTime(rs.getTimestamp("LinkGeneratedTime").toLocalDateTime())
                        .isUsed(rs.getBoolean("IsUsed"))
                        .build();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean saveNewPassword(Long userId, String password) {
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("UPDATE [User]\n"
                        + "SET Password = ?\n"
                        + "WHERE UserID = ?;")) {
            preparedStatement.setString(1, password);
            preparedStatement.setLong(2, userId);
            int result = preparedStatement.executeUpdate();
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (result > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveTokenUsedStatus(String token, boolean status) {
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("UPDATE [ResetPasswordLink]\n"
                        + "SET IsUsed = ?\n"
                        + "WHERE ResetPasswordID = ?;")) {
            preparedStatement.setBoolean(1, status);
            preparedStatement.setString(2, token);
            int result = preparedStatement.executeUpdate();
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (result > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
