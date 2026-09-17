// Concept: Difference between == and .equals() when comparing Strings in Java

public class StringComparision {

    public static void main(String[] args) {

        String a = "Hello";
        String b = "Hello";
        String c = new String("Hello");

        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(a.equals(c));
    }
}