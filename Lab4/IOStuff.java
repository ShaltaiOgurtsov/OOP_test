package Lab4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IOStuff {
    public static void AddToFile(String word){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("moves.txt", true))){
            writer.write(word);
            writer.newLine();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<String> GetArray(){
        try {
            List<String> lines = Files.readAllLines(Paths.get("moves.txt"));
            lines.removeIf(String::isBlank);
            return new ArrayList<>(lines);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
