package tool_rental.service;

import java.time.LocalDate;

import lombok.Data;
import tool_rental.models.Tool;
import tool_rental.models.ToolInventory;
import tool_rental.utils.ChargeDays;
import tool_rental.utils.CheckoutCalculator;

@Data
public class Checkout {
    private Tool tool;
    private String toolCode;
    private int numDaysRenting;
    private int applicableChargeDays;
    private int discountPercentage;
    private LocalDate checkoutDate;
    private double total;
    
    // Primary constructor
    public Checkout(String toolCode, int numDaysRenting, int discountPercentage, LocalDate checkoutDate) {
        if (numDaysRenting < 1) {
            throw new IllegalArgumentException("Number of days renting must be 1 or more days.");
        }
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Discount percentage is not valid.");
        }
        this.toolCode = toolCode;
        this.numDaysRenting = numDaysRenting;
        this.discountPercentage = discountPercentage;
        this.checkoutDate = checkoutDate;
        initializeCheckout();
    }

    // Constructor without discount
    public Checkout(String toolCode, int numDaysRenting, LocalDate checkoutDate) {
        this(toolCode, numDaysRenting, 0, checkoutDate);
    }

    private void initializeCheckout() {
        this.tool = ToolInventory.getToolByCode(toolCode);
        boolean weekdayCharge = this.tool.getType().isWeekdayCharge();
        boolean weekendCharge = this.tool.getType().isWeekendCharge();
        boolean holidayCharge = this.tool.getType().isHolidayCharge();
        this.applicableChargeDays = ChargeDays.countChargeDays(checkoutDate, numDaysRenting, weekdayCharge, weekendCharge, holidayCharge);
        CheckoutCalculator calculator = new CheckoutCalculator(this);
        this.total = calculator.getFinalCharge();
    }
}