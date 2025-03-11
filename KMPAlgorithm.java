public class KMPAlgorithm {
    private static int[] computeLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int j = 0;  // Length of the previous longest prefix suffix
        int i = 1;

        lps[0] = 0;  // LPS[0] is always 0

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
                lps[i] = j;
                i++;
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    // KMP search algorithm
    public static void KMPSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        int[] lps = computeLPSArray(pattern);
        int i = 0;  // Index for text
        int j = 0;  // Index for pattern

        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == m) {
                System.out.println("Pattern found at index " + (i - j));
                j = lps[j - 1];
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";
        KMPSearch(text, pattern);
    }
}
