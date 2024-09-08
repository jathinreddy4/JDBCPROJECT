package MovieBooking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class logins
{
    private int userid;
    private int password;
    Scanner sc = new Scanner(System.in);

    public void register(int userid, int password)
    {
        try
        {
            Connection con  = connectionDetails.getconnection();
            String query = "Insert into details(id,password) values(? ,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,userid);
            ps.setInt(2,password);
            int rows_affected = ps.executeUpdate();
            if(rows_affected > 0)
            {
                System.out.println("Registered Sucessfully ");
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void login(int userid, int password)
    {
        movie m = new movie();
        try
        {
            Connection con = connectionDetails.getconnection();
            String query = "Select * from details where id=? and password = ?";
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1,userid);
            pr.setInt(2,password);
            ResultSet rs = pr.executeQuery();
            if(rs.next())
            {
                System.out.println("logged In sucessfully");
                System.out.println("Movies Available");
                m.showmovies();
                System.out.println();
                System.out.println("Book your ticket");
                System.out.println("Enter movie ID");
                int id = sc.nextInt();
                System.out.println("Enter number of seats");
                int seats = sc.nextInt();
                if(seats <= 6)
                {
                    m.bookticket(id,seats);
                }
                else
                {
                    System.out.println("Max 6 bookings allowed at a time");
                }

            }
            else
            {
                System.out.println("Account does not exist, Please register yourself");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }



}
