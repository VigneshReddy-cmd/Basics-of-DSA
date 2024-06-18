class Solution {

    public int minSubsetSumDifference(int []arr, int n) {
        // generate all the possible sums array dp 
        // dp[i][j] states that till index i and the sum j is possible or not
        // any partition can be made choose min from above
        // dp will be dp[n+1][totalsum+1] but space concern
        // now we can optimise the space for the only last test case od the index
        int totalsum=0;
        for(int i=0;i<n;i++) totalsum+=arr[i];
        boolean[] dp=new boolean[totalsum+1];
        if(arr[0]<=totalsum) dp[arr[0]]=true;
        for(int i=1;i<n;i++)
        {
            boolean temp[]=new boolean[totalsum+1];
             for(int k=0;k<=totalsum;k++)
             {
                  boolean nopick=dp[k];
                  boolean pick=false;
                  if(arr[i]<=k)
                  {
                       pick=dp[k-arr[i]];
                  }
                 temp[k]= pick || nopick;
             }
             dp=temp;
        }
        int minDiff=Integer.MAX_VALUE;
        for(int i=0;i<=totalsum/2;i++)
        {
             if(dp[i])
             {
                minDiff=Math.min(Math.abs(totalsum-2*i),minDiff); 
             }
        }
        return minDiff;
    }
}
public class SubsetSumPartition {
    public static void main(String[] args) {

        int[] arr = {1, 5, 11, 5};
        int n = arr.length;
        Solution solution=new Solution();
        int minDiff=solution.minSubsetSumDifference(arr, n);
        System.out.print(minDiff);
    }
}
