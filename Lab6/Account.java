package Lab6;

public class Account {
    private final int id;
    private double balance;
    private static final double withdrawLimit = 500.0;
    
    public Account(int id){
        this.id = id;
        this.balance = 0;
    }

    public synchronized void deposit(double ammount){
        this.balance += ammount;
    }

    public synchronized void withdraw(double ammount){
        if (ammount > balance || ammount > withdrawLimit){
            System.err.println("Failed to withdraw");
        }
        else{
            this.balance -= ammount;
        }
    }

    public synchronized double getBallance(){
        return this.balance;
    }
}
