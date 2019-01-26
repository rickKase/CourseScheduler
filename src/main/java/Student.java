public class Student {

	private Course[] reqCourses;
	private Course[] desiredCourses;
	private Course[] backupCourses;

	public Student() {

	}

	public Course[] getReqCourses() {
		return reqCourses;
	}

	public void setReqCourses(Course[] reqCourses) {
		this.reqCourses = reqCourses;
	}

	public Course[] getDesiredCourses() {
		return desiredCourses;
	}

	public void setDesiredCourses(Course[] desiredCourses) {
		this.desiredCourses = desiredCourses;
	}

	public Course[] getBackupCourses() {
		return backupCourses;
	}

	public void setBackupCourses(Course[] backupCourses) {
		this.backupCourses = backupCourses;
	}

	public String toString() {
		StringBuilder build = new StringBuilder();
		build.append("Required: [");
		for (int i = 0; i < reqCourses.length; i++) {
			build.append(reqCourses[i].getName());
			if (i != reqCourses.length - 1)
				build.append(", ");
		}
		build.append("]\n Desired: [");
		for (int i = 0; i < desiredCourses.length; i++) {
			build.append(desiredCourses[i].getName());
			if (i != desiredCourses.length - 1)
				build.append(", ");
		}
		build.append("]\n Backup: [");
		for (int i = 0; i < backupCourses.length; i++) {
			build.append(backupCourses[i].getName());
			if (i != backupCourses.length - 1)
				build.append(", ");
		}
		build.append("]");
		return build.toString();
	}
}
