package tool_rental.utils;

import tool_rental.constants.AppConstants;
import tool_rental.models.ToolInventory;
import tool_rental.service.Checkout;
import tool_rental.exceptions.ToolRentalExceptions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CLI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public static void start() {
        System.out.println(AppConstants.WELCOME_MESSAGE);

        while (true) {
            System.out.println(AppConstants.MAIN_MENU);

            int choice = getIntInput(AppConstants.CHOICE_PROMPT);

            switch (choice) {
                case 1:
                    rentTool();
                    break;
                case 2:
                    viewAvailableTools();
                    break;
                case 3:
                    System.out.println(AppConstants.EXIT_MESSAGE);
                    System.out.println(AppConstants.SEPARATOR);
                    return;
                default:
                    System.out.println(AppConstants.INVALID_CHOICE);
            }
        }
    }

    private static void rentTool() {
        System.out.println(AppConstants.RENT_TOOL_HEADER);

        try {
            String toolCode = getStringInput();
            int rentalDays = getIntInput(AppConstants.RENTAL_DAYS_PROMPT);
            int discountPercent = getIntInput(AppConstants.DISCOUNT_PROMPT);
            LocalDate checkoutDate = getDateInput();

            Checkout checkout = new Checkout(toolCode, rentalDays, discountPercent, checkoutDate);
            checkout.printAgreement();
        } catch (InvalidToolCodeException | InvalidRentalDaysException | InvalidDiscountException e) {
            System.err.println(AppConstants.ERROR_PREFIX + e.getMessage());
        } catch (Exception e) {
            System.err.println(AppConstants.ERROR_PREFIX + AppConstants.UNEXPECTED_ERROR);
            System.err.println(AppConstants.ERROR_DETAILS + e.getMessage());
        }
        System.out.println(AppConstants.SEPARATOR);
    }

    private static void viewAvailableTools() {
        System.out.println(AppConstants.AVAILABLE_TOOLS_HEADER);
        ToolInventory.getAllTools().forEach(
                (code, tool) -> System.out.println(AppConstants.TOOL_INFO_PREFIX + "Code: " + code + ", Type: "
                        + tool.getType().getName() + ", Brand: " + tool.getBrand()));
        System.out.println(AppConstants.SEPARATOR);
    }

    private static String getStringInput() {
        System.out.print(AppConstants.TOOL_CODE_PROMPT);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(AppConstants.INVALID_NUMBER_INPUT);
            }
        }
    }

    private static LocalDate getDateInput() {
        while (true) {
            try {
                System.out.print(AppConstants.CHECKOUT_DATE_PROMPT);
                return LocalDate.parse(scanner.nextLine().trim(), dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println(AppConstants.INVALID_DATE_FORMAT);
            }
        }
    }
}