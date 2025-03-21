package Day_5;

import java.util.Arrays;
import java.util.Scanner;

public class DriverClass {
    
    // Method 1: Check if two strings are permutations of each other
    public static boolean arePermutations(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        
        return Arrays.equals(arr1, arr2);
    }

    // Method 2: Check if the string is a palindrome
    public static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Method 3: Reverse the string
    public static String reverseString(String str) {
        StringBuilder reversed = new StringBuilder(str);
        return reversed.reverse().toString();
    }

    // Method 4: Separate words (without spaces) into a sentence with spaces
    public static String separateWords(String str) {
        // Here, we assume that each word starts with a capital letter and that the rest of the word is lowercase
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c) && i != 0) {
                result.append(" ");
            }
            result.append(c);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Menu for choosing the operation
        System.out.println("Choose an operation:");
        System.out.println("1. Check if two strings are permutations of each other");
        System.out.println("2. Check if the string is a palindrome");
        System.out.println("3. Reverse the string");
        System.out.println("4. Separate words into a sentence");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character after the integer input

        switch (choice) {
            case 1:
                System.out.println("Enter first string:");
                String str1 = scanner.nextLine();
                System.out.println("Enter second string:");
                String str2 = scanner.nextLine();
                boolean isPermutation = arePermutations(str1, str2);
                System.out.println("Are the strings permutations of each other? " + isPermutation);
                break;

            case 2:
                System.out.println("Enter a string to check if it's a palindrome:");
                String str3 = scanner.nextLine();
                boolean isPalin = isPalindrome(str3);
                System.out.println("Is the string a palindrome? " + isPalin);
                break;

            case 3:
                System.out.println("Enter a string to reverse:");
                String str4 = scanner.nextLine();
                String reversedStr = reverseString(str4);
                System.out.println("Reversed string: " + reversedStr);
                break;

            case 4:
                System.out.println("Enter a string (no spaces between words, e.g., 'HelloWorld'): ");
                String str5 = scanner.nextLine();
                String sentence = separateWords(str5);
                System.out.println("Separated sentence: " + sentence);
                break;

            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
        }

        scanner.close();
    }
}
