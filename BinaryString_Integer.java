public class BinaryString_Integer {
    public static void main(String[] args) {
        
        String binary = "1111";
        // method 1: return primitive int
        int number = Integer.parseInt(binary,2);
        System.out.println("Using parseInt: "+number);
    
        // method 2: return Integer object
        int number2 = Integer.valueOf(binary,2);
        System.out.println("Using valueOf"+number2);
    
    }
    
}
