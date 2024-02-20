/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Dinith Maleesha
 */
public class DB {
    public static Connection createConnection() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/ead-cw?autoReconnect=true&useSSL=false";
        Connection con = DriverManager.getConnection(url, "root","");
        return con;
    }
}
