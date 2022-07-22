import java.awt.BorderLayout;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

//Add event GUI
public class UserDialog extends JDialog {
	
	//declaring label, textfields, EventDateModel and LocalDate 
	private JLabel dayLabel = new JLabel("Day field here");
	private JTextField startField = new JTextField("00:00am");
	private JTextField endField = new JTextField("00:00am");
	private JTextField eventField = new JTextField("Untitled event");
	private EventDateModel model;
	private LocalDate date;
	
	//constructor referring to parent class constructor JDialog  
	public UserDialog(JFrame owner){
		super(owner, "Create Event", true);		
		//creating new BorderLayout
		this.add(eventField, BorderLayout.NORTH);
		//creating new JPanel to add label, and text fields
		JPanel panel = new JPanel();
		panel.add(dayLabel);
		panel.add(startField);
		panel.add(endField);
		
		//creating save button and calling the ActionListener Java library class
		JButton save = new JButton("Save");
		save.addActionListener(event->{
			//temporary store user input start time and end time, to EventSchedule.Time start and end
			EventSchedule.Time start = extractTime(startField.getText());
			EventSchedule.Time end = extractTime(endField.getText());
			System.out.println(start);
	    	System.out.println(end);
	    	//temporary store all user inputs as a new event calling the constructor of EventSchedule
			EventSchedule sampleEvent = new EventSchedule(eventField.getText(), start,end);
			
			//a check to determine if the event will clash with other already stored events
			//calling addEventToDate method
			boolean addResult = model.addEventToDate(date, sampleEvent);
			//if there is no clash, the listener will update the events for that current day
			if(addResult){
				setVisible(false);
				//calling the update method from EventDateModel class
				model.update();
			}
			else{
				//a conflict message will display
				JOptionPane.showMessageDialog(this, "The time for this event conflicts with an "
						+ "existing event");
				setVisible(true);
			}
		});
		
		panel.add(save);
		this.add(panel, BorderLayout.SOUTH);
		this.pack();
		
	}
	
	//method to display today's date in label dayLabel
	public void attachModel(EventDateModel model, LocalDate date){
		this.model = model;
		this.date = date;
		dayLabel.setText(date.toString());
	}
	
	//Extracting user input method
	public EventSchedule.Time extractTime(String line){
		int colon = line.indexOf(":");
		int hour = Integer.parseInt(line.substring(0, colon));
		int min = Integer.parseInt(line.substring(colon+1,colon+3));
		boolean am = line.substring(colon+3).equalsIgnoreCase("am");
		
		return new EventSchedule.Time(hour,min,am);
	}
}
