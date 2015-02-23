package entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author Wonchana
 *
 */
public class Taxi {

	private String registrationID;
	private String firstName;
	private String lastName;
	private int maximumPassenger;
	
	/**
	 * Constructor that will takes three arguments
	 * that will perform the test for registration ID format,
	 * firstname and lastname cannot be null with custom exception
	 * called InvalidTaxiFormatException.
	 * @throws InvalidTaxiFormatException
	 * @param registrationID
	 * @param firstName
	 * @param lastName
	 */
	public Taxi(String registrationID, String firstName, String lastName) {
		this.registrationID = registrationID;
		this.maximumPassenger = 5;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public String getRegistrationID() {
		return registrationID;
	}
	public void setRegistrationID(String registrationID) {
		this.registrationID = registrationID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getMaximumPassenger() {
		return maximumPassenger;
	}
	public void setMaximumPassenger(int maximumPassenger) {
		this.maximumPassenger = maximumPassenger;
	}
	
	/**
	 * This method will read the taxi text file 
	 * and separate each column using comma. 
	 * The first column(registration number) as key
	 * and Taxi class as value.
	 * @return taxis
	 */
	public static Map<String, Taxi> read(){
		String line = new String();
		Map<String, Taxi> taxis = new HashMap<String, Taxi>();
		try (BufferedReader reader = new BufferedReader(new FileReader("C:\\taxi.txt"));)
		{	
			while((line = reader.readLine())!=null){
				line = line.trim();
				String[] columns = line.split(",");
				Taxi taxi = new Taxi(columns[0],columns[1],columns[2]);
				taxis.put(columns[0], taxi);
			}
		} catch (IOException e){
			e.printStackTrace();
		}
		return taxis;
	}
	
	/**
	 * Method for checking the registration format.
	 * @param registrationID
	 * @return
	 */
	public static boolean registrationIDChecker(String registrationID){
		return false;
	}
}
