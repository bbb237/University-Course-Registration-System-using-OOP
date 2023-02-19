import java.util.ArrayList;

public class Student extends User implements StudentInterface, java.io.Serializable {

	public Student(String username, String password) {
		
		this.setUsername(username);
		this.setPassword(password);
	}

	@Override
	public void displayMenu() {
		
		System.out.print("Course Management (1-6)\n\n"
				+ "1. View all courses\n"
				+ "2. View all courses that are not FULL\n"
				+ "3. Register on a course\n"
				+ "4. Withdraw from a course\n"
				+ "5. View all registered courses\n"
				+ "6. Exit\n\n");
	}

	@Override
	public void addCourse(ArrayList<Course> courses, String courseName, String sectionNum, String firstName,
			String lastName) {
		
		for (Course i : courses) 
			
			if (i.getCourseName().equals(courseName) && i.getSectionNum().equals(sectionNum)) {
				i.addStudent(firstName, lastName);
				i.setCurrentStudents(i.getCurrentStudents() + 1);
				System.out.println("Succesfully registered to " + courseName + ".\n");
				return;
			}
		System.out.print("That course does not exist.\n\n");
		return;
	}

	@Override
	public void withdrawCourse(ArrayList<Course> courses, String courseName, String firstName, String lastName) {
		
		for (Course i : courses) 
			
			if (i.getCourseName().equals(courseName) && i.getStudentNames().contains(firstName + " " + lastName)) {
				
				i.removeStudent(firstName, lastName);
				i.setCurrentStudents(i.getCurrentStudents() - 1);
				System.out.println("Succesfully withdrawed from " + courseName + ".\n");
				return;
			}
		System.out.print("That course does not exist.\n\n");
		return;
	}

	@Override
	public void viewAllCourses(ArrayList<Course> courses) {
		
		for (Course i : courses) {
			
			System.out.println("Course Name: " + i.getCourseName());
			System.out.println("Course Instructor: " + i.getInstructor());
			System.out.println("Section: " + i.getSectionNum());
			System.out.println("Location: " + i.getLocation() + "\n");
		}
		if (courses.size() == 0) {
			System.out.print("No courses at the moment.\n\n");
			return;
		}
	}

	@Override
	public void viewOpenCourses(ArrayList<Course> courses) {
		
		boolean stop = true;
		
		for (Course i : courses) {
			
			if (i.getMaxStudents() > i.getCurrentStudents()) {
			
				System.out.println("Course Name: " + i.getCourseName());
				System.out.println("Course Instructor: " + i.getInstructor());
				System.out.println("Section: " + i.getSectionNum());
				System.out.println("Location: " + i.getLocation() + "\n");
				stop = false;
			}
			
			else if (stop && courses.indexOf(i) == courses.size() - 1) {
				System.out.print("No available courses.\n\n");
				return;
			}
		}
	}

	@Override
	public void viewRegisteredCourses(ArrayList<Course> courses) {

		boolean stop = true;
		
		for (Course i : courses) {
			
			if (i.getStudentNames().contains(getFirstName() + " " + getLastName())) {
			
				System.out.println("Course Name: " + i.getCourseName());
				System.out.println("Course Instructor: " + i.getInstructor());
				System.out.println("Section: " + i.getSectionNum());
				System.out.println("Location: " + i.getLocation() + "\n");
				stop = false;
			}
			
			else if (stop && courses.indexOf(i) == courses.size() - 1) {
				System.out.print("You are not registered in any courses.\n\n");
				return;
			}
		}
	}
}
