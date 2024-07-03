package tool_rental.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class ChargeDays {
    
    public static int countChargeDays(LocalDate startDate, int rentalDays, boolean chargeWeekday, boolean chargeWeekend, boolean chargeHoliday) {
        LocalDate endDate = startDate.plusDays(rentalDays);
        int chargeDays = 0;

        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            if (isChargeableDay(date, chargeWeekday, chargeWeekend, chargeHoliday)) {
                chargeDays++;
            }
        }

        return chargeDays;
    }

    private static boolean isChargeableDay(LocalDate date, boolean chargeWeekday, boolean chargeWeekend, boolean chargeHoliday) {
        if (isWeekend(date) && !chargeWeekend) {
            return false;
        }
        if (isHoliday(date) && !chargeHoliday) {
            return false;
        }
        if (!isWeekend(date) && !isHoliday(date) && !chargeWeekday) {
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
        if (date.getMonth() == Month.JULY && date.getDayOfMonth() == 4) {
            return true;
        }
        // If July 4th is on a Saturday, Friday the 3rd is observed. If on Sunday, Monday the 5th is observed.
        if (date.getMonth() == Month.JULY && date.getDayOfMonth() == 3 && date.getDayOfWeek() == DayOfWeek.FRIDAY) {
            return true;
        }
        if (date.getMonth() == Month.JULY && date.getDayOfMonth() == 5 && date.getDayOfWeek() == DayOfWeek.MONDAY) {
            return true;
        }
        return false;
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