package tool_rental.utils;

import lombok.Data;
import tool_rental.models.Tool;
import tool_rental.service.Checkout;

@Data
public class CheckoutCalculator {

    private Checkout checkout;

    public CheckoutCalculator(Checkout checkout) {
        this.checkout = checkout;
    }

    public double getFinalCharge() {
        int discount = this.checkout.getDiscountPercentage();
        int applicableChargeDays = checkout.getApplicableChargeDays();
        Tool tool = checkout.getTool();
        double totalWithoutDiscount = applicableChargeDays * tool.getType().getDailyCharge();
        double finalCharge = totalWithoutDiscount;
        if (discount > 0) {
            finalCharge = totalWithoutDiscount - (totalWithoutDiscount * (discount / 100.0));
        }
        return roundToTwoDecimalPlaces(finalCharge);
    }

    private double roundToTwoDecimalPlaces(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}