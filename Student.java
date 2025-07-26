import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student {
    private String studentId;
    private String name;
    private int age;
    private List<Course> enrolledCourses;

    public Student(String studentId, String name, int age) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.enrolledCourses = new ArrayList<>();
    }

    // Getters
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    // Setters (if needed, but for ID, usually set in constructor)
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Method to enroll in a course
    public void enrollCourse(Course course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
        } else {
            System.out.println(this.name + " is already enrolled in " + course.getCourseName());
        }
    }

    // Method to unenroll from a course
    public void unenrollCourse(Course course) {
        if (enrolledCourses.contains(course)) {
            enrolledCourses.remove(course);
            System.out.println(this.name + " unenrolled from " + course.getCourseName());
        } else {
            System.out.println(this.name + " is not enrolled in " + course.getCourseName());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student ID: ").append(studentId).append("\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Age: ").append(age).append("\n");
        sb.append("Enrolled Courses: ");
        if (enrolledCourses.isEmpty()) {
            sb.append("None\n");
        } else {
            sb.append("\n");
            for (Course course : enrolledCourses) {
                sb.append("  - ").append(course.getCourseName()).append(" (").append(course.getCourseId()).append(")\n");
            }
        }
        return sb.toString();
    }

    // Override equals and hashCode for proper comparison in collections
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
}