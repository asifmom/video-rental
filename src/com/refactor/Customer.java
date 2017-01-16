package com.refactor;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class Customer {

	private String name;
	private ArrayList<Rental> rentalList = new ArrayList<Rental>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental arg) {
		rentalList.add(arg);
	}
	public List<Rental> getRentals() {
		return rentalList;
	}

	public String getName() {
		return name;
	}

	public String statement() {
        return new TextStatement().value(this);
	}


    public String htmlStatement(){
	    return new HtmlStatement().value(this);
	}

	public double getTotalCharge(){
        double totalAmount = 0;
        Iterator<Rental> rentals = rentalList.iterator();
        while (rentals.hasNext()) {
            Rental each = rentals.next();
            totalAmount += each.getCharge();
        }
        return totalAmount;

    }

    public int getTotalFrequentPoint(){
        int frequentRenterPoints = 0;
        Iterator<Rental> rentals = rentalList.iterator();
        while (rentals.hasNext()) {
            Rental each = rentals.next();
            frequentRenterPoints += each.getFrequentRenterPoints();

        }
        return frequentRenterPoints;

    }

}
