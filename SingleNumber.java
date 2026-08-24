public class SingleNumber {
    public static void main(String[] args) {
        
        int[] arr = {1, 5, 9, 1, 9};
        int result = 0;

        // Sum up all elements in the array
        for (int i = 0; i < arr.length; i++) {
            result += arr[i];
        }

        // Iterate again to find paired numbers and subtract twice their value
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                // If arr[i] is equal to arr[j] and they are different elements (i != j)
                if (arr[i] == arr[j] && i != j) {
                    result = result -arr[i] * 2; // Subtract the paired number twice
                    break; // Once we find a pair, move to the next element
                }
            }
        }

        System.out.println("The single number is: " + result);
    }
}
