
package com.PIS.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionPool {
    static Connection conn;
    public static Connection connectDB(){
        try {
            //step1 register the driver
        Class.forName("com.mysql.cj.jdbc.Driver");
            //step2 create the connection
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pis", "root", "root");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    public static void main(String[] args) {
        ConnectionPool.connectDB();
    }
}
