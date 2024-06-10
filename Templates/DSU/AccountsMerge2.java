

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Disjoint{
    List<Integer> parent=new ArrayList<>();
    List<Integer> size=new ArrayList<>();
    public Disjoint(int n)
    {
        for(int i=0;i<=n;i++)
        {
            parent.add(i);
            size.add(1);
        }
    }
    public int find_par(int node)
    {
        if(node==parent.get(node))
        {
            return node;
        }
        int ulp_node=find_par(parent.get(node));
        parent.set(node,ulp_node);
        return parent.get(node);
    }
    public void union_bySize(int u,int v)
    {
        int ulp_u=find_par(u);
        int ulp_v=find_par(v);
        int su=size.get(ulp_u);
        int sv=size.get(ulp_v);
        if(su < sv)
        {
            parent.set(ulp_u,ulp_v);
            size.set(ulp_v,su+sv);
        }
        else
        {
            parent.set(ulp_v,ulp_u);
            size.set(ulp_u,su+sv);
        }
    }
}
class Solution {
  static List<List<String>> accountsMerge(List<List<String>> accounts) {
    // code here
    int n=accounts.size();
    Map<String,Integer> map=new HashMap<>();
    Disjoint set=new Disjoint(n);
    for(int i=0;i<n;i++)
    {
        for(int j=1;j<accounts.get(i).size();j++)
        {
            String s=accounts.get(i).get(j);
            if(!map.containsKey(s))
            {
                map.put(s,i);
            }
            else
            {
               set.union_bySize(i,map.get(s)); 
            }
        }
    }
    
       // now create a ds whch seregrates the list as the dsu wrt to map

      List<String>[] arr = (ArrayList<String>[]) new ArrayList[n];
      for(int i=0;i<n;i++)
      {
          arr[i]=new ArrayList<>();
      }
      for(String str:map.keySet())
      {
          int index=map.get(str);
          int parent=set.find_par(index);
          arr[parent].add(str);
      }
      
      
      // now add the name and sort the data in listoflists
      
      List<List<String>> result=new ArrayList<>();
      for(int i=0;i<n;i++)
      {
          if(arr[i].size()==0){
              continue;
          }
          String name=accounts.get(i).get(0);
          List<String> temp=new ArrayList<>();
          temp.add(name);
          Collections.sort(arr[i]);
          for(String s:arr[i])
          {
              temp.add(s);
          }
          result.add(temp);
      }
      return result;
  }
}