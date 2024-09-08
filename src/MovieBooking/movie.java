package MovieBooking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class movie
{
    private int id;
    private String name;
    private String movietype;
    private int totalseats;
    private int seatsavailable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovietype() {
        return movietype;
    }

    public void setMovietype(String movietype) {
        this.movietype = movietype;
    }

    public int getTotalseats() {
        return totalseats;
    }

    public void setTotalseats(int totalseats) {
        this.totalseats = totalseats;
    }

    public int getSeatsavailable() {
        return seatsavailable;
    }

    public void setSeatsavailable(int seatsavailable) {
        this.seatsavailable = seatsavailable;
    }

    @Override
    public String toString() {
        return "movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", movietype='" + movietype + '\'' +
                ", totalseats=" + totalseats +
                ", seatsavailable=" + seatsavailable +
                '}';
    }

    public void showmovies()
    {
        /*try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }*/

        try
        {
            Connection con = connectionDetails.getconnection();
            String query = "Select * from movies";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rst = ps.executeQuery();

            while(rst.next())
            {
                int Id = rst.getInt("id");
                String Name = rst.getString("name");
                String type = rst.getString("movietype");
                int Totalseats = rst.getInt("totalseats");
                int Seatsavailable = rst.getInt("seatsavailable");

                System.out.println("movie ID : " + Id + "\n Name :" + Name + " \n Type :" + type + "\n Totalseats :" + Totalseats+ "\n Seats available :"  + Seatsavailable);
                System.out.println("-----------------------------------------------" );
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getStackTrace());
        }
    }

    public boolean bookticket(int MovieID,int seats )
    {
        try
        {
            Connection conn = connectionDetails.getconnection();
            String query = "Select * from movies where id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,MovieID);
            ResultSet rst = ps.executeQuery();
            if(rst.next())
            {
                int availableseats = rst.getInt("seatsavailable");
                if(availableseats > seats)
                {
                    try
                    {
                        String query1 = "UPDATE movies SET seatsavailable = seatsavailable - ? WHERE id = ?";
                        PreparedStatement upd = conn.prepareStatement(query1);
                        upd.setInt(1,seats);
                        upd.setInt(2,MovieID);
                        int rowsaffected = upd.executeUpdate();

                        if(rowsaffected  > 0)
                        {
                            System.out.println("Ticket booked succesfully");
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }

                }
            }
            else
            {
                System.out.println("Seats not available");
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }



}
