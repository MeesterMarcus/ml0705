package tool_rental.service;

import lombok.Data;
import tool_rental.constants.AppConstants;
import tool_rental.models.Tool;
import tool_rental.utils.ChargeDays;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents the RentalAgreement that will be built at checkout for the tool rental
 */
@Data
public class RentalAgreement {
    private final Tool tool;
    private final int rentalDays;
    private final LocalDate checkoutDate;
    private final LocalDate dueDate;
    private final double dailyRentalCharge;
    private final int chargeDays;
    private final double preDiscountCharge;
    private final int discountPercent;
    private final double discountAmount;
    private final double finalCharge;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yy");

    /**
     * Primary constructor
     *
     * @param checkout: Checkout
     */
    public RentalAgreement(Checkout checkout) {
        this.tool = checkout.getTool();
        this.rentalDays = checkout.getNumDaysRenting();
        this.checkoutDate = checkout.getCheckoutDate();
        this.dueDate = ChargeDays.calculateDueDate(checkoutDate, rentalDays);
        this.dailyRentalCharge = tool.getType().getDailyCharge();
        this.chargeDays = checkout.getApplicableChargeDays();
        this.discountPercent = checkout.getDiscountPercentage();
        this.finalCharge = round(checkout.getTotal());

        this.preDiscountCharge = round(chargeDays * dailyRentalCharge);
        this.discountAmount = round(preDiscountCharge * discountPercent / 100);
    }

    public String generateAgreement() {
        return "\n" + AppConstants.RENTAL_AGREEMENT_SEPARATOR + "\n" +
                "                   Rental Agreement                    \n" +
                AppConstants.RENTAL_AGREEMENT_SEPARATOR + "\n" +
                String.format("%-20s: %s%n", "Tool code", tool.getCode()) +
                String.format("%-20s: %s%n", "Tool type", tool.getType().getName()) +
                String.format("%-20s: %s%n", "Tool brand", tool.getBrand()) +
                String.format("%-20s: %d%n", "Rental days", rentalDays) +
                String.format("%-20s: %s%n", "Check out date", formatDate(checkoutDate)) +
                String.format("%-20s: %s%n", "Due date", formatDate(dueDate)) +
                String.format("%-20s: %s%n", "Daily rental charge", formatCurrency(dailyRentalCharge)) +
                String.format("%-20s: %d%n", "Charge days", chargeDays) +
                String.format("%-20s: %s%n", "Pre-discount charge", formatCurrency(preDiscountCharge)) +
                String.format("%-20s: %s%n", "Discount percent", formatPercent(discountPercent)) +
                String.format("%-20s: %s%n", "Discount amount", formatCurrency(discountAmount)) +
                String.format("%-20s: %s%n", "Final charge", formatCurrency(finalCharge)) +
                AppConstants.RENTAL_AGREEMENT_SEPARATOR + "\n";
    }

    private String formatDate(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }

    private String formatCurrency(double amount) {
        return String.format("$%.2f", amount);
    }

    private String formatPercent(int percent) {
        return percent + "%";
    }

    private double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
