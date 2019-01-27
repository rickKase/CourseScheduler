public class TheAlgorithm {

	public static void createCourseRosters() {
		DataManager dataMan = DataManager.getInstance();
		Student[] students = dataMan.getStudents();

		// not checking for course reaching max occupancy because it is guaranteed
		// not to happen in with the current data sets. 1/27
		for (Student student : students)
			for (String reqCourse : student.getReqCourses())
				dataMan.getCourseWithName(reqCourse).addStudentToRoster(student);
		int backupIndex = 0;
		for (Student student : students) {

			// assign students to their choice courses
			for (String desiredCourse : student.getDesiredCourses()) {
				if (!dataMan.getCourseWithName(desiredCourse).isFull()) {
					dataMan.getCourseWithName(desiredCourse).addStudentToRoster(student);

				// if their desired courses aren't available then assign them to their backups
				} else {
					while (backupIndex < student.getBackupCourses().length) {
						if (!dataMan.getCourseWithName(student.getBackupCourses()[backupIndex])
								.isFull()) {
							dataMan.getCourseWithName(student.getBackupCourses()[backupIndex])
									.addStudentToRoster(student);
							backupIndex++;
							break;
						}
						backupIndex++;
					}
				}
			}
		}
	}
}
