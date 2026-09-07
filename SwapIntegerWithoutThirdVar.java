public class SwapIntegerWithoutThirdVar {
    public static void main(String[] args) {
        
        int x = 18;
        int y = 6;

        x = x + y; //24
        y = x - y; //18
        x = x - y; //6

        System.out.println("x: "+x);
        System.out.println("y: "+y);
        
    }
}
