package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StringProcessor processor = new StringProcessor();
        Scanner scanner = new Scanner(System.in);

        // Check for strong password
        System.out.println("Enter a password to check if it's strong:");
        String password = scanner.nextLine();
        boolean isStrong = processor.isStrongPassword(password);
        System.out.println("Is the password strong? " + isStrong);

        // Count digits
        System.out.println("Enter a sentence to count digits:");
        String digitSentence = scanner.nextLine();
        int digitCount = processor.calculateDigits(digitSentence);
        System.out.println("Number of digits: " + digitCount);

        // Count words
        System.out.println("Enter a sentence to count words:");
        String wordSentence = scanner.nextLine();
        int wordCount = processor.calculateWords(wordSentence);
        System.out.println("Number of words: " + wordCount);

        // Evaluate expression
        System.out.println("Enter a mathematical expression to evaluate:");
        String expression = scanner.nextLine();
        try {
            double result = processor.calculateExpression(expression);
            System.out.println("Result of the expression: " + result);
        } catch (UnsupportedOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Close the scanner
        scanner.close();
    }
}
