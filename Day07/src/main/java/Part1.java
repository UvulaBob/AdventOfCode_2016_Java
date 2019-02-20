import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("C:\\workspace\\AoC2016\\Day7\\src\\main\\resources\\input.txt"));
        HashMap<String, String> badIps = new HashMap<>();
        ArrayList<String> goodIps = new ArrayList<>();

        for (String line : lines) {
            boolean goodIp = false;
            String reason = "No ABBA Found";
            for (int primaryPointer = 0; primaryPointer < line.length() - 3; primaryPointer++) {
                String sequence = line.substring(primaryPointer, primaryPointer + 2);
                String reversedSequence = reverse(sequence);
                if (sequence.equals(reversedSequence)) {
                    continue;
                }
                String searchSequence = sequence + reversedSequence;
                int indexOfMatch = line.indexOf(searchSequence);
                    if (indexOfMatch > -1) {
                        goodIp = true;
                        Pattern pattern = Pattern.compile("(\\[.*?\\])");
                        Matcher m = pattern.matcher(line);
                        while (m.find()) {
                            if (m.group(0).contains(searchSequence)) {
                                reason = searchSequence + " found in brackets";
                                goodIp = false;
                                break;
                            }
                        }
                        if (!goodIp) {
                            break;
                        }
                    }
                }
            if (goodIp) {
                if (goodIps.contains(line)) {
                    throw new RuntimeException("What the fuck?");
                } else {
                    goodIps.add(line);
                }
            } else {
                if (badIps.keySet().contains(line)) {
                    throw new RuntimeException("What the fuck?");
                } else {
                    badIps.put(line, reason);
                }
            }
        }

        System.out.println("Good Ips:");
        for (String goodIp : goodIps) {
            System.out.println(goodIp);
        }
        System.out.println();
        System.out.println();
        System.out.println("Bad Ips:");
        for (Map.Entry<String, String> entry : badIps.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println();
        }
        System.out.println();
        System.out.println();

        System.out.println("Good Ips: " + goodIps.size());
        System.out.println("Done!");
    }

    private static String reverse(String input) {
        return input.split("")[1] + input.split("")[0];
    }
}
