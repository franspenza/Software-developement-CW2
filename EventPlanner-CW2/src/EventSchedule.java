import java.util.Arrays;
import java.util.Comparator;

/**
 * Represent an EventSchedule schedule on a date
 */
public class EventSchedule {
	//this class implement Comparable<Time> using the "compare to" method
	public static class Time implements Comparable<Time>{
		//declaring local variables
		private int hour;
		private int min;
		private boolean am;
		
		//Time constructor
		public Time(int hour, int min, boolean am){
			this.hour = hour;
			this.min = min;
			this.am = am;
		}		
		
		//another constructor that returns hour, minute, am/pm
		public Time getTime(){
			return new Time(hour,min,am);
		}

		//compareTo method will compare two strings to return a 0 if the string is equal
		//returning either am or pm
		@Override
		public int compareTo(Time o2) {
			if(this.am!=o2.am){
				if(this.am)
					return -1;
				else return 1;
			}
			else if(this.hour!=o2.hour) return this.hour - o2.hour;
			else return this.min - o2.min;
		}
		
		public boolean equals(Object otherObject){
			if(this == otherObject) return true;
			if(otherObject == null) return false;
			if(getClass() != otherObject.getClass()) return false;
			
			Time b = (Time) otherObject;
			return hour==b.hour && min==b.min && am==b.am;
		}
		
		public String toString(){
			String pad = "";
			if(min < 10)
				pad = "0";
			String line =  hour+":"+pad+min;
			if(am) line+="am";
			else line+="pm";
			return line;
		}
	}
	
	//declaring local variables
	private String title;
	private Time start;
	private Time end;
	
	//EventSchedule constructor
	public EventSchedule(String title, Time start, Time end){
		this.title = title;
		this.start = start;
		this.end = end;
	}
	
	//getters and setters
	public EventSchedule(){
		this.title = "";
	}
	
	public Time startTime(){
		return start;
	}
	
	public Time endTime(){
		return end;
	}
	
	public void setStartTime(int hour, int min, boolean am){
		start = new Time(hour, min, am);
	}
	
	public void setEndTime(int hour, int min, boolean am){
		end = new Time(hour, min, am);
	}
	
	public String toString(){
		return start.toString()+" - "+end.toString()+"\n"+title;
	}
	
	//boolean method to compare objects and return start time, end time and title of event
	public boolean equals(Object otherObject){
		if(this == otherObject) return true;
		if(otherObject == null) return false;
		if(getClass() != otherObject.getClass()) return false;
		
		EventSchedule anEventSchedule = (EventSchedule) otherObject;
		return end.equals(anEventSchedule.end) && start.equals(anEventSchedule.start) && title.equals(anEventSchedule.title);
	}
}
