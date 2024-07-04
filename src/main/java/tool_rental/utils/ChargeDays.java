package tool_rental.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

/**
 * Helper utility class to determine the applicable charge days for a given tool rental
 */
public class ChargeDays {

    public static int countChargeDays(LocalDate checkoutDate, int rentalDays, boolean chargeWeekday,
            boolean chargeWeekend, boolean chargeHoliday) {
        LocalDate startDate = checkoutDate.plusDays(1);
        LocalDate endDate = checkoutDate.plusDays(rentalDays);
        int chargeDays = 0;

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            if (isChargeableDay(date, chargeWeekday, chargeWeekend, chargeHoliday)) {
                chargeDays++;
            }
        }

        return chargeDays;
    }

    /**
     * Determine whether or not the date fits the criteria for a chargeable day
     * @param date: LocalDate we are analyzing
     * @param chargeWeekday: boolean determining if we should charge weekdays
     * @param chargeWeekend: boolean determining if we should charge weekends
     * @param chargeHoliday: boolean determining if we should charge holidays
     * @return boolean
     */
    private static boolean isChargeableDay(LocalDate date, boolean chargeWeekday, boolean chargeWeekend,
            boolean chargeHoliday) {
        boolean isWeekend = isWeekend(date);
        boolean isHoliday = isHoliday(date);

        if (isWeekend && !chargeWeekend) {
            return false;
        }
        if (isHoliday && !chargeHoliday) {
            return false;
        }
        if (!isWeekend && !isHoliday && !chargeWeekday) {
            return false;
        }
        return true;
    }

    /**
     * Check if the date lands on a weekend
     * @param date: LocalDate we are analyzing
     * @return boolean
     */
    private static boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    /**
     * Check if the date lands on a Holiday
     * @param date: LocalDate we are analyzing
     * @return boolean
     */
    private static boolean isHoliday(LocalDate date) {
        return isIndependenceDay(date) || isLaborDay(date);
    }

    /**
     * Helper function to check if date lands on Independence Day
     * @param date: LocalDate we are analyzing
     * @return boolean
     */
    private static boolean isIndependenceDay(LocalDate date) {
        LocalDate observedDate = getObservedIndependenceDay(date.getYear());
        return date.equals(observedDate);
    }

    /**
     * Helper function for Independence Day edge cases regarding weekend
     * @param year: int
     * @return LocalDate
     */
    private static LocalDate getObservedIndependenceDay(int year) {
        LocalDate julyFourth = LocalDate.of(year, Month.JULY, 4);
        if (julyFourth.getDayOfWeek() == DayOfWeek.SATURDAY) {
            return julyFourth.minusDays(1); // Observed on Friday
        } else if (julyFourth.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return julyFourth.plusDays(1); // Observed on Monday
        }
        return julyFourth;
    }

    /**
     * Helper function to check if date lands on Labor Day
     * @param date: LocalDate we are analyzing
     * @return boolean
     */
    private static boolean isLaborDay(LocalDate date) {
        return date.getMonth() == Month.SEPTEMBER &&
                date.getDayOfWeek() == DayOfWeek.MONDAY &&
                date.getDayOfMonth() <= 7;
    }

    /**
     * Given the checkout date and rental days, determine the due date
     * @param checkoutDate: LocalDate representing the date of checkout
     * @param rentalDays: int representing the number of days the tool is being rented for
     * @return LocalDate
     */
    public static LocalDate calculateDueDate(LocalDate checkoutDate, int rentalDays) {
        return checkoutDate.plusDays(rentalDays);
    }
}
