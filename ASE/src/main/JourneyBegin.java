package main;

/**
 * Main mehod to run an application.
 * @author Wanchana
 *
 */
public class JourneyBegin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JourneyCollection collection = new JourneyCollection();
		collection.loadData();
		collection.rankingReport();
		collection.driverReport();
		collection.visitedReport();
	}

}
