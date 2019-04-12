import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Part1 {


    public static void main (String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));
        String input = lines.get(0);
        String[] splitInput = input.split("");
        StringBuilder decompressedInput = new StringBuilder();
        for (int primaryPointer = 0; primaryPointer < splitInput.length; primaryPointer++) {
            if (!splitInput[primaryPointer].equals("(")) {
                decompressedInput.append(splitInput[primaryPointer]);
            } else {
                int secondaryPointer = primaryPointer + 4;
                while (!splitInput[secondaryPointer].equals(")")) {
                    secondaryPointer++;
                }
                String dataMarker = input.substring(primaryPointer + 1, secondaryPointer);
                secondaryPointer++;
                int numberOfCharactersToRepeat = Integer.parseInt(dataMarker.split("x")[0]);
                int numberOfRepeats = Integer.parseInt(dataMarker.split("x")[1]);

                String charactersToRepeat = input.substring(secondaryPointer, secondaryPointer + numberOfCharactersToRepeat);
                for (int i = 1; i <= numberOfRepeats; i++) {
                    decompressedInput.append(charactersToRepeat);
                }
                primaryPointer = secondaryPointer + numberOfCharactersToRepeat - 1;
            }
        }

        System.out.println(decompressedInput);
        System.out.println(decompressedInput.length());

    }

}
