import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Part1 {

    public static void main(String[] args) {
        String input = "abbhdwsy";
        int index = 0;

        String password = "";
        while (password.length() < 8) {

            String hash = getMd5(input + index);
            String hashSubString = hash.substring(0, 5);
            while (!hashSubString.equals("00000")) {
                index++;
                hash = getMd5(input + index);
                hashSubString = hash.substring(0, 5);
            }
            password += hash.substring(5, 6);
            index++;
        }

        System.out.print(password);


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
