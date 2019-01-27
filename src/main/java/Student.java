public class Student {

	private String[] reqCourses;
	private String[] desiredCourses;
	private String[] backupCourses;

	public Student() {

	}

	public String[] getReqCourses() {
		return reqCourses;
	}

	public void setReqCourses(String[] reqCourses) {
		this.reqCourses = reqCourses;
	}

	public String[] getDesiredCourses() {
		return desiredCourses;
	}

	public void setDesiredCourses(String[] desiredCourses) {
		this.desiredCourses = desiredCourses;
	}

	public String[] getBackupCourses() {
		return backupCourses;
	}

	public void setBackupCourses(String[] backupCourses) {
		this.backupCourses = backupCourses;
	}

	public String toString() {
		StringBuilder build = new StringBuilder();
		build.append("Required: [");
		for (int i = 0; i < reqCourses.length; i++) {
			build.append(reqCourses[i]);
			if (i != reqCourses.length - 1)
				build.append(", ");
		}
		build.append("]\nDesired: [");
		for (int i = 0; i < desiredCourses.length; i++) {
			build.append(desiredCourses[i]);
			if (i != desiredCourses.length - 1)
				build.append(", ");
		}
		build.append("]\nBackup: [");
		for (int i = 0; i < backupCourses.length; i++) {
			build.append(backupCourses[i]);
			if (i != backupCourses.length - 1)
				build.append(", ");
		}
		build.append("]");
		return build.toString();
	}
}
