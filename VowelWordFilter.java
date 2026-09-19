import java.util.*;

public class VowelWordFilter {

    public static void main(String[] args) {

        String[] words = {
                "apple",
                "elephant",
                "orange",
                "umbrella",
                "banana",
                "education",
                "icecream",
                "grapes"
        };

        List<String> result = new ArrayList<>();

        for (String word : words) {

            if (word.length() > 5 &&
                    "aeiouAEIOU".indexOf(word.charAt(0)) != -1) {

                result.add(word);
            }
        }

        System.out.println(result);
    }
}