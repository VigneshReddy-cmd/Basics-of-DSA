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
    private SegmentTree tree;
    public NumArray(int[] nums) {
        tree=new SegmentTree(nums);
        tree.construct();
    }
    
    public void update(int index, int val) {
        tree.updateSum(index,val);
    }
    
    public int sumRange(int left, int right) {
        return tree.query(left,right);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
public class RangeSumQueryPointMutable {
    public static void main(String[] args) {
        int arr[]={1,3,5,7,9,11};
        NumArray obj = new NumArray(arr);
        System.out.println(obj.sumRange(0,5));
        obj.update(0,2);
        System.out.println(obj.sumRange(0,5));
    }
}
