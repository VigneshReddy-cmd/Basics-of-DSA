import java.util.ArrayList;
import java.util.List;

class SegmentTree
{
     private int[] seg;
     private int[] arr;
     private int n;
     public SegmentTree(int[] arr)
     {
         this.arr=arr;
         this.n=arr.length;
         this.seg=new int[4*n];
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
     private int findSum(int index,int low,int high,int l,int r)
     {
         // out of range
          if(low>r || high<l)
          {
             return 0;
          }

         // direct range found
         if(low>=l && high<=r)
         {
             return seg[index];
         }
          
        int mid=(low+high)/2;
        int left=findSum(2*index+1,low,mid,l,r);
        int right=findSum(2*index+2,mid+1,high,l,r);
        return left+right;
     }
     public void updateSum(int ind,int val)
     {
          pointUpdate(0,0,n-1,ind,val);
          return;
     }
     private void pointUpdate(int segIndex,int low,int high,int index,int val)
     {
         if(low==high)
         {
            seg[segIndex]=val;
             arr[index] = val;
            return;
         }
         int mid=(low+high)/2;
         if(index<=mid)
         {
             pointUpdate(2*segIndex+1,low,mid,index,val);
         }
         else
         {
             pointUpdate(2*segIndex+2,mid+1,high,index,val);
         }
         seg[segIndex]=seg[2*segIndex+1]+seg[2*segIndex+2];
     }
}
class NumArray {
    List<SegmentTree> seg=new ArrayList<>();
    public NumArray(int[][] nums) {
       for(int i=0;i<nums.length;i++)
       {
         SegmentTree tree = new SegmentTree(nums[i]);
         tree.construct();  // Missing call to construct the segment tree
         seg.add(tree);
       }
    }
    
    public void update(int row,int col,int val) {
        seg.get(row).updateSum(col,val);
    }
    
    public int sumRange(int r1,int c1,int r2,int c2) {
        int sum=0;
        for(int i=r1;i<=r2;i++)
        {
          sum+=seg.get(i).query(c1,c2);
        }
       return sum;
    }
}
public class RangeSum2DPointMutable {
    public static void main(String[] args) {

        int[][] arr={{1,2,3},{4,5,6},{7,8,9}};  
        NumArray obj = new NumArray(arr);
        System.out.println(obj.sumRange(1,1,2,2));
        obj.update(1,1,10);
        System.out.println(obj.sumRange(1,1,2,2));
    }
}
