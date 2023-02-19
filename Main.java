import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	
	public static void main(String [] args) throws IOException {
		
		ArrayList<Course> courses = new ArrayList<Course> ();
		ArrayList<Student> students = new ArrayList<Student> ();
		User user = new User();
		Admin admin = null;
		Student student = null;
		
		String courseName, courseID, maxStudents, currentStudents, instructor, sectionNum, location,
			firstName, lastName, option, change, username, password, userChoice = "temp";
		
		int attempts = 0;
		
		Scanner input = new Scanner(System.in);
		
		FileInputStream fileIn = null;
		ObjectInputStream objIn = null;
		
		boolean serial = true;
		
		try {
			fileIn = new FileInputStream("ObjData.ser");
			objIn = new ObjectInputStream(fileIn);
			
			while (true) {
				Object o = objIn.readObject();
				
				if (o instanceof Student)
					students.add((Student) o);
				
				else if (o instanceof Course)
					courses.add((Course) o);
			}
		}
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println();
		} 
		
		catch (EOFException e) {
			objIn.close();
			fileIn.close();
			serial = false;
		}
		
		catch (IOException e) {
			e.printStackTrace();
			System.out.println();
		} 
		
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println();
		}
		
		if (serial) {
			try {
			System.out.print("Enter csv file directory: ");
			String filePath = input.nextLine();
			System.out.println();
			File file = new File(filePath);
			Scanner scan = new Scanner(file);
			
			String header = scan.nextLine();
			
			while (scan.hasNextLine()) {
				
				String courseLine = scan.nextLine();
				String [] courseDataList = courseLine.split(",");
				
				courses.add(new Course(courseDataList));
			}
			scan.close();
			}
			
			catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("File not found");
			}
		}
		
		user.displayMenu();
		
		do {
			System.out.print("Enter Username: ");
			username = input.nextLine();
			System.out.print("Enter Password: ");
			password = input.nextLine();
			System.out.println();
			
			user.setUsername(username); user.setPassword(password);
		
			if (user.isAdmin()) {
				admin = new Admin(user.getUsername(), user.getPassword());
				break;
			}
			
			else if (user.isStudent(students)) {
				student = user.getStudent(students);
				break;
			}
			
			else {
				System.out.println("Wrong username or password. Please try again.\n");
				attempts++;
			}
		}
		
		while (attempts < 10);
		
		while (user.isAdmin() && !userChoice.equals("Exit")) {
			
			admin.displayMenu();
			System.out.print("Option: ");
			userChoice = input.nextLine();
			System.out.println();

			if (userChoice.equals("1")) {
				System.out.print("Enter Course Name: ");
				courseName = input.nextLine();
			
				System.out.print("Enter Course ID: ");
				courseID = input.nextLine();
		
				System.out.print("Enter Max Number of Students: ");
				maxStudents = input.nextLine();
			
				System.out.print("Enter Current Number of Students: ");
				currentStudents = input.nextLine();
		
				System.out.print("Enter Instructor Name: ");
				instructor = input.nextLine();
				
				System.out.print("Enter Course Section: ");
				sectionNum = input.nextLine();
		
				System.out.print("Enter Course Location: ");
				location = input.nextLine();
		
				admin.createCourse(courses, courseName, courseID, maxStudents, currentStudents, instructor, sectionNum, location);
				
				for (int i = 0; i < Integer.parseInt(currentStudents); i++) {
					
					System.out.print("Enter Student First Name: ");
					firstName = input.nextLine();
					System.out.print("Enter Student Last Name: ");
					lastName = input.nextLine();
					
					courses.get(-1).addStudent(firstName, lastName);
				}
				System.out.println();
			}
			
			else if (userChoice.equals("2")) {
				System.out.print("Enter Course Name: ");
				courseName = input.nextLine();
				
				System.out.print("Enter Course Section: ");
				sectionNum = input.nextLine();
				
				System.out.println();
				admin.deleteCourse(courses, courseName, sectionNum);
			}
			
			else if (userChoice.equals("3")) {
				System.out.print("Enter Course Name: ");
				courseName = input.nextLine();
				
				System.out.print("Enter Course Section: ");
				sectionNum = input.nextLine();
				
				do {
					System.out.print("\nChoose an Option (1-7)\n\n"
							+ "1. Change Max Students\n"
							+ "2. Add Student\n"
							+ "3. Remove Student\n"
							+ "4. Change Instructor\n"
							+ "5. Change Section Number\n"
							+ "6. Change Location\n"
							+ "7. Back to Main Menu\n\n");
					System.out.print("Option: ");
					
					option = input.nextLine();
					
					if (option.equals("2") || option.equals("3")) {
						
						System.out.print("Enter First Name: ");
						firstName = input.nextLine();
						System.out.print("Enter Last Name: ");
						lastName = input.nextLine();
						
						change = firstName + " " + lastName;
						admin.editCourse(courses, courseName, sectionNum, option, change);
					}
					
					else if (option.equals("1") || option.equals("4") || option.equals("5") || option.equals("6")) {
						System.out.print("Enter Change: ");
						change = input.nextLine();
						admin.editCourse(courses, courseName, sectionNum, option, change);
					}
					
					else if (!option.equals("7"))
						System.out.print("That is not an option.\n\n");
				}
				while (!option.equals("7"));
			}
			
			else if (userChoice.equals("4")) {
				System.out.print("Enter Course ID: ");
				courseID = input.nextLine();
				
				System.out.println();
				admin.displayCourse(courses, courseID);
			}
			
			else if (userChoice.equals("5")) {
				System.out.print("Enter Student First Name: ");
				firstName = input.nextLine();
				System.out.print("Enter Student Last Name: ");
				lastName = input.nextLine();
				System.out.print("Enter Student Username: ");
				username = input.nextLine();
				System.out.print("Enter Student Password: ");
				password = input.nextLine();
				
				System.out.println();
				admin.registerStudent(students, firstName, lastName, username, password);
			}
			
			else if (userChoice.equals("A")) {
				System.out.println("Displaying all courses...\n");
				admin.viewAllCourses(courses);
			}

			else if (userChoice.equals("B")) {
				System.out.println("Displaying all FULL courses...\n");
				admin.viewFullCourses(courses);
			}
			else if (userChoice.equals("C"))
				admin.fileFullCourses(courses);
			
			else if (userChoice.equals("D")) {
				System.out.print("Enter Course Name: ");
				courseName = input.nextLine();
				
				System.out.println();
				System.out.println("Students in " + courseName + ": ");
				admin.viewStudentsInCourse(courses, courseName);
			}
			
			else if (userChoice.equals("E")) {
				System.out.print("Enter Student First Name: ");
				firstName = input.nextLine();
				System.out.print("Enter Student Last Name: ");
				lastName = input.nextLine();
				
				System.out.println();
				System.out.print(firstName + " " + lastName + " is enrolled in:\n");
				admin.viewCoursesOfStudent(courses, firstName, lastName);
			}
			
			else if (userChoice.equals("F")) 
				admin.sortCourses(courses);
			
			else if (userChoice.equals("Exit"))
				break;
			
			else
				System.out.print("That is not an option.\n\n");
		}
		
		while (user.isStudent(students) && !userChoice.equals("6")) {
			
			student.displayMenu();
			System.out.print("Option: ");
			userChoice = input.nextLine();
			System.out.println();
		
			if (userChoice.equals("1")) {
				System.out.println("Displaying all courses...\n");
				student.viewAllCourses(courses);
			}
			
			else if (userChoice.equals("2")) {
				System.out.println("Displaying all OPEN courses...\n");
				student.viewOpenCourses(courses);
			}
				
			else if (userChoice.equals("3")) {
				System.out.print("Enter Course Name: ");
				courseName = input.nextLine();
				
				System.out.print("Enter Course Section: ");
				sectionNum = input.nextLine();
				
				System.out.print("Enter Your First Name: ");
				firstName = input.nextLine();
				
				System.out.print("Enter Your Last Name: ");
				lastName = input.nextLine();
				
				System.out.println();
				student.addCourse(courses, courseName, sectionNum, firstName, lastName);
			}
			
			else if (userChoice.equals("4")) {
				System.out.print("Enter Course Name: ");
				courseName = input.nextLine();
				
				System.out.print("Enter Your First Name: ");
				firstName = input.nextLine();
				
				System.out.print("Enter Your Last Name: ");
				lastName = input.nextLine();
				
				System.out.println();
				student.withdrawCourse(courses, courseName, firstName, lastName);
			}
			
			else if (userChoice.equals("5")) {
				System.out.println("Displaying all REGISTERED courses...\n");
				student.viewRegisteredCourses(courses);
			}
			
			else if (userChoice.equals("6"))
				break;
			
			else
				System.out.print("That is not an option.\n\n");
		}
		
		input.close();
		
		try {
			FileOutputStream fileOut = new FileOutputStream("ObjData.ser");
			ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
			
			for (Course i : courses) 
				objOut.writeObject(i);

			for (Student i : students) 
				objOut.writeObject(i);
				
			objOut.close();
			fileOut.close();
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
