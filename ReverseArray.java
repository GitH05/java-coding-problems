public class ReverseArray {
    public static void main(String[] args) {
        
        int[] arr = {1, 2, 85, 9, 4, 5};
        reverse(arr);
        System.out.println("Reversed array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    private static void reverse(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            // Swap elements at start and end  to reverse
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            // Move start forward and end backward to make each  elemnts swaped
            start++;
            end--;
        }
    }
}
