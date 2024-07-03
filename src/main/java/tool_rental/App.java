package tool_rental;


import tool_rental.models.Tool;
import tool_rental.models.ToolInventory;


public class App 
{
    public static void main( String[] args )
    {
        Tool chainsaw = ToolInventory.getToolByCode("CHNS");
        System.out.println(chainsaw);
        System.out.println(chainsaw.getType().getDailyCharge());
    }
}
