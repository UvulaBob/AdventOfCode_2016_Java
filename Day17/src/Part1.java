import javafx.util.Pair;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Part1 {
    private static final String input = "bwnlcvfs";
    private static final int gridSize = 3;


    public static void main (String[] args) {
        HashMap<String, Integer> distances = new HashMap<>();
        distances.put("0,0|", 0);

        HashSet<String> visited = new HashSet<>();

        Queue<String> queue = new ArrayDeque<>();
        queue.add("0,0|");

        while (!queue.isEmpty()) {
            String currentPoint = queue.poll();

            if (currentPoint.contains("3,3")) {
                System.out.println("Victory!");
                System.out.println(currentPoint);
                break;
            }

            if (visited.contains(currentPoint)) {
                continue;
            }

            visited.add(currentPoint);

            int currentDistance = distances.get(currentPoint);
            HashMap<String, Integer[]> validDirections = getValidDirections(currentPoint);
            int currentX = Integer.parseInt(currentPoint.substring(0, 1));
            int currentY = Integer.parseInt(currentPoint.substring(2, 3));

            for (Map.Entry<String, Integer[]> entry : validDirections.entrySet()) {
                Integer[] directionModifier = entry.getValue();
                String direction = entry.getKey();
                String futurePoint = String.format("%s,%s", currentX + directionModifier[0], currentY + directionModifier[1]) + currentPoint.substring(3) + direction;
                distances.put(futurePoint, currentDistance + 1);
                queue.add(futurePoint);
            }
        }

        System.out.println("Done!");

    }

    private static HashMap<String, Integer[]> getValidDirections(String currentPoint) {

        String thingToHash = input;

        if (currentPoint.length() > 4) {
            thingToHash += currentPoint.split("\\|")[1];
        }

        String validCharacters = "bcdef";

        ArrayList<Integer[]> directionModifiers = new ArrayList<>();
        directionModifiers.add(new Integer[] {0,-1});
        directionModifiers.add(new Integer[] {0,1});
        directionModifiers.add(new Integer[] {-1,0});
        directionModifiers.add(new Integer[] {1,0});

        String[] directions = new String[] {"U", "D", "L", "R"};

        HashMap<String, Integer[]> validDirections = new HashMap<>();
        String hash = getMd5(thingToHash);

        String[] possibleDirections = hash.substring(0, 4).split("");
        int currentX = Integer.parseInt(currentPoint.substring(0, 1));
        int currentY = Integer.parseInt(currentPoint.substring(2, 3));

        for (int index = 0; index < possibleDirections.length; index++) {
            String possibleDirection = possibleDirections[index];
            if (validCharacters.contains(possibleDirection)) {
                Integer[] directionModifier = directionModifiers.get(index);
                if (currentX + directionModifier[0] > gridSize || currentX + directionModifier[0] < 0 || currentY + directionModifier[1] > gridSize || currentY + directionModifier[1] < 0) {
                    continue;
                }

                validDirections.put(directions[index], directionModifier);
            }
        }

        return validDirections;
    }

    private static String getMd5(String input)
    {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
