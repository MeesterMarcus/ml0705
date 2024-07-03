package tool_rental.constants;

public final class AppConstants {

    // Private constructor to prevent instantiation
    private AppConstants() {
    }

    // Tool Codes
    public static final String CHAINSAW_CODE = "CHNS";
    public static final String LADDER_CODE = "LADW";
    public static final String JACKHAMMER_DEWALT_CODE = "JAKD";
    public static final String JACKHAMMER_RIDGID_CODE = "JAKR";

    // Brand Names
    public static final String STIHL_BRAND = "Stihl";
    public static final String WERNER_BRAND = "Werner";
    public static final String DEWALT_BRAND = "DeWalt";
    public static final String RIDGID_BRAND = "Ridgid";

    // Tool Types
    public static final String CHAINSAW_TYPE = "Chainsaw";
    public static final String LADDER_TYPE = "Ladder";
    public static final String JACKHAMMER_TYPE = "Jackhammer";

    // Messages
    public static final String INVALID_RENTAL_DAYS = "Number of days renting must be 1 or more days.";
    public static final String INVALID_DISCOUNT = "Discount percentage is not valid.";
    public static final String TOOL_NOT_FOUND = "No tool found with code: ";
}