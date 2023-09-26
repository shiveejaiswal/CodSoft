package JavaInternship;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Course {
    String code;
    String title;
    String description;
    int capacity;
    List<Student> registeredStudents = new ArrayList<>();
    public Course(String code, String title, String description, int capacity) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
    }
    // Add a student to the course
    public void registerStudent(Student student) {
        if (registeredStudents.size() < capacity) {
            registeredStudents.add(student);
        } else {
            System.out.println("Course is full. Cannot register.");
        }
    }
    // Remove a student from the course
    public void removeStudent(Student student) {
        registeredStudents.remove(student);
    }
    // Display course details
    public void displayCourseDetails() {
        System.out.println("Course Code: " + code);
        System.out.println("Course Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Capacity: " + capacity);
        System.out.println("Registered Students: " + registeredStudents.size());
    }
}
class Student {
    String studentID;
    String name;
    List<Course> registeredCourses = new ArrayList<>();

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }
    // Register for a course
    public void registerForCourse(Course course) {
        registeredCourses.add(course);
        course.registerStudent(this);
    }
    // Drop a course
    public void dropCourse(Course course) {
        registeredCourses.remove(course);
        course.removeStudent(this);
    }
    // Display student details
    public void displayStudentDetails() {
        System.out.println("Student ID: " + studentID);
        System.out.println("Student Name: " + name);
        System.out.println("Registered Courses:");
        for (Course course : registeredCourses) {
            System.out.println(course.code + " - " + course.title);
        }
    }
}
public class StudentCourseRegistrationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();
        List<Course> courses = new ArrayList<>();
        // Create some sample courses
        Course c1 = new Course("CSC101", "Introduction to Programming", "Basic programming concepts", 30);
        Course c2 = new Course("MAT202", "Calculus II", "Advanced calculus topics", 25);
        Course c3 = new Course("ENG103", "English Composition", "Writing and communication skills", 40);
        courses.add(c1);
        courses.add(c2);
        courses.add(c3);
        // Menu
        while (true) {
            System.out.println("\nStudent Course Registration System");
            System.out.println("1. Register Student");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Display Student Details");
            System.out.println("5. Display Course Details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline
            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String studentID = scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String studentName = scanner.nextLine();
                    Student student = new Student(studentID, studentName);
                    students.add(student);
                    System.out.println("Student registered successfully!");
                    break;
                case 2:
                    System.out.println("Available Courses:");
                    for (Course course : courses) {
                        course.displayCourseDetails();
                    }
                    System.out.print("Enter Student ID: ");
                    String stuID = scanner.nextLine();
                    System.out.print("Enter Course Code to Register: ");
                    String courseCode = scanner.nextLine();
                    Student stu = null;
                    Course courseToRegister = null;

                    // Find the student and course objects
                    for (Student s : students) {
                        if (s.studentID.equals(stuID)) {
                            stu = s;
                            break;
                        }
                    }
                    for (Course course : courses) {
                        if (course.code.equals(courseCode)) {
                            courseToRegister = course;
                            break;
                        }
                    }
                    if (stu != null && courseToRegister != null) {
                        stu.registerForCourse(courseToRegister);
                        System.out.println("Registration successful!");
                    } else {
                        System.out.println("Student or course not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    String studentIDToDrop = scanner.nextLine();
                    System.out.print("Enter Course Code to Drop: ");
                    String courseCodeToDrop = scanner.nextLine();
                    Student studentToDrop = null;
                    Course courseToDrop = null;

                    // Find the student and course objects
                    for (Student s : students) {
                        if (s.studentID.equals(studentIDToDrop)) {
                            studentToDrop = s;
                            break;
                        }
                    }
                    for (Course course : courses) {
                        if (course.code.equals(courseCodeToDrop)) {
                            courseToDrop = course;
                            break;
                        }
                    }
                    if (studentToDrop != null && courseToDrop != null) {
                        studentToDrop.dropCourse(courseToDrop);
                        System.out.println("Course dropped successfully!");
                    } else {
                        System.out.println("Student or course not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    String studentIDToDisplay = scanner.nextLine();
                    Student studentToDisplay = null;

                    // Find the student object
                    for (Student s : students) {
                        if (s.studentID.equals(studentIDToDisplay)) {
                            studentToDisplay = s;
                            break;
                        }
                    }
                    if (studentToDisplay != null) {
                        studentToDisplay.displayStudentDetails();
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter Course Code: ");
                    String courseCodeToDisplay = scanner.nextLine();
                    Course courseToDisplay = null;
                    // Find the course object
                    for (Course course : courses) {
                        if (course.code.equals(courseCodeToDisplay)) {
                            courseToDisplay = course;
                            break;
                        }
                    }
                    if (courseToDisplay != null) {
                        courseToDisplay.displayCourseDetails();
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 6:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}

