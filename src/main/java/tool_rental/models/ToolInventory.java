package tool_rental.models;

public class ToolInventory {
    public static final Tool CHAINSAW = new Tool("CHNS", Tool.ToolType.CHAINSAW, "Stihl");
    public static final Tool LADDER = new Tool("LADW", Tool.ToolType.LADDER, "Werner");
    public static final Tool JACKHAMMER_DEWALT = new Tool("JAKD", Tool.ToolType.JACKHAMMER, "DeWalt");
    public static final Tool JACKHAMMER_RIDGID = new Tool("JAKR", Tool.ToolType.JACKHAMMER, "Ridgid");

    public static Tool getToolByCode(String code) {
        switch (code) {
            case "CHNS": return CHAINSAW;
            case "LADW": return LADDER;
            case "JAKD": return JACKHAMMER_DEWALT;
            case "JAKR": return JACKHAMMER_RIDGID;
            default: throw new IllegalArgumentException("Invalid tool code: " + code);
        }
    }
}