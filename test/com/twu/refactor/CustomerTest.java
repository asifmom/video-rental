package com.twu.refactor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import junit.framework.TestCase;

public class CustomerTest extends TestCase {

	private static final String TEST_DATA_ROOT = "test/data";

    private Customer dinsdaleCustomer = new Customer("Dinsdale Pirhana");

    private Movie pythonMovie = new Movie("Monty Python and the Holy Grail", Movie.REGULAR);
	private Movie ranMovie = new Movie("Ran", Movie.REGULAR);
	private Movie laMovie = new Movie("LA Confidential", Movie.NEW_RELEASE);
	private Movie trekMovie = new Movie("Star Trek 13.2", Movie.NEW_RELEASE);
	private Movie wallaceMovie = new Movie("Wallace and Gromit", Movie.CHILDRENS);

    public void setUp (){
       dinsdaleCustomer.addRental(new Rental(pythonMovie, 3));
       dinsdaleCustomer.addRental(new Rental(ranMovie, 1));
       dinsdaleCustomer.addRental(new Rental(laMovie, 2));
       dinsdaleCustomer.addRental(new Rental(trekMovie, 1));
       dinsdaleCustomer.addRental(new Rental(wallaceMovie, 6));
   }

    public void testStatementWithoutAnyRentals() throws Exception {
    	Customer customerWithoutAnyRental = new Customer("Dinsdale Pirhana");
        equalsFile("outputEmpty", customerWithoutAnyRental.statement());
    }
    public void testStatementWithRentals() throws Exception {
        equalsFile("output1", dinsdaleCustomer.statement());
    }

    public void testStatementShouldReflectChangeInMoviePriceCode() throws Exception {
    	laMovie.setPriceCode(Movie.REGULAR);
        equalsFile("outputChange", dinsdaleCustomer.statement());
    }

    /*
    public void testHtml() throws Exception {
        equalsFile("1st Output", "outputHtml", dinsdaleCustomer.htmlStatement());
    }
    */
    	
    protected void equalsFile(String fileName, String actualValue) throws IOException{
        BufferedReader file = new BufferedReader (new FileReader (TEST_DATA_ROOT + '/' + fileName));
        BufferedReader actualStream = new BufferedReader (new StringReader (actualValue));
        String thisFileLine;
        while  ((thisFileLine = file.readLine()) != null) {
            assertEquals ("in file: " + fileName, thisFileLine, actualStream.readLine());
        }
    }

}
