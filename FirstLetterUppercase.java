public class FirstLetterUppercase {
    public static void main(String[] args) {
        
        String word = "HeLlo vSSS";
        String[] words = word.split(" ");
        String uppCase = "";
        if (word == null || word.isEmpty()) {
            return;
        }
        for (String i : words) {
            uppCase += i.substring(0, 1).toUpperCase() +
                    i.substring(1).toLowerCase() + " ";
        }
        System.err.println(uppCase);
        
    }

}
