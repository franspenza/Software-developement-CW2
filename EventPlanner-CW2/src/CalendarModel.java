
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CalendarModel {
		
		//declaring variables and array
		private Calendar cal;
		private LocalDate now;
		private int monthsFromCurrent;
		private int daysFromCurrent;
		private String[] rowData;
		
		//constructor to build calendar 
		public CalendarModel(){
			//creating an instance of the Calendar
			cal = Calendar.getInstance();
			//returning current year, month and day using ISO8601 calendar system using the getRequestedDay method
			now = LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1 ,cal.get(Calendar.DAY_OF_MONTH));
			//building up rows for the calendar using setRowData()
			setRowData();
			monthsFromCurrent = 0;
			daysFromCurrent = 0;
		}
		
		//a return method to obtain year, month and day of month
		public LocalDate getRequestedDay(){
			int requestedYear = cal.get(Calendar.YEAR);
			int requestedMonth = cal.get(Calendar.MONTH);
			int requestedDay = cal.get(Calendar.DAY_OF_MONTH);
			return LocalDate.of(requestedYear, requestedMonth+1, requestedDay);
		}
		/**
		 * Displays the next month on the calendar
		 * @param eventMan An eventManager that manages events for this calendar
		 */
		public void nextMonth(){
			monthsFromCurrent = 1;
			this.setRowData();
		}
		
		/**
		 * Displays the current month on the calendar
		 * @param eventMan An eventManager that manages events for this calendar
		 */
		public void currentMonth(){
			monthsFromCurrent = 0;
			this.setRowData();
		}
		
		/**
		 * Displays the previous month on the calendar
		 * @param eventMan An eventManager that manages events for this calendar
		 */
		public void previousMonth(){
			monthsFromCurrent = -1;
			this.setRowData();
		}
		
		private void setRowData(){
			cal.add(Calendar.MONTH, monthsFromCurrent);
			
						
//			cal = new GregorianCalendar(Locale.forLanguageTag("en-US"));
			//set calendar to current time
			cal = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),1);	
//			cal.set(Calendar.DAY_OF_MONTH, 1);			
			
			//get other important dates
			int firstDayOfMonth = cal.get(Calendar.DAY_OF_WEEK);
			int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			int weeksInMonth = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
			
			//populating array to form the calendar
			int day = 1;
			rowData = new String[weeksInMonth*10];
			for(int i = 0; i < weeksInMonth*10; i++){
				if(i < firstDayOfMonth - 1){
					rowData[i]="  ";
				}
				else if(i > firstDayOfMonth-2 + daysInMonth){
					rowData[i]="  ";
				}else{
					rowData[i]=""+day;
					day++;
				}
			}
			
		}
		
		public LocalDate getDateNow(){
			return now;
		}
		
		//returns a copy of the rowData
		public String[] getRowData(){
			return rowData.clone();
		}
}

