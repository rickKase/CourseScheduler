import java.util.ArrayList;
import java.util.List;

public class Course {

	private String name;
	private int minStudents;
	private int maxStudents;

	private List<Student> studentRoster;

	public Course() {
		studentRoster = new ArrayList<>();
	}


	/* Student Roster Functions */
	public Student[] getStudentRoster() {
		return studentRoster.toArray(new Student[0]);
	}

	public void addStudentToRoster(Student student) {
		if (studentRoster.contains(student))
			return;
		studentRoster.add(student);
		if (!student.takesCourse(this))
			student.addCourseToSchedule(this);
	}

	public boolean isStudentEnrolled(Student student) {
		return studentRoster.contains(student);
	}
	/* Student Roster Functions */


	/* Standard Getters & Setters */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinStudents() {
		return minStudents;
	}

	public void setMinStudents(int minStudents) {
		this.minStudents = minStudents;
	}

	public int getMaxStudents() {
		return maxStudents;
	}

	public void setMaxStudents(int maxStudents) {
		this.maxStudents = maxStudents;
	}
	/* Standard Getters & Setters */
}
