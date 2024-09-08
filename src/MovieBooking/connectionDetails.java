package MovieBooking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionDetails
{
    private static final String url = "jdbc:mysql://localhost:3306/bms";
    private static final String username = "root";
    private static final String password = "reddy04";

    public static Connection getconnection() throws SQLException
    {
        return DriverManager.getConnection(url,username,password);
    }
}
