import javax.swing.JFrame;

public class main {

	public static void main(String[] args) {
		
//		ScheduleEvent scheduleEvent = new ScheduleEvent();
//		scheduleEvent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		scheduleEvent.setVisible(true);
		
//		Register register = new Register();
//		register.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		register.setVisible(true);
		
//		Login login = new Login();
//		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		login.setVisible(true);	
		
		CalendarModel cal = new CalendarModel();
		EventDateModel events = new EventDateModel();
		events.read("events.txt");
		
		MainCalendarScene frame = new MainCalendarScene(cal,events);
		
		events.attach(frame);
		String[] row = cal.getRowData();
		
	}

}
