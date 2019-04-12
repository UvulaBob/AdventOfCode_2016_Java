import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));

        Queue<Disc> discs = new PriorityQueue<>();
        Pattern pattern = Pattern.compile("Disc #([0-9])+ has ([0-9]+) positions; at time=0, it is at position ([0-9]+)");
        for (String line : lines) {
            Matcher m = pattern.matcher(line);
            if (m.find()) {
                Disc newDisc = new Disc();
                newDisc.id = Integer.parseInt(m.group(1));
                newDisc.positions = Integer.parseInt(m.group(2));
                newDisc.currentPosition = Integer.parseInt(m.group(3));
                discs.add(newDisc);
            }
        }

        int time = 0;
        int timeMultiplier = 1;

        while (true) {

            Disc topDisc = discs.peek();

            while (topDisc.futurePosition(topDisc.id) == 0) {
                discs.poll();
                if (discs.isEmpty()) {
                    break;
                }
                timeMultiplier *= topDisc.positions;
                topDisc = discs.peek();
            }

            if (discs.isEmpty()) {
                break;
            }

            int nextTime = time + timeMultiplier;
            for (Disc disc : discs) {
                for (int i = time; i < nextTime; i++) {
                    disc.rotate();
                }
            }

            time = nextTime;
        }

        System.out.println(time);
        System.out.println("Done!");
    }
}
