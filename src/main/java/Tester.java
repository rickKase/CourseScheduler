import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

public class Tester {

	private static Course[] courseList;
	private static Student[] studentList;

	public static void main(String[] args) {
		courseList = generateCourses();
		studentList = loadStudentData(getStudentDataFile(0));


	}

	private static File getStudentDataFile(int i) {
		File folder = new File("data\\students");
		return new File(folder, "stud_list" + i + ".json");
	}

	private static void generateStudentDataFiles() {
		File folder = new File("data\\students");
		folder.mkdir();
		for (int i = 0; i < 10; i++) {
			saveStudentData(generateStudentPreferences(),
					new File(folder, "stud_list" + i + ".json"));
		}
	}

	private static Student[] loadStudentData(File file) {
		Student[] students = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			students = new Gson().fromJson(reader, Student[].class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return students;
	}

	private static void saveStudentData(Student[] students, File file) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(students));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Student[] generateStudentPreferences() {
		Student[] students = new Student[200];
		Map<Course, Integer> availableSpace = new HashMap<>();
		for (int i = 0; i < 40; i++)
			availableSpace.put(courseList[i], 30);

		for (int i = 0; i < students.length; i++) {
			students[i] = new Student();
			Course[] preferredCourses = generateRandomCourseList(8);
			int swapIndex = 8;
			while (availableSpace.get(preferredCourses[0]) == 0) {
				Course temp = preferredCourses[0];
				preferredCourses[0] = preferredCourses[swapIndex];
				preferredCourses[swapIndex] = temp;
				swapIndex++;
			}
			while (availableSpace.get(preferredCourses[1]) == 0) {
				Course temp = preferredCourses[1];
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
//		for (Map.Entry<Course, Integer> entry : availableSpace.entrySet()) {
//			System.out.println(entry.getKey() + ": " + entry.getValue());
//		}
		return students;
	}

	private static Course[] generateCourses() {
		String[] courseNames = getCourseNames();
		Course[] courses = new Course[courseNames.length];
		for (int i = 0; i < courseNames.length; i++) {
			Course course = new Course();
			course.setName(courseNames[i]);
			course.setMaxStudents(30);
			course.setMinStudents(15);
			courses[i] = course;
		}
		return courses;
	}

	private static String[] getCourseNames() {
		StringBuilder build = new StringBuilder();
		String[] names = new String[0];
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("data\\CourseNames.json")));
			String line;
			while ((line = reader.readLine()) != null)
				build.append(line);
			Gson gson = new Gson();
			names = gson.fromJson(build.toString(), String[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return names;
	}

	private static int[] generateRandomOrderedList(int size) {
		int[] shuffledList = IntStream.range(0, size).toArray();
		int index = shuffledList.length;
		Random rand = new Random();

		while (index > 1) {
			int pos = rand.nextInt(index--);
			int temp = shuffledList[pos];
			shuffledList[pos] = shuffledList[index];
			shuffledList[index] = temp;
		}

		return shuffledList;
	}

	private static Course[] generateRandomCourseList(int size) {
		int[] shuffledList = generateRandomOrderedList(40);
		Course[] courses = new Course[size];
		for (int i = 0; i < size; i++) {
			courses[i] = courseList[shuffledList[i]];
		}
		return courses;
	}


//		public static int[] generateRandomCourseSizes(int numberOfCourses, int totalSeats) {
//		double[] randomValues = new double[numberOfCourses];
//		int[] seatDistribution = new int[numberOfCourses];
//		double sum = 0;
//		for (int i = 0; i < numberOfCourses; i++) {
//			randomValues[i] = Math.random();
//			sum += randomValues[i];
//		}
//		for (int i = 0; i < numberOfCourses; i++) {
//			randomValues[i] = randomValues[i] / sum * totalSeats;
//			seatDistribution[i] = (int) randomValues[i] + 1;
//		}
//		return seatDistribution;
//	}

}
