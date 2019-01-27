public class Main {

	public static void main(String[] args) {
		DataManager dataMan = DataManager.getInstance();
		TheAlgorithm.createCourseRosters();


		for (Student student : dataMan.getStudents()) {
			System.out.print("Student Schedules:\n[");
			for (int i  = 0; i < student.getSchedule().length; i++) {
				System.out.print(student.getSchedule()[i].getName());
				System.out.print(", ");
			}
			System.out.println("]");
		}

		System.out.println("\n\nCourse Occupancies");

		for (Course course : dataMan.getCourses()) {
			System.out.println(course.getName() + ": " + course.getStudentRoster().length);
		}
	}

}
