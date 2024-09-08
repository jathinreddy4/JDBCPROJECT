package MovieBooking;

import java.util.Scanner;

public class BookShow
{
    public static void main(String[] args) {

        movie m = new movie();
        logins log = new logins();
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to PictureDehko");
        System.out.println("Enter \n 1.Login \n 2.Register");
        int choice = sc.nextInt();
        switch (choice)
        {
            case 1 :
                System.out.println("Please enter your credentials");
                System.out.println("Enter username");
                int username = sc.nextInt();
                System.out.println("Enter password");
                int password = sc.nextInt();
                log.login(username,password);
                break;

            case 2:
                System.out.println("Enter username");
                int id = sc.nextInt();
                System.out.println("Set password");
                int pass = sc.nextInt();
                log.register(id,pass);
                break;
        }

    }
}
