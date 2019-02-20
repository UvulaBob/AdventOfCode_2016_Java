import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Part2 {
    private static final int maxColumn = 50;
    private static final int maxRow = 6;
    private static boolean[][] grid = new boolean[maxRow][maxColumn];

    public static void main (String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\UvulaBob\\IdeaProjects\\AoC2016\\Java\\Day8\\src\\main\\resources\\input.txt"));


        for (String line : lines) {
            String[] splitInstruction = line.split(" ", 2);
            String instruction = splitInstruction[0];

            switch (instruction) {
                case "rect":
                    makeRectangle(splitInstruction[1]);
                    break;
                case "rotate":
                    if (line.contains("row")) {
                        rotateRow(splitInstruction[1].split(" ", 2)[1]);
                    } else {
                        rotateColumn(splitInstruction[1].split(" ", 2)[1]);
                    }
                    break;
            }

            for (int row = 0; row < maxRow; row++) {
                for (int column = 0; column < maxColumn; column++) {
                    if (grid[row][column]) {
                        System.out.print("#");
                    } else {
                        System.out.print(".");
                    }
                }
                System.out.println();
            }
            System.out.println();
        }

        int lightCount = 0;
        for (int row = 0; row < maxRow; row++) {
            for (int column = 0; column < maxColumn; column++) {
                lightCount += (grid[row][column]) ? 1 : 0;
            }
        }

        System.out.println(lightCount);
    }

    private static void rotateColumn(String instruction) {
        String[] splitInstruction = instruction.split(" by ");
        int column = Integer.parseInt(splitInstruction[0].substring(2));
        int amount = Integer.parseInt(splitInstruction[1]);

        for (int i = amount; i > 0; i--){
            boolean tempValue = grid[maxRow - 1][column];
            for (int row = maxRow - 1; row >= 0; row--) {
                if (row == 0) {
                    grid[row][column] = tempValue;
                } else {
                    grid[row][column] = grid[row - 1][column];
                }
            }
        }
    }

    private static void rotateRow(String instruction) {
        String[] splitInstruction = instruction.split(" by ");
        int row = Integer.parseInt(splitInstruction[0].substring(2));
        int amount = Integer.parseInt(splitInstruction[1]);

        for (int i = amount; i > 0; i--){
            boolean tempValue = grid[row][maxColumn - 1];
            for (int column = maxColumn - 1; column >= 0; column--) {
                if (column == 0) {
                    grid[row][column] = tempValue;
                } else {
                    grid[row][column] = grid[row][column - 1];
                }
            }
        }


    }

    private static void makeRectangle(String instruction) {
        int width = Integer.parseInt(instruction.split("x")[0]);
        int height = Integer.parseInt(instruction.split("x")[1]);

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                grid[row][column] = true;
            }
        }
    }
}
