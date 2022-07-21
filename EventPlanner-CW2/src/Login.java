import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends JFrame{
	
	//declaration of textboxes making use of JTextField
    private JTextField txtUsername, txtPassword;
    //declaration of JLabels
    private JLabel lblUsername, lblPassword;
    //declaration of JButtons
    private JButton btnLogin, btnClear, btnClose;   
    
    //default constructor
    public Login(){   
    	
    	buildLoginGUI();
    }
    
    public void buildLoginGUI(){
    	setTitle("Login"); //set title of form
        setSize(400, 600); //set the size of the form
        setLocationRelativeTo(null); //the form registration would be loaded exactly where I am working
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); //this property disposes any form details from memory, when the form is 
        
        //Create new grid layout having 15 rows and 2 columns
        JPanel loginForm = new JPanel(new GridLayout(15,2));
        //creating new instances of the private attributes in the grid layout
        loginForm.add(new JLabel("Login into account"));
        loginForm.add(new JLabel(" "));
        loginForm.add(new JLabel(" "));
        
        loginForm.add(lblUsername = new JLabel("Username: "));
        loginForm.add(txtUsername = new JTextField(10));
        loginForm.add(new JLabel(" "));    
        loginForm.add(lblUsername = new JLabel("Password: "));
        loginForm.add(txtUsername = new JTextField(15));
        loginForm.add(new JLabel(" ")); 
                
      //declaration of another panel for the event buttons
        JPanel loginFormControlPanel = new JPanel(new FlowLayout());
        loginFormControlPanel.add(btnLogin = new JButton("Login"));        
        loginFormControlPanel.add(btnClear = new JButton("Clear"));   
        loginFormControlPanel.add(btnClose = new JButton("Close"));

        //declaring a new grid layout to insert the other panels inside it
        JPanel panel = new JPanel(new GridLayout(2,1));
        panel.add(loginForm, BorderLayout.CENTER);              		
        panel.add(loginFormControlPanel, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);
    }	

}
