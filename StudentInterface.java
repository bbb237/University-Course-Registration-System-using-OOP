import java.util.ArrayList;

public interface StudentInterface {
	
	public void addCourse(ArrayList<Course> courses, String courseName, String sectionNum, String firstName, String lastName); //student name will be added to course
	public void withdrawCourse(ArrayList<Course> courses, String courseName, String firstName, String lastName); //student will be removed from class
	public void viewAllCourses(ArrayList<Course> courses); //prints out course name, instructor, section, and location
	public void viewOpenCourses(ArrayList<Course> courses); //prints the courses that are not full
	public void viewRegisteredCourses(ArrayList<Course> courses); 
	
}
