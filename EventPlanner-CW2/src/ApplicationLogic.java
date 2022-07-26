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
import java.util.Map.Entry;
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

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
            addPersonRecord("2", "Mr", "Christian", "Attard", "Male", null, 30, "chris.att.91@gmail.com", "test");
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
    
    // get size of hashmap
    public static int sizeOfPersonHashmap() {
    	int sizeOfPersonHashmap = myPersonHashMap.size();
    	return sizeOfPersonHashmap;
    }
    
    public static boolean getPersonId(String personId){
        if(myPersonHashMap.containsKey(personId)){
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean getPersonEmail(String personEmail){
        	int userIdCounter = ApplicationLogic.sizeOfPersonHashmap() + 1;
        	for(int i = 1; i < userIdCounter;) {
        		Person personData = myPersonHashMap.get(String.valueOf(i));
        		i++;
//        		System.out.println(personData.getEmail());
//        		System.out.println(personEmail);
        		String email1 = personData.getEmail().toString();
        		if (email1.equals(personEmail)) {
//        			System.out.println("True");
        			 return true;
        			}
        		}
        		
        	System.out.println("False");
    		return false;

    }
    
    
    public static boolean loginVerification(String inputUsername, String inputPassword){
    	
    	int userIdCounter = ApplicationLogic.sizeOfPersonHashmap() + 1;
		System.out.println("input username: "+inputUsername);
		System.out.println("input password: "+inputPassword);
    	for(int i = 1; i < userIdCounter;) {
    		Person personData = myPersonHashMap.get(String.valueOf(i));
    		i++;
    		System.out.println("loop username: " + personData.getEmail());
    		System.out.println("loop password: " + personData.getPassword());
    		if (personData.getEmail().equals(inputUsername)) {
    			if (personData.getPassword().equals(inputPassword)) {
    				System.out.printf("success");
    				return true;
    			}
    		}
    		
    		}
		return false;

    	}
    
}
