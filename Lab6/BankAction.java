package Lab6;

public class BankAction implements Runnable{
    private final ActionType type;
    private final int accountId;
    private final double amount;

    public BankAction(ActionType type, int accountId, double ammount){
        this.type = type;
        this.accountId = accountId;
        this.amount = ammount;
    }

    public BankAction(){
        this.type = ActionType.OPEN;
        this.amount = 0;
        this.accountId = 0;
    }

    public BankAction(int accountId){
        this.type = ActionType.CLOSE;
        this.accountId = accountId;
        this.amount = 0;
    }

    @Override
    public void run(){
        switch(type){
            case OPEN -> {
                AccountManager.getInstance().openAccount();
            }
            case CLOSE -> AccountManager.getInstance().closeAccount(accountId);
            case DEPOSIT -> AccountManager.getInstance().getAccount(accountId).deposit(amount);
            case WITHDRAW -> AccountManager.getInstance().getAccount(accountId).withdraw(amount);
        }
    }
}
