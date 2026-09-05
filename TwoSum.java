import java.util.HashMap;

public class TwoSum {

    public static void main(String[] args) {

        int[] nums = {2, 7, 11, 15, 1, 8, 3, 6};
        int target = 9;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int need = target - nums[i]; // Number needed to reach target

            if (map.containsKey(need)) { // Check if we already have that number

                int firstIndex = map.get(need);
                int secondIndex = i;

                int firstNumber = nums[firstIndex];
                int secondNumber = nums[secondIndex];

                System.out.println("Numbers: " + firstNumber + " + " + secondNumber);
                System.out.println("Indexes: " + firstIndex + ", " + secondIndex);

                return;
            }

            map.put(nums[i], i); // Store number and its index
        }

        System.out.println("No pair found");
    }
}
