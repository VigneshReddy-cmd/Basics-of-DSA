package Greedy.BitFlip;
/*
 * You are given a 
binary array
 nums.

You can do the following operation on the array any number of times (possibly zero):

Choose any 3 consecutive elements from the array and flip all of them.
Flipping an element means changing its value from 0 to 1, and from 1 to 0.

Return the minimum number of operations required to make all elements in nums equal to 1. If it is impossible, return -1.

 

Example 1:

Input: nums = [0,1,1,1,0,0]

Output: 3

Explanation:
We can do the following operations:

Choose the elements at indices 0, 1 and 2. The resulting array is nums = [1,0,0,1,0,0].
Choose the elements at indices 1, 2 and 3. The resulting array is nums = [1,1,1,0,0,0].
Choose the elements at indices 3, 4 and 5. The resulting array is nums = [1,1,1,1,1,1].
 */
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int res = 0;

        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                // If we encounter a zero and there are at least two more elements to flip
                if (i + 2 < n) {
                    // Flip the current element and the next two elements
                    nums[i] = 1 - nums[i];
                    nums[i + 1] = 1 - nums[i + 1];
                    nums[i + 2] = 1 - nums[i + 2];
                    res++;
                } else {
                    // Not enough elements to flip, return -1
                    return -1;
                }
            }
        }
        return res;
    }
}

public class MakeAllOnesFixesWindow {
    public static void main(String[] args) {
        Solution solution=new Solution();
        int[] arr={0,1,1,1,0,0};
        System.out.println(solution.minOperations(arr));
    }
}
