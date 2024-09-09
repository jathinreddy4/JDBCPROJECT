package BankingSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class banklogs
{
    private String username;
    private String password;

    public void login(String username,String password)
    {
        try
        {
            Scanner sc = new Scanner(System.in);
            Connection con = ConnectionDetails.getconnection();
            String query = "Select id from logins where username= ? and password = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rst  = ps.executeQuery();
            if(rst.next())
            {
                int userid = rst.getInt("id");
                try
                {
                    userOperation uo = new userOperation();
                    String query1 = "Select * from accounts where userid = ?";
                    PreparedStatement pst = con.prepareStatement(query1);
                    pst.setInt(1,userid);
                    ResultSet rst1 = pst.executeQuery();
                    if(rst1.next())
                    {
                        System.out.println("Welcome to SBI online Banking");
                        System.out.println("Choose option \n 1.Check Balance \n 2.Withdraw \n 3.Deposit \n 4.Transfer money \n 5.Exit");
                        int choice = sc.nextInt();
                        switch (choice)
                        {
                            case 1 : uo.getBalance(userid);
                            break;
                            case 2 : uo.withdraw(userid);
                            break;
                            case 3 : uo.deposit(userid);
                            break;
                            case 4 : uo.transfer(userid);
                            case 5 :
                                System.out.println("Thanks you");
                                return;
                        }
                    }
                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
            else
            {
                System.out.println("Wrong username or password");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
