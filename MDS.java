import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MDS {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the text to hash:");
        String text = scanner.nextLine();

        try {
            // Creating a MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            // Update the message digest with the input text
            md.update(text.getBytes());
            
            // Compute the digest (hash value)
            byte[] digest = md.digest();
            
            // Convert the byte array into hexadecimal format
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            
            // Output the hash value
            System.out.println("MD5 Hash: " + hexString.toString());

        } catch (NoSuchAlgorithmException e) {
            System.err.println("MD5 algorithm not available.");
            e.printStackTrace();
        }
    }
}
