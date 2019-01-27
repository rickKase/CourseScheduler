public class Main {

	public static void main(String[] args) {
		DataManager dataMan = DataManager.getInstance();

		System.out.println("Course List:\n_________________");
		for (Course course : dataMan.getCourses()) {
			System.out.println(course.getName());
		}

		System.out.println();
		for (Student student : dataMan.getStudents()) {
			System.out.println(student);
			System.out.println();
		}
	}

}
