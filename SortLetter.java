public class SortLetter {

    public static void main(String[] args) {
        
        String word = "hello hi";
        // String[] words = word.split(" ");
        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] > chars[j]) {
                    char temp = chars[i];
                    chars[i] = chars[j];
                    chars[j] = temp;
                }
            }
        }
        System.out.println(chars);
        
    }
}
