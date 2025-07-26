import java.util.List;
import java.util.Scanner;

public class StudentManagementSystem {
    private StudentManagement manager;
    private Scanner scanner;

    public StudentManagementSystem() {
        this.manager = new StudentManagement();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        StudentManagementSystem app = new StudentManagementSystem();
        app.run();
    }

    public void run() {
        int choice;
        do {
            displayMainMenu();
            choice = ValidationUtils.getIntInput(scanner, "Enter your choice: ", 1, 6);

            switch (choice) {
                case 1:
                    manageStudents();
                    break;
                case 2:
                    manageCourses();
                    break;
                case 3:
                    manageEnrollment();
                    break;
                case 4:
                    displayAllStudentsWithCourses();
                    break;
                case 5:
                    displayAllCourses();
                    break;
                case 6:
                    System.out.println("Exiting Student Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println("\n------------------------------------\n");
        } while (choice != 6);

        scanner.close();
    }

    private void displayMainMenu() {
        System.out.println("--- Student Management System ---");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Manage Enrollment");
        System.out.println("4. View All Students (with courses)");
        System.out.println("5. View All Courses");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    private void manageStudents() {
        int choice;
        do {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Student Details");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. View All Students");
            System.out.println("6. Back to Main Menu");
            choice = ValidationUtils.getIntInput(scanner, "Enter your choice: ", 1, 6);

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudent();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    displayAllStudents();
                    break;
                case 6:
                    System.out.println("Returning to Main Menu...");
                    break;
            }
        } while (choice != 6);
    }

    private void addStudent() {
        System.out.println("\n--- Add New Student ---");
        String id = ValidationUtils.getNonEmptyStringInput(scanner, "Enter Student ID: ");
        String name = ValidationUtils.getNonEmptyStringInput(scanner, "Enter Student Name: ");
        int age = ValidationUtils.getIntInput(scanner, "Enter Student Age: ", 16, 99);

        manager.addStudent(new Student(id, name, age));
    }

    private void viewStudent() {
        System.out.println("\n--- View Student Details ---");
        String id = ValidationUtils.getNonEmptyStringInput(scanner, "Enter Student ID to view: ");
        Student student = manager.getStudent(id);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    private void updateStudent() {
        System.out.println("\n--- Update Student ---");
        String id = ValidationUtils.getNonEmptyStringInput(scanner, "Enter Student ID to update: ");
        Student existingStudent = manager.getStudent(id);
        if (existingStudent != null) {
            String newName = ValidationUtils.getNonEmptyStringInput(scanner, "Enter New Name (current: " + existingStudent.getName() + "): ");
            int newAge = ValidationUtils.getIntInput(scanner, "Enter New Age (current: " + existingStudent.getAge() + "): ", 16, 99);
            manager.updateStudent(id, newName, newAge);
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    private void deleteStudent() {
        System.out.println("\n--- Delete Student ---");
        String id = ValidationUtils.getNonEmptyStringInput(scanner, "Enter Student ID to delete: ");
        manager.deleteStudent(id);
    }

    private void displayAllStudents() {
        System.out.println("\n--- All Students ---");
        List<Student> students = manager.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students registered yet.");
        } else {
            for (Student student : students) {
                System.out.println("ID: " + student.getStudentId() + ", Name: " + student.getName() + ", Age: " + student.getAge());
            }
        }
    }

    private void displayAllStudentsWithCourses() {
        System.out.println("\n--- All Students and Their Enrolled Courses ---");
        List<Student> students = manager.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students registered yet.");
        } else {
            for (Student student : students) {
                System.out.println(student); // Uses Student's toString() which includes courses
                System.out.println("--------------------");
            }
        }
    }


    private void manageCourses() {
        int choice;
        do {
            System.out.println("\n--- Course Management ---");
            System.out.println("1. Add Course");
            System.out.println("2. View Course Details");
            System.out.println("3. Update Course");
            System.out.println("4. Delete Course");
            System.out.println("5. View All Courses");
            System.out.println("6. Back to Main Menu");
            choice = ValidationUtils.getIntInput(scanner, "Enter your choice: ", 1, 6);

            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    viewCourse();
                    break;
                case 3:
                    updateCourse();
                    break;
                case 4:
                    deleteCourse();
                    break;
                case 5:
                    displayAllCourses();
                    break;
                case 6:
                    System.out.println("Returning to Main Menu...");
                    break;
            }
        } while (choice != 6);
    }

    private void addCourse() {
        System.out.println("\n--- Add New Course ---");
        String id = ValidationUtils.getNonEmptyStringInput(scanner, "Enter Course ID: ");
        String name = ValidationUtils.getNonEmptyStringInput(scanner, "Enter Course Name: ");
        int credits = ValidationUtils.getPositiveIntInput(scanner, "Enter Credit Hours: ");

        manager.addCourse(new Course(id, name, credits));
    }

    private void viewCourse() {
        System.out.println("\n--- View Course Details ---");
        String id = ValidationUtils.getNonEmptyStringInput(scanner, "Enter Course ID to view: ");
        Course course = manager.getCourse(id);
        if (course != null) {
            System.out.println(course);
        } else {
            System.out.println("Course with ID " + id + " not found.");
        }
    }

    private void updateCourse() {
        System.out.println("\n--- Update Course ---");
        String id = ValidationUtils.getNonEmptyStringInput(scanner, "Enter Course ID to update: ");
        Course existingCourse = manager.getCourse(id);
        if (existingCourse != null) {
            String newName = ValidationUtils.getNonEmptyStringInput(scanner, "Enter New Name (current: " + existingCourse.getCourseName() + "): ");
            int newCredits = ValidationUtils.getPositiveIntInput(scanner, "Enter New Credit Hours (current: " + existingCourse.getCreditHours() + "): ");
            manager.updateCourse(id, newName, newCredits);
        } else {
            System.out.println("Course with ID " + id + " not found.");
        }
    }

    private void deleteCourse() {
        System.out.println("\n--- Delete Course ---");
        String id = ValidationUtils.getNonEmptyStringInput(scanner, "Enter Course ID to delete: ");
        manager.deleteCourse(id);
    }

    private void displayAllCourses() {
        System.out.println("\n--- All Courses ---");
        List<Course> courses = manager.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses available yet.");
        } else {
            for (Course course : courses) {
                System.out.println(course);
            }
        }
    }

