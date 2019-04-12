import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public static void main(String[] args) {
        String input = "ihaygndm";
        ArrayList<Integer> indexesWithKeys = new ArrayList<>();
        int index = 0;
        ArrayDeque<String> nextThousandHashes = new ArrayDeque<>();
        for (int i = 0; i < 1000; i++) {
            nextThousandHashes.add(getMd5(input + i));
        }


        while(true) {

            String currentHash = nextThousandHashes.pop();
            nextThousandHashes.add(getMd5(input + (index + 1000)));

            Pattern triplePattern = Pattern.compile("(.)\\1{2,}");
            Matcher tripleMatcher = triplePattern.matcher(currentHash);

            if (tripleMatcher.find()) {
                String character = tripleMatcher.group(1);
                String quint = character + character + character + character + character;
                for (String futureHash : nextThousandHashes) {
                    if (futureHash.contains(quint)) {
                        indexesWithKeys.add(index);
                        break;
                    }
                }
            }

            if (indexesWithKeys.size() == 64) {
                break;
            }

            index++;


        }

        Collections.reverse(indexesWithKeys);
        System.out.println(indexesWithKeys.get(0));
        System.out.println("Done!");


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
