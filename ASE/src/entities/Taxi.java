package entities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import utilities.InvalidRegistrationFormatException;
/**
 * Main calss of Taxi contained read() method to read a text file from drive C:
 * and registrationIDChecker() method to check registration ID format.
 * @author Wanchana, Thanaphong
 *
 */
public class Taxi {

	private String registrationID;
	private String firstName;
	private String lastName;
	public static final int maximumPassenger = 5;
	
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
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public Taxi(){}
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
	
	/**
	 * This method will read the taxi text file 
	 * and separate each column using comma. 
	 * The first column(registration number) as key
	 * and Taxi class as value.
	 * @return taxis
	 */
	public static Map<String, Taxi> read(){
		String fileName = "C:\\taxi.txt";
		String line = new String();
		Map<String, Taxi> taxis = new HashMap<String, Taxi>();
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName));)
		{	
			while((line = reader.readLine())!=null){
				line = line.trim();
				String[] columns = line.split(",");
				Taxi taxi = new Taxi(columns[0],columns[1],columns[2]);
				taxis.put(columns[0], taxi);
			}
		} catch (FileNotFoundException e){
			System.err.println("Can not find "+fileName+" file.");
		} catch (IOException e){
			e.printStackTrace();
		}
		return taxis;
	}
	
	/**
	 * Method for checking the registration format.
	 * @param registrationID
	 * @throws InvalidRegistrationFormatException 
	 */
	public static void registrationIDChecker(String registrationID) throws InvalidRegistrationFormatException{
		String reg = registrationID.trim();
		if(reg.length()!=8){
			throw new InvalidRegistrationFormatException("The length of RegistrationID must be 8 digits");
		}else if(!reg.matches("BKK[A-Z]{2}[0-9]{3}")){
			throw new InvalidRegistrationFormatException("RegistrationID must start with \"BKK\" and follow by 2 capital chacraters and end with 3 digits number.");
		}
	}
}
