package Lab5;

public class AISimulation {
    public static int CoinGame(int remainingCoins){
        return (remainingCoins % 3 == 0) ? 1 : (remainingCoins % 3);
    }
}