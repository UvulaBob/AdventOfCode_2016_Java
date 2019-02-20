import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Part1 {

    public static void main (String[] args)throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("C:\\workspace\\AoC2016\\Day20\\src\\main\\resources\\input.txt"));
        ArrayList<FirewallRule> rules = new ArrayList<>();

        for (String line : lines) {
            FirewallRule newRule = new FirewallRule();
            newRule.start = Long.parseLong(line.split("-")[0]);
            newRule.end = Long.parseLong(line.split("-")[1]);
            rules.add(newRule);
        }

        long counter = 0;
        boolean searching = true;
        while (searching) {
            int applicableRules = 0;
            for (FirewallRule rule : rules){
                if (rule.contains(counter)) {
                    applicableRules++;
                    break;
                }
            }
            if (applicableRules > 0) {
                counter++;
            } else {
                searching = false;
            }
        }

        System.out.println(counter);
        System.out.println("Done!");



    }
}
