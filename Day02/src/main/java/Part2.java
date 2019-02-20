import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Part2 {

    public static void main (String[] args) throws IOException {
        HashMap<String, String> keypad = new HashMap<>();
        keypad.put("0,0", "7");
        keypad.put("0,-1", "3");
        keypad.put("0,-2", "1");
        keypad.put("0,1", "B");
        keypad.put("0,2", "D");
        keypad.put("-1,0", "6");
        keypad.put("-2,0", "5");
        keypad.put("1,0", "8");
        keypad.put("2,0", "9");
        keypad.put("-1,1", "A");
        keypad.put("1,1", "C");
        keypad.put("-1,-1", "2");
        keypad.put("1,-1", "4");

        int x = -2;
        int y = 0;

        List<String> instructions = Files.readAllLines(Paths.get("C:\\workspace\\AoC2016\\Day2\\src\\main\\resources\\input.txt"));
        for (String instruction : instructions) {
            ArrayList<String> steps = new ArrayList<>();
            for (String step : instruction.split("")) {
                switch (step) {
                    case "U":
                        if (keypad.keySet().contains(key(x, y - 1))) {
                            y--;
                        }
                        break;
                    case "D":
                        if (keypad.keySet().contains(key(x, y + 1))) {
                            y++;
                        }
                        break;
                    case "L":
                        if (keypad.keySet().contains(key(x - 1, y))) {
                            x--;
                        }
                        break;
                    case "R":
                        if (keypad.keySet().contains(key(x + 1, y))) {
                            x++;
                        }
                        break;
                }
                String currentKeypadSpot = keypad.get(key(x, y));
                steps.add(step + " -> " + currentKeypadSpot);

            }
            System.out.println(steps);
            System.out.println(keypad.get(key(x, y)));
        }

    }

    private static String key(int x, int y) {
        return String.format("%s,%s", x, y);
    }
}
