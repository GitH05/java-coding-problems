import java.util.HashMap;

public class FindTargetSum {

    public static void main(String[] args) {

        int[] nums = { 2, 7, 11, 15, 1, 8, 3, 6 };
        int target = 9;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int need = target - nums[i]; // Number needed to reach target
            // [when i = 0, need = 9 - 2, 7 needed | when i = 1, need 9 - 7, 2 needed]

            if (map.containsKey(need)) { // Check if we already have that number
                // [when i = 0, need = 7, map not have 7 | when i = 1, need = 2 map have 2 =>
                // CONDITION TRUE]

                int firstIndex = map.get(need);
                int secondIndex = i;

                int firstNumber = nums[firstIndex];
                int secondNumber = nums[secondIndex];

                System.out.println("Numbers: " + firstNumber + " + " + secondNumber);
                System.out.println("Indexes: " + firstIndex + ", " + secondIndex);

                return;
            }

            map.put(nums[i], i); // Store number and its index
            // [when i = 0, store (2, 0) and when i = 1, store (7, 1) ]
        }

        System.out.println("No pair found");
    }
}
