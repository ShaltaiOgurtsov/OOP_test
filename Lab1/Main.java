import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


class Main {
    public static void main(String[] args){  
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please, enter the name of your file: ");

        String filename = scanner.nextLine();

        
        String fileContentsString = " ";

        try {
            fileContentsString = ReadFile(filename);
            System.out.println(fileContentsString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String[] sentences = fileContentsString.split("\\s*\\.\\s*");



        ArrayList<String> words = SplitIntoWords(sentences);
        System.out.println("Tasks");
        System.out.println("===============================================================");
        System.out.println("1.There are ecactly " + words.size() + " words");
        System.out.println("2.There are exactly " + CountOriginalWords(words) + " unique words");
        System.out.println("3.There are exactly " + sentences.length + " sentences");
        System.out.println("4.There are exactly " + CountComas(fileContentsString) + " comas in sentence");
        System.out.println("5.Average word length is " + AverageWordSize(words));
        System.out.println("6.Average sentence size is " + AverageSentenceSize(sentences));
        System.out.println("7. Most frequant words are: ");
        MostFrequantWords(words, 10);
    }



    static String ReadFile(String filename) throws IOException{
        Path path = Paths.get(filename);
        return Files.readString(path);
    }



    static ArrayList<String> SplitIntoWords(String[] sentences){
        ArrayList<String> words = new ArrayList<>();
        for (String sentence : sentences){
            String[] splitSentence = sentence.split("[,\\s]+");

            for (String word : splitSentence){
                words.add(word.toLowerCase());
            }
        } 

        return words;
    } 


    static int CountOriginalWords(ArrayList<String> words){
        ArrayList<String> uniqueWords = new ArrayList<>();
        for (String word : words){
            if (!uniqueWords.contains(word)){
                uniqueWords.add(word);
            }
        }

        return uniqueWords.size();
    }

    static int CountComas(String messege){
        String comas = ".,;_-:";
        int count = 0;
        char[] messegeArray = messege.toCharArray();
        for (char letter : messegeArray){
            if (comas.indexOf(letter) != -1){
                count++;
            }
        }

        return count;
    }

    static float AverageWordSize(ArrayList<String> words){
        int temp = 0;

        for (String word : words){
            temp += word.length();
        }
        
        return  temp / words.size();
    }

    static float AverageSentenceSize(String[] sentences){
        int temp = 0;

        for (String sentence : sentences){
            temp += sentence.length();
        }


        return temp / sentences.length;
    }

    static void MostFrequantWords(ArrayList<String> words, Integer count){
        Map<String, Integer> frequancyMap = new HashMap<>();

        for (String word : words){
            frequancyMap.put(word, frequancyMap.getOrDefault(word, 0) + 1);
        }

        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(frequancyMap.entrySet());
        sortedEntries.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        for (int i = 0; i < count; i++){
            Map.Entry<String, Integer> entry = sortedEntries.get(i);

            System.out.println((i + 1) + ". " + entry.getKey());
        }
    }
}