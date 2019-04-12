import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Part2 {

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
            HashMap<String, Integer> letterCounts = new HashMap<>();
            for (String letter : columns.get(i)) {
                letterCounts.putIfAbsent(letter, 0);
                letterCounts.put(letter, letterCounts.get(letter) + 1);
            }

            ArrayList<LetterCount> sortedLetterCountList = new ArrayList<>();
            for (String letter : letterCounts.keySet()) {
                int letterCount = letterCounts.get(letter);
                sortedLetterCountList.add(new LetterCount(letter, letterCount));
            }

            Collections.sort(sortedLetterCountList);
            message += sortedLetterCountList.get(0).letter;

        }

        System.out.println(message);
        System.out.println("Done!");
    }
}
