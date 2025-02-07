package BankingSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bank {

    private  ArrayList<Account> accounts;
    private Scanner keyboard;


    public Bank(){
        
        accounts = new ArrayList<>();
        keyboard= new Scanner(System.in);
        loadAccountNumbersFromFile();
    }

    public void start(){
        int choice;
        do {
            choice = menu(keyboard);

            if (choice == 1) {
                accounts.add(createAccount(keyboard));
            } else if (choice == 2) {
                doDeposit(accounts, keyboard);

            } else if (choice == 3) {
                doWithdraw(accounts, keyboard);
            } else if (choice == 4) {
                applyInterestRate(accounts, keyboard);
            }
            else {
                System.out.println("GoodBye!");
            }

            System.out.println();
        } while (choice != 5);
    }

    public void saveAccountNumbersToFile(){
        try  (BufferedWriter writer = new BufferedWriter(new FileWriter("accountNumbers.txt",true))) {
            for (Account account : accounts) {
                if (account instanceof CheckingAccount) {
                    writer.write("Checking," + account.getAccountNumber() + "\n");
                } else if (account instanceof SavingsAccount) {
                    writer.write("Savings," + account.getAccountNumber() +  "\n");
                }
            }
            System.out.println("Account numbers saved successfully!");

           
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

    }

    public void loadAccountNumbersFromFile(){

        try (BufferedReader reader = new BufferedReader(new FileReader("accountNumbers.txt"))){
            String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(","); 
            String type = parts[0].trim();
            long accountNumber = Long.parseLong(parts[1].trim());

            if (type.equalsIgnoreCase("Checking")) {
                accounts.add(new CheckingAccount(accountNumber));
            } else if (type.equalsIgnoreCase("Savings")) {
                accounts.add(new SavingsAccount(accountNumber, 0.03)); 
            }
            System.out.println("Loaded " + type + " Account: " + accountNumber);
        }
                
            
            
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        
        }
    }

    public int accountMenu(Scanner keyboard) {
        System.out.println("Select Account Type");
        System.out.println("1. Checking Account");
        System.out.println("2. Savings Account");

        int choice;

        do {
            System.out.println("Enter choice: ");
            choice = keyboard.nextInt();
        } while (choice < 1 || choice > 2);

        return choice;

    }

    public int searchAccounts(ArrayList<Account> accounts,  long accountNumber) {
        
                for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).getAccountNumber() == accountNumber) {
                return i;
            }

        }
        return -1;

    }

    public void doDeposit(ArrayList<Account> accounts, Scanner keyboard) {
            System.out.println("\nPlease enter account number");
    
            long accountNumber = keyboard.nextLong();
    
            int index = searchAccounts(accounts, accountNumber);
            if (index >= 0) {
    
                System.out.println("Please enter deposit amount");
                double amount = keyboard.nextDouble();
    
                accounts.get(index).deposit(amount,accountNumber);

        } else {
            System.out.println("No account exist with AccountNumber: " + accountNumber);
        }

    }

    public void doWithdraw(ArrayList<Account> accounts, Scanner keyboard) {
        System.out.println("\nPlease enter account number");

        long accountNumber = keyboard.nextLong();

        int index = searchAccounts(accounts, accountNumber);
        if (index >= 0) {

            System.out.println("Please enter withdraw amount");
            double amount = keyboard.nextDouble();

            accounts.get(index).withdraw(amount);

        } else {
            System.out.println("No account exist with AccountNumber: " + accountNumber);
        }

    }

    public void applyInterestRate(ArrayList<Account> accounts, Scanner keyboard) {
        System.out.println("\nPlease enter account number");

        long accountNumber = keyboard.nextLong();

        int index = searchAccounts(accounts, accountNumber);
        if (index >= 0) {
           if(accounts.get(index) instanceof SavingsAccount){
            ((SavingsAccount) accounts.get(index)).applyInterestRate(accountNumber);}

        } else {
            System.out.println("No account exist with AccountNumber: " + accountNumber);
        }

    }

    public Account createAccount(Scanner keyboard) {

        Account account = null;
        long accountNumber;

        System.out.println("Enter Account Number: ");
        accountNumber = keyboard.nextLong();

        int choice = accountMenu(keyboard);

        if (choice == 1) {
            System.out.println("Enter Transaction Fee: ");
            account = new CheckingAccount(accountNumber);
        } else {
            System.out.println("Please Enter Interest Rate: ");
            double interestRate = keyboard.nextDouble();
            account = new SavingsAccount(accountNumber, interestRate);
        }


            accounts.add(account); 
            saveAccountNumbersToFile();
            System.out.println("Account created and saved successfully!");
        return account;

    }

    public int menu(Scanner keyboard) {
        System.out.println("Bank Account Menu");
        System.out.println("1. Create New Account");
        System.out.println("2. Deposit Funds");
        System.out.println("3. Withdraw Funds");
        System.out.println("4. apply Interest");
        System.out.println("5. Quit");

        int choice;

        do {
            System.out.println("Enter choices");
            choice = keyboard.nextInt();
            keyboard.nextLine();

        } while (choice < 1 || choice > 5);

        return choice;

    }


}
