import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\main\\resources\\input.txt"));
        ArrayList<String> badIps = new ArrayList<>();
        ArrayList<String> goodIps = new ArrayList<>();

        for (String line : lines) {
            boolean goodIp = false;
            for (int primaryPointer = 0; primaryPointer < line.length() - 2; primaryPointer++) {
                String aba = line.substring(primaryPointer, primaryPointer + 3);
                if (!aba.split("")[0].equals(aba.split("")[2])){
                    continue;
                }

                boolean goodAba = false;
                String[] splitLine = line.split("\\[");
                for (String str : splitLine) {
                    if (str.contains("]")) {
                        str = str.split("]")[1];
                    }

                    if (str.contains(aba)) {
                        goodAba = true;
                        break;
                    }
                }

                if (!goodAba) {
                    continue;
                }

                String bab = babify(aba);
                for (String str : splitLine) {
                    if (!str.contains("]")) {
                        continue;
                    }

                    str = str.split("]")[0];

                    if (str.contains(bab)) {
                        goodIp = true;
                        break;
                    }
                }

                if (goodIp) {
                    break;
                }
            }

            if (goodIp) {
                goodIps.add(line);
            } else {
                badIps.add(line);
            }
        }

        System.out.println("Good Ips:");
        for (String goodIp : goodIps) {
            System.out.println(goodIp);
        }
        System.out.println();
        System.out.println();
        System.out.println("Bad Ips:");
        for (String badIp : badIps) {
            System.out.println(badIp);
        }

        System.out.println();
        System.out.println();

        System.out.println("Good Ips: " + goodIps.size());
        System.out.println("Done!");
    }

    private static String babify(String input) {
        return input.split("")[1] + input.split("")[0] + input.split("")[1];
    }
}
