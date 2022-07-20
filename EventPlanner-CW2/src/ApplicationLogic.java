import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.LocalTime;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
//import java.util.Vector;
//import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;


/**
 * 
 * @author FransPenza
 *
 */
public class ApplicationLogic implements Serializable{

    //declare and initialize the Hashmap myPersonHashMap
    private static Map<String, Person> myPersonHashMap = new HashMap<String, Person>();
    
    //declare and initialize the Hashmap myEventHashMap
    private static Map<String, Event> myEventHashMap = new HashMap<String, Event>();
	
    //declare the path (location) and file name for each object 
    String personFileName = "Person.obj";    
    String eventFileName = "Event.obj";
    
    public ApplicationLogic(){
    	
    	boolean loadedPersonHashMap = loadFromDiskPerson(personFileName);
    	
    	//Create the users through hard code in Constructor.
        if(loadedPersonHashMap == false){
        
            addPersonRecord("1", "Mr", "Frans", "Penza", "Male", null, 30, "franspenza@gmail.com", "test");
            addPersonRecord("1", "Mr", "Christian", "Attard", "Male", null, 30, "chris.att.91@gmail.com", "test");
        }
    }
    
    
    //save to disk method for object patient
    public boolean saveToDiskPerson(String path){
        //encapsulate code through a try and catch block
        try{
            //open the output gateway to save file
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path)); //opening the stream           
            out.writeObject(myPersonHashMap); //write the object
            out.close(); //save the object
        }catch (IOException ioe){ //unsuccessful
            JOptionPane.showMessageDialog(null, "Error");
            return false;
        }  
        return true; //successful
    }
    
    
    //save to disk method for object patient
    public boolean saveToDiskEvent(String path){
        //encapsulate code through a try and catch block
        try{
            //open the output gateway to save file
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path)); //opening the stream           
            out.writeObject(myEventHashMap); //write the object
            out.close(); //save the object
        }catch (IOException ioe){ //unsuccessful
            JOptionPane.showMessageDialog(null, "Error");
            return false;
        }  
        return true; //successful
    }
    
    
    public boolean loadFromDiskPerson(String path) {

        if (path != null) {

            //encapsulate code through a try and catch block
            try {
                File file = new File(path);
                //open the input gateway to load file
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                myPersonHashMap = (HashMap<String, Person>) in.readObject();
                in.close();
                return true; //this will return the hashmap loaded with the data or null
            } catch (FileNotFoundException fnfe)  {//error showing no file was found                
                JOptionPane.showMessageDialog(null, "ERROR: " + fnfe.getMessage());
                return false;
            } catch (IOException | ClassNotFoundException e) { //any other exceptions
                JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
                return false;
            }
        }
        return false;
    }
    
    
    public boolean loadFromDiskEvent(String path) {

        if (path != null) {

            //encapsulate code through a try and catch block
            try {
                File file = new File(path);
                //open the input gateway to load file
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                myEventHashMap = (HashMap<String, Event>) in.readObject();
                in.close();
                return true; //this will return the hashmap loaded with the data or null
            } catch (FileNotFoundException fnfe)  {//error showing no file was found                
                JOptionPane.showMessageDialog(null, "ERROR: " + fnfe.getMessage());
                return false;
            } catch (IOException | ClassNotFoundException e) { //any other exceptions
                JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
                return false;
            }
        }
        return false;
    }
    
    /**
     * 
     * @param personId
     * @param personTitle
     * @param personName
     * @param personSurname
     * @param personGender
     * @param personDateOfBirth
     * @param personAge
     * @param personEmail
     * @param personPassword
     */
    public void addPersonRecord(String personId, String personTitle, String personName, String personSurname,String personGender ,Date personDateOfBirth, int personAge, String personEmail, String personPassword){
        myPersonHashMap.put(personId, new Person(personId, personTitle, personName, personSurname, personGender , personDateOfBirth, personAge, personEmail, personPassword));
        
        //call the saveToDiskPerson method to save a person in the specified path
        saveToDiskPerson(personFileName);
    }
    
    public void addEventRecord(String eventId, String eventTitle, String eventDescription, String eventType, Date eventDate ,LocalTime eventTime, String eventLocation){
        myEventHashMap.put(eventId, new Event(eventId, eventTitle, eventDescription, eventType, eventDate , eventTime, eventLocation));
        //String eventType, Date eventDate, LocalTime eventTime, String eventLocation
        //call the saveToDiskPerson method to save a person in the specified path
        saveToDiskEvent(eventFileName);
    }
    
    public String hashPassword(String password) throws NoSuchAlgorithmException, NoSuchProviderException {
    	
    	String passwordToHash = password;
    	String salt = getSalt();
    	String securePassword = getSecurePassword(passwordToHash, salt);

        String regeneratedPassowrdToVerify = getSecurePassword(passwordToHash, salt);

        System.out.println(regeneratedPassowrdToVerify);
    	
    	return securePassword;
    }
    
    public String unhashPassword(String password) throws NoSuchAlgorithmException, NoSuchProviderException {
    	
    	String passwordToHash = password;
    	String salt = getSalt();
        String regeneratedPassowrd = getSecurePassword(passwordToHash, salt);
    	
    	return regeneratedPassowrd;
    }
    
    
    private static String getSecurePassword(String passwordToHash, String salt) {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            // Add password bytes to digest
            md.update(salt.getBytes());
            
            // Get the hash's bytes
            byte[] bytes = md.digest(passwordToHash.getBytes());
            
            // This bytes[] has bytes in decimal format;
            // Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            
            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    
    
    private static String getSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
        // Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "MOON");

        // Create array for salt
        byte[] salt = new byte[16];

        // Get a random salt
        sr.nextBytes(salt);

        // return salt
        return salt.toString();
    }
    
    
    /**
     * 
     * @param getPersonData
     * @return
     */
    //String is the ID of myPersonHashMap in order to get the person data
    public static Person getPersonData(String getPersonData){
        Person personData = myPersonHashMap.get(getPersonData);
        return personData;
    }
    
    /**
     * 
     * @param getEventData
     * @return
     */
    //String is the ID of myEventHashMap in order to get the event data
    public static Event getEventData(String getEventData){
        Event eventData = myEventHashMap.get(getEventData);
        return eventData;
    }
    
    /**
     * 
     * @return
     */
    public static String[] listPersonName(){
        return myPersonHashMap.keySet().toArray(new String[0]);
    }   
    
    /**
     * 
     * @param ID
     * @return
     */
    public static String[] listEventNameByName(String ID){
        String[] split = new String[0];
        String e = ID;
        
        for(String key: myEventHashMap.keySet()){
            e = key + "\n" + e;
            split = e.split("\n");
        }
        return split;
    }
}
