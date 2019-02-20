import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public static void main (String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\UvulaBob\\IdeaProjects\\AoC2016\\Java\\Day10\\src\\main\\resources\\input.txt"));
        HashMap<String, Bot> bots = new HashMap<>();
        HashMap<String, Integer> outputs = new HashMap<>();

        for (String line : lines) {
            if (line.contains("value")) {
                Pattern pattern = Pattern.compile("value (\\d+) goes to bot (\\d+)");
                Matcher m = pattern.matcher(line);
                if (m.find()) {
                    String botId = m.group(2);
                    bots.putIfAbsent(botId, new Bot());
                    Bot bot = bots.get(botId);
                    bot.takeChip(Integer.parseInt(m.group(1)));
                    bot.id = Integer.parseInt(botId);
                }
            } else {
                Pattern pattern = Pattern.compile("(\\d+) gives low to (\\w+) (\\d+) and high to (\\w+) (\\d+)");
                Matcher m = pattern.matcher(line);
                if (m.find()) {
                    String sourceBotId = m.group(1);
                    bots.putIfAbsent(sourceBotId, new Bot());
                    Bot bot = bots.get(sourceBotId);
                    bot.id = Integer.parseInt(sourceBotId);
                    bot.lowDestination = m.group(2);
                    bot.lowDestinationId = m.group(3);
                    bot.highDestination = m.group(4);
                    bot.highDestinationId = m.group(5);
                }
            }
        }


        ArrayList<Bot> queue = new ArrayList<>(bots.values());

        while (!queue.isEmpty()) {
            Collections.sort(queue);
            Bot sourceBot = queue.get(0);
            queue.remove(0);

            if (sourceBot.chipCount < 2) {
                throw new RuntimeException("Not enough chips to act!");
            }

            // decide which chips go where.
            if (sourceBot.lowDestination.equals("output")) {
                outputs.put(sourceBot.lowDestinationId, sourceBot.lowChip);
            } else {
                Bot destinationBot = bots.get(sourceBot.lowDestinationId);
                destinationBot.takeChip(sourceBot.lowChip);
            }

            if (sourceBot.highDestination.equals("output")) {
                outputs.put(sourceBot.highDestinationId, sourceBot.highChip);
            } else {
                Bot destinationBot = bots.get(sourceBot.highDestinationId);
                destinationBot.takeChip(sourceBot.highChip);
            }
        }

        for (Bot bot : bots.values()) {
            if (bot.lowChip == 17 && bot.highChip == 61) {
                System.out.println(bot);
            }
        }


        System.out.println("Done!");

    }
}
