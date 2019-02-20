import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;

public class Part1 {
    private static HashSet<String> trapConditions = new HashSet<>();
    private static final int rowCount = 40;

    public static void main (String[] args) throws IOException {
        String input = Files.readAllLines(Paths.get("C:\\workspace\\AoC2016\\Day18\\src\\main\\resources\\input.txt")).get(0);

        trapConditions.add("^^.");
        trapConditions.add(".^^");
        trapConditions.add("^..");
        trapConditions.add("..^");

        ArrayList<String> floor = new ArrayList<>();
        floor.add(input);

        int safeTileCount = 0;

        for (int i = 1; i < rowCount; i++) {
            String currentRow = floor.get(floor.size() - 1);
            String[] splitRow = currentRow.split("");
            StringBuilder newRow = new StringBuilder();
            for (int tileNumber = 0 ; tileNumber < splitRow.length; tileNumber++) {
                String leftTile = (tileNumber > 0) ? splitRow[tileNumber - 1] : ".";
                String centerTile = splitRow[tileNumber];
                String rightTile = (tileNumber < splitRow.length - 1) ? splitRow[tileNumber + 1] : ".";
                String threeTiles = leftTile + centerTile + rightTile;
                String newTile = trapConditions.contains(threeTiles) ? "^" : ".";
                newRow.append(newTile);
            }

            floor.add(newRow.toString());
        }

        for (String row : floor) {
            safeTileCount += row.length() - row.replace(".", "").length();
        }

        System.out.println(safeTileCount);
        System.out.println("Done!");
    }
}
