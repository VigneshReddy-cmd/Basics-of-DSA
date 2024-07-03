import java.util.ArrayList;
import java.util.List;

class SegmentTree
{
     private int[] seg;
     private int[] arr;
     private int n;
     private int[] lazy;
     public SegmentTree(int[] arr)
     {
         this.arr=arr;
         this.n=arr.length;
         this.seg=new int[4*n];
         this.lazy=new int[4*n];
     }
     public void construct()
     {
        constructTree(0,0,n-1);
     }
     private void constructTree(int index,int low,int high)
     {
        if(low==high)
        {
            seg[index]=arr[low];
            return;
        }

        int mid=(low+high)/2;
        constructTree(2*index+1,low,mid);
        constructTree(2*index+2,mid+1,high);
        seg[index]=seg[2*index+1]+seg[2*index+2];
     }

     public int query(int l,int r)
     {
          return findSum(0,0,n-1,l,r);
     }
     private int findSum(int segIndex,int low,int high,int l,int r)
     {
        //
        if(lazy[segIndex]!=0)
           {
             seg[segIndex] += (high-low+1)*lazy[segIndex];
             if(low!=high)
             {
                 lazy[2*segIndex+1]+=lazy[segIndex];
                 lazy[2*segIndex+2]+=lazy[segIndex];
             }
             lazy[segIndex]=0;
           }
         // out of range
          if(low>r || high<l)
          {
             return 0;
          }

         // direct range found
         if(low>=l && high<=r)
         {
             return seg[segIndex];
         }
          
        int mid=(low+high)/2;
        int left=findSum(2*segIndex+1,low,mid,l,r);
        int right=findSum(2*segIndex+2,mid+1,high,l,r);
        return left+right;
     }
     public void updateSum(int l,int r,int val)
     {
         lazyUpdate(0,0,n-1,l,r,val);
         return;
     }
    private void lazyUpdate(int segIndex,int low,int high,int l,int r,int val)
    {
           // pending Updates
           if(lazy[segIndex]!=0)
           {
             seg[segIndex] += (high-low+1)*lazy[segIndex];
             if(low!=high)
             {
                 lazy[2*segIndex+1]+=lazy[segIndex];
                 lazy[2*segIndex+2]+=lazy[segIndex];
             }
             lazy[segIndex]=0;
           }

           // out of range
           if(low>r || high<l)
           {
             return;
           }

           // with in range
           if(low>=l && high<=r)
           {
            seg[segIndex] += (high-low+1)*val;
            if(low!=high)
            {
                lazy[2*segIndex+1]+=val;
                lazy[2*segIndex+2]+=val;
            }
            return;
           }

           //over lap
           int mid=(low+high)/2;
           lazyUpdate(2*segIndex+1, low, mid, l, r, val);
           lazyUpdate(2*segIndex+2, mid+1, high, l, r, val);
           seg[segIndex]=seg[2*segIndex+1]+seg[2*segIndex+2];;
    }
}
class NumArray {
    List<SegmentTree> trees=new ArrayList<>();
    public NumArray(int[][] nums) {
       for(int i=0;i<nums.length;i++)
       {
           SegmentTree tree=new SegmentTree(nums[i]);
           tree.construct();
           trees.add(tree);
       }
    }
    
    public void update(int r1,int c1,int r2,int c2,int val) {
       for(int i=r1;i<=r2;i++)
       {
          trees.get(i).updateSum(c1, c2, val);
       }
    }
    
    public int sumRange(int r1,int c1,int r2,int c2) {
        int sum=0;
        for(int i=r1;i<=r2;i++)
        {
             sum+=trees.get(i).query(c1, c2);
        }
        return sum;
    }
}
public class RangeSumQuery2DRangeMutable {
    public static void main(String[] args) {
        int[][] matrix = {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};
        NumArray numArray = new NumArray(matrix);
        System.out.println(numArray.sumRange(1, 1, 2, 3));
        numArray.update(1, 1, 2, 3, 10);
        System.out.println(numArray.sumRange(1, 1, 2, 3));
    }
}
