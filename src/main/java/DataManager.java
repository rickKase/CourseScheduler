import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DataManager {

	public static final String inputLocation = "data\\input";
	public static final String outputLocation = "data\\output";
	public static final String courseNameLocation = "data\\CourseNames.json";

	private int activeDataSet;

	private Student[] studentList;
	private static String[] courseNameList;
	private static Map<String, Course> courses;

	/* Singleton */
	private static DataManager instance = new DataManager();

	private DataManager() {
		loadCourseNamesFromFile();
		loadCourseList();
		loadDataSet(0);
	}

	public static DataManager getInstance() {
		return instance;
	}
	/* Singleton */


	/* Data Access Methods */
	public Course[] getCourses() {
		return courses.values().toArray(new Course[0]);
	}

	public Student[] getStudents() {
		return studentList;
	}

	public static String[] getCourseNames() {
		return courseNameList;
	}

	public Course getCourseWithName(String courseName) {
		for (Course course : courses.values())
			if (course.getName().equals(courseName))
				return course;
		return null;
	}
	/* Data Access Methods */


	/* Loading Data and Saving Data */
	private File getInputFile() {
		File folder = new File(inputLocation);
		return new File(folder, "stud_list" + activeDataSet + ".json");
	}

	private File getOutputFile() {
		File folder = new File(outputLocation);
		return new File(folder, "stud_list" + activeDataSet + ".json");
	}

	/**
	 * Saves the computed output from the algorithm to the output file with
	 * the same index.
	 */
	public void saveOutput() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(getOutputFile()));
			writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(studentList));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * There are 10 data sets indexed from 0 to 9. This method will load
	 * the selected data set into memory.
	 */
	public void loadDataSet(int activeDataSet) {
		try {
			this.activeDataSet = activeDataSet;
			BufferedReader reader = new BufferedReader(new FileReader(getInputFile()));
			studentList = new Gson().fromJson(reader, Student[].class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This loads the course names and creates the Course objects
	 * used in the algorithm.
	 */
	private void loadCourseList() {
		courses = new HashMap<>();
		for (String courseName : courseNameList) {
			Course course = new Course();
			course.setName(courseName);
			course.setMaxStudents(30);
			course.setMinStudents(15);
			courses.put(courseName, course);
		}
	}

	/**
	 * This loads a list of the names of Courses that Students can take.
	 * @return
	 */
	private static void loadCourseNamesFromFile() {
		StringBuilder build = new StringBuilder();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(courseNameLocation)));
			String line;
			while ((line = reader.readLine()) != null)
				build.append(line);
			Gson gson = new Gson();
			courseNameList = gson.fromJson(build.toString(), String[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/* Loading Data and Saving Data */
}
