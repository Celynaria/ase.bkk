package main;

import test.JourneyTest;
import test.TaxiTest;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		TaxiTest test = new TaxiTest();
//		test.registrationIDLengthTest("BKKAAA01");
		JourneyTest test = new JourneyTest();
		test.testFeeCalculation();
	}
}
