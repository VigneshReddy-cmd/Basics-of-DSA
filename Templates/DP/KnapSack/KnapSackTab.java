package KnapSack;

public class KnapSackTab {
    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {

        // Tabulation
        int dp[][]=new int[n+1][maxWeight+1];
        for(int w=weight[0];w<=maxWeight;w++)
        {
             dp[0][w]=value[0];
        }

        for(int ind=1;ind<n;ind++)
        {
             for(int w=0;w<=maxWeight;w++)
             {
                  int notPick=dp[ind-1][w];
                  int pick=Integer.MIN_VALUE;
                  if(weight[ind]<=w)
                  {
                       pick=value[ind]+dp[ind-1][w-weight[ind]];
                  }

                  dp[ind][w]=Math.max(notPick,pick);
             }
        }
        return dp[n-1][maxWeight];
        
}
    public static void main(String[] args) {

        int[] weight = {1, 3, 4, 5};

        int[] value = {1, 4, 5, 7};

        int n = 4;

        int maxWeight = 7;
        
        System.out.println(knapsack(weight, value, n, maxWeight));
    }
}
