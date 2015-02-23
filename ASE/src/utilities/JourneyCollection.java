package utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import entities.Destination;
import entities.Journey;
import entities.Taxi;
/**
 * 
 * @author Wonchana
 *
 */
public class JourneyCollection {

	private List<Journey> journeys = new ArrayList<Journey>();
	public JourneyCollection(){}
	
	/**
	 * Load Destination object, Taxi object
	 * and Journey object and store them in arrayList. 
	 */
	public void loadData(){
		Map<String, Destination> destinations = Destination.read();
		Map<String, Taxi> taxis = Taxi.read();
		this.journeys = Journey.read(destinations,taxis);
	}
	/**
	 * Producing the ranking report.
	 * This report will be ranked the top 5 highest fee
	 * and top 5 lowest fee.
	 * @return valid
	 */
	public boolean rankingReport(){
		boolean valid = true;
		int top = 5;
		String header = "Charges for The Top 5 Journeys";
		String header2 = "Charges for The Cheapest 5 Journeys";
		try{
			Collections.sort(this.journeys);
			File file = new File("C:\\ranking.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter out = new FileWriter(file.getAbsoluteFile());
			BufferedWriter writer = new BufferedWriter(out);
			Iterator<Journey> i = this.journeys.iterator();
			int count = 1;
			writer.write(header);
			while(i.hasNext()&& count <=top){
				writer.write(i.next().toString());
				writer.newLine();
				count++;
			}
			Collections.sort(this.journeys,Collections.reverseOrder());
			i = this.journeys.iterator();
			count = 1;
			writer.write(header2);
			while(i.hasNext()&& count <=top){
				writer.write(i.toString());
				writer.newLine();
				count++;
			}
			writer.close();
		}catch(IOException e){
			valid = false;
			e.printStackTrace();
		}
		return valid;
	}
	/**
	 * Producing the Driver report.
	 * This report will match all journeys 
	 * that associate to particular driver 
	 * which has been visited and order by ascending.
	 * @return valid
	 */
	public boolean driverReport(){
		boolean valid = true;
		String header = "Driver's routes";
		TreeMap<String, TreeSet<String>> reportData = new TreeMap<String, TreeSet<String>>();
		try{
			File file = new File("C:\\driver.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter out = new FileWriter(file.getAbsoluteFile());
			BufferedWriter writer = new BufferedWriter(out);
			Iterator<Journey> journeyIterator = this.journeys.iterator();
			writer.write(header);
			while(journeyIterator.hasNext()){
				Journey journey = journeyIterator.next();
				String key = journey.getTaxi().getLastName().trim()+", "+journey.getTaxi().getLastName();
				TreeSet<String> destinations = reportData.get(key);
				if(destinations == null){
					destinations = new TreeSet<String>();
					destinations.add(journey.getDestination().getName());
					reportData.put(key,destinations);
				}else{
					destinations.add(journey.getDestination().getName().trim());
				}
			}
			Set<String> keys =  reportData.keySet();
			Iterator<String> driverIterator = keys.iterator();
			while(driverIterator.hasNext()){
				String driverName = driverIterator.next();
				writer.write(driverName);
				writer.newLine();
				TreeSet<String> destinations = reportData.get(driverName);
				Iterator<String> destinationIterator = destinations.iterator();
				while(destinationIterator.hasNext()){
					String destinationName = destinationIterator.next();
					writer.write(String.format("%-20s%",destinationName));
					writer.newLine();
				}
			}
			writer.close();
		}catch(IOException e){
			valid = false;
			e.printStackTrace();
		}
		return valid;
	}
	
	/**
	 * Producing the Visited report.
	 * This report will consists of the new places that 
	 * visited in 2015, places that visited only in 2014
	 * and places that visited both in 2014 and 2015.
	 * @return valid
	 */
	public boolean visitedReport(){
		boolean valid = true;
		DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
		TreeSet<String> set2014 = new TreeSet<String>();
		TreeSet<String> set2015 = new TreeSet<String>();
		String header = " New Places in 2015";
		String header2 = " Places Only Visited in 2014";
		String header3 = "Places Visited in Both 2014 and 2015";
		try{
			Date year2015 = format.parse("01/01/2015");
			File file = new File("C:\\visited.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter out = new FileWriter(file.getAbsoluteFile());
			BufferedWriter writer = new BufferedWriter(out);
			Iterator<Journey> journeyIterator = this.journeys.iterator();
			while(journeyIterator.hasNext()){
				Journey journey = journeyIterator.next();
				if(journey.getDate().before(year2015)){
					set2014.add(journey.getDestination().getName());
				}else{
					set2015.add(journey.getDestination().getName());
				}
			}
			TreeSet<String> both20142015 = new TreeSet<String>();
			both20142015.addAll(set2014);
			both20142015.retainAll(set2015);
			set2015.removeAll(both20142015);
			set2014.removeAll(both20142015);
			
			header = set2015.size()+header;
			header2 = set2014.size()+header2;
			
			writer.write(header);
			writer.newLine();
			Iterator<String> iterator2015 = set2015.iterator();
			while(iterator2015.hasNext()){
				writer.write(iterator2015.next());
				writer.newLine();
			}
			
			writer.write(header2);
			writer.newLine();
			Iterator<String> iterator2014 = set2014.iterator();
			while(iterator2014.hasNext()){
				writer.write(iterator2014.next());
				writer.newLine();
			}
			
			writer.write(header3);
			writer.newLine();
			Iterator<String> iteratorBoth = both20142015.iterator();
			while(iteratorBoth.hasNext()){
				writer.write(iteratorBoth.next());
				writer.newLine();
			}
			writer.close();
		}catch (IOException | ParseException e) {
			valid = false;
			e.printStackTrace();
		}
		return valid;
	}
}
