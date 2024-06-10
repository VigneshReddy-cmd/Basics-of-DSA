import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class sumofelementsisK {
  public static HashMap<String,Boolean> map=new HashMap<>();
    //  public static List<ArrayList<Integer>> list=new ArrayList<ArrayList<Integer>>();
   public static boolean sub_arr(int index,int[] arr,int sum,int target,int n)
    {
    //   String
    //     // ArrayList<Integer> list1=new ArrayList<>();
    //       if(ind==arr.length)
    //       {
    //         if(sum==res){
    //         // list.add(list1);
    //         return 1;
    //         }
    //         return 0;
    //       }
          
    //       // the below 3 lines prints only one subsequence but not recomended using of global variables
    //       // use function as boolean
    //     //   if(list.size()==1)
    //     //   return;


    //       // select one element and push in to stack
    //         //  list1.add(arr[ind]);
    //          sum+=arr[ind];  // adding element to sum
    //          // call the subsets of selected elements in the array
    //      int l=sub_arr(arr, ind+1,sum,res);
        
    //     //    return true;
    // // if element found then return true;
    // // not continue the right side of recursion tree

    //          // remove the selected item and select new one 
    //         //  list1.remove(list1.size()-1);
    //          sum-=arr[ind]; // removing element form sum 
    //          // call the same func for the newly selected one

    //          // if sequence found in the right side
    //        int r=sub_arr(arr, ind+1,sum,res);
           
    //          return l|r;
    String key = index + "," + String.valueOf(sum);
    if (map.containsKey(key)) {
        return !map.get(key);
    }
    if (index == n) {
        map.put(key, sum == target);
        return sum == target;
    }
    sum += arr[index];
    boolean l = sub_arr(index + 1, arr, target, n, sum);
    sum -= arr[index]; // Resetting sum to its previous value
    boolean r = sub_arr(index + 1, arr, target, n, sum);
    map.put(key, l || r);
    return l ^ r;
          

    }
    public static void main(String[] args) {
        int a[]={1,2,1,1,1,1};
        // ArrayList<Integer> ds=new ArrayList<>();
       System.out.print(sub_arr(0,a,0,2,6));
    }
}
