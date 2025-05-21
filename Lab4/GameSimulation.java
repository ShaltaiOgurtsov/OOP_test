package Lab4;

public class GameSimulation {
    public static int[] Simulate(String optionOne, String optionTwo){
        if(optionOne == optionTwo){
            return new int[]{0, 0};
        }
        else if ((optionOne == "Rock" && optionTwo == "Scizors" )|| (optionOne == "Scizors" && optionTwo == "Paper") || (optionOne == "Paper" && optionTwo == "Rock")){
            return new int[]{1, 0};
        }
        else{
            return new int[] {0, 1};
        }
    }
}
