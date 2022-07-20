import java.util.Date;
import java.time.LocalTime;

public class Event {

	private int id;
	private String title;
	private String description;
	private String type;
	private Date date;
	private LocalTime time;
	private String location;
	
	public Event() {
		
	}
	
	public Event(int eventId, String eventTitle, String eventDescription, String eventType, Date eventDate, LocalTime eventTime, String eventLocation) {
		id = eventId;
		title = eventTitle;
		description = eventDescription;
		type = eventType;
		date = eventDate;
		time = eventTime;
		location = eventLocation;
	}
	
	// ==== Getters & Setters ====
	
	// ID
	public int getId() {
		return id;
	}

	public void setId(int eventId) {
		this.id = eventId;
	}
	
	// Title
	public String getTitle() {
		return title;
	}

	public void setTitle(String eventTitle) {
		this.title = eventTitle;
	}
	
	// Description
	public String getDescription() {
		return description;
	}

	public void setDescription(String eventDescription) {
		this.description = eventDescription;
	}
	
	// Type
	public String getType() {
		return type;
	}

	public void setType(String eventType) {
		this.type = eventType;
	}
	
	// Date
	public Date getDate() {
		return date;
	}

	public void setDate(Date eventDate) {
		this.date = eventDate;
	}
	
	// Time
	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime eventTime) {
		this.time = eventTime;
	}
	
	// Location
	public String getLocation() {
		return location;
	}

	public void setLocation(String eventLocation) {
		this.location = eventLocation;
	}
	
}
