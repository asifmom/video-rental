package com.refactor;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;


public class CustomerTest{

	private static final String TEST_DATA_PATH = "test/data";

    private Customer customerWithRental = new Customer("Dinsdale Pirhana");

    private Movie python = new Movie("Monty Python and the Holy Grail", Movie.REGULAR);
	private Movie ran = new Movie("Ran", Movie.REGULAR);
	private Movie la = new Movie("LA Confidential", Movie.NEW_RELEASE);
	private Movie trek = new Movie("Star Trek 13.2", Movie.NEW_RELEASE);
	private Movie wallace = new Movie("Wallace and Gromit", Movie.CHILDRENS);

    @Before
	public void setUp (){
       customerWithRental.addRental(new Rental (python, 3));
       customerWithRental.addRental(new Rental (ran, 1));
       customerWithRental.addRental(new Rental (la, 2));
       customerWithRental.addRental(new Rental (trek, 1));
       customerWithRental.addRental(new Rental (wallace, 6));
   }

   @Test
   public void shouldGenerateStatementForCustomerWithNoRentals() throws Exception {
    	Customer customerWithNoRental = new Customer("Dinsdale Pirhana");
        equalsFile("outputEmpty", customerWithNoRental.statement());
    }
    @Test
    public void shouldGenerateStatementForCustomerWithRentals() throws Exception {
        equalsFile("output1", customerWithRental.statement());
    }

    @Test
    public void shouldGenerateStatementForCustomerWithModifiedRentals() throws Exception {
    	la.setPriceCode(Movie.REGULAR);
        equalsFile("outputChange", customerWithRental.statement());
    }

    @Test
    public void testHtml() throws Exception {
        equalsFile("outputHtml", customerWithRental.htmlStatement());
    }

    protected void equalsFile(String fileName, String actualValue) throws IOException{
        BufferedReader file = new BufferedReader (new FileReader (TEST_DATA_PATH + '/' + fileName));
        BufferedReader actualStream = new BufferedReader (new StringReader (actualValue));
        String thisFileLine;
        while  ((thisFileLine = file.readLine()) != null) {
            assertEquals ("in file: " + fileName, thisFileLine, actualStream.readLine());
        }
    }

}
