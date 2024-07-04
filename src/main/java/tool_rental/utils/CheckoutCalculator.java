package tool_rental.utils;

import lombok.Data;
import tool_rental.models.Tool;
import tool_rental.service.Checkout;

/**
 * Helper utility to calculate the final charge
 */
@Data
public class CheckoutCalculator {

    private Checkout checkout;

    public CheckoutCalculator(Checkout checkout) {
        this.checkout = checkout;
    }

    /**
     * Calculate the final charge using the information from Checkout
     * @return double
     */
    public double getFinalCharge() {
        int discount = this.checkout.getDiscountPercentage();
        int applicableChargeDays = checkout.getApplicableChargeDays();
        Tool tool = checkout.getTool();
        double totalWithoutDiscount = applicableChargeDays * tool.getType().getDailyCharge();
        double finalCharge = totalWithoutDiscount;
        if (discount > 0) {
            finalCharge = totalWithoutDiscount - (totalWithoutDiscount * (discount / 100.0));
        }
        return finalCharge;
    }
}