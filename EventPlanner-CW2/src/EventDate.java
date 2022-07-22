import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

//class to add, get and check events
public class EventDate {
	//declaring local variables
	LocalDate date;
	//declaring an ArrayList <EventSchedule>
	ArrayList<EventSchedule> events;
	
	//constructor
	public EventDate(LocalDate date){
		this.date = date;
		events = new ArrayList<>();
	}
	
	//constructor to return the number of events
	public int getEventCount(){
		return events.size();
	}
	
	//method to add an event into the ArrayList
	public boolean add(EventSchedule anEvent){
		//will check if there is another event conflicting with startTime and endTime
		boolean conflict = false;
		int i = 0;
		if(anEvent.startTime().compareTo(anEvent.endTime()) > 0)
			return false;

		while(i < events.size() && !conflict){
			int start12 = anEvent.startTime().compareTo(events.get(i).startTime());
			int end1start2 = anEvent.endTime().compareTo(events.get(i).startTime());
			int start1end2 = anEvent.startTime().compareTo(events.get(i).endTime());
			if(start12 <= 0 && end1start2 >= 0 )
				conflict = true;
			else if(start12 >= 0 && start1end2 <= 0)
				conflict = true;
			else 
				conflict = false;
			i++; 
		}
		//if there is no conflict when using the compareTo method, the event will be added
		if(!conflict)
			events.add(anEvent);
		return !conflict;
	}
	
	//method to remove an event from the ArrayList
	public void remove(EventSchedule anEvent){
		events.remove(anEvent);
	}
	
	//method to get a list of events
	public String[] getEventList(){
		String[] list = new String[events.size()];
		for(int i = 0; i < list.length; i++){
			list[i] = events.get(i).toString();
		}
		return list;
	}
	
	//method to sort events according to startTime
	public String toString(){
		String line = "";
		
		events.sort((e1, e2)->{
			return e1.endTime().compareTo(e2.startTime());
		});
		
		for(int i = 0; i < events.size(); i++){
			line+= events.get(i).toString()+"\n";
		}
		
		return line+"\n";
	}
	
	public LocalDate getDate(){
		return date;
	}
}
