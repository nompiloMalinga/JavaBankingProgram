package BankingSystem;

import java.util.Scanner;

public class Bank {

    private int numAccounts;
    private Account accounts[];
    private Scanner keyboard;


    public Bank(){
        numAccounts =0;
        accounts = new Account[10];
        keyboard= new Scanner(System.in);
    }

    public void start(){
        int choice;
        do {
            choice = menu(keyboard);

            if (choice == 1) {
                accounts[numAccounts++] = createAccount(keyboard);
            } else if (choice == 2) {
                doDeposit(accounts, numAccounts, keyboard);

            } else if (choice == 3) {
                doWithdraw(accounts, numAccounts, keyboard);
            } else if (choice == 4) {
                applyInterestRate(accounts, numAccounts, keyboard);
            } else {
                System.out.println("GoodBye!");
            }

            System.out.println();
        } while (choice != 5);
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

    public int searchAccounts(Account accounts[], int count, long accountNumber) {
    
            for (int i = 0; i < count; i++) {
                if (accounts[i].getAccountNumber() == accountNumber) {
                return i;
            }

        }
        return -1;

    }

    public void doDeposit(Account accounts[], int count, Scanner keyboard) {
        System.out.println("\nPlease enter account number");

        long accountNumber = keyboard.nextLong();

        int index = searchAccounts(accounts, count, accountNumber);
        if (index >= 0) {

            System.out.println("Please enter deposit amount");
            double amount = keyboard.nextDouble();

            accounts[index].deposit(amount);

        } else {
            System.out.println("No account exist with AccountNumber: " + accountNumber);
        }

    }

    public void doWithdraw(Account accounts[], int count, Scanner keyboard) {
        System.out.println("\nPlease enter account number");

        long accountNumber = keyboard.nextLong();

        int index = searchAccounts(accounts, count, accountNumber);
        if (index >= 0) {

            System.out.println("Please enter withdraw amount");
            double amount = keyboard.nextDouble();

            accounts[index].withdraw(amount);

        } else {
            System.out.println("No account exist with AccountNumber: " + accountNumber);
        }

    }

    public void applyInterestRate(Account accounts[], int count, Scanner keyboard) {
        System.out.println("\nPlease enter account number");

        long accountNumber = keyboard.nextLong();

        int index = searchAccounts(accounts, count, accountNumber);
        if (index >= 0) {

            ((SavingsAccount) accounts[index]).applyInterestRate();

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
            double fee = keyboard.nextDouble();
            account = new CheckingAccount(accountNumber, fee);
        } else {
            System.out.println("Please Enter Interest Rate: ");
            double interestRate = keyboard.nextDouble();
            account = new SavingsAccount(accountNumber, interestRate);
        }
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
