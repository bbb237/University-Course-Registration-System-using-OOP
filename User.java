import java.util.ArrayList;

public class User implements java.io.Serializable{
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	public User() {}
	
	public void setUsername(String username) {
		
		this.username = username;
	}
	
	public String getUsername() {
		
		return this.username;
	}

	public void setPassword(String password) {
		
		this.password = password;
	}
	
	public String getPassword() {
		
		return this.password;
	}
	
	public void setName(String firstName, String lastName) {
		
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		
		return this.firstName;
	}
	
	public String getLastName() {
		
		return this.lastName;
	}
	
	public boolean isAdmin() {

		if (getUsername().equals("Admin") && getPassword().equals("Admin001")) 
			return true;
		
		else
			return false;
	}
	
	public boolean isStudent(ArrayList<Student> students) {
		
		try {
			for (Student i : students)
			
			if (i.getUsername().equals(this.getUsername()) && i.getPassword().equals(this.getPassword()))
				return true;
		
			return false; 
		}
		
		catch (NullPointerException e) {
			System.out.println("No student found.\n");
			return false;
		}
	}
	
	public Student getStudent(ArrayList<Student> students) {

		for (Student i : students)
		
			if (i.getUsername().equals(this.getUsername()) && i.getPassword().equals(this.getPassword()))
				return i;
		
		return null;
	}
	
	public void displayMenu() {
		
		System.out.println("Entering Course Registration System...\n");
	}
	
	
	
	
}
