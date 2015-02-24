package entities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class destination that takes destination name
 * and distance in km.
 * @author Wanchana, Thanaphong
 * 
 */
public class Destination {

	private String name;
	private double distance;

	/**
	 * Simple constructor that two parameters.
	 * @param name
	 * @param distance
	 */
	public Destination(String name, double distance) {
		this.name = name;
		this.distance = distance;
	}
	public Destination(){}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	/**
	 * This method will read the destination text file 
	 * and separate each column using comma. 
	 * The first column(destination name) as key and 
	 * second column(distance) as value of HashMap data structure.
	 * 
	 * @return destinations 
	 */
	public static Map<String, Destination> read(){
		String fileName = ".\\destination.txt";
		String line = new String();
		Map<String, Destination> destinations = new HashMap<String, Destination>();
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName));)
		{	
			while((line = reader.readLine())!=null){
				line = line.trim();
				String[] columns = line.split(",");
				Double distance = new Double(columns[1]);
				destinations.put(columns[0], new Destination(columns[0], distance));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("Can not find "+fileName+" file.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return destinations;
	}
}
