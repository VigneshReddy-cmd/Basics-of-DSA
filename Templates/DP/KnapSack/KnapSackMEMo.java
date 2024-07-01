package KnapSack;

import java.util.* ;

 class Solution{
    private static int helper(int idx,int currWeight,int[] weight,int[] value,int n,int maxWeight,int[][] dp)
    {
         //base
         if(idx==n-1)
         {
              if(currWeight+weight[idx]<=maxWeight)
              {
                   return value[idx];
              }
              else
              {
                  return 0;
              }
         }


         
         //dp base
         if(dp[idx][currWeight]!=-1)
         {
             return dp[idx][currWeight];
         }   

         int notPick=helper(idx+1, currWeight, weight, value, n, maxWeight,dp); 
         int pick=Integer.MIN_VALUE;
         if(weight[idx]+currWeight<=maxWeight)
         {
              pick=helper(idx+1, currWeight+weight[idx], weight, value, n, maxWeight,dp)+value[idx];
         }
         
         return dp[idx][currWeight]=Math.max(notPick,pick);
    }
    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {
            int dp[][]=new int[n+1][maxWeight+1];
            for(int i=0;i<=n;i++)
            {
                Arrays.fill(dp[i],-1);
            }
            return helper(0,0,weight,value,n,maxWeight,dp);
    }
}

public class KnapSackMEMo {
     public static void main(String[] args) {

         try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();

             int maxWeight = sc.nextInt();

             int[] weight = new int[n];

             int[] value = new int[n];

             for (int i = 0; i < n; i++) {

                 weight[i] = sc.nextInt();

                 value[i] = sc.nextInt();

             }

             System.out.println(Solution.knapsack(weight, value, n, maxWeight));
        }

     }
}
