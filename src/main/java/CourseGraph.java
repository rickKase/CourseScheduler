import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.ArrayList;
import java.util.List;

/*
 * Could optimize by:
 *   - Designing the courseGraph to be symmetric across x = y
 */
public class CourseGraph {

	private BiMap<Course, Integer> indexMap = HashBiMap.create();
	private CourseEdge[][] courseEdges;

	public CourseGraph(Course[] courses) {
		for (int i = 0; i < courses.length; i++) {
			indexMap.put(courses[i], i);
		}
		courseEdges = new CourseEdge[courses.length][courses.length];
		for (int i = 0; i < courses.length; i++)
			for (int j = 0; j < courses.length; j++)
				courseEdges[i][j] = new CourseEdge(
						!courses[i].studentRosterOverlapsWith(courses[j]), true);

	}

	public Course[] getNonconflictingCourses(Course course) {
		List<Course> courses = new ArrayList<>();
		for (int i = 0; i < courseEdges.length; i++) {
			if (courseEdges[indexMap.get(course)][i].isTime())
				courses.add(indexMap.inverse().get(i));
		}
		return courses.toArray(new Course[0]);
	}
	
}
