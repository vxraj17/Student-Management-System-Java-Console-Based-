import java.util.Objects;

public class Course {
    private String courseId;
    private String courseName;
    private int creditHours;

    public Course(String courseId, String courseName, int creditHours) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.creditHours = creditHours;
    }

    // Getters
    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCreditHours() {
        return creditHours;
    }

    // Setters (if needed)
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    @Override
    public String toString() {
        return "Course ID: " + courseId +
               ", Name: " + courseName +
               ", Credits: " + creditHours;
    }

    // Override equals and hashCode for proper comparison in collections
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseId, course.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId);
    }
}