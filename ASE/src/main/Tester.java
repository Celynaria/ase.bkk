package main;

import test.JourneyTest;
import test.TaxiTest;

/**
 * Simple main to run Junit test case.
 * Remove a comment if you want to test both cases.
 * @author Wanchana
 *
 */
public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		TaxiTest test = new TaxiTest();
//		test.registrationIDLengthTest("BKKAAA01");
		JourneyTest test = new JourneyTest();
		test.testFeeCalculation();
	}
}
