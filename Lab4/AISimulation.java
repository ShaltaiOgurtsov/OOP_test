package Lab4;

import java.util.*;

public class AISimulation {
    public static String RandomAI(){
        List<String> list = List.of("Rock", "Paper", "Scizors");
        Random random = new Random();

        return list.get(random.nextInt(3));
    }

    public static String GetTheMostFrequant(List<String> words){
        if (words.size() == 0){
            return RandomAI();
        }
        else{
            
            Map<String, Integer> frequancyMap = new HashMap<>();
            frequancyMap.put("Rock", 0);
            frequancyMap.put("Paper", 0);
            frequancyMap.put("Scizors", 0);

            for (String word : words){
                frequancyMap.put(word, frequancyMap.getOrDefault(word, 0) + 1);
            }

            int rockFrequancy = frequancyMap.get("Rock");
            int paperFrequancy = frequancyMap.get("Paper");
            int scizorsFrequancy = frequancyMap.get("Scizors");

            System.out.println(rockFrequancy + " " + paperFrequancy + " " + scizorsFrequancy);

            int summFrequancy = rockFrequancy + scizorsFrequancy + paperFrequancy;

            Random random = new Random();

            int randomInt = random.nextInt(summFrequancy);

            String result;
            if (randomInt < rockFrequancy){
                result = "Rock";
            }
            else if (randomInt < rockFrequancy + paperFrequancy){
                result = "Paper";
            }
            else{
                result = "Scizors";
            }

            System.out.println(result);

            return getCounter(result);
        }
    }

    private static String getCounter(String key){
        if (!"Rock".equals(key) || !"Scizors".equals(key) || !"Paper".equals(key)){
            return null;
        }

        if (key.equals("Rock")){
            return "Paper";
        }
        else if (key.equals("Paper")){
            return "Scizors";
        }
        else{
            return "Scizors";
        }
    }
}
