package BinarySearch;
import java.io.*;
import java.util.*;
/*
  Farmer John has built a new long barn, with N stalls. The stalls are located along a straight line at positions x1,x2,x3,...,xN.

His C cows don't like this barn layout and become aggressive towards each other once put into a stall. To prevent the cows from hurting each other, John wants to assign the cows to the stalls, such that the minimum distance between any two of them is as large as possible.

Given the positions of the stalls and the number of cows, you should help John find the largest minimum distance between any pair of cows.

link : https://www.geeksforgeeks.org/problems/aggressive-cows/1
 */
class Solution {
    private static boolean check(int mid, int[] arr, int n, int m) {
        int count = 1;
        int lastPos = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] - lastPos >= mid) {
                count++;
                lastPos = arr[i];
            }
            if (count >= m) return true;
        }
        return false;
    }

    public static int aggressiveCows(int[] arr, int n, int m) {
        // Write your code here.
        Arrays.sort(arr);
        int res = -1;
        int start = 0;
        int end = arr[n - 1] - arr[0];
        while (start <= end) {
            int mid = (start + end) / 2;
            if (check(mid, arr, n, m)) {
                res = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return res;
    }
}

public class AggressiveCows {
    Solution solution=new Solution();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt(); // Number of test cases
        Solution solution = new Solution();
        while (t-- > 0) {
            int n = sc.nextInt(); // Number of positions
            int c = sc.nextInt(); // Number of cows
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
             int res=solution.aggressiveCows(arr, n, c);
            System.out.println(res);
        }
}
}
