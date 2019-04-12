import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    public static void main (String[] args) throws IOException {
        ArrayList<ArrayList<Integer>> triangles = new ArrayList<>();
        triangles.add(new ArrayList<>());
        triangles.add(new ArrayList<>());
        triangles.add(new ArrayList<>());

        int validTriangles = 0;
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));
        for (String line : lines) {
            Pattern pattern = Pattern.compile("(\\d+)\\s+(\\d+)\\s+(\\d+)");
            Matcher m = pattern.matcher(line);
            if (m.find()) {
                int a = Integer.parseInt(m.group(1));
                int b = Integer.parseInt(m.group(2));
                int c = Integer.parseInt(m.group(3));
                triangles.get(0).add(a);
                triangles.get(1).add(b);
                triangles.get(2).add(c);
            }
            if (triangles.get(0).size() == 3) {
                for (ArrayList<Integer> triangle : triangles) {
                    if (validTriangle(triangle)) {
                        validTriangles++;
                    }
                    triangle.clear();
                }
            }
        }

        System.out.print("Valid Triangles: " + validTriangles);
    }

    private static boolean validTriangle(ArrayList<Integer> triangle) {
        int a = triangle.get(0);
        int b = triangle.get(1);
        int c = triangle.get(2);

        if (!(a + b > c)) {
            return false;
        }

        if (!(a + c > b)) {
            return false;
        }

        return c + b > a;
    }
}
