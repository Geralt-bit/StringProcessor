package org.example;
import java.util.Stack;

public class StringProcessor {

     // Method to check if a password is strong
        public boolean isStrongPassword(String password) {
            if (password.length() < 8) {
                return false; // Assuming a minimum length for a strong password
            }

            boolean hasUpperCase = false;
            boolean hasLowerCase = false;
            boolean hasDigit = false;
            boolean hasSpecialSymbol = false;

            for (char ch : password.toCharArray()) {
                if (Character.isUpperCase(ch)) {
                    hasUpperCase = true;
                } else if (Character.isLowerCase(ch)) {
                    hasLowerCase = true;
                } else if (Character.isDigit(ch)) {
                    hasDigit = true;
                } else if (!Character.isLetterOrDigit(ch)) {
                    hasSpecialSymbol = true;
                }
            }

            return hasUpperCase && hasLowerCase && hasDigit && hasSpecialSymbol;
        }

        // Method to count the number of digits in a sentence
        public int calculateDigits(String sentence) {
            int count = 0;
            for (char ch : sentence.toCharArray()) {
                if (Character.isDigit(ch)) {
                    count++;
                }
            }
            return count;
        }

        // Method to calculate the number of words in a sentence
        public int calculateWords(String sentence) {
            if (sentence == null || sentence.trim().isEmpty()) {
                return 0; // No words if the sentence is empty or null
            }
            String[] words = sentence.trim().split("\\s+"); // Split by whitespace
            return words.length;
        }

        // Method to calculate a mathematical expression
        public double calculateExpression(String expression) {
            return evaluate(expression);
        }

        // Helper method to evaluate the expression
        private double evaluate(String expression) {
            Stack<Double> values = new Stack<>();
            Stack<Character> operators = new Stack<>();

            for (int i = 0; i < expression.length(); i++) {
                char ch = expression.charAt(i);

                // If the character is a whitespace, skip it
                if (ch == ' ') {
                    continue;
                }

                // If the character is a number, push it to the values stack
                if (Character.isDigit(ch)) {
                    StringBuilder sb = new StringBuilder();
                    while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                        sb.append(expression.charAt(i++));
                    }
                    values.push(Double.parseDouble(sb.toString()));
                    i--; // Decrement i since it will be incremented in the for loop
                } else if (ch == '(') {
                    operators.push(ch);
                } else if (ch == ')') {
                    while (operators.peek() != '(') {
                        values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                    }
                    operators.pop(); // Remove the '(' from the stack
                } else if (isOperator(ch)) {
                    while (!operators.isEmpty() && hasPrecedence(ch, operators.peek())) {
                        values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                    }
                    operators.push(ch);
                }
            }

            while (!operators.isEmpty()) {
                values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
            }

            return values.pop();
        }

        // Helper method to determine if the character is a valid operator
        private boolean isOperator(char ch) {
            return ch == '+' || ch == '-' || ch == '*' || ch == '/';
        }

        // Helper method to check operator precedence
        private boolean hasPrecedence(char op1, char op2) {
            if (op2 == '(' || op2 == ')') {
                return false;
            }
            return (op1 != '*' && op1 != '/') || (op2 != '+' && op2 != '-');
        }

        // Helper method to apply the operator on two operands
        private double applyOperation(char operator, double b, double a) {
            switch (operator) {
                case '+':
                    return a + b;
                case '-':
                    return a - b;
                case '*':
                    return a * b;
                case '/':
                    if (b == 0) {
                        throw new UnsupportedOperationException("Cannot divide by zero");
                    }
                    return a / b;
            }
            return 0;
        }
}
