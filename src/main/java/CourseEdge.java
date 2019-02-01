public class CourseEdge {

	private boolean time, space;

	public CourseEdge(boolean time, boolean space) {
		this.time = time;
		this.space = space;
	}

	public boolean isTime() {
		return time;
	}

	public void setTime(boolean time) {
		this.time = time;
	}

	public boolean isSpace() {
		return space;
	}

	public void setSpace(boolean space) {
		this.space = space;
	}

}
