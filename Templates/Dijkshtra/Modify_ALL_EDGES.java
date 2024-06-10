import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Pair {
    int node;
    int wt;

    public Pair(int node, int wt) {
        this.node = node;
        this.wt = wt;
    }
}

class Solution {
    private ArrayList<ArrayList<Pair>> adj;
    private int src;
    private int end;
    private int n;

    private void make_adj(int[][] edges, boolean flag, int val) {
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        int m = edges.length;
        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];
            if (flag) {
                if (w == -1) {
                    w = val;
                }
                adj.get(u).add(new Pair(v, w));
                adj.get(v).add(new Pair(u, w));
            } else if (w != -1) {
                adj.get(u).add(new Pair(v, w));
                adj.get(v).add(new Pair(u, w));
            }

        }
    }

    private int dijkstra() {
        int[] dist = new int[n];
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.wt - b.wt);
        pq.add(new Pair(src, 0));
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        while (!pq.isEmpty()) {
            int node = pq.peek().node;
            int wt = pq.peek().wt;
            pq.remove();
            if (node == end) {
                return wt;
            }
            for (Pair p : adj.get(node)) {
                int next_node = p.node;
                int next_wt = p.wt;
                if (p.wt + wt < dist[next_node]) {
                    dist[next_node] = wt + p.wt;
                    pq.add(new Pair(next_node, dist[next_node]));
                }
            }
        }
        return -1;
    }

    private int binarySearch(int[][] edges, int low, int high, int target) {
        if (low <= high) {
            int mid = low + (high - low) / 2;
            make_adj(edges, true, mid);
            int dist = dijkstra();
            if (dist == target) {
                return mid;
            } else if (dist > target) {
                return binarySearch(edges, low, mid - 1, target);
            } else {
                return binarySearch(edges, mid + 1, high, target);
            }
        }
        return -1;
    }

    public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
        this.n = n;
        this.src = source;
        this.end = destination;
        make_adj(edges, false, 0);
        int pos_dist = dijkstra();
        if (pos_dist < target && pos_dist>0) {
            return new int[0][0];
        }
        make_adj(edges, true, 1);
        int pos_one_dist = dijkstra();
        if (pos_one_dist > target) {
            return new int[edges.length][3];
        }
        if (pos_one_dist <= target) {
            for (int i = 0; i < edges.length; i++) {
                if (edges[i][2] == -1) {
                    edges[i][2] = 1;
                }
            }
            return edges;
        }
        // Continue with binary search
        int max = (int) (1e9);
        max *= 2;
        int val = binarySearch(edges, 2, max, target);
        if (val == -1) {
            return new int[edges.length][3];
        } else {
            for (int i = 0; i < edges.length; i++) {
                if (edges[i][2] == -1) {
                    edges[i][2] = val;
                }
            }
            return edges;
        }
    }
}
