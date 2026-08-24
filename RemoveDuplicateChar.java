import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public class RemoveDuplicateChar {
    public static void main(String[] args) {
        String input = "communication";
        System.out.println("Input: " + input);
        removeDuplicates(input);
    }

    public static void removeDuplicates(String str) {
        Set<Character> set = new LinkedHashSet<>();

        for (char c : str.toCharArray()) {
            set.add(c);
        }

        StringBuilder sb = new StringBuilder();
        for (Character s : set) {
            sb.append(s);
        }

        System.out.println("Output: " + sb);
    }
}
