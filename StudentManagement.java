import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentManagement {
    private Map<String, Student> students; // Store students by ID for quick lookup
    private Map<String, Course> courses;   // Store courses by ID for quick lookup

    public StudentManagement() {
        this.students = new HashMap<>();
        this.courses = new HashMap<>();
        // Add some dummy data for testing
        addDummyData();
    }

    private void addDummyData() {
        // Add some courses
        addCourse(new Course("C001", "Introduction to Programming", 3));
        addCourse(new Course("C002", "Data Structures", 4));
        addCourse(new Course("C003", "Algorithms", 4));
        addCourse(new Course("C004", "Database Systems", 3));

        // Add some students
        addStudent(new Student("S001", "Alice Smith", 20));
        addStudent(new Student("S002", "Bob Johnson", 21));
        addStudent(new Student("S003", "Charlie Brown", 19));

        // Enroll students in courses
        enrollStudentInCourse("S001", "C001");
        enrollStudentInCourse("S001", "C002");
        enrollStudentInCourse("S002", "C001");
        enrollStudentInCourse("S003", "C004");
    }

    // --- Student Management Methods ---

    public void addStudent(Student student) {
        if (students.containsKey(student.getStudentId())) {
            System.out.println("Error: Student with ID " + student.getStudentId() + " already exists.");
        } else {
            students.put(student.getStudentId(), student);
            System.out.println("Student " + student.getName() + " added successfully.");
        }
    }

    public Student getStudent(String studentId) {
        return students.get(studentId);
    }

    public void updateStudent(String studentId, String newName, int newAge) {
        Student student = students.get(studentId);
        if (student != null) {
            student.setName(newName);
            student.setAge(newAge);
            System.out.println("Student " + studentId + " updated successfully.");
        } else {
            System.out.println("Error: Student with ID " + studentId + " not found.");
        }
    }

    public void deleteStudent(String studentId) {
        if (students.containsKey(studentId)) {
            Student removedStudent = students.remove(studentId);
            System.out.println("Student " + removedStudent.getName() + " deleted successfully.");
        } else {
            System.out.println("Error: Student with ID " + studentId + " not found.");
        }
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    // --- Course Management Methods ---

    public void addCourse(Course course) {
        if (courses.containsKey(course.getCourseId())) {
            System.out.println("Error: Course with ID " + course.getCourseId() + " already exists.");
        } else {
            courses.put(course.getCourseId(), course);
            System.out.println("Course " + course.getCourseName() + " added successfully.");
        }
    }

    public Course getCourse(String courseId) {
        return courses.get(courseId);
    }

    public void updateCourse(String courseId, String newName, int newCreditHours) {
        Course course = courses.get(courseId);
        if (course != null) {
            course.setCourseName(newName);
            course.setCreditHours(newCreditHours);
            System.out.println("Course " + courseId + " updated successfully.");
        } else {
            System.out.println("Error: Course with ID " + courseId + " not found.");
        }
    }

    public void deleteCourse(String courseId) {
        if (courses.containsKey(courseId)) {
            // Before deleting a course, ensure no student is enrolled in it
            boolean isEnrolled = false;
            for (Student student : students.values()) {
                if (student.getEnrolledCourses().contains(courses.get(courseId))) {
                    isEnrolled = true;
                    System.out.println("Error: Cannot delete course " + courseId + ". Student " + student.getName() + " is still enrolled.");
                    break;
                }
            }
            if (!isEnrolled) {
                Course removedCourse = courses.remove(courseId);
                System.out.println("Course " + removedCourse.getCourseName() + " deleted successfully.");
            }
        } else {
            System.out.println("Error: Course with ID " + courseId + " not found.");
        }
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses.values());
    }

    // --- Enrollment Management Methods ---

    public void enrollStudentInCourse(String studentId, String courseId) {
        Student student = students.get(studentId);
        Course course = courses.get(courseId);

        if (student == null) {
            System.out.println("Error: Student with ID " + studentId + " not found.");
            return;
        }
        if (course == null) {
            System.out.println("Error: Course with ID " + courseId + " not found.");
            return;
        }

        student.enrollCourse(course);
        System.out.println("Student " + student.getName() + " enrolled in " + course.getCourseName() + " successfully.");
    }

    public void unenrollStudentFromCourse(String studentId, String courseId) {
        Student student = students.get(studentId);
        Course course = courses.get(courseId);

        if (student == null) {
            System.out.println("Error: Student with ID " + studentId + " not found.");
            return;
        }
        if (course == null) {
            System.out.println("Error: Course with ID " + courseId + " not found.");
            return;
        }

        student.unenrollCourse(course);
    }
}