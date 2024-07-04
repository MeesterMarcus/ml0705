package tool_rental.service;

import lombok.Data;
import tool_rental.exceptions.ToolRentalExceptions;
import tool_rental.interfaces.CalculationStrategy;
import tool_rental.models.Tool;
import tool_rental.models.ToolInventory;
import tool_rental.utils.ChargeDays;
import tool_rental.utils.StandardCalculationStrategy;

import java.time.LocalDate;

/**
 * This class handles the checkout operations required to rent a tool
 */
@Data
public class Checkout {

    private Tool tool;
    private String toolCode;
    private int numDaysRenting;
    private int applicableChargeDays;
    private int discountPercentage;
    private LocalDate checkoutDate;
    private double total;
    private CalculationStrategy calculationStrategy;

    /**
     * Primary constructor
     *
     * @param toolCode:           String representing the unique lookup code for a tool
     * @param numDaysRenting:     int representing the base num days renting
     * @param discountPercentage: int representing the discount percent e.g. 25 -> 25%
     * @param checkoutDate:       LocalDate representing the checkout date
     */
    public Checkout(String toolCode, int numDaysRenting, int discountPercentage, LocalDate checkoutDate) {
        if (numDaysRenting < 1) {
            throw new ToolRentalExceptions.InvalidRentalDaysException(numDaysRenting);
        }
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new ToolRentalExceptions.InvalidDiscountException(discountPercentage);
        }
        this.toolCode = toolCode;
        this.numDaysRenting = numDaysRenting;
        this.discountPercentage = discountPercentage;
        this.checkoutDate = checkoutDate;
        initializeCheckout();
    }

    /**
     * Constructor without discount
     *
     * @param toolCode:       String representing the unique lookup code for a tool
     * @param numDaysRenting: int representing the base num days renting
     * @param checkoutDate:   LocalDate representing the checkout date
     */
    public Checkout(String toolCode, int numDaysRenting, LocalDate checkoutDate) {
        this(toolCode, numDaysRenting, 0, checkoutDate);
    }

    /**
     * Initialize the properties needed in order to check out and rent the item
     */
    private void initializeCheckout() {
        this.tool = ToolInventory.getToolByCode(toolCode);
        boolean weekdayCharge = this.tool.getType().isWeekdayCharge();
        boolean weekendCharge = this.tool.getType().isWeekendCharge();
        boolean holidayCharge = this.tool.getType().isHolidayCharge();
        this.applicableChargeDays = ChargeDays.countChargeDays(checkoutDate, numDaysRenting, weekdayCharge,
                weekendCharge, holidayCharge);
        this.calculationStrategy = new StandardCalculationStrategy(); // Default strategy
        this.total = calculationStrategy.calculateFinalCharge(this);
    }

    /**
     * Use the RentalAgreement class to build the agreement, and print to the console
     */
    public void printAgreement() {
        RentalAgreement agreement = new RentalAgreement(this);
        System.out.println(agreement.generateAgreement());
    }
}