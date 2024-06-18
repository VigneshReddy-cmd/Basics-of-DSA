class Solution {
    public int countPrimes(int n) {
        boolean arr[]=new boolean[n];
       if(n<3) return 0;
        int count=n/2;
        int range=(int)Math.sqrt(n);
        for(int i=3;i<=range;i+=2)
        {
            if(arr[i]==false)
            {
                for(int j=i*i;j<n;j+=2*i)
                {
                    if(!arr[j]){
                    count--;
                    arr[j]=true;
                    }
                }
            }
        }
        return count;
    }
}