import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//class to read, write and extract events/dates using a text file "events.txt"
public class EventDateModel {
	//declaring an ArrayList of dates from events
	ArrayList<EventDate> dates;
	ChangeListener listener;

	public EventDateModel(){
		dates = new ArrayList<>();
	}
	
	//adding dates to the ArrayList dates
	public void add(EventDate aDate){
		dates.add(aDate);
	}
	
	//method to search and dates from the ArrayList dates to String
	public String search(LocalDate date){
		for(int i = 0; i < dates.size(); i++){
			if(dates.get(i).getDate().equals(date))
				return dates.get(i).toString();			
		}
		return "";
	}
	
	//method to get events from ArrayList dates
	public String[] getEventList(LocalDate date){
		EventDate dateNeeded = null;
		for(int i = 0; i < dates.size(); i++){
			if(dates.get(i).getDate().equals(date)){
				dateNeeded = dates.get(i);			
				break;
			}
		}
		//return events list
		return dateNeeded.getEventList();
	}
	
	//add event method according to the date
	public boolean addEventToDate(LocalDate adate, EventSchedule event){
		int i = 0;
		boolean found = false;
		boolean addResult = false;
		EventDate date =  null;
		//checking if event already exists
		while(i < dates.size() && !found){
			if(dates.get(i).date.equals(adate)){
				date = dates.get(i);
				found = true;
			}
			i++;
		}
		
		if(found)
			//calling add method from EventDate
			addResult = date.add(event);
		if(!found){
			//add a new date to ArrayList <EventDate>
			date = new EventDate(adate);
			//calling add method from EventDate class
			addResult =date.add(event);
			dates.add(date);
		}
		//return addResult which is an event from EventDate	
		return addResult;
	}
	
	//remove event method according to the date
	public void deleteEventFromDate(LocalDate adate, EventSchedule event){
		int i = 0;
		boolean found = false;
		while(i < dates.size() && !found){
			//if date is found, the remove method from EventDate will be called and the event removed
			if(dates.get(i).date.equals(adate)){
				dates.get(i).remove(event);
				found = true;
			}
			i++;
		}
	}
	
	//method to display dates from ArrayList <EventDate> dates
	public String toString(){
		String line = "";
		for(int i = 0; i < dates.size(); i++){
			line+=dates.get(i).getDate()+"\n"+dates.get(i).toString()+"\n";
		}
		return line;
	}
	
	public void attach(ChangeListener listener){
		this.listener = listener;
	}
	
	public void update(){
		listener.stateChanged(new ChangeEvent(this));
	}
	
	//method to write event in a text file with a try-catch block IOexception error handler
	public void write(String filename){
		try{
			File file = new File(filename);
			FileWriter writer = new FileWriter(file.getAbsoluteFile());
			BufferedWriter buffer = new BufferedWriter(writer);
			for(int i = 0; i < dates.size(); i++){
				buffer.write(dates.get(i).getDate().toString());
				buffer.write("\n");
				buffer.write(dates.get(i).toString());
			}
			buffer.close();
		}catch(IOException ex){
			System.out.println("Error writing to file");
		}
	}
	
	//method to read event from a text file with a try-catch block IOException error handler
	public void read(String filename){
		try{
			File file = new File(filename);
			if(file.exists()){
				Scanner in = new Scanner(file);
				String line = "";
				String previousLine = "";
				EventDate eventDate = null;
				LocalDate date = null;
				EventSchedule.Time start = null;
				EventSchedule.Time end = null;
				
				boolean newDate = true;
				
				while(in.hasNextLine()){
					if(previousLine.equals("")){// get day
						line = in.nextLine();
						String[] l = line.split("-");
						date = LocalDate.of(Integer.parseInt(l[0]), 
								Integer.parseInt(l[1]),Integer.parseInt(l[2]));
					}
					if(in.hasNextLine()){
						previousLine = line;
						line = in.nextLine();
						if(!line.equals("")){ //get time
							String[] t = line.split("-");
							start = extractTime(t[0].trim());
							end = extractTime(t[1].trim());
						}else previousLine = line;
					}
					if(in.hasNextLine() && !previousLine.equals("")){
						previousLine = line;
						line = in.nextLine();
						if(!line.equals("")){
							this.addEventToDate(date, new EventSchedule(line, start,end));
						}
					}
				}	
			}
			else{
			System.out.println("This is the first run");
			}
		}catch(IOException ex){
			System.out.println("Error opening file");
		}
	}
	
	public EventSchedule.Time extractTime(String line){
		int colon = line.indexOf(":");
		int hour = Integer.parseInt(line.substring(0, colon));
		int min = Integer.parseInt(line.substring(colon+1,colon+3));
		boolean am = line.substring(colon+3).equalsIgnoreCase("am");
		
		return new EventSchedule.Time(hour,min,am);
	}
	
}
