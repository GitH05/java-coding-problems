import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraysAsListExample {

    public static void main(String[] args) {

        // Arrays.asList() creates a fixed-size List
        List<String> names = Arrays.asList("Ram", "Shyam", "Hari");

        System.out.println("Before set(): " + names);

        // Changing an existing element is allowed
        names.set(0, "Sita");

        System.out.println("After set(): " + names);

        // Adding a new element is NOT allowed
        try {
            names.add("Gita");
        } catch (UnsupportedOperationException e) {
            System.out.println("add() failed: " + e);
        }

        // Use ArrayList when you need a resizable List
        List<String> resizableNames = new ArrayList<>(
                Arrays.asList("Ram", "Shyam", "Hari"));

        resizableNames.add("Gita");

        System.out.println("ArrayList: " + resizableNames);
    }
}