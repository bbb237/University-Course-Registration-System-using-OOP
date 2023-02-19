import java.util.ArrayList;

public interface AdminInterface {
	
	//courses management
	public void createCourse(ArrayList<Course> courses, String courseName, String courseID, String maxStudents, 
				String currentStudents, String instructor, String sectionNum, String location);
	public void deleteCourse(ArrayList<Course> courses, String courseName, String sectionNum); //delete by removing from course object ArrayList
	public void editCourse(ArrayList<Course> courses, String courseName, String sectionNum, String option,  String change); //edit any info except course ID and name
	public void displayCourse(ArrayList<Course> courses, String courseID); //by course ID, prints out course info
	public void registerStudent(ArrayList<Student> students, String firstName, String lastName, String username, String password); //add student
	
	//reports
	public void viewAllCourses(ArrayList<Course> courses); //prints out list of course name, course id, number of students registered, 
								  //and the maximum number of students allowed to be registered
	public void viewFullCourses(ArrayList<Course> courses);//prints out the courses that are full and their info
	public void fileFullCourses(ArrayList<Course> courses);
	public void viewStudentsInCourse(ArrayList<Course> courses, String courseName); //enter course name, prints out students names in that specific course
	public void viewCoursesOfStudent(ArrayList<Course> courses, String firstName, String lastName); //prints all the courses the student is taking
	public void sortCourses(ArrayList<Course> courses); //by current no. of students
	

}
