package tool_rental;

import org.junit.Test;
import tool_rental.constants.AppConstants;
import tool_rental.exceptions.ToolRentalExceptions;
import tool_rental.service.Checkout;
import tool_rental.service.RentalAgreement;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Unit tests to ensure our checkout and agreement functionality is working as intended
 */
public class AppTest {

    /**
     * Test 1: Exception for invalid discount percent
     */
    @Test
    public void testCheckout1() {
        int invalidDiscount = 101;
        try {
            new Checkout(AppConstants.JACKHAMMER_RIDGID_CODE, 5, invalidDiscount, LocalDate.of(2015, 9, 3));
            fail("Expected IllegalArgumentException was not thrown");
        } catch (ToolRentalExceptions.InvalidDiscountException e) {
            assertEquals(AppConstants.INVALID_DISCOUNT + invalidDiscount, e.getMessage());
        }
    }

    /**
     * Test 2: 3 day rental of Ladder
     */
    @Test
    public void testCheckout2() {
        Checkout checkout = new Checkout(AppConstants.LADDER_CODE, 3, 10, LocalDate.of(2020, 7, 2));
        RentalAgreement agreement = new RentalAgreement(checkout);

        assertEquals("LADW", agreement.getTool().getCode());
        assertEquals("Ladder", agreement.getTool().getType().getName());
        assertEquals("Werner", agreement.getTool().getBrand());
        assertEquals(99, agreement.getRentalDays());
        assertEquals(LocalDate.of(2020, 7, 2), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2020, 7, 5), agreement.getDueDate());
        assertEquals(1.99, agreement.getDailyRentalCharge(), 0.001);
        assertEquals(2, agreement.getChargeDays());
        assertEquals(3.98, agreement.getPreDiscountCharge(), 0.01);
        assertEquals(10, agreement.getDiscountPercent());
        assertEquals(0.40, agreement.getDiscountAmount(), 0.01);
        assertEquals(3.58, agreement.getFinalCharge(), 0.01);
    }

    /**
     * Test 3: 5 day rental of Chainsaw
     */
    @Test
    public void testCheckout3() {
        Checkout checkout = new Checkout(AppConstants.CHAINSAW_CODE, 5, 25, LocalDate.of(2015, 7, 2));
        RentalAgreement agreement = new RentalAgreement(checkout);

        assertEquals("CHNS", agreement.getTool().getCode());
        assertEquals("Chainsaw", agreement.getTool().getType().getName());
        assertEquals("Stihl", agreement.getTool().getBrand());
        assertEquals(5, agreement.getRentalDays());
        assertEquals(LocalDate.of(2015, 7, 2), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2015, 7, 7), agreement.getDueDate());
        assertEquals(1.49, agreement.getDailyRentalCharge(), 0.001);
        assertEquals(3, agreement.getChargeDays());
        assertEquals(4.47, agreement.getPreDiscountCharge(), 0.01);
        assertEquals(25, agreement.getDiscountPercent());
        assertEquals(1.12, agreement.getDiscountAmount(), 0.01);
        assertEquals(3.35, agreement.getFinalCharge(), 0.01);
    }

    /**
     * Test 4: 6 day rental of DeWalt Jackhammer
     */
    @Test
    public void testCheckout4() {
        Checkout checkout = new Checkout(AppConstants.JACKHAMMER_DEWALT_CODE, 6, 0, LocalDate.of(2015, 9, 3));
        RentalAgreement agreement = new RentalAgreement(checkout);

        assertEquals("JAKD", agreement.getTool().getCode());
        assertEquals("Jackhammer", agreement.getTool().getType().getName());
        assertEquals("DeWalt", agreement.getTool().getBrand());
        assertEquals(6, agreement.getRentalDays());
        assertEquals(LocalDate.of(2015, 9, 3), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2015, 9, 9), agreement.getDueDate());
        assertEquals(2.99, agreement.getDailyRentalCharge(), 0.001);
        assertEquals(3, agreement.getChargeDays());
        assertEquals(8.97, agreement.getPreDiscountCharge(), 0.01);
        assertEquals(0, agreement.getDiscountPercent());
        assertEquals(0.00, agreement.getDiscountAmount(), 0.01);
        assertEquals(8.97, agreement.getFinalCharge(), 0.01);
    }

    /**
     * Test 5: 9 day rental of Ridgid Jackhammer
     */
    @Test
    public void testCheckout5() {
        Checkout checkout = new Checkout(AppConstants.JACKHAMMER_RIDGID_CODE, 9, 0, LocalDate.of(2015, 7, 2));
        RentalAgreement agreement = new RentalAgreement(checkout);

        assertEquals("JAKR", agreement.getTool().getCode());
        assertEquals("Jackhammer", agreement.getTool().getType().getName());
        assertEquals("Ridgid", agreement.getTool().getBrand());
        assertEquals(9, agreement.getRentalDays());
        assertEquals(LocalDate.of(2015, 7, 2), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2015, 7, 11), agreement.getDueDate());
        assertEquals(2.99, agreement.getDailyRentalCharge(), 0.001);
        assertEquals(5, agreement.getChargeDays());
        assertEquals(14.95, agreement.getPreDiscountCharge(), 0.01);
        assertEquals(0, agreement.getDiscountPercent());
        assertEquals(0.00, agreement.getDiscountAmount(), 0.01);
        assertEquals(14.95, agreement.getFinalCharge(), 0.01);
    }

    /**
     * Test 6: 4 day rental of Ridgid Jackhammer
     */
    @Test
    public void testCheckout6() {
        Checkout checkout = new Checkout(AppConstants.JACKHAMMER_RIDGID_CODE, 4, 50, LocalDate.of(2020, 7, 2));
        RentalAgreement agreement = new RentalAgreement(checkout);

        assertEquals("JAKR", agreement.getTool().getCode());
        assertEquals("Jackhammer", agreement.getTool().getType().getName());
        assertEquals("Ridgid", agreement.getTool().getBrand());
        assertEquals(4, agreement.getRentalDays());
        assertEquals(LocalDate.of(2020, 7, 2), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2020, 7, 6), agreement.getDueDate());
        assertEquals(2.99, agreement.getDailyRentalCharge(), 0.001);
        assertEquals(1, agreement.getChargeDays());
        assertEquals(2.99, agreement.getPreDiscountCharge(), 0.01);
        assertEquals(50, agreement.getDiscountPercent());
        assertEquals(1.50, agreement.getDiscountAmount(), 0.01);
        assertEquals(1.50, agreement.getFinalCharge(), 0.01);
    }

    /**
     * Additional test for invalid rental days
     */
    @Test
    public void testInvalidRentalDays() {
        int numDaysRenting = 0;
        try {
            new Checkout(AppConstants.CHAINSAW_CODE, numDaysRenting, 0, LocalDate.now());
            fail("Expected IllegalArgumentException was not thrown");
        } catch (ToolRentalExceptions.InvalidRentalDaysException e) {
            assertEquals(AppConstants.INVALID_RENTAL_DAYS + numDaysRenting, e.getMessage());
        }
    }

    /**
     * Additional test for invalid tool code
     */
    @Test
    public void testInvalidToolCode() {
        try {
            new Checkout("INVALID", 1, 0, LocalDate.now());
            fail("Expected IllegalArgumentException was not thrown");
        } catch (ToolRentalExceptions.InvalidToolCodeException e) {
            assertTrue(e.getMessage().startsWith(AppConstants.TOOL_NOT_FOUND));
        }
    }
}
