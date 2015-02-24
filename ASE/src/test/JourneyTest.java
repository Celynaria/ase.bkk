package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utilities.FeeCalculationException;
import entities.Destination;
import entities.Journey;

/**
 * Simple unit test for checking Journey.feeCalculation() method inside Journey class.
 * This test will expected return value from Journey.feeCalculation() method to be the same as a prepareData() method
 * inside this test case.
 * @author Wanchana, Thanaphong
 *
 */
public class JourneyTest {
	private List<double[]> inputList = new ArrayList<double[]>();
	@Before
	public void prepareData(){
		double[] a = {5,70.7};
		double[] b = {4,70.7};
		double[] c = {3,65.7};
		this.inputList.add(a);
		this.inputList.add(b);
		this.inputList.add(c);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testFeeCalculation() {
		Journey journey = new Journey();
		Destination des = new Destination();
		des.setDistance(65.7);
		journey.setDestination(des);
		Iterator<double[]> i = this.inputList.iterator();
		while(i.hasNext()){
			double[] input = i.next();
			journey.setNumberOfPassenger(Integer.parseInt(Double.toString(input[0])));
			try {
				Assert.assertEquals(input[1], journey.feeCalculation());
			} catch (FeeCalculationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
