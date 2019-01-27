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

	private Course[] courseList;
	private Student[] studentList;
	private static String[] courseNameList;

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


	/* instance variables, getters, and setters */
	public Course[] getCourses() {
		return courseList;
	}

	public Student[] getStudents() {
		return studentList;
	}

	public static String[] getCourseNames() {
		return courseNameList;
	}
	/* instance variables, getters, and setters */


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
		courseList = new Course[courseNameList.length];
		for (int i = 0; i < courseNameList.length; i++) {
			Course course = new Course();
			course.setName(courseNameList[i]);
			course.setMaxStudents(30);
			course.setMinStudents(15);
			courseList[i] = course;
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
