package tool_rental;

import java.time.LocalDate;
import tool_rental.service.Checkout;


public class App 
{
    public static void main( String[] args )
    {
        String code = "CHNS";
        Checkout checkout = new Checkout(code, 10, 25, LocalDate.now());
        System.out.println(checkout.getTotal());
    }
}
