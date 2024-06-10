// package DSU;
import java.util.ArrayList;
import java.util.List;

class Rank_Union{
    List<Integer> parent=new ArrayList<>();
    List<Integer> rank=new ArrayList<>();
    List<Integer> size=new ArrayList<>();
   public Rank_Union(int n)
   {
      for(int i=0;i<=n;i++)
      {
        parent.add(i); // Initialize parent to itself initially
        rank.add(0); // Initialize rank to 0 initially
        size.add(1); // Initialize all to 1 
      }
   }
   public int find_parent(int node)
   {
       if(node==parent.get(node))
         return node;
         int ultimate_par=find_parent(parent.get(node));
         parent.set(node,ultimate_par);
         return parent.get(node);
   }
   public void UnionByrank(int u,int v)
   {
        int ulp_v=find_parent(v);
        int ulp_u=find_parent(u);
        // if already in same component then no need of adding
        if(ulp_v==ulp_u){
            return;
        }
        if(rank.get(ulp_u)<rank.get(ulp_v))
        {
            parent.set(ulp_u,ulp_v);
        }
        else if(rank.get(ulp_u)>rank.get(ulp_v))
        {
            parent.set(ulp_v, ulp_u);
        }
        else
        {
            // if both rank equal then add any one to other here i am adding the u to v
            parent.set(ulp_v, ulp_u);
            rank.set(ulp_u,rank.get(ulp_u)+1);
        }
   }
   public void UnionBySize(int u,int v)
   {
      int ulp_u=find_parent(u);
      int ulp_v=find_parent(v);
      int sizev=size.get(ulp_v);
      int sizeu=size.get(ulp_u);
      if(sizeu>sizev)
      {
         parent.set(ulp_v,ulp_u);
         size.set(ulp_v,sizeu+sizev);
      }
      else
      {
        parent.set(ulp_u,ulp_v);
         size.set(ulp_u,sizeu+sizev);
      }
   }

}

  public class Union{
    public static void main(String[] args) {
       Rank_Union ru=new Rank_Union(7);
        ru.UnionBySize(1,2);
        ru.UnionBySize(2,3);
        ru.UnionBySize(4,5);
        ru.UnionBySize(5,6);
        ru.UnionBySize(6,7);
        if(ru.find_parent(3)==ru.find_parent(7)) 
        {
            System.out.println("SAME");
        }
        else{
            
            System.out.println(" NOT SAME");
        }
        ru.UnionBySize(3,7);  
        if(ru.find_parent(1)==ru.find_parent(7)) 
        {
            System.out.println("SAME");
        }
        else{
            
            System.out.println(" NOT SAME");
        }
      }
}
