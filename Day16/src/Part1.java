import java.util.ArrayList;

public class Part1 {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String input = "10001001100000001";
        String dragonCurve = getDragonCurve(input);
        int targetLength = 272;
        String checksum;

        while (dragonCurve.length() < targetLength) {
            dragonCurve = getDragonCurve(dragonCurve);
        }

        dragonCurve = dragonCurve.substring(0, targetLength);
        checksum = getChecksum(dragonCurve);

        while (checksum.length() % 2 == 0) {
            checksum = getChecksum(checksum);
        }


        System.out.println(checksum);
        System.out.println("Done!");
        System.out.println("Finished in " + (System.currentTimeMillis() - startTime) + " milliseconds");
    }

    private static String getChecksum(String input) {
        StringBuilder newChecksum = new StringBuilder();
        for (int i = 0; i < input.length(); i += 2) {
            String pair = input.substring(i, i + 2);
            if (pair.equals("00") || pair.equals("11")) {
                newChecksum.append("1");
            } else {
                newChecksum.append("0");
            }
        }

        return newChecksum.toString();
    }

    private static String getDragonCurve(String input) {
        StringBuilder a = new StringBuilder(input);
        a.reverse();
        StringBuilder b = new StringBuilder(a.toString());
        a.reverse();
        StringBuilder bitFlippedB = new StringBuilder();

        for (String character : b.toString().split("")) {
            if (character.equals("1")) {
                bitFlippedB.append("0");
            } else {
                bitFlippedB.append("1");
            }
        }

        a.append("0");
        a.append(bitFlippedB);
        return a.toString();
    }
}
