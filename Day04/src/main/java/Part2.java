import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Part2 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("C:\\workspace\\AoC2016\\Day4\\src\\main\\resources\\input.txt"));
        HashMap<String, Integer> roomNames = new HashMap<>();

        for (String line : lines ) {
            String roomName = line.substring(0, line.lastIndexOf("-"));
            int sectorId = Integer.parseInt(line.substring(line.lastIndexOf("-") + 1, line.indexOf("[")));

            roomNames.put(decryptRoomName(roomName, sectorId), sectorId);

        }

        for (String roomName : roomNames.keySet()) {
            System.out.println(String.format("%s", roomName));
        }
    }

    private static String decryptRoomName(String roomName, int sectorId) {
        ArrayList<String> alphabet = new ArrayList<>(Arrays.asList("abcdefghijklmnopqrstuvwxyz".split("")));
        StringBuilder sb = new StringBuilder();

        for (String letter : roomName.split("")) {
            if (letter.equals("-")) {
                sb.append(" ");
                continue;
            }

            int alphabetCharacterIndex = alphabet.indexOf(letter);
            alphabetCharacterIndex += sectorId;

            alphabetCharacterIndex = (alphabetCharacterIndex % alphabet.size());
            sb.append(alphabet.get(alphabetCharacterIndex));
        }

        return sb.toString();
    }

}
