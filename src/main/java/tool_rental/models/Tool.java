package tool_rental.models;

import lombok.Data;
import tool_rental.constants.AppConstants;

@Data
public class Tool {
    private final String code;
    private final ToolType type;
    private final String brand;

    public enum ToolType {
        LADDER(AppConstants.LADDER_TYPE, 1.99, true, true, false),
        CHAINSAW(AppConstants.CHAINSAW_TYPE, 1.49, true, false, true),
        JACKHAMMER(AppConstants.JACKHAMMER_TYPE, 2.99, true, false, false);

        private final String name;
        private final double dailyCharge;
        private final boolean weekdayCharge;
        private final boolean weekendCharge;
        private final boolean holidayCharge;

        ToolType(String name, double dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
            this.name = name;
            this.dailyCharge = dailyCharge;
            this.weekdayCharge = weekdayCharge;
            this.weekendCharge = weekendCharge;
            this.holidayCharge = holidayCharge;
        }

        public String getName() {
            return name;
        }

        public double getDailyCharge() {
            return dailyCharge;
        }

        public boolean isWeekdayCharge() {
            return weekdayCharge;
        }

        public boolean isWeekendCharge() {
            return weekendCharge;
        }

        public boolean isHolidayCharge() {
            return holidayCharge;
        }
    }
}