import java.util.InputMismatchException;
import java.util.Scanner;

public class ValidationUtils {

    // Validates if a string is not empty or just whitespace
    public static boolean isValidString(String str) {
        return str != null && !str.trim().isEmpty();
    }

    // Gets a non-empty string input from the user
    public static String getNonEmptyStringInput(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (!isValidString(input)) {
                System.out.println("Input cannot be empty. Please try again.");
            }
        } while (!isValidString(input));
        return input;
    }

    // Gets an integer input within a specified range
    public static int getIntInput(Scanner scanner, String prompt, int min, int max) {
        int value = -1;
        boolean isValid = false;
        do {
            System.out.print(prompt);
            try {
                value = scanner.nextInt();
                if (value >= min && value <= max) {
                    isValid = true;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
            } finally {
                scanner.nextLine(); // Consume newline left-over
            }
        } while (!isValid);
        return value;
    }

    // Gets a positive integer input
    public static int getPositiveIntInput(Scanner scanner, String prompt) {
        int value = -1;
        boolean isValid = false;
        do {
            System.out.print(prompt);
            try {
                value = scanner.nextInt();
                if (value > 0) {
                    isValid = true;
                } else {
                    System.out.println("Please enter a positive number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
            } finally {
                scanner.nextLine(); // Consume newline left-over
            }
        } while (!isValid);
        return value;
    }
}