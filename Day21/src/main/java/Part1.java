import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    private static ArrayList<String> input = new ArrayList<>(Arrays.asList("abcdefgh".split("")));

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("C:\\workspace\\AoC2016\\Day21\\src\\main\\resources\\input.txt"));

        Pattern simpleRotatePattern = Pattern.compile("rotate (left|right) (\\d+) step");
        Pattern complexRotatePattern = Pattern.compile("rotate based on position of letter (\\w)");
        Pattern letterSwapPattern = Pattern.compile("swap letter (\\w) with letter (\\w)");
        Pattern positionSwapPattern = Pattern.compile("swap position (\\d) with position (\\d)");
        Pattern movePattern = Pattern.compile("move position (\\d) to position (\\d)");
        Pattern reversePattern = Pattern.compile("reverse positions (\\d) through (\\d)");

        for (String line : lines) {
            System.out.println(input.toString() + "\t(" + line + (")"));
            Matcher m = simpleRotatePattern.matcher(line);
            if (m.find()) {
                int direction = (m.group(1).equals("right") ? 1 : -1);
                int steps = Integer.parseInt(m.group(2));
                rotate(steps * direction);
                continue;
            }

            m = complexRotatePattern.matcher(line);
            if (m.find()) {
                String letter = m.group(1);
                rotate(letter);
                continue;
            }

            m = letterSwapPattern.matcher(line);
            if (m.find()) {
                String firstLetter = m.group(1);
                String secondLetter = m.group(2);
                swap(firstLetter, secondLetter);
                continue;
            }

            m = positionSwapPattern.matcher(line);
            if (m.find()) {
                int firstPosition = Integer.parseInt(m.group(1));
                int secondPosition = Integer.parseInt(m.group(2));
                swap(firstPosition, secondPosition);
                continue;
            }

            m = movePattern.matcher(line);
            if (m.find()) {
                int firstPosition = Integer.parseInt(m.group(1));
                int secondPosition = Integer.parseInt(m.group(2));
                move(firstPosition, secondPosition);
                continue;
            }

            m = reversePattern.matcher(line);
            if (m.find()) {
                int firstPosition = Integer.parseInt(m.group(1));
                int secondPosition = Integer.parseInt(m.group(2));
                reverse(firstPosition, secondPosition);
            }
        }

        System.out.println(input.toString() + "\t(finish)");
        System.out.println(input.toString().replace("[", "").replace("]", "").replace(", ",""));
        System.out.println("Done!");
    }

    private static void swap(int x, int y) {
        String temp = input.get(x);
        input.set(x, input.get(y));
        input.set(y, temp);
    }

    private static void swap(String x, String y) {
        int indexOfX = input.indexOf(x);
        int indexOfY = input.indexOf(y);
        input.set(indexOfX, y);
        input.set(indexOfY, x);
    }

    private static void reverse(int x, int y) {
        String inputToString = input.toString().replace("[", "").replace("]", "").replace(", ","");
        String firstHalf = inputToString.substring(0, x);
        String middle = inputToString.substring(x, y + 1);
        String secondHalf = inputToString.substring(y + 1);

        ArrayList<String> splitMiddle = new ArrayList<>(Arrays.asList(middle.split("")));
        Collections.reverse(splitMiddle);
        middle = splitMiddle.toString().replace("[", "").replace("]", "").replace(", ","");

        input = new ArrayList<>(Arrays.asList((firstHalf + middle + secondHalf).split("")));
    }

    private static void rotate(int steps) {
        Collections.rotate(input, steps);
    }

    private static void rotate(String character) {
        int index = input.indexOf(character);
        int rotationAmount = index + 1;
        rotationAmount += (index >= 4) ? 1 : 0;
        Collections.rotate(input, rotationAmount);
    }

    private static void move(int x, int y) {
        String character = input.get(x);
        input.remove(x);
        input.add(y, character);
    }

}
