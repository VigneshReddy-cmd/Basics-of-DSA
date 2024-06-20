import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PartitionSubset {
   public static void main(String[] args) {
    Solution solution=new Solution();
    int[] arr = {1, 5, 11, 5};
    int minDiff = solution.minimumDifference(arr);
    System.out.println(minDiff);
   } 
}
class Solution {
    public int minimumDifference(int[] arr) {
         int tot = Arrays.stream(arr).sum();
        int n=arr.length;
        int N=n/2;
        List<List<Integer>> left=new ArrayList<>(N+1);
        List<List<Integer>> right=new ArrayList<>(N+1);
        for(int i=0;i<=N;i++)
        {
             left.add(new ArrayList<>());
             right.add(new ArrayList<>());
        }
        for(int mask=0;mask<(1<<N);mask++)
        {
             int sum1=0;
             int sum2=0;
             int idx = Integer.bitCount(mask);
             for(int i=0;i<N;i++)
             {
                  if ((mask & (1 << i)) != 0)
                 {
                     sum1+=arr[i];
                     sum2+=arr[i+N];
                 }
             }
             left.get(idx).add(sum1);
             right.get(idx).add(sum2);
        }

        for(int i=0;i<=N;i++)
        {
             Collections.sort(right.get(i));
        }
    int res=Math.min(Math.abs(tot-2*left.get(N).get(0)),
         Math.abs(tot-2*right.get(N).get(0)));
        for(int i=1;i<N;i++)
        {
             for(int s1:left.get(i))
             {
                  int rightidx=N-i;
                  int s2=(tot-2*s1)/2;
                  List<Integer> rightList=right.get(rightidx);
                  int index=Collections.binarySearch(rightList,s2);
                  index= index >= 0 ? index : -(index + 1);
                  if(index<rightList.size())
                  res=Math.min(res,Math.abs(tot-2*(s1+rightList.get(index))));
             }
        }
        return res;
    }
}
