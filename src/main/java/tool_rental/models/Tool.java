package tool_rental.models;

import lombok.Data;

@Data
public class Tool {
    private final String code;
    private final ToolType type;
    private final String brand;

    public enum ToolType {
        LADDER("Ladder", 1.99, true, true, false),
        CHAINSAW("Chainsaw", 1.49, true, false, true),
        JACKHAMMER("Jackhammer", 2.99, true, false, false);

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

        public String getName() { return name; }
        public double getDailyCharge() { return dailyCharge; }
        public boolean isWeekdayCharge() { return weekdayCharge; }
        public boolean isWeekendCharge() { return weekendCharge; }
        public boolean isHolidayCharge() { return holidayCharge; }
    }
}