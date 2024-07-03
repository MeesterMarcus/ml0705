package tool_rental;

import java.time.LocalDate;

import tool_rental.constants.AppConstants;
import tool_rental.service.Checkout;


public class App 
{
    public static void main( String[] args )
    {
        Checkout checkout = new Checkout(AppConstants.LADDER_CODE, 35, 25, LocalDate.now());
        System.out.println(checkout.getTotal());
    }
}
