import java.util.ArrayList;

public class Course implements Comparable<Course>, java.io.Serializable{

	private String courseName;
	private String courseID;
	private int maxStudents;
	private int currentStudents;
	private ArrayList<String> studentNames = new ArrayList<String> ();
	private String instructor;
	private String sectionNum;
	private String location;
	
	public Course() {}
	
	public Course(String [] courseDataList) {
		
		this.courseName = courseDataList[0];
		this.courseID = courseDataList[1];
		this.maxStudents = Integer.parseInt(courseDataList[2]);
		this.currentStudents = Integer.parseInt(courseDataList[3]);
		this.instructor = courseDataList[5];
		this.sectionNum = courseDataList[6];
		this.location = courseDataList[7];
		
	}

	public void addStudent(String firstName, String lastName) {
		
		this.studentNames.add(firstName + " " + lastName);
	}
	
	public void addStudent(String fullName) {
		
		this.studentNames.add(fullName);
	}
	
	public boolean removeStudent(String firstName, String lastName) {
		
		for (int i = 0; i < studentNames.size(); i++) 
			
			if (studentNames.get(i).equals(firstName + " " + lastName)) {
				
				this.studentNames.remove(i);
				return true;
			}
		return false;
	}
	
	public boolean removeStudent(String fullName) {
		
		for (int i = 0; i < studentNames.size(); i++) 
			
			if (studentNames.get(i).equals(fullName)) {
				
				this.studentNames.remove(i);
				return true;
			}
		return false;
	}
	
	public ArrayList<String> getStudentNames()	{
		
		return this.studentNames;
	}
	
	public void setCourseName(String name) {
		
		this.courseName = name;
	}
	
	public String getCourseName() {
		
		return this.courseName;
	}
	
	public void setCourseID(String id) {
		
		this.courseID = id;
	}
	
	public String getCourseID() {
		
		return this.courseID;	
	}
	
	public void setMaxStudents(String num) {
		
		this.maxStudents = Integer.parseInt(num);
	}
	
	public void setMaxStudents(int num) {
		
		this.maxStudents = num;
	}
	
	public int getMaxStudents()	{
		
		return this.maxStudents;
	}
	
	public void setCurrentStudents(String num) {
		
		this.currentStudents = Integer.parseInt(num);
	}
	
	public void setCurrentStudents(int num) {
		
		this.currentStudents = num;
	}
	
	public int getCurrentStudents()	{
		
		return this.currentStudents;
	}
	
	public void setInstructor(String name) {
		
		this.instructor = name;
	}
	
	public String getInstructor()	{
		
		return this.instructor;
	}
	
	public void setSectionNum(String section) {
		
		this.sectionNum = section;
	}
	
	public String getSectionNum()	{
		
		return this.sectionNum;
	}
	
	public void setLocation(String loc) {
		
		this.location = loc;
	}
	
	public String getLocation()	{
		
		return this.location;
	}

	@Override
	public int compareTo(Course o) {
		
		return this.getCurrentStudents() > o.getCurrentStudents() ? 1 : -1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
