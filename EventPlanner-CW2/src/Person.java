import java.util.Date;
// import java.time.LocalTime;

public class Person {

	private int id;
	private String title;
	private String name;
	private String surname;
	private Date dateOfBirth;
	private int age;
	private String email;
	private String password;
	
	public Person() {
		
	}
	
	public Person(int personId, String personTitle, String personName, String personSurname, Date personDateOfBirth, int personAge, String personEmail, String personPassword) {
		id = personId;
		title = personTitle;
		name = personName;
		surname = personSurname;
		dateOfBirth = personDateOfBirth;
		age = personAge;
		email = personEmail;
		password = personPassword;
	}
	
	// ==== Getters & Setters ====
	
	// ID
	public int getId() {
		return id;
	}

	public void setId(int personId) {
		this.age = personId;
	}
	
	// Title
	public String getTitle() {
		return title;
	}

	public void setTitle(String personTitle) {
		this.title = personTitle;
	}
	
	// Name
	public String getName() {
		return name;
	}

	public void setName(String personName) {
		this.name = personName;
	}
	
	// Surname
	public String getSurname() {
		return surname;
	}

	public void setSurname(String personSurname) {
		this.surname = personSurname;
	}
	
	// DoB
	public Date getDoB() {
		return dateOfBirth;
	}

	public void setDoB(Date personDateOfBirth) {
		this.dateOfBirth = personDateOfBirth;
	}
	
	// Age
	public int getAge() {
		return age;
	}

	public void setAge(int personAge) {
		this.age = personAge;
	}
	
	// Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String personEmail) {
		this.email = personEmail;
	}
	
	// Password
	public String getPassword() {
		return password;
	}

	public void setPassword(String personPassword) {
		this.password = personPassword;
	}
}
