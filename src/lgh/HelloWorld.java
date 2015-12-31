package lgh;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class HelloWorld {
    public static void main(String[] args) throws Exception {
        System.out.println("==start");

        int result;
        Connection conn = null;
        String sql;
        String url = "jdbc:mysql://localhost:3306/test?user=root&password=&useUnicode=true&characterEncoding=UTF-8";

        try{
            // init MySQL Driver
            // com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
            // OR 
            // new com.mysql.jdbc.Driver();
            // OR
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement();

            // create table
            sql = "create table student(id int, name varchar(20), primary key(id))";
            statement.executeUpdate(sql);

            // insert
            sql = "insert into student(id, name) values(1, 'Johnson');";
            result = statement.executeUpdate(sql);
            if (result != 1) System.out.println("insert failed.");

            sql = "insert into student(id, name) values(2, 'Denny');";
            result = statement.executeUpdate(sql);
            if (result != 1) System.out.println("insert failed.");
            
            // select
            sql = "select * from student";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next())
            {
                System.out.println(rs.getString(1) + " " + rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conn.close();
        }

        System.out.println("==end");
    }
}
