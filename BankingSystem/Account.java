package BankingSystem;

public abstract class Account {

    private long accountNumber;

    protected double balance;

    public Account(){}

    public Account(long accountNumber2){
        this.accountNumber= accountNumber2;
        balance =0;

    }
    public long getAccountNumber() {
        return this.accountNumber;
    }

    public double getBalance() {
        return  this.balance;
    }

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);

    
}