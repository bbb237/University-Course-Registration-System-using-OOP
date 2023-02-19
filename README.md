# University-Course-Registration-System-using-OOP

## Required information about each course: 

Course name, Course id, Maximum number of students registered in the course<br>
Current number of registered students, A list of names of students being registered in the given course <br>
Course instructor, Course section number, course location.     
                                          
**IMPPORTANT!! CRS only reads the course information data from a csv file.**

One is already included if you don't want to make your own (*MyUniversityCourses.csv*). 

## Outline of University Course Registration System:

CRS has 2 types of users: `Admin` and `Student`, which both inherit from the `User` class. They each have their own respective<br> 
interface with the methods’ signatures.

**This system only allows for 1 `Admin` with username: Admin and password: Admin001**

If the program is being run for the first time the user will be asked to enter the path to the courses csv file. If not, then it will deserialize the data from the last time it was run.

The `User` logs in and is asked to enter credentials. `User` has 10 attempts before the program closes. If `Admin` credentials match, an admin object 
is created, giving access to the methods and displaying the menu choice. If `Student` credentials match, a student object is added to the 
students ArrayList and displays the menu options for the student. Once the `User` quits, the program will serialize the courses ArrayList and students 
ArrayList and contain their respective objects.

The `Admin` has access to the following:

Courses Management
  1. Create a new course
  2. Delete a course
  3. Edit a course (this will allow the admin to edit any information on the course except for course ID and name)
  4. Display information for a given course (by course ID)
  5. Register a student (this option will allow the admin to add a student without assigning to a course)

Reports
<ol type="A">
  <li>View all courses (for every course the admin can see the list of course name, course id, number of students registered, and the maximum number of
		 students allowed to be registered)</li>
  <li>View all courses that are FULL (reached the maximum number of students)</li>
  <li>Write to a file the list of course that are Full</li>
  <li>View the names of the students being registered in a specific course</li>
  <li>View the list of courses that a given student is being registered on (given a student first name and last name the system shall display all the 
  courses that students is being registered in)</li>
  <li>Sort (courses based on the current number of student registers)</li>
</ol>

Exit(Exit)

The `Student` has access to the following:

  1. View all courses
  2. View all courses that are not FULL
  3. Register on a course (for this option the student must enter the course name, section, and student full name, the name will be added to the appropriate course) 
  4. Withdraw from a course(for this option the student will be asked to enter their student name and the course, then the name of the student will be taken off from the given course’ list)
  5. View all courses that the current student is being registered in
  6. Exit

Thank you for reading this and using my Course Registration System :]
