import java.util.Scanner;

public class RSA {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter two prime numbers (p and q):");
        long p = scanner.nextLong();
        long q = scanner.nextLong();

        long n = p * q;
        long phi = (p - 1) * (q - 1);

        long e = calculatePublicKey(phi);
        System.out.println("Public key (e, n): (" + e + ", " + n + ")");

        long d = calculatePrivateKey(e, phi);
        System.out.println("Private key (d, n): (" + d + ", " + n + ")");

        System.out.println("Enter message to encrypt (as a number):");
        long message = scanner.nextLong();

        long encryptedMessage = encrypt(message, e, n);
        System.out.println("Encrypted message: " + encryptedMessage);

        long decryptedMessage = decrypt(encryptedMessage, d, n);
        System.out.println("Decrypted message: " + decryptedMessage);

        scanner.close();
    }

    // Function to calculate public key (e)
    private static long calculatePublicKey(long phi) {
        long e = 2;
        while (e < phi) {
            if (gcd(e, phi) == 1)
                break;
            e++;
        }
        return e;
    }

    // Function to calculate private key (d)
    private static long calculatePrivateKey(long e, long phi) {
        long d = 1;
        while ((d * e) % phi != 1) {
            d++;
        }
        return d;
    }

    // Function to find greatest common divisor
    private static long gcd(long a, long b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    // Function to encrypt message
    private static long encrypt(long message, long e, long n) {
        return modPow(message, e, n);
    }

    // Function to decrypt message
    private static long decrypt(long encryptedMessage, long d, long n) {
        return modPow(encryptedMessage, d, n);
    }

    // Function to calculate (base^exponent) % modulus using modular exponentiation
    private static long modPow(long base, long exponent, long modulus) {
        long result = 1;
        base = base % modulus;
        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = (result * base) % modulus;
            }
            exponent = exponent >> 1;
            base = (base * base) % modulus;
        }
        return result;
    }
}
