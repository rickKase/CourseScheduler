import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TrialDataGenerator {

	public static void generateStudentDataFiles(int dataSets) { // should be static
		File folder = new File(DataManager.inputLocation);
		for (int i = 0; i < dataSets; i++) {
			saveStudentData(generateStudentPreferences(),
					new File(folder, "stud_list" + i + ".json"));
		}
	}

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
			courses[i] = DataManager.getCourseNames()[shuffledList[i]];
		}
		return courses;
	}

	/**
	 * Generates a random data set of student course preferences and returns
	 * it as an Array of Student objects.
	 * @return
	 */
	private static Student[] generateStudentPreferences() { // should be static
		Student[] students = new Student[200]; // always generates 200 students
		Map<String, Integer> availableSpace = new HashMap<>();

		// for current tests, I am not going to more students require a course
		// than the max capacity. This keeps track of how many students require a course
		for (int i = 0; i < 40; i++)
			availableSpace.put(DataManager.getCourseNames()[i], 30);

		// Generates the student preferences
		for (int i = 0; i < students.length; i++) {
			students[i] = new Student();
			// randomly generate 8 courses from the list
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
			// splits the random courses up among the students priority queues
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
	private static void saveStudentData(Student[] students, File file) {  // Should be static
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(students));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
