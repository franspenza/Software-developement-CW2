/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.util.Date;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 *
 * @author Christian
 */
public class ScheduleEvent extends JFrame{
                
    //declaration of textboxes making use of JTextField
    private JTextField txtTitle, txtStartDate, txtStartTime, txtEndDate, txtEndTime, txtHour, txtMinute, txtLocation, txtDetails;
    //declaration of comboboxes making use of JComboBox
    private JComboBox cmbInterval, cmbReminderHour, cmbReminderOnScreen;
    //declaration of JLabels
    private JLabel lblTitle, lblStartDate, lblStartTime, lblEndDate, lblDuration, lblHour, lblMinute, lblRecurring, lblInterval, lblLocation, lblReminder, lblDetails;
    //declaration of JButtons
    private JButton btnSave, btnEdit, btnClear, btnClose;   
    //declaration of JCheckBoxes
    private JCheckBox chbRecurring;
    
    //default constructor
    public ScheduleEvent(){   
    	
    	buildScheduleEventGUI();
    }
    
    public void buildScheduleEventGUI(){
    	setTitle("Schedule Event"); //set title of form
        setSize(400, 500); //set the size of the form
        setLocationRelativeTo(null); //the form schedule event would be loaded exactly where I am working
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); //this property disposes any form details from memory, when the form is 
        
        //Create new grid layout having 10 rows and 2 columns
        JPanel scheduleEvent = new JPanel(new GridLayout(10,2));
        //creating new instances of the private attributes in the grid layout
        scheduleEvent.add(new JLabel("Schedule Events"));
        scheduleEvent.add(new JLabel(" "));
        scheduleEvent.add(new JLabel(" "));
        
        scheduleEvent.add(lblTitle = new JLabel("Title: "));
        scheduleEvent.add(txtTitle = new JTextField(5));
        scheduleEvent.add(new JLabel(" "));
        
        scheduleEvent.add(lblTitle = new JLabel("Title: "));
        scheduleEvent.add(txtTitle = new JTextField(5));
        scheduleEvent.add(new JLabel(" "));

        scheduleEvent.add(lblStartDate = new JLabel("Start Date: "));
        DateFormat startFormatDate = new SimpleDateFormat("dd/MM/yyyy"); //creating the mask format for date
        DateFormatter startDateFormatter = new DateFormatter(startFormatDate);
        scheduleEvent.add(txtStartDate = new JFormattedTextField(startDateFormatter));
        scheduleEvent.add(new JLabel(" "));

        scheduleEvent.add(lblStartTime = new JLabel("Start Time: "));
        DateFormat startFormatTime = new SimpleDateFormat("hh:mm"); //creating the mask format for time
        DateFormatter startTimeFormatter = new DateFormatter(startFormatTime);
        scheduleEvent.add(txtStartTime = new JFormattedTextField(startTimeFormatter));
        scheduleEvent.add(new JLabel(" "));

        scheduleEvent.add(lblEndDate = new JLabel("End Time: "));
        DateFormat endFormatTime = new SimpleDateFormat("hh:mm"); //creating the mask format for time
        DateFormatter endTimeFormatter = new DateFormatter(endFormatTime);
        scheduleEvent.add(txtEndDate = new JFormattedTextField(endTimeFormatter));
        scheduleEvent.add(new JLabel(" "));

        scheduleEvent.add(lblDetails = new JLabel("Details: "));        
        scheduleEvent.add(txtDetails = new JTextField(20));
        scheduleEvent.add(new JLabel(" "));      
        
        scheduleEvent.add(chbRecurring = new JCheckBox("Recurring?"));
        chbRecurring.setMnemonic(KeyEvent.VK_C);
        chbRecurring.setSelected(true);       

                
      //declaration of another panel for the event buttons
        JPanel scheduleEventControlPanel = new JPanel(new FlowLayout());
        scheduleEventControlPanel.add(btnSave = new JButton("Save"));
        scheduleEventControlPanel.add(btnEdit = new JButton("Edit"));
        scheduleEventControlPanel.add(btnClear = new JButton("Clear"));   
        scheduleEventControlPanel.add(btnClose = new JButton("Close"));

        //declaring a new grid layout to insert the other panels inside it
        JPanel panel = new JPanel(new GridLayout(2,1));
        panel.add(scheduleEvent, BorderLayout.CENTER);
        panel.add(scheduleEventControlPanel, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);
    }
    
    //method to change radio button state
    public void itemStateChanged(ItemEvent e) {
    	Object source = e.getItemSelectable();
    	if (source == chbRecurring) {
    		//...make a note of it			
		}
    	
    	if(e.getStateChange() == ItemEvent.DESELECTED) {
    		//...make a note if it
    	}
    }
}
