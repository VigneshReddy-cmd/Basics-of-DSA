import java.util.*;

public class SUbsetSumMeet {
    public static void main(String[] args) {
        int arr[] = { 3, 34, 4, 12, 5, 2 }; 
        int n = arr.length;
        int N=n/2;
        int target=10;
        HashMap<Integer,Integer> map1=new HashMap<>(1<<N);
        HashMap<Integer,Integer> map2=new HashMap<>(1<<N);
    
        for(int mask=0;mask<(1<<N);mask++)
        {
            int sum1=0;
            int sum2=0;
            for(int i=0;i<N;i++)
            {
                if((mask&(1<<i))!=0)
                {
                    sum1+=arr[i];
                    sum2+=arr[i+N];
                }
            }
            map1.put(sum1,map1.getOrDefault(sum1, 0)+1);
            map2.put(sum2,map2.getOrDefault(sum2, 0)+1);
        }
        long result=0;
        for(int sum:map1.keySet())
        {
            int requiredSum=target-sum;
            if(map2.containsKey(requiredSum))
            {
                result+=(long)map1.get(sum)*(long)map2.get(requiredSum);
            } 
        }
        System.out.println(result);
    }
}
