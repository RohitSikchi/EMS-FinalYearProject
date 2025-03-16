package employee.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class conn {

    Connection connection;
    Statement statement;
    String url = "jdbc:mysql://localhost:3306/emsdb";
    String user = "root";
    String password = "MySQLPass@123"; 
    public conn() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
