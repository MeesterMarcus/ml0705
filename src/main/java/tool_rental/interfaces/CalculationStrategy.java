package tool_rental.interfaces;

import tool_rental.service.Checkout;

/**
 * Interface for creating calculation strategy. This will allow us to create additional strategies in the future.
 */
public interface CalculationStrategy {
    double calculateFinalCharge(Checkout checkout);
}
