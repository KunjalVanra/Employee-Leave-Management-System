package dao;
import java.sql.*;

public class EmployeeDAO {

    public ResultSet login(String email, String password) {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM employees WHERE email=? AND password=?"
            );

            ps.setString(1, email);
            ps.setString(2, password);

            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}