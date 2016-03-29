package com.oneapm.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.oneapm.util.common.Constants.DataSource;

public class DBUtil {
    public static Connection getConnection() {  
        Connection connection = null;    
        try {  
            Class.forName(DataSource.DRIVER);  
            connection = DriverManager.getConnection(DataSource.URL, 
            		DataSource.USERNAME, 
            		DataSource.PASSWORD);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return connection;  
    }  

    public static void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs) {  
        try {  
            if (rs != null) {  
                rs.close();  
                rs = null;  
            }  
            if (ps != null) {  
                ps.close();  
                ps = null;  
            }  
            if (conn != null) {  
                conn.close();  
                conn = null;  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}
