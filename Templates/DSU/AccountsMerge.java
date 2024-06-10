
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
class Disjoint {
    int[] parent;
    int[] size;

    public Disjoint(int n) {
        parent = new int[n + 1];
        size = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find_par(int node) {
        if (node == parent[node]) {
            return node;
        }
        parent[node] = find_par(parent[node]); // Path Compression
        return parent[node];
    }

    public void union_bySize(int u, int v) {
        int ulp_u = find_par(u);
        int ulp_v = find_par(v);
        if (ulp_u != ulp_v) {
            if (size[ulp_u] < size[ulp_v]) {
                parent[ulp_u] = ulp_v;
                size[ulp_v] += size[ulp_u];
            } else {
                parent[ulp_v] = ulp_u;
                size[ulp_u] += size[ulp_v];
            }
        }
    }
}

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap<>();
        Map<String, Integer> emailToID = new HashMap<>();
        int id = 0;

        for (List<String> account : accounts) {
            String name = account.get(0);
            int size = account.size();
            for (int i = 1; i < size; i++) {
                String email = account.get(i);
                if (!emailToID.containsKey(email)) {
                    emailToID.put(email, id++);
                    emailToName.put(email, name);
                }
            }
        }

        Disjoint set = new Disjoint(id);
        for (List<String> account : accounts) {
            String firstEmail = account.get(1);
            int firstID = emailToID.get(firstEmail);
            int size = account.size();
            for (int i = 2; i < size; i++) {
                String nextEmail = account.get(i);
                int nextID = emailToID.get(nextEmail);
                set.union_bySize(firstID, nextID);
            }
        }

        Map<Integer, List<String>> mergedAccounts = new HashMap<>();
        for (String email : emailToID.keySet()) {
            int index = set.find_par(emailToID.get(email));
            mergedAccounts.computeIfAbsent(index, key -> new ArrayList<>()).add(email);
        }

        List<List<String>> result = new ArrayList<>();
        for (List<String> emails : mergedAccounts.values()) {
            Collections.sort(emails);
            String name = emailToName.get(emails.get(0));
            List<String> account = new ArrayList<>();
            account.add(name);
            account.addAll(emails);
            result.add(account);
        }

        return result;
    }
}
