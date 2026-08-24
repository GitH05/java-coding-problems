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
        Set<String> set = new LinkedHashSet<>();
        
        for (char c : str.toCharArray()) {
            set.add(String.valueOf(c));
        }
        
        StringBuilder sb = new StringBuilder();
        for (String s : set) {
            sb.append(s);
        }

        System.out.println("Output: " + sb);
    }
}
