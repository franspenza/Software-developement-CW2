import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;

public class Register extends JFrame {
	
	//declaration of textboxes making use of JTextField
    private JTextField txtUsername, txtPassword;
    //declaration of JLabels
    private JLabel lblUsername, lblPassword;
    //declaration of JButtons
    private JButton btnSave, btnClear, btnClose;   
    
    //default constructor
    public Register(){   
    	
    	buildRegisterGUI();
    }
    
    public void buildRegisterGUI(){
    	setTitle("Registration"); //set title of form
        setSize(400, 600); //set the size of the form
        setLocationRelativeTo(null); //the form registration would be loaded exactly where I am working
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); //this property disposes any form details from memory, when the form is 
        
        //Create new grid layout having 15 rows and 2 columns
        JPanel registrationForm = new JPanel(new GridLayout(15,2));
        //creating new instances of the private attributes in the grid layout
        registrationForm.add(new JLabel("Register for an account"));
        registrationForm.add(new JLabel(" "));
        registrationForm.add(new JLabel(" "));
        
        registrationForm.add(lblUsername = new JLabel("Create username: "));
        registrationForm.add(txtUsername = new JTextField(10));
        registrationForm.add(new JLabel(" "));    
        registrationForm.add(lblUsername = new JLabel("Create password: "));
        registrationForm.add(txtUsername = new JTextField(15));
        registrationForm.add(new JLabel(" ")); 
                
      //declaration of another panel for the event buttons
        JPanel registrationFormControlPanel = new JPanel(new FlowLayout());
        registrationFormControlPanel.add(btnSave = new JButton("Create"));        
        registrationFormControlPanel.add(btnClear = new JButton("Clear"));   
        registrationFormControlPanel.add(btnClose = new JButton("Close"));

        //declaring a new grid layout to insert the other panels inside it
        JPanel panel = new JPanel(new GridLayout(2,1));
        panel.add(registrationForm, BorderLayout.CENTER);              		
        panel.add(registrationFormControlPanel, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);
    }

}
