package BankingSystem;


public class CheckingAccount extends Account {

    private static double FEE = 2.5;


    public CheckingAccount(){
        super();
    }

    public CheckingAccount(long accountNumber){
        super(accountNumber);
    
    }

    
    public static void setFEE(double fEE) {
        FEE = fEE;
    }

    @Override
    public void deposit(double amount,long accountNumber) {

        if(amount > 0){
            balance+= amount;
            System.out.printf("Amount %.2f deposited into ccount %d%n", amount, accountNumber);
           

            balance-=FEE;
            System.out.printf("Fee %.2f Applied%n",FEE);
            System.out.printf("Current balance is : %.2f%n",balance);
            

        }else{
            System.out.println("A negative amount cannot be deposited");
        }
       
    }

    @Override
    public void withdraw(double amount) {
        if(amount > 0){
            if((amount+FEE)<=balance){

                System.out.printf("Amount of %.2f withdrawn from Account%n", amount);
                balance-=amount;
                balance-=FEE;
                System.out.printf("Fee of %.2f applied%n", FEE);
                System.out.printf("Current Balance is : %.2f%n",balance);

            }else{
                System.out.println("Not enough funds in your account");
            }
        }else{
            System.out.println("A negative amount cannot be withdrawn."); 
        }


        
    }
    
}
