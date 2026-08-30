import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Time Complexity: O(n) - We go through each element once to group and count.
// Space Complexity: O(n) - The Map stores each unique element and its frequency.

public class CountFrequencyOfElement {

    public static void main(String[] args) {

        List<Integer> elements = Arrays.asList(10, 45, 10, 8, 8, 10, 45, 8, 8, 10);
        // Stores all the numbers we want to count.

        Map<Integer, Long> frequency = elements.stream()
                // Converts the list into a stream so we can process the numbers.

                .collect(Collectors.groupingBy(
                        elem -> elem, // Uses each number as the key.
                        Collectors.counting() // Counts how many times each number appears.
                ));

        System.out.println("Frequency of each element: " + frequency);
        // Prints each number with its frequency.
        
    }
}
