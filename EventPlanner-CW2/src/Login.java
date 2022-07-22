import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//declaration of textboxes making use of JTextField
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    //declaration of JLabels
    private JLabel lblUsername, lblPassword;
    //declaration of JButtons
    private JButton btnLogin, btnReg, btnClear, btnClose;   
    
    private JCheckBox hideShowPw;  
    
    Register register = new Register();
    
    //create instance of application logic in the person user interface class (RegisterUI)
    ApplicationLogic applicationLogic = new ApplicationLogic();
    
    //default constructor
    public Login(){   
    	
    	buildLoginGUI();
    	
    	
    	btnLogin.addActionListener(new ActionListener(){
            @Override
                public void actionPerformed(ActionEvent e){
            		try {
						login();
					} catch (NoSuchAlgorithmException | NoSuchProviderException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
                }
            });
    	
    	btnReg.addActionListener(new ActionListener(){
            @Override
                public void actionPerformed(ActionEvent e){
            			register.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            			register.setVisible(true);
            			
                }
            });
    	
    	btnClose.addActionListener(new ActionListener(){
            @Override
                public void actionPerformed(ActionEvent e){
            		setVisible(false);
            		dispose(); 		
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
        loginForm.add(lblPassword = new JLabel("Password: "));
        loginForm.add(txtPassword = new JPasswordField('*'));
        loginForm.add(hideShowPw =  new JCheckBox("Show/Hide Password"));
        loginForm.add(new JLabel(" ")); 
        
        
                
      //declaration of another panel for the event buttons
        JPanel loginFormControlPanel = new JPanel(new FlowLayout());
        loginFormControlPanel.add(btnLogin = new JButton("Login"));      
        loginFormControlPanel.add(btnReg = new JButton("Register"));
        loginFormControlPanel.add(btnClear = new JButton("Clear"));   
        loginFormControlPanel.add(btnClose = new JButton("Close"));

        //declaring a new grid layout to insert the other panels inside it
        JPanel panel = new JPanel(new GridLayout(2,1));
        panel.add(loginForm, BorderLayout.CENTER);              		
        panel.add(loginFormControlPanel, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);
    }	

    
    //create the clear data method
    public void clearData(){
    	this.txtUsername.setText("");
    	this.txtPassword.setText("");
    }
    
    public void itemStateChanged() {       
    	if(this.hideShowPw.isSelected()){  
    		txtPassword.setEchoChar((char) 0);
        }  
    	else {
    	txtPassword.setEchoChar('*');    
     }    

    }
    
    public void login() throws NoSuchAlgorithmException, NoSuchProviderException {
    	String username, unHashedPassword, hashedPassword;
    	boolean loginAuthentication = false;
    
    	username = this.txtUsername.getText();
    	unHashedPassword = String.valueOf(txtPassword.getPassword());
    	hashedPassword = applicationLogic.hashPassword(unHashedPassword);   	
    	
    	loginAuthentication = applicationLogic.loginVerification(username, hashedPassword);
    	
    	if (loginAuthentication == true){
    		System.out.println("Login Succesfull");
    	}else {
    		System.out.println("Login Failure");
    	}

    	
    }
}
