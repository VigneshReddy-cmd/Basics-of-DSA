package Greedy.BitFlip;
/*
 * You are given a binary array nums and an integer k.

A k-bit flip is choosing a subarray of length k from nums and simultaneously changing every 0 in the subarray to 1, and every 1 in the subarray to 0.

Return the minimum number of k-bit flips required so that there is no 0 in the array. If it is not possible, return -1.

A subarray is a contiguous part of an array.

 Input: nums = [0,0,0,1,0,1,1,0], k = 3
Output: 3
Explanation: 
Flip nums[0],nums[1],nums[2]: nums becomes [1,1,1,1,0,1,1,0]
Flip nums[4],nums[5],nums[6]: nums becomes [1,1,1,1,1,0,0,0]
Flip nums[5],nums[6],nums[7]: nums becomes [1,1,1,1,1,1,1,1]
 */
class Solution {
    public int minKBitFlips(int[] arr, int k) {
        int n=arr.length;
        int[] flips=new int[n];
        int count=0;
        int times=0;
        for(int i=0;i<n;i++)
        {
            if(i>=k)
            {
               times=times-flips[i-k];  
            }
            if((arr[i]==0 && times%2==0) || (arr[i]==1 && times%2==1))
            {
                if(i+k > n) {
                    return -1;
                }
                 times++;
                 flips[i]=1;
                 count++;
            }
        }
        return count;
    }
}
public class MakeAllonesDynamicWindow {
    public static void main(String[] args) {
        Solution solution=new Solution();
    int[] arr={0,0,0,1,0,1,1,0};
    int k=3;
    System.out.print(solution.minKBitFlips(arr,k));
    }
    
}
