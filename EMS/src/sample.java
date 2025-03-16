import java.sql.*;

public class sample {
    public static void main(String[] args) {
        System.out.println();
        String url = "jdbc:mysql://localhost:3306/rohitwbdb"; // Change to your database name
        String user = "root"; // Change if your MySQL username is different
        String password = "MySQLPass@123"; // Change to your MySQL password

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL JDBC Driver

            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp");

            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " +
                                   rs.getString(3) + " " );
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}









