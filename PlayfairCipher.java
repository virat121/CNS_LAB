import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class PlayFairCipher {
    private char[][] matrix = new char[5][5];
    private boolean paddingAdded = false;

    public PlayFairCipher(String key) {
        Set<Character> charset = new LinkedHashSet<>();
        for (char c : (key.toUpperCase() + "ABCDEFGHIKLMNOPQRSTUVWXYZ").toCharArray()) {
            if (c >= 'A' && c <= 'Z' && c != 'J')
                charset.add(c);
        }
        int i = 0;
        for (char c : charset)
            matrix[i / 5][i++ % 5] = c;
    }

    private int[] findPosition(char c) {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (matrix[i][j] == c)
                    return new int[] { i, j };
        return null;
    }

    private String processText(String text, boolean encrypt) {
        StringBuilder result = new StringBuilder();
        text = text.toUpperCase().replaceAll("[^A-Z]", "").replace('J', 'I');
        StringBuilder formattedText = new StringBuilder();
        
        for (int i = 0; i < text.length(); i++) {
            char a = text.charAt(i);
            formattedText.append(a);
            if (i < text.length() - 1 && a == text.charAt(i + 1)) {
                formattedText.append('X');
            }
        }
        if (formattedText.length() % 2 != 0) {
            formattedText.append('X');
            paddingAdded = true;
        }
        text = formattedText.toString();
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);

            int[] posA = findPosition(a);
            int[] posB = findPosition(b);

            if (posA[0] == posB[0]) {
                result.append(matrix[posA[0]][(posA[1] + (encrypt ? 1 : 4)) % 5]);
                result.append(matrix[posB[0]][(posB[1] + (encrypt ? 1 : 4)) % 5]);
            } else if (posA[1] == posB[1]) {
                result.append(matrix[(posA[0] + (encrypt ? 1 : 4)) % 5][posA[1]]);
                result.append(matrix[(posB[0] + (encrypt ? 1 : 4)) % 5][posB[1]]);
            } else {
                result.append(matrix[posA[0]][posB[1]]);
                result.append(matrix[posB[0]][posA[1]]);
            }
        }

        if (!encrypt && paddingAdded && result.charAt(result.length() - 1) == 'X') {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the key:");
        String key = scanner.nextLine();
        System.out.println("Enter the plaintext:");
        String plaintext = scanner.nextLine();
        PlayFairCipher cipher = new PlayFairCipher(key);
        String ciphertext = cipher.processText(plaintext, true);
        System.out.println("Encrypted text: " + ciphertext);
        String decryptedText = cipher.processText(ciphertext, false);
        System.out.println("Decrypted text: " + decryptedText);
        scanner.close();
    }
}
