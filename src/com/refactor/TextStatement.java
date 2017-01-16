package com.refactor;

import java.util.Iterator;

abstract class Statement {

    public String value(Customer customer) {
        Iterator<Rental> rentals = customer.getRentals().iterator();
        String result = headerString(customer);
        while (rentals.hasNext()) {
            Rental each = rentals.next();

            // show figures for this rental
            result += bodyString(each);

        }
        // add footer lines
        result += footerString(customer);
        return result;
    }

    abstract String footerString(Customer customer);
    abstract String bodyString(Rental rental);
    abstract String headerString(Customer customer);
}

class TextStatement extends Statement{

    public String footerString(Customer customer) {
        String result = "Amount owed is " + String.valueOf(customer.getTotalCharge()) + "\n";
        result += "You earned " + String.valueOf(customer.getTotalFrequentPoint())
                + " frequent renter points";
        return result;
    }

    public String bodyString(Rental each) {
        return "\t" + each.getMovie().getTitle() + "\t"
                + String.valueOf(each.getCharge()) + "\n";
    }

    public String headerString(Customer customer) {
        return "Rental Record for " + customer.getName() + "\n";
    }
}

class HtmlStatement extends Statement{

    public String footerString(Customer customer) {
        String result = "<P>You owe <EM>" + String.valueOf(customer.getTotalCharge()) +
                "</EM><P>" ;
        result += "On this rental you earned <EM>" +
                String.valueOf(customer.getTotalFrequentPoint())+"</EM> frequent renter points<P>";
        return result;
    }

    public String bodyString(Rental each) {
        return each.getMovie().getTitle()+ ": " +
                String.valueOf(each.getCharge()) + "<BR>";
    }

    public String headerString(Customer customer) {
        return "<H1>Rentals for <EM>" + customer.getName() + "</EM></H1><P>";
    }
}
