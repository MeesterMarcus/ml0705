package tool_rental.constants;

/**
 * This file is used in order to store constants used throughout the app.
 */
public final class AppConstants {

    // Private constructor to prevent instantiation
    private AppConstants() {
    }

    // Tool Codes
    public static final String CHAINSAW_CODE = "CHNS";
    public static final String LADDER_CODE = "LADW";
    public static final String JACKHAMMER_DEWALT_CODE = "JAKD";
    public static final String JACKHAMMER_RIDGID_CODE = "JAKR";

    // Brand Names
    public static final String STIHL_BRAND = "Stihl";
    public static final String WERNER_BRAND = "Werner";
    public static final String DEWALT_BRAND = "DeWalt";
    public static final String RIDGID_BRAND = "Ridgid";

    // Tool Types
    public static final String CHAINSAW_TYPE = "Chainsaw";
    public static final String LADDER_TYPE = "Ladder";
    public static final String JACKHAMMER_TYPE = "Jackhammer";

    // Messages
    public static final String INVALID_RENTAL_DAYS = "Number of rental days must be at least 1, but was: ";
    public static final String INVALID_DISCOUNT = "Discount percentage must be between 0 and 100, but was: ";
    public static final String TOOL_NOT_FOUND = "No tool found with code: ";

    // Rental Agreement
    public static final String RENTAL_AGREEMENT_SEPARATOR = "------------------------------------------------------------";

    // CLI
    public static final String WELCOME_MESSAGE = "**********************************************\n" +
            "*      Welcome to the Tool Rental POS        *\n" +
            "**********************************************";

    public static final String MAIN_MENU = "\nMain Menu:\n" +
            "1. Rent a Tool\n" +
            "2. View Available Tools\n" +
            "3. Exit\n" +
            "**********************************************";

    public static final String CHOICE_PROMPT = "Please enter your choice (1-3): ";
    public static final String EXIT_MESSAGE = "\nThank you for using the Tool Rental POS. Have a great day!";
    public static final String INVALID_CHOICE = "Invalid choice. Please select an option from the menu.";
    public static final String RENT_TOOL_HEADER = "\n*** Rent a Tool ***";
    public static final String TOOL_CODE_PROMPT = "Enter tool code: ";
    public static final String RENTAL_DAYS_PROMPT = "Enter number of rental days: ";
    public static final String DISCOUNT_PROMPT = "Enter discount percentage (0-100): ";
    public static final String CHECKOUT_DATE_PROMPT = "Enter checkout date (MM/dd/yyyy): ";
    public static final String ERROR_PREFIX = "Error: ";
    public static final String AVAILABLE_TOOLS_HEADER = "\n*** Available Tools ***";
    public static final String TOOL_INFO_PREFIX = "";
    public static final String SEPARATOR = "**********************************************";
    public static final String INVALID_NUMBER_INPUT = "Invalid input. Please enter a valid number.";
    public static final String INVALID_DATE_FORMAT = "Invalid date format. Please use MM/dd/yyyy";
    public static final String UNEXPECTED_ERROR = "Encountered unexpected error.";
    public static final String ERROR_DETAILS = "Error Details: ";
}