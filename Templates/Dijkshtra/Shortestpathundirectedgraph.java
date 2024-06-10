import java.util.*;
public class Shortestpathundirectedgraph{
    static class Pair
    {
        int node;
        int wt;
        public Pair(int node,int wt)
        {
            this.node=node;
            this.wt=wt;
        }
    }
    public static List<Integer> shortestPath(int n, int m, int edges[][]){
        //  Code Here.
        List<List<Pair>> adj=new ArrayList<>();
        for(int i=0;i<n+1;i++)
        {
            adj.add(new ArrayList<Pair>());
        }
        for(int i=0;i<m;i++)
        {
            adj.get(edges[i][0]).add(new Pair(edges[i][1],edges[i][2]));
            adj.get(edges[i][1]).add(new Pair(edges[i][0],edges[i][2]));
        }
        PriorityQueue<Pair> pq=new PriorityQueue<>((a,b)-> a.wt-b.wt);
        int parent[]=new int[n+1];
        int dist[]=new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[1]=0;
        for(int i=0;i<n+1;i++) parent[i]=i;
        pq.add(new Pair(1,0));
        while(!pq.isEmpty())
        {
            // System.out.print("JJ");
            Pair p=pq.poll();
            int node=p.node;
            int wt=p.wt;
            for(Pair it:adj.get(node))
            {
                int next_node=it.node;
                int next_wt=it.wt;
                if(next_wt+wt < dist[next_node])
                {
                    parent[next_node]=node;
                    dist[next_node]=next_wt+wt;
                    pq.add(new Pair(next_node,dist[next_node]));
                }
            }
        }
        List<Integer> list=new ArrayList<>();
        if(dist[n]==Integer.MAX_VALUE)
        {
            list.add(-1);
            return list;
        }
        list.add(dist[n]);
        int node=n;
        while(parent[node]!=node)
        {
            list.add(1,node);
            node=parent[node];
        }
        list.add(1,1);
        return list;
        
    }
    public static void main(String args[])
    {
        // [[1,2,2], [2,5,5], [2,3,4], [1,4,1],[4,3,3],[3,5,1]]
        int edges[][]={{1,2,2},{2,5,5},{2,3,4},{1,4,1},{4,3,3},{3,5,1}};
         System.out.print(shortestPath(5, 6, edges));
    }
}