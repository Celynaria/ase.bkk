package entities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import utilities.DestinationNotFoundException;
import utilities.InvalidRegistrationFormatException;
import utilities.RegistrationIDNotFoundException;
import utilities.FeeCalculationException;
/**
 * Journey class that will takes taxi object, destination object, date and number of passenger.
 * This is Main class of Journey contained read() method to read a text file from drive C:
 * and feeCalculation() method to calculate the fee from number of passenger and distance.
 * @author Wanchana, Thanaphong
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
	 * @throws ParseException 
	 */
	public Journey(Taxi taxi, Destination destination, String date,int numberOfPassenger) throws ParseException {
		DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
		Date journeyDate;
		try {
			journeyDate = format.parse(date);
			this.taxi = taxi;
			this.destination = destination;
			this.date = journeyDate;
			this.numberOfPassenger = numberOfPassenger;
		} catch (ParseException e) {
			throw new ParseException("Date format must be dd/mm/yyyy", e.getErrorOffset());
		}
	}
	public Journey(){}
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
	 * @throws FeeCalculationException 
	 */
	public double feeCalculation() throws FeeCalculationException{
		
		double init = 5.0;
		double feePerKilo = 0.5;
		
		if(this.getNumberOfPassenger()<=0 || this.getNumberOfPassenger()>5){
			throw new FeeCalculationException("The number of passenger must be > 0 or <= 5.");
		}
		else if(this.getNumberOfPassenger()>= 4){
			feePerKilo+=0.5;
		}
		return init+(this.destination.getDistance()*feePerKilo);
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
		String fileName = "C:\\journey.txt";
		String line = new String();
		String registrationID = new String();
		String destinationName = new String();
		List<Journey> journeys = new ArrayList<Journey>();
		int failed = 0;
		try{
			LineNumberReader reader = new LineNumberReader(new FileReader(fileName));
			while((line = reader.readLine())!=null){
				line = line.trim();
				String[] columns = line.split(",");
				Journey journey;
				try {
					registrationID = columns[0].trim();
					destinationName = columns[1].trim();
					Taxi.registrationIDChecker(registrationID);
					Taxi taxi = taxies.get(registrationID);
					Destination destination = destinations.get(destinationName);
					if(taxi != null){
						if(destination!=null){
							journey = new Journey(taxi,destination ,columns[3], new Integer(columns[2]).intValue());
							journey.setFee(journey.feeCalculation());
							journeys.add(journey);
						}else{
							throw new DestinationNotFoundException(destinationName);
						}
					}else{
						throw new RegistrationIDNotFoundException(registrationID);
					}
				} catch (ParseException e) {
					failed = failed + 1;
					System.err.println("Line "+reader.getLineNumber()+" is skipped:[Reason]"+e.getMessage());
				} catch (InvalidRegistrationFormatException e) {
					failed = failed + 1;
					System.err.println("Line "+reader.getLineNumber()+" is skipped:[Reason]"+e.getMessage());
				} catch (RegistrationIDNotFoundException e){
					failed = failed + 1;
					System.err.println("Line "+reader.getLineNumber()+" is skipped:[Reason]"+e.getMessage());
				} catch (FeeCalculationException e){
					failed = failed + 1;
					System.err.println("Line "+reader.getLineNumber()+" is skipped:[Reason]"+e.getMessage());
				} catch (DestinationNotFoundException e) {
					failed = failed + 1;
					System.err.println("Line "+reader.getLineNumber()+" is skipped:[Reason]"+e.getMessage());
				}
			}
			System.out.println(failed+" Journeys are skipped due to the ploblems with raw data.");
			System.out.println(journeys.size()+" Journeys are succesfully loaded.");
			reader.close();
		} catch (FileNotFoundException e){
			System.err.println("Can not find "+fileName+" file.");
		} catch (IOException e){
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
