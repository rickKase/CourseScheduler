import java.util.Random;
import java.util.stream.IntStream;

public class ToolBox {

	/**
	 * Shuffles a list from 0 to the size provide and returns
	 * the list of randomly ordered numbers.
	 * @param size
	 * @return
	 */
	public static int[] fisherYatesShuffle(int size) {
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
}
