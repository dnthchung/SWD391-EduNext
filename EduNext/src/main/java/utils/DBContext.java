/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Vanhle
 */
public class DBContext {

    public static Connection makeConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String username = "sa";
            String password = "123";
            String url = "jdbc:sqlserver://DESKTOP-7MF95U3:1433;databaseName=InterviewSystem;encrypt=true;trustServerCertificate=true";//3. Open Connection
//            jdbc:sqlserver://DESKTOP-7MF95U3:1433;databaseName=InterviewSystem;encrypt=true;trustServerCertificate=true
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        System.out.println(makeConnection());
    }
}
