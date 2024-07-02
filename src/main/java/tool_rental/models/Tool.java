package tool_rental.models;

import lombok.Getter;

public class Tool {
    @Getter private final String code;
    @Getter private final ToolType type;
    @Getter private final String brand;

    public Tool(String code, ToolType type, String brand) {
        this.code = code;
        this.type = type;
        this.brand = brand;
    }

    public enum ToolType {
        LADDER("Ladder", 1.99, true, true, false),
        CHAINSAW("Chainsaw", 1.49, true, false, true),
        JACKHAMMER("Jackhammer", 2.99, true, false, false);

        @Getter private final String name;
        @Getter private final double dailyCharge;
        @Getter private final boolean weekdayCharge;
        @Getter private final boolean weekendCharge;
        @Getter private final boolean holidayCharge;

        ToolType(String name, double dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
            this.name = name;
            this.dailyCharge = dailyCharge;
            this.weekdayCharge = weekdayCharge;
            this.weekendCharge = weekendCharge;
            this.holidayCharge = holidayCharge;
        }
    }
}