    private void manageEnrollment() {
        int choice;
        do {
            System.out.println("\n--- Enrollment Management ---");
            System.out.println("1. Enroll Student in Course");
            System.out.println("2. Unenroll Student from Course");
            System.out.println("3. Back to Main Menu");
            choice = ValidationUtils.getIntInput(scanner, "Enter your choice: ", 1, 3);

            switch (choice) {
                case 1:
                    enrollStudent();
                    break;
                case 2:
                    unenrollStudent();
                    break;
                case 3:
                    System.out.println("Returning to Main Menu...");
                    break;
            }
        } while (choice != 3);
    }

    private void enrollStudent() {
        System.out.println("\n--- Enroll Student ---");
        String studentId = ValidationUtils.getNonEmptyStringInput(scanner, "Enter Student ID to enroll: ");
        String courseId = ValidationUtils.getNonEmptyStringInput(scanner, "Enter Course ID to enroll in: ");
        manager.enrollStudentInCourse(studentId, courseId);
    }

    private void unenrollStudent() {
        System.out.println("\n--- Unenroll Student ---");
        String studentId = ValidationUtils.getNonEmptyStringInput(scanner, "Enter Student ID to unenroll: ");
        String courseId = ValidationUtils.getNonEmptyStringInput(scanner, "Enter Course ID to unenroll from: ");
        manager.unenrollStudentFromCourse(studentId, courseId);
    }
}