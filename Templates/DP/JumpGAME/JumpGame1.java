package JumpGAME;

import java.util.Arrays;

class Solution {
    private boolean helper(int idx,int[] arr,int[] dp,int n)
    {
         if(idx==n)
         {
             return true;
         }
         if(dp[idx]!=-1) return dp[idx]==1?true:false;
         boolean flag=false;
         for(int i=1;i<=arr[idx];i++)
         {
            if(idx+i==n-1){
                flag=true;
                break;
            }
             if(helper(idx+i,arr,dp,n))
             {
                flag=true;
                break;
             }
         }
         dp[idx]=flag?1:0;
         return flag;
    }
    public boolean canJump(int[] arr) {
        int n=arr.length;
        if(n==1) return true; 
        int[] dp=new int[n+1];
        Arrays.fill(dp,-1);
        return helper(0,arr,dp,n);
    }
}