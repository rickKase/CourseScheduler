import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		DataManager dataMan = DataManager.getInstance();
		TheAlgorithm.createCourseRosters();
	}



	public static void printStudentSchedules() {
		System.out.println("Student Schedules:");
		for (Student student : DataManager.getInstance().getStudents()) {
			System.out.print(student.getId() + ": [");
			for (int i  = 0; i < student.getSchedule().length; i++) {
				System.out.print(student.getSchedule()[i].getName());
				if (i < student.getSchedule().length - 1)
					System.out.print(", ");
			}
			System.out.println("]");
		}
	}

	public static void printCourseEnrollment() {
		System.out.println("\n\nCourse Occupancies");
		for (Course course : DataManager.getInstance().getCourses()) {
			System.out.println(course.getName() + ": " + course.getStudentRoster().length);
		}
	}

	public static void printCourseRosters() {
		System.out.println("\n\nCourse Rosters");
		for (Course course : DataManager.getInstance().getCourses()) {
			System.out.print(course.getName() + ": [");
			for (int i  = 0; i < course.getStudentRoster().length; i++) {
				System.out.print(course.getStudentRoster()[i].getId());
				if (i < course.getStudentRoster().length - 1)
					System.out.print(", ");
			}
			System.out.println("]");
		}
	}
}
