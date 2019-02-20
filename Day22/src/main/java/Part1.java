import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("C:\\workspace\\AoC2016\\Day22\\src\\main\\resources\\input.txt"));
        ArrayList<Node> nodes = new ArrayList<>();
        Pattern pattern = Pattern.compile("node.*x(\\d+)-y(\\d+)\\s+\\d+T\\s+(\\d+)T\\s+(\\d+)");
        int pairCount = 0;

        for (String line : lines) {
            Node newNode = new Node();
            Matcher m = pattern.matcher(line);

            if (m.find()) {
                newNode.x = Integer.parseInt(m.group(1));
                newNode.y = Integer.parseInt(m.group(2));
                newNode.used = Integer.parseInt(m.group(3));
                newNode.avail = Integer.parseInt(m.group(4));
                nodes.add(newNode);
            }

        }

        for (int currentNodeIndex = 0 ; currentNodeIndex < nodes.size(); currentNodeIndex++) {
            Node currentNode = nodes.get(currentNodeIndex);
            if (currentNode.used == 0) {
                continue;
            }

            for (int otherNodeIndex = 0; otherNodeIndex < nodes.size(); otherNodeIndex++) {
                if (currentNodeIndex == otherNodeIndex) {
                    continue;
                }

                Node otherNode = nodes.get(otherNodeIndex);
                if (currentNode.used <= otherNode.avail) {
                    pairCount++;
                }
            }

        }

        System.out.println(pairCount);
        System.out.println("Done!");

    }
}
