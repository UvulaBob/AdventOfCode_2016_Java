import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Part2 {


    public static void main (String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));
        String input = lines.get(0);

        long characterCount = countCharacters(input);

        System.out.println(characterCount);

    }

    private static long countCharacters(String input) {
        long characterCount = 0;
        for (int primaryPointer = 0; primaryPointer < input.length(); primaryPointer++) {
            if (input.charAt(primaryPointer) != '(') {
                characterCount++;
            } else {
                int rightParenIndex = input.indexOf(')', primaryPointer);
                String dataMarker = input.substring(primaryPointer + 1, rightParenIndex);
                int numberOfCharacters = Integer.parseInt(dataMarker.split("x")[0]);
                int amountOfRepetitions = Integer.parseInt(dataMarker.split("x")[1]);
                String repeatedCharacters = input.substring(rightParenIndex + 1, rightParenIndex + 1 + numberOfCharacters);
                long newCharacters = countCharacters(repeatedCharacters) * amountOfRepetitions;
                characterCount += newCharacters;
                primaryPointer = rightParenIndex + numberOfCharacters;
            }
        }

        return characterCount;

    }

}
