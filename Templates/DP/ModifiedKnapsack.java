
public class ModifiedKnapsack {
    public static int maxValue(int[][] items, int k) {
        int n = items.length;
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= k; w++) {
                if (items[i - 1][1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - items[i - 1][1]] + items[i - 1][2]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[n][k];
    }
    public static void main(String[] args) {
        int[][] items = {{1,3,10}, {5,1,10}, {2,2,1},{1,4,9},{4,5,11},{1,5,9}};
        int k = 6;
        System.out.println(maxValue(items, k)); // Output: 10
    }
}

