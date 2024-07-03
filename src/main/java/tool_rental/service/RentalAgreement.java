package tool_rental.service;

import tool_rental.models.Tool;
import tool_rental.utils.ChargeDays;
import tool_rental.utils.CheckoutCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.Data;

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

    public RentalAgreement(Checkout checkout) {
        this.tool = checkout.getTool();
        this.rentalDays = checkout.getNumDaysRenting();
        this.checkoutDate = checkout.getCheckoutDate();
        this.dueDate = ChargeDays.calculateDueDate(checkoutDate, rentalDays);
        this.dailyRentalCharge = tool.getType().getDailyCharge();
        this.chargeDays = checkout.getApplicableChargeDays();
        this.discountPercent = checkout.getDiscountPercentage();

        CheckoutCalculator calculator = new CheckoutCalculator(checkout);
        this.finalCharge = round(calculator.getFinalCharge());

        this.preDiscountCharge = round(chargeDays * dailyRentalCharge);
        this.discountAmount = round(preDiscountCharge * discountPercent / 100);
    }

    public String generateAgreement() {
        StringBuilder agreement = new StringBuilder();
        String separator = "------------------------------------------------------------";

        agreement.append("\n").append(separator).append("\n");
        agreement.append("                   Rental Agreement                    \n");
        agreement.append(separator).append("\n");
        agreement.append(String.format("%-20s: %s%n", "Tool code", tool.getCode()));
        agreement.append(String.format("%-20s: %s%n", "Tool type", tool.getType().getName()));
        agreement.append(String.format("%-20s: %s%n", "Tool brand", tool.getBrand()));
        agreement.append(String.format("%-20s: %d%n", "Rental days", rentalDays));
        agreement.append(String.format("%-20s: %s%n", "Check out date", formatDate(checkoutDate)));
        agreement.append(String.format("%-20s: %s%n", "Due date", formatDate(dueDate)));
        agreement.append(String.format("%-20s: %s%n", "Daily rental charge", formatCurrency(dailyRentalCharge)));
        agreement.append(String.format("%-20s: %d%n", "Charge days", chargeDays));
        agreement.append(String.format("%-20s: %s%n", "Pre-discount charge", formatCurrency(preDiscountCharge)));
        agreement.append(String.format("%-20s: %s%n", "Discount percent", formatPercent(discountPercent)));
        agreement.append(String.format("%-20s: %s%n", "Discount amount", formatCurrency(discountAmount)));
        agreement.append(String.format("%-20s: %s%n", "Final charge", formatCurrency(finalCharge)));
        agreement.append(separator).append("\n");

        return agreement.toString();
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
