package BankingSystem;

import java.util.Scanner;

public class SBI
{
    public static void main(String[] args) {
        banklogs bl = new banklogs();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome To SBI");
        System.out.println("Enter username");
        String username = sc.nextLine();
        System.out.println("Enter passowrd");
        String password = sc.nextLine();

        bl.login(username,password);
    }

}
