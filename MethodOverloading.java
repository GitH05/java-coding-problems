public class MethodOverloading {

    static void print(Object value) {
        System.out.println("Object method");
    }

    static void print(String value) {
        System.out.println("String method");
    }

    public static void main(String[] args) {

        print(null);
        // Both methods accept null.
        // String is more specific than Object,
        // so Java selects print(String).
    }
}
