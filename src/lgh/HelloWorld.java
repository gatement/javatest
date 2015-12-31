package lgh;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class HelloWorld {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {
        System.out.println("==start");

        Connection conn = null;
        String sql;
        String url = "jdbc:mysql://local:3306/test?user=root&password=&useUnicode=true&characterEncoding=UTF-8";

        try{
            // com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
            // OR 
            // new com.mysql.jdbc.Driver();
            // OR
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("MySQl Driver loaded.");
        //} catch (SQLException e) {
        //    System.out.println("SQL Exception");
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (conn != null) conn.close();
        }

        System.out.println("==end");
    }
}
