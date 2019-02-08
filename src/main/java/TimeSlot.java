import java.util.HashMap;
import java.util.Map;

/**
 * TODO Write better comments
 * The thinking behind this code is that the amount of timeSlot objects
 * is standardized and immutable. An instance can be obtained from statically
 * managed set of all existing timeSlots. This also ensures that all TimeSlots
 * that contain the same data are also the same object.
 *
 * - Student Preferences shall contain a list of timeslots
 * - Course Heat Maps shall contain a Map<TimeSlot, double> where double is a number
 * between 0 and 1 signifying it's popularity.
 * - Finalized Schedules shall both refer to a TimeSlot object and tell the time slot
 * that they are being held at that time. (the second part isn't strictly necessary
 * but something tells me it will be useful for later parts of the code... ths will
 * be written later).
 */
public class TimeSlot {

	private final int day;
	private final int hour;
	private static Map<Integer, TimeSlot> timeSlots;

	{
		timeSlots = new HashMap<>();
		for (int day = 0; day < 2; day++) {
			for (int hour = 0; hour < 10; hour++) {
				timeSlots.put((day * 10) + hour, new TimeSlot(day, hour));
			}
		}
	}

	private TimeSlot(int day, int hour) {
		this.day = day;
		this.hour = hour;
	}

	public static TimeSlot getTimeSlot(int day, int hour) {
		return timeSlots.get(day * 10 + hour);
	}

	public static TimeSlot getTimeSlot(int id) {
		return  timeSlots.get(id);
	}

	public static TimeSlot[] getTimeSlotsOnDay(int day) {
		TimeSlot[] timeSlots = new TimeSlot[10];
		for (int i = 0; i < 10; i++)
			timeSlots[i] = getTimeSlot(day, i);
		return timeSlots;
	}

	public static TimeSlot[] getTimeSlotsAtHour(int hour) {
		TimeSlot[] timeSlots = new TimeSlot[2];
		for (int i = 0; i < 2; i++)
			timeSlots[i] = getTimeSlot(i, hour);
		return timeSlots;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof TimeSlot))
			return false;
		TimeSlot slot = (TimeSlot) obj;
		return slot.day == this.day && slot.hour == this.hour;
	}

	public String toString() {
		StringBuilder build = new StringBuilder();
		if (this.day == 0)
			build.append("Mon.");
		else
			build.append("Tues.");
		switch (this.hour) {
			case 0:
				build.append("7:30AM");
				break;
			case 1:
				build.append("8:30AM");
				break;
			case 2:
				build.append("9:30AM");
				break;
			case 3:
				build.append("10:30AM");
				break;
			case 4:
				build.append("11:30AM");
				break;
			case 5:
				build.append("12:30PM");
				break;
			case 6:
				build.append("1:30PM");
				break;
			case 7:
				build.append("2:30PM");
				break;
			case 8:
				build.append("3:30PM");
				break;
			case 9:
				build.append("4:30PM");
				break;
		}
		return  build.toString();
	}
}
