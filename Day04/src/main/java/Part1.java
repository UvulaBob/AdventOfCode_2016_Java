import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Part1 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("C:\\workspace\\AoC2016\\Day4\\src\\main\\resources\\input.txt"));

        int sectorTotals = 0;
        for (String line : lines ) {
            String roomName = line.substring(0, line.lastIndexOf("-"));
            int sectorId = Integer.parseInt(line.substring(line.lastIndexOf("-") + 1, line.indexOf("[")));
            String checksum = line.substring(line.indexOf("[") + 1, line.indexOf("]"));

            if (roomIsValid(roomName, checksum)) {
                sectorTotals += sectorId;
            }
        }

        System.out.println(sectorTotals);


    }

    private static boolean roomIsValid(String roomName, String checksum) {
        Queue<LetterCount> sortedLetterCounts = new PriorityQueue<>();
        HashMap<String, Integer> letterCounts = new HashMap<>();

        roomName = roomName.replace("-", "");
        String[] letters = roomName.split("");
        for (String letter : letters) {
            for (int i = 0; i < letters.length; i++) {
                letterCounts.putIfAbsent(letter, 0);
                letterCounts.put(letter, letterCounts.get(letter) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : letterCounts.entrySet()) {
            sortedLetterCounts.add(new LetterCount(entry.getKey(), entry.getValue()));
        }

        for (String letter : checksum.split("")) {
            LetterCount letterCount = sortedLetterCounts.poll();
            if (letterCount != null) {
                if (!letter.equals(letterCount.letter)) {
                    return false;
                }
            }
        }

        return true;
    }
}
