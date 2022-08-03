import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;
import javax.swing.JOptionPane;
import java.text.ParseException;



public class Register extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//declaration of textboxes making use of JTextField
    private JTextField txtEmail, txtName, txtSurname, txtAge;
    //declaration of textboxes making use of JPasswordField
    private JPasswordField txtPassword;
    private JCheckBox hideShowPw;  
    //declaration of comboboxes making use of JComboBox
    private JComboBox cmbGender, cmbTitle;
    //declaration of JFormattedTextField (mask Textbox)
    private JFormattedTextField txtDob;
    //declaration of JLabels
    private JLabel lblEmail, lblTitle, lblPassword, lblName, lblSurname, lblGender, lblAge, lblDob;
    //declaration of JButtons
    private JButton btnSave, btnClear, btnClose;   
    
    private final String[] GenderList = {"Male","Female","Other"};
    private final String[] titleList = {"Mr","Ms","Mss"};
    
    //create instance of application logic in the person user interface class (RegisterUI)
    ApplicationLogic applicationLogic = new ApplicationLogic();
    
    
    //default constructor
    public Register(){   
    	
    	buildRegisterGUI();
    	
    	btnSave.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent e){
                //clear data method
        		try {
					if (addUser() == true) {
						setVisible(false);
	            		dispose(); 	
						Login login = new Login();
						login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						login.setVisible(true);	
					}

				} catch (GeneralSecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
    	
    	btnClose.addActionListener(new ActionListener(){
            @Override
                public void actionPerformed(ActionEvent e){
        			setVisible(false);
        			dispose(); 	
					Login login = new Login();
					login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					login.setVisible(true);	
                }
        });
    	
    	btnClear.addActionListener(new ActionListener(){
            @Override
                public void actionPerformed(ActionEvent e){
            		clearData();
                }
        });
    	
    	hideShowPw.addActionListener(new ActionListener(){
            @Override
                public void actionPerformed(ActionEvent e){
            		itemStateChanged();
                }
        });
    }
    
    public void buildRegisterGUI(){
    	setTitle("Registration"); //set title of form
        setSize(400, 600); //set the size of the form
        setLocationRelativeTo(null); //the form registration would be loaded exactly where I am working
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); //this property disposes any form details from memory, when the form is 
        
        //Create new grid layout having 15 rows and 2 columns
        JPanel registrationForm = new JPanel(new GridLayout(10, 5));
        //creating new instances of the private attributes in the grid layout
        registrationForm.add(new JLabel(" Register for an account "));
        //JLabel label.setPreferredSize(new Dimension(250, 100));
        registrationForm.add(new JLabel("<html><body><br /></body></html>"));
        
        registrationForm.add(lblTitle = new JLabel(" Title: "));
        registrationForm.add(cmbTitle = new JComboBox(titleList));
        //registrationForm.add(new JLabel("<html><body><br /></body></html>"));
        
        registrationForm.add(lblName = new JLabel(" Input Name: "));
        registrationForm.add(txtName = new JTextField(10)); 
        //registrationForm.add(new JLabel("<html><body><br /></body></html>"));
        
        registrationForm.add(lblSurname = new JLabel(" Input Surname: "));
        registrationForm.add(txtSurname = new JTextField(10));  
        //registrationForm.add(new JLabel("<html><body><br /></body></html>"));
     
        registrationForm.add(lblDob = new JLabel(" Date of birth: "));
        //DateFormat format = new SimpleDateFormat("dd/MM/yyyy"); //creating the mask format for date
        //DateFormatter formatter = new DateFormatter(format);
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        registrationForm.add(txtDob = new JFormattedTextField(formatter));
        txtDob.setToolTipText("dd/mm/yyyy");
        //registrationForm.add(new JLabel("<html><body><br /></body></html>"));
        
        registrationForm.add(lblAge = new JLabel(" Input Age: "));
        registrationForm.add(txtAge = new JTextField(10));  
        //registrationForm.add(new JLabel("<html><body><br /></body></html>"));

        
        registrationForm.add(lblEmail = new JLabel(" Input Email: "));
        registrationForm.add(txtEmail = new JTextField(10)); 
        //registrationForm.add(new JLabel("<html><body><br /></body></html>"));


        
        registrationForm.add(lblPassword = new JLabel("Password: "));
        registrationForm.add(txtPassword = new JPasswordField('*'));
        registrationForm.add(new JLabel(" ")); 
        registrationForm.add(hideShowPw =  new JCheckBox("Show/Hide Password"));
        //registrationForm.add(new JLabel(" ")); 
        //registrationForm.add(new JLabel("<html><body><br /></body></html>"));

        
        registrationForm.add(lblGender = new JLabel(" Gender: "));
        registrationForm.add(cmbGender = new JComboBox(GenderList));
        //registrationForm.add(new JLabel("<html><body><br /></body></html>"));

                
        //declaration of another panel for the event buttons
        JPanel registrationFormControlPanel = new JPanel(new FlowLayout());
        registrationFormControlPanel.add(btnSave = new JButton("Create"));        
        registrationFormControlPanel.add(btnClear = new JButton("Clear"));   
        registrationFormControlPanel.add(btnClose = new JButton("Close"));
        //registrationForm.add(new JLabel(" "));

        //declaring a new grid layout to insert the other panels inside it
        JPanel panel = new JPanel(new GridLayout(2,1));
        panel.add(registrationForm, BorderLayout.CENTER);              		
        panel.add(registrationFormControlPanel, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);
    }
    
    public void itemStateChanged() {       
    	if(this.hideShowPw.isSelected()){  
    		txtPassword.setEchoChar((char) 0);
        }  
    	else {
    	txtPassword.setEchoChar('*');    
     }    

    }
    public boolean addUser() throws GeneralSecurityException, GeneralSecurityException{
    	String personId, personTitle, personName, personSurname, personGender, personEmail, personPassword;
    	Date personDateOfBirth = null;
    	int personAge = 0;
        
        //this attribute will be used in the do while loop
        boolean quit = true;
        
        do {
            
        	// getting the next available user ID
        	int userIdCounter = ApplicationLogic.sizeOfPersonHashmap() + 1;
        	
        	//assign UserID with the next available ID
        	personId = String.valueOf(userIdCounter);
        	
            //checking if the string variable UserID is not null
            if (personId != null) {
                //checking if the UserID is not empty
                if (personId.trim().equals("")) {
                    //display a message to the user that UserID is mandatory
                    JOptionPane.showMessageDialog(null, "User ID is required", "Error - Required data is missing", JOptionPane.ERROR_MESSAGE);
                    //boolean true is determining to stop to while loop
                    quit = true;
                    return false;
                    //break;
                } else {
                    //boolean false is determining to continue the while loop
                    quit = false;
                } 
                } //else {
                    //return;                                 
                //}
            
            //assign personName with text submitted in txtpersonName
            personName = this.txtName.getText();
            //checking if the string variable personName is not null
            if (personName != null) {
                //checking if the personName is not empty
                if (personName.trim().equals("")) {
                    //display a message to the user that name is mandatory
                    JOptionPane.showMessageDialog(null, "Name is required", "Error - Required data is missing", JOptionPane.ERROR_MESSAGE);
                    //boolean true is determining to stop to while loop
                    quit = true;
                    return false;
                } else {
                    //boolean false is determining to continue the while loop
                    quit = false;
                } 
                } //else {
                    //return;                                 
                //}
            
            //assign personTitle with text submitted in txtTitle
            personTitle = this.cmbTitle.getSelectedItem().toString();
            //checking if the string variable personTitle is not null
            if (personTitle != null) {
                //checking if the personTitle is not empty
                if (personTitle.trim().equals("")) {
                    //display a message to the user that personTitle is mandatory
                    JOptionPane.showMessageDialog(null, "Title is required", "Error - Required data is missing", JOptionPane.ERROR_MESSAGE);
                    //boolean true is determining to stop to while loop
                    quit = true;
                    return false;
                } else {
                    //boolean false is determining to continue the while loop
                    quit = false;
                } 
                } //else {
                    //return;                                 
                //}
            
            //assign personSurname with text submitted in txtPersonSurname
            personSurname = this.txtSurname.getText();
            //checking if the string variable personSurname is not null
            if (personSurname != null) {
                //checking if the personSurname is not empty
                if (personSurname.trim().equals("")) {
                    //display a message to the user that personSurname is mandatory
                    JOptionPane.showMessageDialog(null, "Surname is required", "Error - Required data is missing", JOptionPane.ERROR_MESSAGE);
                    //boolean true is determining to stop to while loop
                    quit = true;
                    return false;
                } else {
                    //boolean false is determining to continue the while loop
                    quit = false;
                } 
                } //else {
                    //return;                                 
                //}
            
            String getPersonDob = this.txtDob.getText();
            //checking if the string variable getPersonDob is not null
            if (getPersonDob != null) {
                 //checking if the getPersonDob is not empty
                if (getPersonDob.trim().equals("")) {
                    //display a message to the user that personDOB is mandatory
                    JOptionPane.showMessageDialog(null, "Date of birth is required", "Error - Required data is missing", JOptionPane.ERROR_MESSAGE);
                    quit = true;
                } else {
                    try {
                    //setting a date format
                    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    formatter.setLenient(true);
                    //parse DOB
                    personDateOfBirth = formatter.parse(getPersonDob);
                    quit = false;
                    } catch (ParseException e){
                        JOptionPane.showMessageDialog(null, "Please enter a valid date of birth with DD/MM/YYYY format", "Error" + "Type Mismatch", JOptionPane.ERROR_MESSAGE);
                        quit = true;                        
                    }
                }
            } //else {
              //  return;
            //} 
            
            String getPersonAge = this.txtAge.getText();
            //checking if the string variable GetPersonAge is not null
            if (getPersonAge != null) {
                //checking if the personAge is not empty
                if (getPersonAge.trim().equals("")) {
                    //display a message to the user that personAge is mandatory
                    JOptionPane.showMessageDialog(null, "Age is required", "Error - Required data is missing", JOptionPane.ERROR_MESSAGE);
                    //boolean true is determining to stop to while loop
                    quit = true;
                    return false;
                } else {
                try{
                    //parse to integer
                    personAge = Integer.parseInt(getPersonAge);
                    //boolean false is determining to continue the while loop
                    quit = false;
                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Please enter a valid age in numeric digits", "Error" + "Type Mismatch", JOptionPane.ERROR_MESSAGE);
                    quit = true;
                    throw e;
                        }
                    } 
                } //else {
                   // return;                                 
                //}
            //assign personEmail with text submitted in txtpersonEmail
            personEmail = this.txtEmail.getText();
            //checking if the string variable personSurname is not null
            if (personEmail != null) {
                //checking if the personEmail is not empty
                if (personEmail.trim().equals("")) {
                    //display a message to the user that personEmail is mandatory
                    JOptionPane.showMessageDialog(null, "Email is required", "Error - Required data is missing", JOptionPane.ERROR_MESSAGE);
                    //boolean true is determining to stop to while loop
                    quit = true;
                    return false;
                } else {
                    //boolean false is determining to continue the while loop
                    quit = false;
                } 
                } //else {
                    //return;                                 
                //}
            
            
            
            //assign personPassword with text submitted in txtpersonSurname
            //personPassword = applicationLogic.hashPassword(this.txtSurname.getText());
            personPassword = String.valueOf(txtPassword.getPassword());
            //checking if the string variable personPassword is not null
            if (personPassword != null) {
                //checking if the personPassword is not empty
                if (personPassword.trim().equals("")) {
                    //display a message to the user that personPassword is mandatory
                    JOptionPane.showMessageDialog(null, "Password is required", "Error - Required data is missing", JOptionPane.ERROR_MESSAGE);
                    //boolean true is determining to stop to while loop
                    quit = true;
                    return false;
                } else {
                    //boolean false is determining to continue the while loop
                    quit = false;
                } 
                } //else {
                    //return;                                 
                //}
            
            
                        

            

            
            //assign personGender with text submitted in txtPersonGender
            personGender = this.cmbGender.getSelectedItem().toString();
            //checking if the string variable personGender is not null
            if (personGender != null) {
                //checking if the personGender is not empty
                if (personGender.trim().equals("")) {
                    //display a message to the user that personGender is mandatory
                    JOptionPane.showMessageDialog(null, "Gender is required", "Error - Required data is missing", JOptionPane.ERROR_MESSAGE);
                    //boolean true is determining to stop to while loop
                    quit = true;
                    return false;
                } else {
                    //boolean false is determining to continue the while loop
                    quit = false;
                } 
                } //else {
                  //  return;                                 
                //}

            
            //validate if the ID already exists in the hashmap/file
            boolean personExists = ApplicationLogic.getPersonId(personId);
            boolean emailInUse = ApplicationLogic.getPersonEmail(personEmail);
            
            if (personExists == true) {
                JOptionPane.showMessageDialog(null, "person ID is already exists", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (emailInUse == true) {
            	JOptionPane.showMessageDialog(null, "Email already exists", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
        		System.out.println("input password: "+personPassword);
//            	String personId, personTitle, personName, personSurname, personGender, personEmail, personPassword;
//            	Date personDateOfBirth;    	
                applicationLogic.addPersonRecord(personId, personTitle, personName, personSurname, personGender, personDateOfBirth, personAge, personEmail, personPassword);
                JOptionPane.showMessageDialog(null, "Successful added person to the file", "Add record",JOptionPane.INFORMATION_MESSAGE);
                
                //clear the form components when saved
                clearData();
                return true;
                
            }
            
        } while (quit == true);
        return true;
    }
    

    //create the clear data method
    public void clearData(){
        this.txtEmail.setText("");
        this.txtPassword.setText("");
        this.txtName.setText("");
        this.txtSurname.setText("");
        this.txtAge.setText("");
        this.cmbGender.setSelectedIndex(0);
        this.cmbTitle.setSelectedIndex(0);
        this.txtDob.setText("");
    }

}
        
