package com.refactor;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

public class Customer {

	private String name;
	private ArrayList<Rental> rentalList = new ArrayList<Rental>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental arg) {
		rentalList.add(arg);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		Iterator<Rental> rentals = rentalList.iterator();
		String result = "Rental Record for " + getName() + "\n";
		while (rentals.hasNext()) {
			Rental each = rentals.next();

			// show figures for this rental
			result += "\t" + each.getMovie().getTitle() + "\t"
					+ String.valueOf(each.getCharge()) + "\n";

		}
		// add footer lines
		result += "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
		result += "You earned " + String.valueOf(getTotalFrequentPoint())
				+ " frequent renter points";
		return result;
	}


    public String htmlStatement(){
        Iterator<Rental> rentals = rentalList.iterator();
        String result = "<H1>Rentals for <EM>" + getName() + "</EM></H1><P>";
        while (rentals.hasNext()) {
            Rental each = rentals.next(); //show figures for each rental
            result += each.getMovie().getTitle()+ ": " +
                    String.valueOf(each.getCharge()) + "<BR>";
        }
        result += "<P>You owe <EM>" + String.valueOf(getTotalCharge()) +
                "</EM><P>" ;
        result += "On this rental you earned <EM>" +
                String.valueOf(getTotalFrequentPoint())+"</EM> frequent renter points<P>";
        return result;
	}

	private double getTotalCharge(){
        double totalAmount = 0;
        Iterator<Rental> rentals = rentalList.iterator();
        while (rentals.hasNext()) {
            Rental each = rentals.next();
            totalAmount += each.getCharge();
        }
        return totalAmount;

    }

    private int getTotalFrequentPoint(){
        int frequentRenterPoints = 0;
        Iterator<Rental> rentals = rentalList.iterator();
        while (rentals.hasNext()) {
            Rental each = rentals.next();
            frequentRenterPoints += each.getFrequentRenterPoints();

        }
        return frequentRenterPoints;

    }

}
