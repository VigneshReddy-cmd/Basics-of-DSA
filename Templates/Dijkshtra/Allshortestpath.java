import java.util.*;
public class Allshortestpath {
    static class Pair
    {
        int node;
        int wt;
        Pair(int node,int wt)
        {
            this.node=node;
            this.wt=wt;
        }
    }
    // private static boolean[] ans;
    private static List<List<Pair>> adj;
    private static List<StringBuilder> res;
    private static void dfs(int start,int[] dist,int n,StringBuilder str,int min)
    {
        str.append(start);
        if(start==n-1)
        {
            System.out.println(str+" "+dist[n-1]+" "+min);
            if(dist[n-1]==min){
            System.out.println(str);
            res.add(new StringBuilder(str));
            }
            return;
        }
        for(Pair p:adj.get(start))
        {
            int node=p.node;
            int wt=p.wt;
            if(wt+dist[start]<=dist[node])
            {
                int x=dist[node];
                dist[node]=wt+dist[start];
                dfs(node,dist,n,new StringBuilder(str),min);
                // dist[node]=x;
            }
        }
        str.deleteCharAt(str.length()-1);
    }
    public static int[] findAnswer(int n, int[][] edges,int src,int dest) {
        int m=edges.length;
        for(int i=0;i<m;i++)
        {
            adj.add(new ArrayList<>());
        }
        for(int i=0;i<m;i++)
        {
            adj.get(edges[i][0]).add(new Pair(edges[i][1],edges[i][2]));
            adj.get(edges[i][1]).add(new Pair(edges[i][0],edges[i][2]));
        }
        PriorityQueue<Pair> pq=new PriorityQueue<>((a,b)->a.wt-b.wt);
        int[] dist=new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src]=0;
        pq.add(new Pair(src,0));
        while(!pq.isEmpty())
        {
            Pair p=pq.poll();
            int node=p.node;
            int wt=p.wt;
            for(Pair it:adj.get(node))
            {
                int next_node=it.node;
                int next_wt=it.wt;
                if(next_wt+wt<dist[next_node])
                {
                   dist[next_node]=next_wt+wt;
                    pq.add(new Pair(next_node,dist[next_node]));
                }
            }   
        }
        //  int min=dist[n-1];
        //  if(min==Integer.MAX_VALUE) return;
        //  // finding paths for all mindist=5;
        //  // dfs
        //  res=new ArrayList<>();
        //  dist=new int[n];
        //  Arrays.fill(dist,Integer.MAX_VALUE);
        //  dist[0]=0;
        //  System.out.println(min);
        // dfs(0,dist,n,new StringBuilder(),min);
        
        return dist;
    }
    public static void main(String[] args) {
        int[][] edges = {
            {0, 1, 4},
            {0, 2, 1},
            {1, 3, 2},
            {1, 4, 3},
            {1, 5, 1},
            {2, 3, 1},
            {3, 5, 3},
            {4, 5, 2}
        };
        // ans=new boolean[6]; 
        adj=new ArrayList<>();
        res=new ArrayList<>();
        // for(int i=0;i<res.size();i++)
        // {
            //     System.out.println(res.get(i));
            // }
            // HashMap<Integer,Integer> map=new HashMap<>(); 
            //The following are all the shortest paths between nodes 0 and 5:
            // The path 0 -> 1 -> 5: The sum of weights is 4 + 1 = 5.
            // The path 0 -> 2 -> 3 -> 5: The sum of weights is 1 + 1 + 3 = 5.
            // The path 0 -> 2 -> 3 -> 1 -> 5: The sum of weights is 1 + 1 + 2 + 1 = 5.
            // done generating all shortest pairs
            // now checking edge present within the edge
            // takes too long now do with the easy approach
            // solution
            // do reverse approach then 

            // from 0 to n-1
            // and n-1 to 0
            int d1[]=findAnswer(6, edges,0,5);
            int d2[]=findAnswer(6, edges,5,0);
            boolean[] ans=new boolean[8];
            for(int i=0;i<8;i++)
            {
               int u=edges[i][0];
               int v=edges[i][1];
               int w=edges[i][2];
               if(w+d1[u]+d2[v]==d1[5] || w+d1[v]+d2[u]==d1[5] )
               {
                  ans[i]=true;
               }
            }
            System.out.println(Arrays.toString(ans));
    }
}
