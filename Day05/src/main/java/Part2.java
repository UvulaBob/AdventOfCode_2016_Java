import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

public class Part2 {

    public static void main(String[] args) {
        String input = "abbhdwsy";
        int index = 0;

        ArrayList<String> password = new ArrayList<>(Arrays.asList("_", "_", "_", "_", "_", "_", "_", "_"));
        int knownCharacters = 0;
        while (knownCharacters < 8) {

            String hash = getMd5(input + index);
            String hashSubString = hash.substring(0, 5);
            while (!hashSubString.equals("00000")) {
                index++;
                hash = getMd5(input + index);
                hashSubString = hash.substring(0, 5);
            }

            int characterLocation = 0;
            try {
                characterLocation = Integer.parseInt(hash.substring(5, 6));
                if (characterLocation < 8 && password.get(characterLocation).equals("_")) {
                    password.set(characterLocation, hash.substring(6, 7));
                    knownCharacters++;
                }
            } catch (NumberFormatException e) {

            }
            index++;
        }

        for (String letter : password) {
            System.out.print(letter);
        }


    }

    public static String getMd5(String input)
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
