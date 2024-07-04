package tool_rental.exceptions;

import tool_rental.constants.AppConstants;

/**
 * Custom Exceptions used throughout the app.
 */
public class ToolRentalExceptions {

    public static class ToolRentalException extends RuntimeException {
        public ToolRentalException(String message) {
            super(message);
        }
    }

    public static class InvalidToolCodeException extends ToolRentalException {
        public InvalidToolCodeException(String code) {
            super(AppConstants.TOOL_NOT_FOUND + code);
        }
    }

    public static class InvalidRentalDaysException extends ToolRentalException {
        public InvalidRentalDaysException(int days) {
            super(AppConstants.INVALID_RENTAL_DAYS + days);
        }
    }

    public static class InvalidDiscountException extends ToolRentalException {
        public InvalidDiscountException(int discount) {
            super(AppConstants.INVALID_DISCOUNT + discount);
        }
    }
}