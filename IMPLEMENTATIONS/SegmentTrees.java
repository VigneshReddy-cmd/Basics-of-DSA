class SegmentTree{
    private int[] seg;
    private int[] arr;
    private int n;
    public SegmentTree(int[] arr){
        this.arr=arr;
        this.n=arr.length;
        seg=new int[4*n];
    }
    public void construct(){
        construct(arr,0,n-1,0);
    }
    private void construct(int[] arr,int start,int end,int index){
           if(start==end)
           {
             seg[index]=arr[start];
             return;
           }

           int mid=(start+end)/2;
           construct(arr, start, mid, 2*index+1);
           construct(arr, mid+1, end, 2*index+2);
           seg[index]=Math.max(seg[2*index+1],seg[2*index+2]);
    }
     
    public int query(int left,int right){
        return getMaxInRange(0,0,n-1,left,right);
    }
    private int getMaxInRange(int index, int start, int end, int left, int right) {
        // Completely outside the range
        if (start > right || end < left) {
            return Integer.MIN_VALUE;
        }

        // Completely within the range
        if (start >= left && end <= right) {
            return seg[index];
        }

        // Partially within the range
        int mid = (start + end) / 2;
        int leftMax = getMaxInRange(2 * index + 1, start, mid, left, right);
        int rightMax = getMaxInRange(2 * index + 2, mid + 1, end, left, right);
        return Math.max(leftMax, rightMax);
    }
    
}
public class SegmentTrees {

    public static void main(String[] args) {
        int arr[]={1,3,5,7,9,11};
        SegmentTree st=new SegmentTree(arr);
        st.construct();
        System.out.println(st.query(0, arr.length-1));
        System.out.println(st.query(0,3));
        System.out.println(st.query(3,5));
        System.out.println(st.query(2,5));
        System.out.println(st.query(1,4));
       
    }
}