import java.util.*;

class StringMatching {
    // Brute Force String Matching
    public void bruteForceMatch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        System.out.println("Brute Force Matching Positions:");
        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                System.out.println("Pattern found at index " + i);
            }
        }
    }

    // Rabin-Karp Algorithm for String Matching
    public void rabinKarpMatch(String text, String pattern, int prime) {
        int n = text.length();
        int m = pattern.length();
        int hashText = 0, hashPattern = 0, h = 1;
        int d = 256;

        for (int i = 0; i < m - 1; i++)
            h = (h * d) % prime;

        for (int i = 0; i < m; i++) {
            hashPattern = (d * hashPattern + pattern.charAt(i)) % prime;
            hashText = (d * hashText + text.charAt(i)) % prime;
        }

        System.out.println("Rabin-Karp Matching Positions:");
        for (int i = 0; i <= n - m; i++) {
            if (hashPattern == hashText) {
                int j;
                for (j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j))
                        break;
                }
                if (j == m)
                    System.out.println("Pattern found at index " + i);
            }
            if (i < n - m) {
                hashText = (d * (hashText - text.charAt(i) * h) + text.charAt(i + m)) % prime;
                if (hashText < 0)
                    hashText = (hashText + prime);
            }
        }
    }
}

public class MainMatching {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the text:");
        String text = sc.nextLine();
        System.out.println("Enter the pattern:");
        String pattern = sc.nextLine();

        StringMatching sm = new StringMatching();

        System.out.println("Choose algorithm:\n1. Brute Force Matching\n2. Rabin-Karp Matching");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                sm.bruteForceMatch(text, pattern);
                break;
            case 2:
                System.out.println("Enter a prime number for hashing:");
                int prime = sc.nextInt();
                sm.rabinKarpMatch(text, pattern, prime);
                break;
            default:
                System.out.println("Invalid choice!");
        }
        sc.close();
    }
}
