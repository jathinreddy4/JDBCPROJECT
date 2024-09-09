package BankingSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class userOperation
{
    Scanner sc = new Scanner(System.in);
    public void getBalance(int id)
    {
        try
        {
            Connection con = ConnectionDetails.getconnection();
            String query = "Select * from accounts where userid = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rst = ps.executeQuery();
            if(rst.next())
            {
                int accountNumber = rst.getInt("accnumber");
                int balance = rst.getInt("balance");
                System.out.println("Account number: "+ accountNumber +" \n Your balance is : "  + balance);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public boolean withdraw(int id)
    {
        try
        {
            Connection con = ConnectionDetails.getconnection();
            String query = "Select * from accounts where userid = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rst = ps.executeQuery();
            if(rst.next())
            {
                int accountnumber = rst.getInt("accnumber");
                int balance = rst.getInt("balance");
                System.out.println("Enter amount to withdraw");
                int amount = sc.nextInt();
                if(amount<= balance)
                {
                    try
                    {
                        Connection con1 = ConnectionDetails.getconnection();
                        String query1 = "update accounts set balance = balance - ? where userid = ?";
                        PreparedStatement pst = con1.prepareStatement(query1);
                        pst.setInt(1,amount);
                        pst.setInt(2,id);
                        int rows_affected = pst.executeUpdate();
                        if(rows_affected > 0)
                        {
                            System.out.println("Money withdrawed Sucessfully from account number :" + accountnumber);
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
                else
                {
                    System.out.println("Insufficient Balacne");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return  false;
    }

    public void deposit(int id)
    {
        try
        {
            Connection con = ConnectionDetails.getconnection();
            String query = "Select * from accounts where userid = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rst = ps.executeQuery();
            if(rst.next())
            {
                int accNummber = rst.getInt("accnumber");
                int balance = rst.getInt("balance");
                System.out.println("Enter amount to deposit");
                int amount = sc.nextInt();
                try
                {
                    Connection con1 = ConnectionDetails.getconnection();
                    String query1 = "update accounts set balance=balance+ ? where userid = ?";
                    PreparedStatement pst1 = con.prepareStatement(query1);
                    pst1.setInt(1,amount);
                    pst1.setInt(2,id);
                    int rows_affected = pst1.executeUpdate();
                    if(rows_affected > 0)
                    {
                        System.out.println("Money deposited Sucessfully in account number : " + accNummber);
                    }
                    else
                    {
                        System.out.println("Money not deposited");
                    }
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public boolean transfer(int id)
    {
        try
        {
            Connection con = ConnectionDetails.getconnection();
            String query = "Select * from accounts where userid = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rst = ps.executeQuery();
            if(rst.next())
            {
                int accountnumber = rst.getInt("accnumber");
                int balance = rst.getInt("balance");
                System.out.println("Enter amount to Transfer");
                int amount = sc.nextInt();
                if(amount< balance)
                {
                    try
                    {
                        System.out.println("Enter Account number to transfer");
                        int accountnumber1 = sc.nextInt();
                        Connection con1 = ConnectionDetails.getconnection();
                        String query1 = "update accounts set balance = balance - ? where userid = ?";
                        PreparedStatement pst1 = con1.prepareStatement(query1);
                        pst1.setInt(1,amount);
                        pst1.setInt(2,id);
                        int rowsaff = pst1.executeUpdate();
                        if(rowsaff > 0)
                        {
                            try
                            {
                                Connection con2 = ConnectionDetails.getconnection();
                                String query2 = "update accounts set balance = balance + ? where accnumber = ?";
                                PreparedStatement pst = con2.prepareStatement(query2);
                                pst.setInt(1,amount);
                                pst.setInt(2,accountnumber1);
                                int rowsAff = pst.executeUpdate();
                                if(rowsAff > 0)
                                {
                                    System.out.println("Money transfered sucessfully from Account number : " + accountnumber + " to " + accountnumber1 );
                                }


                            }
                            catch (Exception e)
                            {
                                System.out.println(e.getMessage());
                            }

                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
                else
                {
                    System.out.println("Insufficient Balacne");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
