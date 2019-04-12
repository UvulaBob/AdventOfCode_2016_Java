import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public static void main (String[] args) throws IOException {
        int validTriangles = 0;
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));
        for (String line : lines) {
            Pattern pattern = Pattern.compile("(\\d+)\\s+(\\d+)\\s+(\\d+)");
            Matcher m = pattern.matcher(line);
            if (m.find()) {
                int a = Integer.parseInt(m.group(1));
                int b = Integer.parseInt(m.group(2));
                int c = Integer.parseInt(m.group(3));
                validTriangles += (validTriangle(a, b, c)) ? 1 : 0;
            }
        }

        System.out.print("Valid Triangles: " + validTriangles);
    }

    private static boolean validTriangle(int a, int b, int c) {
        if (!(a + b > c)) {
            return false;
        }

        if (!(a + c > b)) {
            return false;
        }

        return c + b > a;
    }
}
