package tool_rental.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import tool_rental.service.Checkout;
import tool_rental.models.ToolInventory;

public class CLI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public static void start() {
        System.out.println("Welcome to the Tool Rental Point of Sales System");

        while (true) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Rent a tool");
            System.out.println("2. View available tools");
            System.out.println("3. Exit");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    rentTool();
                    break;
                case 2:
                    viewAvailableTools();
                    break;
                case 3:
                    System.out.println("\nThank you for using the Tool Rental Point of Sales System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void rentTool() {
        String toolCode = getStringInput("Enter tool code: ");
        int rentalDays = getIntInput("Enter number of rental days: ");
        int discountPercent = getIntInput("Enter discount percentage (0-100): ");
        LocalDate checkoutDate = getDateInput("Enter checkout date (MM/dd/yyyy): ");

        try {
            Checkout checkout = new Checkout(toolCode, rentalDays, discountPercent, checkoutDate);
            checkout.printAgreement();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewAvailableTools() {
        System.out.println("\nAvailable Tools:");
        ToolInventory.getAllTools().forEach(
                (code, tool) -> System.out.println(code + " - " + tool.getType() + " (" + tool.getBrand() + ")"));
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static LocalDate getDateInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return LocalDate.parse(scanner.nextLine().trim(), dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use MM/dd/yyyy.");
            }
        }
    }
}
