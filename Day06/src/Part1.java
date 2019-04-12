import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Part1 {

    public static void main (String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));
        ArrayList<ArrayList<String>> columns = new ArrayList<>();

        for (int i = 0; i < lines.get(0).length(); i++) {
            columns.add(new ArrayList<>());
        }

        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                columns.get(i).add(line.split("")[i]);
            }
        }

        String message = "";
        for (int i = 0; i < columns.size(); i++) {
            String mostFrequentLetter = "a";
            int frequentLetterCount = 0;
            HashMap<String, Integer> letterCounts = new HashMap<>();
            for (String letter : columns.get(i)) {
                letterCounts.putIfAbsent(letter, 0);
                letterCounts.put(letter, letterCounts.get(letter) + 1);
            }
            for (String letter : letterCounts.keySet()) {
                int letterCount = letterCounts.get(letter);
                if (letterCount > frequentLetterCount) {
                    mostFrequentLetter = letter;
                    frequentLetterCount = letterCount;
                }
            }
            message += mostFrequentLetter;
        }

        System.out.println(message);
        System.out.println("Done!");
    }
}
