package demo;
import java.util.*;

public class DelimiterValidator {
    private Map<Character, Character> delimiters = new HashMap<>();

    public DelimiterValidator() {
        // Initialize with standard delimiters
        delimiters.put('(', ')');
        delimiters.put('[', ']');
        delimiters.put('{', '}');
    }

    public void addCustomDelimiter(char open, char close) {
        delimiters.put(open, close);
    }

    public List<String> validateExpression(String expression) {
        Stack<DelimiterInfo> stack = new Stack<>();
        List<String> errors = new ArrayList<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (delimiters.containsKey(ch)) { // Opening delimiter
                stack.push(new DelimiterInfo(ch, i));
            } else if (delimiters.containsValue(ch)) { // Closing delimiter
                if (stack.isEmpty()) {
                    errors.add("Unmatched closing delimiter '" + ch + "' at position " + i);
                } else {
                    DelimiterInfo top = stack.peek();
                    if (delimiters.get(top.delimiter) == ch) {
                        stack.pop(); // Correct match
                    } else {
                        errors.add("Mismatched delimiter at position " + i + ": expected '"
                                + delimiters.get(top.delimiter) + "' but found '" + ch + "'");
                    }
                }
            }
        }

        // Check for any unmatched opening delimiters left in the stack
        while (!stack.isEmpty()) {
            DelimiterInfo unmatched = stack.pop();
            errors.add("Unmatched opening delimiter '" + unmatched.delimiter + "' at position " + unmatched.position);
        }

        return errors;
    }

    private static class DelimiterInfo {
        char delimiter;
        int position;

        DelimiterInfo(char delimiter, int position) {
            this.delimiter = delimiter;
            this.position = position;
        }
    }

    public static void main(String[] args) {
        DelimiterValidator validator = new DelimiterValidator();
        validator.addCustomDelimiter('<', '>'); // Add custom delimiters

        // Test expressions
        String[] testExpressions = {
            "a = { ( b + c ) * [ d + e ] }",        // Correctly nested
            "a = { ( b + c ) * d ]",                // Incorrect closing delimiter
            "a = < { b + [ c * d ] } >",            // Custom delimiters, correctly nested
            "a = { ( b + c * [ d + e }",            // Missing closing delimiters
            "a = { ( [ b + c ] } )"                 // Incorrect nesting
        };

        // Validate each test expression and print results
        for (String expression : testExpressions) {
            System.out.println("Validating expression: " + expression);
            List<String> errors = validator.validateExpression(expression);
            if (errors.isEmpty()) {
                System.out.println("No errors found.");
            } else {
                System.out.println("Errors:");
                for (String error : errors) {
                    System.out.println(" - " + error);
                }
            }
            System.out.println();
        }
    }
}


