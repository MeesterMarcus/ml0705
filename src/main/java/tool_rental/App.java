package tool_rental;

import java.time.LocalDate;
import tool_rental.service.Checkout;


public class App 
{
    public static void main( String[] args )
    {
        // Possible Tool codes:
        // CHNS, LADW, JAKD, JAKR
        String code = "LADW";
        Checkout checkout = new Checkout(code, 35, 25, LocalDate.now());
        System.out.println(checkout.getTotal());
    }
}
