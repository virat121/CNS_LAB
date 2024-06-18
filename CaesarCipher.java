import java.util.Scanner;

public class CaesarCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter any String:");
        String str = scanner.nextLine();
        
        System.out.println("Enter the key:");
        int key = scanner.nextInt();
        
        String encryptedString = processString(str, key, true);
        System.out.println("\nEncrypted string is: " + encryptedString);
        
        String decryptedString = processString(encryptedString, key, false);
        System.out.println("\nDecrypted string is: " + decryptedString);
        
        scanner.close();
    }

    public static String processString(String str, int key, boolean encrypt) {
        StringBuilder result = new StringBuilder();
        int direction = encrypt ? 1 : -1;

        for (char ch : str.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                ch = (char) ('A' + (ch - 'A' + direction * key + 26) % 26);
            } else if (Character.isLowerCase(ch)) {
                ch = (char) ('a' + (ch - 'a' + direction * key + 26) % 26);
            }
            result.append(ch);
        }
        return result.toString();
    }
}
