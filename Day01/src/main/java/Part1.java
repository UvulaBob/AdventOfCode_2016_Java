import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Part1 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> facings = new ArrayList<>(Arrays.asList("N", "E", "S", "W"));

        int x = 0;
        int y = 0;

        List<String> inputLines = Files.readAllLines(Paths.get("C:\\workspace\\AoC2016\\Day1\\src\\main\\resources\\input.txt"));
        List<String> directions = new ArrayList<>(Arrays.asList(inputLines.get(0).split(", ")));
        for (String direction : directions) {
            String turnDirection = direction.split("")[0];
            Integer movementAmount = Integer.parseInt(direction.split(turnDirection)[1]);

            if (turnDirection.equals("L")) {
                Collections.rotate(facings, 1);
            } else {
                Collections.rotate(facings, -1);
            }

            switch (facings.get(0)) {
                case "W":
                    x -= movementAmount;
                    break;
                case "E":
                    x += movementAmount ;
                    break;
                case "N":
                    y -= movementAmount;
                    break;
                case "S":
                    y += movementAmount;
                    break;
            }

        }

        System.out.println("Distance: " + (Math.abs(x) + Math.abs(y)));
        System.out.println("Done!");


    }
}
