import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Part1 {

    public static void main (String[] args) throws IOException {
        int[][] keypad = new int[3][3];
        int value = 1;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                keypad[x][y] = value;
                value++;
            }
        }

        int x = 1;
        int y = 1;

        List<String> instructions = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));
        for (String instruction : instructions) {
            for (String step : instruction.split("")) {
                switch (step) {
                    case "U":
                        if (x > 0) {
                            x--;
                        }
                        break;
                    case "D":
                        if (x < 2) {
                            x++;
                        }
                        break;
                    case "L":
                        if (y > 0) {
                            y--;
                        }
                        break;
                    case "R":
                        if (y < 2) {
                            y++;
                        }
                        break;
                }
//                int currentKeypadSpot = keypad[x][y];
//                System.out.println(currentKeypadSpot);
            }
            System.out.println(keypad[x][y]);
        }

    }
}
