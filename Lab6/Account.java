package Lab6;

public class Account {
    private String name;
    private int money;
    private boolean isOpen;

    public String getName(){
        return name;
    }

    public int getMoney(){
        return money;
    }

    public boolean isOpen(){
        return isOpen;
    } 


    public Account(int money, String name){
        this.money = money;
        this.name = name;
        this.isOpen = false;
    }

    public void Open(){
        this.isOpen = true;
    }

    public void Close(){
        this.isOpen = false;
    }

    public void TakeMoney(int ammount){
        if (isOpen){
            int newSumm = this.money - ammount;
            if (newSumm < 0){
                System.out.println("INCORRECT SUMM, PLEASE CHOSE BETTER");
            }
            else {
                this.money = newSumm;
            }
        }
    }

    public void PutMoney(int ammount){
        if (isOpen){
            this.money += ammount;
        }
    }
}
