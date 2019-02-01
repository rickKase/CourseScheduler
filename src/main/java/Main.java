public class Main {

	public static void main(String[] args) {
		DataManager dataMan = DataManager.getInstance();
		TheAlgorithm.createCourseRosters();

		CourseGraph courseGraph = new CourseGraph(dataMan.getCourses());

		Course[] courses = dataMan.getCourses();
		for (int i = 0; i < courses.length; i++) {
			System.out.print(courses[i].getName() + ": ");
			System.out.print("[");
			Course[] conflictedCourses = courseGraph.getNonconflictedCourses(courses[i]);
			for (int j = 0; j < conflictedCourses.length; j++) {
				System.out.print(conflictedCourses[j].getName());
				if (j < conflictedCourses.length - 1) {
					System.out.print(", ");
				}
			}
			System.out.println("]");
		}
		System.out.println("\n");
		printCourseRosters();
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
