package BankingSystem;

public class SavingsAccount extends Account {

    private double interestRate;

    
    public SavingsAccount(){
        super();
    }

    public SavingsAccount(long accountNumber, double interestRate){
        super(accountNumber);
        this.interestRate =interestRate;

    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    

    public double calcInterest(){
        return balance*(interestRate/100);
    }

    public void applyInterestRate(){
        double interest = calcInterest();
        System.out.printf("Interest amount  %.2f added to your balance%n",interest);
        deposit(interest);;
    }

    @Override
    public void deposit(double amount) {

        if(amount > 0){
            balance += amount;
            System.out.printf("Amount %.2f deposited%n",amount);
        }else{
            System.out.println("Deposit amount must be positive!");

        }
        
    }

    @Override
    public void withdraw(double amount) {

        if (amount > 0 && amount<=balance) {

            balance -= amount;

            System.out.printf("Current Balance is : %.2f%n", balance);

        } else {
            System.out.println("Not enough funds in your account");
        }
       
    }
    
}
