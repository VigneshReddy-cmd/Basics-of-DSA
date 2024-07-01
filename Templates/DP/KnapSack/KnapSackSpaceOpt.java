package KnapSack;

public class KnapSackSpaceOpt {
    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {
        int dp[]=new int[maxWeight+1];
        // Tabulation
        for(int w=weight[0];w<=maxWeight;w++)
        {
             dp[w]=value[0];
        }
        for(int ind=1;ind<n;ind++)
        {
             for(int w=maxWeight;w>=0;w--)
             {
                  int notPick=dp[w];
                  int pick=Integer.MIN_VALUE;
                  if(weight[ind]<=w)
                  {
                       pick=value[ind]+dp[w-weight[ind]];
                  }

                  dp[w]=Math.max(notPick,pick);
             }
        }
        return dp[maxWeight];
        
}
    public static void main(String[] args) {

        int[] weight = {1, 3, 4, 5};

        int[] value = {1, 4, 5, 7};

        int n = 4;

        int maxWeight = 7;

        System.out.println(knapsack(weight, value, n, maxWeight));
    }
}
