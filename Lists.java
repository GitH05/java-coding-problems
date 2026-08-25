import java.util.*;

public class Lists {
	public static void main(String[] args) {

		System.out.println("List");
		List<Integer> arr = new ArrayList<>();
		for (int i = 0; i <= 5; i++) {
			arr.add((int) (i * 2));
		}
		System.out.println(arr);

	}
}