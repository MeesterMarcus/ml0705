package tool_rental;

import tool_rental.service.Checkout;
import tool_rental.service.RentalAgreement;
import tool_rental.utils.CLI;

import java.time.LocalDate;

/**
 * Main entry point into the application. If CLI_ENABLED, run the CLI, else run the example
 */
public class App {
    private static final boolean CLI_ENABLED = true;

    public static void main(String[] args) {
        if (CLI_ENABLED) {
            CLI.start();
        } else {
            runExample();
        }
    }

    private static void runExample() {
        String toolCode = "JAKR";
        int rentalDays = 4;
        int discountPercent = 50;
        LocalDate checkoutDate = LocalDate.of(2020, 7, 2);

        try {
            Checkout checkout = new Checkout(toolCode, rentalDays, discountPercent, checkoutDate);
            RentalAgreement agreement = new RentalAgreement(checkout);
            System.out.println(agreement.generateAgreement());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
