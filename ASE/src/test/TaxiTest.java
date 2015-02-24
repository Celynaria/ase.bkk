package test;

import org.junit.Assert;
import org.junit.Test;

import entities.Taxi;
import utilities.InvalidRegistrationFormatException;

/**
 * Simple unit test for checking Taxi.registrationIDChecker() method inside Taxi class.
 * This test will expected a matched two custom exception messages .
 * @author Wanchana, Thanaphong
 *
 */
public class TaxiTest {
	@Test
	public void registrationIDFormatTest(String ID) {
		try{
			Taxi.registrationIDChecker(ID);
			Assert.fail("There are no exception");
		} catch(InvalidRegistrationFormatException e) {
			Assert.assertTrue(e.getMessage().equals("RegistrationID must start with \"BKK\" and follow by 2 capital chacraters and end with 3 digits number."));
		}
	}
	@Test
	public void registrationIDLengthTest(String ID) {
		try{
			Taxi.registrationIDChecker(ID);
			Assert.fail("There are no exception");
		} catch(InvalidRegistrationFormatException e) {
			Assert.assertTrue(e.getMessage().equals("The length of RegistrationID must be 8 digits"));
		}
	}

}
