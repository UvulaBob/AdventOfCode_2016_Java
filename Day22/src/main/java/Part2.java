import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    private static int highestX = 0;
    private static int highestY = 0;
    private static Node[][] grid;

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("C:\\workspace\\AoC2016\\Day22\\src\\main\\resources\\input.txt"));
        ArrayList<Node> nodes = new ArrayList<>();
        Pattern pattern = Pattern.compile("node.*x(\\d+)-y(\\d+)\\s+\\d+T\\s+(\\d+)T\\s+(\\d+)");

        for (String line : lines) {
            Node newNode = new Node();
            Matcher m = pattern.matcher(line);

            if (m.find()) {
                newNode.x = Integer.parseInt(m.group(1));
                newNode.y = Integer.parseInt(m.group(2));
                newNode.used = Integer.parseInt(m.group(3));
                newNode.avail = Integer.parseInt(m.group(4));
                nodes.add(newNode);
                highestX = (newNode.x > highestX) ? newNode.x : highestX;
                highestY = (newNode.y > highestY) ? newNode.y : highestY;
            }

        }

        grid = new Node[highestY + 1][highestX + 1];

        for (Node node : nodes) {
            grid[node.y][node.x] = node;
        }

        printGridWithNumbers(grid);
        printGridWithSymbols(grid);


        System.out.println("Done!");

    }

    private static void printGridWithNumbers(Node[][] grid) {
        for (Node[] row : grid) {
            for (Node node : row) {
                System.out.print(node + " ");
            }
            System.out.println();
        }
    }

    private static void printGridWithSymbols(Node[][] grid) {
        for (int y = 0; y < grid.length; y++) {
            Node[] row = grid[y];
            for (int x = 0; x < row.length; x++) {
                if (x == 0 && y == 0) {
                    System.out.print("S ");
                    continue;
                }

                if (x == highestX && y == 0) {
                    System.out.print("G ");
                    continue;
                }

                Node currentNode = grid[y][x];
                if (currentNode.used > 200) {
                    System.out.print("! ");
                    continue;
                }
                if (currentNode.used == 0) {
                    System.out.print("_ ");
                    continue;
                }

                ArrayList<Node> adjacentNodes = getAdjacentNodes(x, y);
                int openSides = 0;
                for (Node adjacentNode : adjacentNodes) {
                    if (adjacentNode.avail > currentNode.used){
                        openSides++;
                    }
                }
                if (openSides > 0) {
                    System.out.print(". ");
                } else {
                    System.out.print("# ");
                }
            }
            System.out.println();
        }
    }

    private static ArrayList<Node> getAdjacentNodes(int x, int y) {
        ArrayList<Node> adjacentNodes = new ArrayList<>();

        if (x > 0) {
            adjacentNodes.add(grid[y][x - 1]);
        }
        if (x < highestX) {
            adjacentNodes.add(grid[y][x + 1]);
        }

        if (y > 0) {
            adjacentNodes.add(grid[y - 1][x]);
        }
        if (y < highestY) {
            adjacentNodes.add(grid[y + 1][x]);
        }

        return adjacentNodes;
    }

}
