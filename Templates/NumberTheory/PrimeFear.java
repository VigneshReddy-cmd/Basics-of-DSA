import java.io.*;
import java.util.*;

 class Solution {
    private static boolean[] arr;

    private static int check(int num) {
        if (arr[num]) return 0;
        String str = Integer.toString(num);
        while (str.length() != 0) {
            if (str.charAt(0) == '0')
                return 0;
            int n = Integer.parseInt(str);
            if (arr[n])
                return 0;
            str = str.substring(1);
        }
        return 1;
    }

    public static void main(String[] args) throws IOException {
        // Using BufferedReader for fast I/O
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        // precompute
        arr = new boolean[1000001];
        arr[0] = true;
        arr[1] = true;
        int range = (int) Math.sqrt(1000000);
        for (int i = 2; i <= range; i++) {
            if (arr[i] == false) {
                for (int j = i * i; j <= 1000000; j += i) {
                    arr[j] = true;
                }
            }
        }
        int[] dp = new int[1000000 + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i < 1000000; i++) {
            dp[i] = dp[i - 1] + check(i);
        }
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            bw.write(dp[n] + "\n");
        }

        // Closing resources
        bw.close();
        br.close();
    }
}
