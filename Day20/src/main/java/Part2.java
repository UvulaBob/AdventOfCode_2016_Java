import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Part2 {

    public static void main (String[] args)throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("C:\\workspace\\AoC2016\\Day20\\src\\main\\resources\\input.txt"));
        ArrayList<FirewallRule> rules = new ArrayList<>();

        for (String line : lines) {
            FirewallRule newRule = new FirewallRule();
            newRule.start = Long.parseLong(line.split("-")[0]);
            newRule.end = Long.parseLong(line.split("-")[1]);
            rules.add(newRule);
        }

        Collections.sort(rules);

        for (int currentRuleNumber = 0; currentRuleNumber < rules.size(); currentRuleNumber++) {
            FirewallRule currentRule = rules.get(currentRuleNumber );
            for (int otherRuleNumber = 0; otherRuleNumber < rules.size(); otherRuleNumber++) {
                if (currentRuleNumber == otherRuleNumber) {
                    continue;
                }
                FirewallRule otherRule = rules.get(otherRuleNumber);

                if (otherRule.start >= currentRule.start && otherRule.start <= currentRule.end) {
                    if (otherRule.end > currentRule.end) {
                        currentRule.end = otherRule.end;
                    }
                    rules.remove(otherRuleNumber);
                    otherRuleNumber--;
                } else if (otherRule.start == currentRule.end + 1) {
                    if (otherRule.end > currentRule.end) {
                        currentRule.end = otherRule.end;
                    }
                    rules.remove(otherRuleNumber);
                    otherRuleNumber--;
                }
            }
        }

        long validIps = 0;
        long maxIp = 4294967295L;

        for (long counter = 0; counter <= maxIp; counter++) {
            if (rules.size() == 0) {
                validIps += 1 + maxIp - counter;
                break;
            }

            for (int ruleNumber = 0; ruleNumber < rules.size(); ruleNumber++) {
                FirewallRule rule = rules.get(ruleNumber);

                if (counter < rule.start) {
                    long ipsToBeAdded = rule.start - counter;
                    validIps += ipsToBeAdded;
                    counter = rule.start - 1;
                    break;
                }

                if (counter == rule.start) {
                    counter = rule.end;
                    rules.remove(ruleNumber);
                    break;
                }

            }
        }


        System.out.println();
        System.out.println("Valid IPs: " + validIps);
        System.out.println("Done!");



    }
}
