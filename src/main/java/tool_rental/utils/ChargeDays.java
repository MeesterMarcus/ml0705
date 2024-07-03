package tool_rental.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class ChargeDays {

    public static int countChargeDays(LocalDate checkoutDate, int rentalDays, boolean chargeWeekday,
            boolean chargeWeekend, boolean chargeHoliday) {
        LocalDate startDate = checkoutDate.plusDays(1);
        LocalDate endDate = checkoutDate.plusDays(rentalDays);
        int chargeDays = 0;

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            if (isChargeableDay(date, chargeWeekday, chargeWeekend, chargeHoliday)) {
                chargeDays++;
            } else {
            }
        }

        return chargeDays;
    }

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

    private static boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    private static boolean isHoliday(LocalDate date) {
        return isIndependenceDay(date) || isLaborDay(date);
    }

    private static boolean isIndependenceDay(LocalDate date) {
        LocalDate observedDate = getObservedIndependenceDay(date.getYear());
        return date.equals(observedDate);
    }

    private static LocalDate getObservedIndependenceDay(int year) {
        LocalDate julyFourth = LocalDate.of(year, Month.JULY, 4);
        if (julyFourth.getDayOfWeek() == DayOfWeek.SATURDAY) {
            return julyFourth.minusDays(1); // Observed on Friday
        } else if (julyFourth.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return julyFourth.plusDays(1); // Observed on Monday
        }
        return julyFourth;
    }

    private static boolean isLaborDay(LocalDate date) {
        return date.getMonth() == Month.SEPTEMBER &&
                date.getDayOfWeek() == DayOfWeek.MONDAY &&
                date.getDayOfMonth() <= 7;
    }

    public static LocalDate calculateDueDate(LocalDate checkoutDate, int rentalDays) {
        return checkoutDate.plusDays(rentalDays);
    }
}
