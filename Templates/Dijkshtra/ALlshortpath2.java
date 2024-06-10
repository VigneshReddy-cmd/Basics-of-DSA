import java.util.*;
public class ALlshortpath2 {
    static class Pair2
    {
        String path;
        int node;
        int wt;
        public Pair2(String path,int node,int wt)
        {
          this.path=path;
          this.node=node;
          this.wt=wt;
        }
    }
    private static List<String> gen_paths(int n, int[][] edges, int src, int dest) {
        List<List<Pair2>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            adj.get(edges[i][0]).add(new Pair2("", edges[i][1], edges[i][2]));
            adj.get(edges[i][1]).add(new Pair2("", edges[i][0], edges[i][2]));
        }
    
        Queue<Pair2> pq = new LinkedList<>();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        String s = Integer.toString(src);
        pq.add(new Pair2(s, src, 0));
        int min = 0;
        List<String> list = new ArrayList<>();
        boolean flag=false;
        while (!pq.isEmpty()) {
            Pair2 p2 = pq.poll();
            String path = p2.path;
            int node = p2.node;
            int wt = p2.wt;
            int last = Character.getNumericValue(path.charAt(path.length() - 1));
            if (last == dest) {
                if (!flag) {
                    flag = true;
                    min = wt;
                }
                if (min == wt) {
                    // System.out.println(p2.path+" "+p2.node+" "+p2.wt);
                    list.add(path);
                }
            }
    
            for (Pair2 p : adj.get(node)) {
                int nxt_node = p.node;
                int nxt_wt = p.wt;
                String str = Integer.toString(nxt_node);
                if (nxt_wt + wt <=dist[nxt_node]) {
                    dist[nxt_node] = nxt_wt + wt;
                    pq.add(new Pair2(path + str, nxt_node, nxt_wt + wt));
                }
            }
        }
        return list;
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
        List<String> list=gen_paths(6, edges, 0, 5);
        HashSet<String> set=new HashSet<>();
        for(String str:list)
        {
            int n=str.length();
            for(int i=0;i<n-1;i++)
            {
                set.add(str.substring(i,i+2));
            }
        }
        boolean[] res=new boolean[edges.length];
        for(int i=0;i<edges.length;i++)
        {
            String s1=Integer.toString(edges[i][0]);
            s1+=Integer.toString(edges[i][1]);
            String s2=Integer.toString(edges[i][1]);
            s2+=Integer.toString(edges[i][0]);
            if(set.contains(s2) || set.contains(s1)) res[i]=true;
        }
        System.out.print(Arrays.toString(res));
    }
}
