import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Admin extends User implements AdminInterface{

	public Admin(String username, String password) {
		
		this.setUsername(username);
		this.setPassword(password);
	}
	
	@Override
	public void displayMenu() {
		
		System.out.print("Courses Management (1-5)\n\n"
				+ "1. Create a new course\n"
				+ "2. Delete a course\n"
				+ "3. Edit a course\n"
				+ "4. Display information for a given course\n"
				+ "5. Register a student\n\n");
		
		System.out.print("Reports (A-F)\n\n"
				+ "A. View all courses\n"
				+ "B. View all FULL courses \n"
				+ "C. Write to a file the list of course that are Full\n"
				+ "D. View the names of the students being registered in a specific course\n"
				+ "E. View the list of courses that a given student is being registered on\n"
				+ "F. Sort\n");
		
		System.out.print("\nExit(Exit)\n\n");	
	}

	@Override
	public void createCourse(ArrayList<Course> courses, String courseName, String courseID, String maxStudents, 
			String currentStudents, String instructor, String courseSection, String courseLocation) {
		
		courses.add(new Course());

		courses.get(courses.size() - 1).setCourseName(courseName);
		courses.get(courses.size() - 1).setCourseID(courseID);
		courses.get(courses.size() - 1).setMaxStudents(maxStudents);
		courses.get(courses.size() - 1).setCurrentStudents(currentStudents);
		courses.get(courses.size() - 1).setInstructor(instructor);
		courses.get(courses.size() - 1).setSectionNum(courseSection);
		courses.get(courses.size() - 1).setLocation(courseLocation);	
		
		System.out.print("Succesfully created course " + courseName + ".\n\n");
	}

	@Override
	public void deleteCourse(ArrayList<Course> courses, String courseName, String sectionNum) {
		
		for (Course i : courses) 
			
			if (i.getCourseName().equals(courseName) && i.getSectionNum().equals(sectionNum)) {
				courses.remove(i);
				System.out.print("Succesfully deleted " + courseName + ", section " + sectionNum + ".\n\n");
				return;
			}
		System.out.print("That course does not exist.\n\n");
		return;
	}

	@Override
	public void editCourse(ArrayList<Course> courses, String courseName, String sectionNum, String option, String change) {
	
		Course selectedCourse = null;
		
		for (Course i : courses) {

			if (i.getCourseName().equals(courseName) && i.getSectionNum().equals(sectionNum)) 
				selectedCourse = courses.get(courses.indexOf(i));
		}
		
		if (selectedCourse == null) {
			System.out.print("That course does not exist.\n\n");
			return;
		}
		
		switch (Integer.parseInt(option)) {
			
		case 1:
			if (Integer.parseInt(change) >= selectedCourse.getCurrentStudents()) {
				selectedCourse.setMaxStudents(change);
				System.out.println("Maximum students of " + courseName + " changed to " + change + ".\n");
			}
			
			else {
				System.out.print("Maximum students cannot be less than current students.\n\n");
				return;
			}
			break;
			
		case 2:
			if (selectedCourse.getCurrentStudents() >= selectedCourse.getMaxStudents()) {
				System.out.print("Maximum students reached. Unable to add any more students.\n\n");
				return;
			}
			else {
				selectedCourse.addStudent(change);
				selectedCourse.setCurrentStudents(selectedCourse.getCurrentStudents() + 1);
				System.out.println("Added " + change + " to " + courseName + ".\n");
			}
			break;
		
		case 3:
			if (selectedCourse.removeStudent(change)) {
				selectedCourse.setCurrentStudents(selectedCourse.getCurrentStudents() - 1);
				System.out.println("Removed " + change + " from " + courseName + ".\n");
			}
			
			else {
				System.out.print("That student is not in this course.\n\n");
				return;
			}
			break;
			
		case 4:
			selectedCourse.setInstructor(change);
			System.out.println(courseName + " instructor changed to " + change + ".\n");
			break;
			
		case 5:
			selectedCourse.setSectionNum(change);
			System.out.println(courseName + " section changed to " + change + ".\n");
			break;
			
		case 6:
			selectedCourse.setLocation(change);
			System.out.println(courseName + " location changed to " + change + ".\n");
			break;
		}
	}

	@Override
	public void displayCourse(ArrayList<Course> courses, String courseID) {
		
		boolean stop = true;
		
		for (Course i : courses) {

			if (i.getCourseID().equals(courseID)) {
				
				System.out.println("Course Name: " + i.getCourseName());
				System.out.println("Max Students: " + i.getMaxStudents());
				System.out.println("Current Students: " + i.getCurrentStudents());
				System.out.println("Students Names: " + i.getStudentNames());
				System.out.println("Course Instructor: " + i.getInstructor());
				System.out.println("Section Number: " + i.getSectionNum());
				System.out.println("Course Location: " + i.getLocation() + "\n");
				stop = false;
			}
			
			else if (stop && courses.indexOf(i) == courses.size() - 1) {
				System.out.print("That course does not exist.\n\n");
				return;
			}
		}
	}

	@Override
	public void registerStudent(ArrayList<Student> students, String firstName, String lastName, String username, String password) {
		
		try {
			for (Student i : students) {
			
				if (i.getUsername().equals(username) && i.getPassword().equals(password) && i.getFirstName().equals(firstName) && i.getLastName().equals(lastName)) {
					System.out.println("That student already exists.\n"); 
					return;	
				}
			}
			students.add(new Student(username, password));
			students.get(students.size() - 1).setName(firstName, lastName);
			System.out.print("Succesfully registered " + firstName + " " + lastName + " as a student.\n\n");
			return;
		}
		
		catch (NullPointerException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void viewAllCourses(ArrayList<Course> courses) {
		
		for (Course i : courses) {
			
			System.out.println("Course Name: " + i.getCourseName());
			System.out.println("Course ID: " + i.getCourseID());
			System.out.println("Max Students: " + i.getMaxStudents());
			System.out.println("Current Students: " + i.getCurrentStudents() + "\n");
		}
		
		if (courses.size() == 0) {
			System.out.print("No courses at the moment.\n\n");
			return;
		}
	}

	@Override
	public void viewFullCourses(ArrayList<Course> courses) {
		
		boolean stop = true;
		
		for (Course i : courses) {
			
			if (i.getMaxStudents() == i.getCurrentStudents()) {
			
				System.out.println("Course Name: " + i.getCourseName());
				System.out.println("Course ID: " + i.getCourseID());
				System.out.println("Max Students: " + i.getMaxStudents());
				System.out.println("Current Students: " + i.getCurrentStudents() + "\n");
				stop = false;
			}
			
			else if (stop && courses.indexOf(i) == courses.size() - 1) {
				System.out.print("No courses are full.\n\n");
				return;
			}
		}
	}

	@Override
	public void fileFullCourses(ArrayList<Course> courses) {
		
		boolean stop = true;
		
		try {
			FileWriter writer = new FileWriter("FullCourses.csv");
			
			for (Course i : courses) {
				
				if (i.getMaxStudents() == i.getCurrentStudents()) {
					writer.write(i.getCourseName() + "," + i.getCourseID() + "," + i.getMaxStudents() + "," + i.getCurrentStudents() 
					+ "," + i.getStudentNames() + "," + i.getInstructor() + "," + i.getSectionNum() + "," + i.getLocation() + "\n");
					stop = false;
				}
				
				else if (stop && courses.indexOf(i) == courses.size() - 1) {
					System.out.print("No courses are full.\n\n");
					return;
				}
			}
			writer.close();
			System.out.println("File of Full Classes Created.\n");
		}
		
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error in creating file. Please try again.\n");
		}
	}

	@Override
	public void viewStudentsInCourse(ArrayList<Course> courses, String courseName) {
		
		boolean stop = true;
		
		for (Course i : courses) {
			
			if (i.getCourseName().equals(courseName)) {
				System.out.println(i.getStudentNames() + "\n");
				stop = false;
			}
			
			else if (stop && courses.indexOf(i) == courses.size() - 1) {
				System.out.print("That course does not exist.\n\n");
				return;
			}
		}
	}

	@Override
	public void viewCoursesOfStudent(ArrayList<Course> courses, String firstName, String lastName) {
		
		boolean stop = true;
		
		for (Course i : courses) {
			
			if (i.getStudentNames().contains(firstName + " " + lastName)) {
				System.out.println(i.getCourseName() + "\n");
				stop = false;
			}
			
			else if (stop && courses.indexOf(i) == courses.size() - 1) {
				System.out.print("None.\n\n");
				return;
			}
		}
	}

	@Override
	public void sortCourses(ArrayList<Course> courses) {
		Collections.sort(courses);
		System.out.print("Successfully sorted courses based on current students.\n\n");
		return;
	}

	

}
