public class mergesort {
    private static int temp[]=new int[9];
    private static void merge(int[] arr,int low,int mid,int high)
    {
        int i=0;
        int left=low;
        int right=mid+1;
        while(left<mid && right<high)
        {
            if(arr[left]<arr[right])
            {
                temp[i]=arr[left];
                left++;
                i++;
            }
            else{
                temp[i]=arr[right];
                right++;
                i++;
            }
        }
        while(left<mid)
        {
            temp[i]=arr[left];
            left++;
            i++;
        }
         while(left<mid)
        {
            temp[i]=arr[left];
            left++;
            i++;
        }
        return;
    }
    private static void mergesort(int[] arr,int low,int high)
    {
        if(low>=high)
        return;
        int mid=low+high;
        mid/=2;
        mergesort(arr,low,mid);
        mergesort(arr,mid+1,high);
        merge(arr,low,mid,high);
    }
    public static void main(String[] args) {
        int arr[]={2,5,7,23,87,1,9,7,0};
        mergesort(arr,0,arr.length);
        for(int i=0;i<temp.length;i++)
        {
            System.out.print(temp[i]+"->");
        }
    }
}
