package entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author Wonchana
 *
 */
public class Journey implements Comparable<Journey>{

	private Taxi taxi;
	private Destination destination;
	private double fee;
	private Date date;
	private int numberOfPassenger = 1;
	
	/**
	 * Constructor that takes Taxi object,
	 * Destination object, date and number of passenger.
	 * @throws NullPointerException
	 * @param taxi
	 * @param destination
	 * @param date
	 * @param numberOfPassenger
	 */
	public Journey(Taxi taxi, Destination destination, Date date,int numberOfPassenger) {
		this.taxi = taxi;
		this.destination = destination;
		this.date = date;
		this.numberOfPassenger = numberOfPassenger;
	}
	public Taxi getTaxi() {
		return taxi;
	}
	public void setTaxi(Taxi taxi) {
		this.taxi = taxi;
	}
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getNumberOfPassenger() {
		return numberOfPassenger;
	}
	public void setNumberOfPassenger(int numberOfPassenger) {
		this.numberOfPassenger = numberOfPassenger;
	}
	/**
	 * Calculate the cost of the journey with initial value of £5
	 * and increase every 5p per kilometre but if the passenger 
	 * is greater or equal than 4 people the increase rate will be
	 * £1 per kilometre.
	 */
	public void feeCalculation(){
		
		double init = 5.0;
		double feePerKilo = 0.5;
		if(this.getNumberOfPassenger()>= 4){
			feePerKilo+=0.5;
		}
		this.fee = init+(this.destination.getDistance()*feePerKilo);
	}
	
	/**
	 * This method will read journey text file
	 * and separate each column using comma.
	 * 
	 * @param destinations
	 * Specific Destination object will be retrieved from this HashMap by using 
	 * second column of journey record(Destination Name).
	 * @param taxies
	 * Specific Taxi object will be retrieved from this HashMap by using
	 * first column of journey record(registrationID).
	 * @return journeys
	 */
	public static List<Journey> read(Map<String, Destination> destinations, Map<String, Taxi> taxies) {
		String line = new String();
		DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
		String registrationID = new String();
		List<Journey> journeys = new ArrayList<Journey>();
		try (BufferedReader reader = new BufferedReader(new FileReader("C:\\journey.txt"));)
		{	
			while((line = reader.readLine())!=null){
				line = line.trim();
				String[] columns = line.split(",");
				registrationID = columns[0];
				Date journeyDate = format.parse(columns[3]);
				Journey journey = new Journey(taxies.get(registrationID), destinations.get(columns[1]),journeyDate, new Integer(columns[2]).intValue());
				journey.feeCalculation();
				journeys.add(journey);
			}
		} catch (IOException | NumberFormatException | ParseException e){
			e.printStackTrace();
		}
		return journeys;
	}
	
	/**
	 * Custom method that will compare the cost
	 * in order for sorting purpose.
	 */
	@Override
	public int compareTo(Journey o) {
		Double fee1 = new Double(this.fee);
		Double fee2 = new Double(o.fee);
		return fee1.compareTo(fee2); 
	}
	/**
	 * Override method for printing Ranking report.
	 */
	@Override
	public String toString(){
		return String.format("%-20s%-20s%-20s%-20s%-20s",this.taxi.getRegistrationID(),this.destination.getName(),this.destination.getDistance()+" km",this.numberOfPassenger+" people","Cost £"+this.fee);
	}
	
	
}
