import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	public boolean isFull() {
		return maxStudents == studentRoster.size();
	}
	/* Student Roster Functions */


	/* Core Methods */
	public boolean studentRosterOverlapsWith(Course course) {
		Set<Student> studentSet = new HashSet<>(studentRoster);
		for (Student student : course.getStudentRoster())
			if (!studentSet.add(student))
				return true;
		return false;
	}
	/* Core Methods */


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
