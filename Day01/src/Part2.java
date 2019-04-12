import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Part2 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> facings = new ArrayList<>(Arrays.asList("N", "E", "S", "W"));
        HashSet<String> visitedPoints = new HashSet<>();

        int x = 0;
        int y = 0;

        List<String> inputLines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));
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
                    for (int i = movementAmount; i > 0 ; i--) {
                        x--;
                        String point = x + ", " + y;
                        if (visitedPoints.contains(point)) {
                            System.out.println("We've already been to " + point);
                            System.out.println("Distance: " + (Math.abs(x) + Math.abs(y)));
                            return;
                        } else {
                            visitedPoints.add(point);
                        }
                    }
                    break;
                case "E":
                    for (int i = movementAmount; i > 0 ; i--) {
                        x++;
                        String point = x + ", " + y;
                        if (visitedPoints.contains(point)) {
                            System.out.println("We've already been to " + point);
                            System.out.println("Distance: " + (Math.abs(x) + Math.abs(y)));
                            return;
                        } else {
                            visitedPoints.add(point);
                        }
                    }
                    break;
                case "N":
                    for (int i = movementAmount; i > 0 ; i--) {
                        y--;
                        String point = x + ", " + y;
                        if (visitedPoints.contains(point)) {
                            System.out.println("We've already been to " + point);
                            System.out.println("Distance: " + (Math.abs(x) + Math.abs(y)));
                            return;
                        } else {
                            visitedPoints.add(point);
                        }
                    }
                    break;
                case "S":
                    for (int i = movementAmount; i > 0 ; i--) {
                        y++;
                        String point = x + ", " + y;
                        if (visitedPoints.contains(point)) {
                            System.out.println("We've already been to " + point);
                            System.out.println("Distance: " + (Math.abs(x) + Math.abs(y)));
                            return;
                        } else {
                            visitedPoints.add(point);
                        }
                    }
                    break;
            }

        }

        System.out.println("Distance: " + (Math.abs(x) + Math.abs(y)));
        System.out.println("Done!");


    }
}
