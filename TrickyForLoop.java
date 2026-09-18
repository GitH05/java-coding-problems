public class TrickyForLoop {
    public static void main(String[] args) {

        // Tricky concept: The semicolon (;) after the for loop
        // makes the loop body empty.
        // The block { } is NOT part of the loop.

        for (int i = 0; i < 5; i++);

        {
            System.out.println("Hello! This is a tricky for loop.");
        }
    }
}