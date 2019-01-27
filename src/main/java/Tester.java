import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

public class Tester {

	public static void main(String[] args) {
		DataManager dataMan = DataManager.getInstance();

//		dataMan.generateStudentDataFiles(10);

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
