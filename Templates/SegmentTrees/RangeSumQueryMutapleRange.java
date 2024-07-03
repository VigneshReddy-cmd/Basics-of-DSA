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
             seg[segIndex] += (high-low+1)*val;
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
                lazy[2*segIndex+1]+=lazy[segIndex];
                lazy[2*segIndex+2]+=lazy[segIndex];
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
    private SegmentTree tree;
    public NumArray(int[] nums) {
        tree=new SegmentTree(nums);
        tree.construct();
    }
    
    public void update(int left,int right, int val) {
        tree.updateSum(left,right,val);
    }
    
    public int sumRange(int left, int right) {
        return tree.query(left,right);
    }
}
public class RangeSumQueryMutapleRange {
    public static void main(String[] args) {
        int arr[]={1,3,5,7,9,11};
        NumArray obj = new NumArray(arr);
        System.out.println(obj.sumRange(0,5));
        obj.update(0,4,2);
        System.out.println(obj.sumRange(0,5));
    }
}
