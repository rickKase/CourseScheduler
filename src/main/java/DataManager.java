import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DataManager {

	private static String inputLocation = "data\\input";
	private static String outputLocation = "data\\output";
	private static String courseNameLocation = "data\\CourseNames.json";

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


	/* Generate New Random Data */
	/**
	 * Generates a list of random courses. The number of courses
	 * generated is determined by the parameter.
	 * @param size
	 * @return
	 */
	private static String[] generateShuffledCourseList(int size) {
		int[] shuffledList = ToolBox.fisherYatesShuffle(40); // 40 is the number of courses offered
		String[] courses = new String[size];
		for (int i = 0; i < size; i++) {
			courses[i] = courseNameList[shuffledList[i]];
		}
		return courses;
	}

	/**
	 * Generates a random data set of student course preferences and returns
	 * it as an Array of Student objects.
	 * @return
	 */
	private Student[] generateStudentPreferences() { // should be static
		Student[] students = new Student[200]; // always generates 200 students
		Map<String, Integer> availableSpace = new HashMap<>();

		// for current tests, I am not going to more students require a course
		// than the max capacity. This keeps track of how many students require a course
		for (int i = 0; i < 40; i++)
			availableSpace.put(courseNameList[i], 30);

		// Generates the student preferences
		for (int i = 0; i < students.length; i++) {
			students[i] = new Student();
			// randomly generate 8 courses from the list TODO switch this to String[]
			String[] preferredCourses = generateShuffledCourseList(8);
			// ensure students only put a class a required if the course is not full.
			int swapIndex = 8;
			while (availableSpace.get(preferredCourses[0]) == 0) {
				String temp = preferredCourses[0];
				preferredCourses[0] = preferredCourses[swapIndex];
				preferredCourses[swapIndex] = temp;
				swapIndex++;
			}
			while (availableSpace.get(preferredCourses[1]) == 0) {
				String temp = preferredCourses[1];
				preferredCourses[1] = preferredCourses[swapIndex];
				preferredCourses[swapIndex] = temp;
				swapIndex++;
			}

			students[i].setReqCourses(Arrays.copyOfRange(preferredCourses, 0, 2));
			availableSpace.put(preferredCourses[0], availableSpace.get(preferredCourses[0]) - 1);
			availableSpace.put(preferredCourses[1], availableSpace.get(preferredCourses[1]) - 1);
			students[i].setDesiredCourses(Arrays.copyOfRange(preferredCourses, 2, 5));
			students[i].setBackupCourses(Arrays.copyOfRange(preferredCourses, 5, 8));
		}
		return students;
	}

	/**
	 * Saves the Student Data in the first parameter to the File of the second
	 * parameter. This method is mostly designed to create a new
	 * @param students
	 * @param file
	 */
	private void saveStudentData(Student[] students, File file) {  // Should be static
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(students));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void generateStudentDataFiles(int dataSets) { // should be static
		File folder = new File(inputLocation);
		for (int i = 0; i < dataSets; i++) {
			saveStudentData(generateStudentPreferences(),
					new File(folder, "stud_list" + i + ".json"));
		}
	}
	/* Generate New Random Data */
}
