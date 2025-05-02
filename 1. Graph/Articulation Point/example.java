import java.util.*;
class Solution {
    int timer = 0;

    public ArrayList<Integer> articulationPoints(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] tin = new int[V]; 
        int[] low = new int[V];  
        boolean[] vis = new boolean[V];
        boolean[] isArticulation = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfs(i, -1, vis, tin, low, adj, isArticulation);
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (isArticulation[i]) {
                res.add(i);
            }
        }

        if (res.size() == 0) {
            res.add(-1);
        }

        return res;
    }

    private void dfs(int u, int parent, boolean[] vis, int[] tin, int[] low,
                     ArrayList<ArrayList<Integer>> adj, boolean[] isArticulation) {

        vis[u] = true;
        tin[u] = low[u] = timer++;
        int children = 0;

        for (int v : adj.get(u)) {
            if (v == parent) continue;

            if (!vis[v]) {
                dfs(v, u, vis, tin, low, adj, isArticulation);
                low[u] = Math.min(low[u], low[v]);
                
             
                if (low[v] >= tin[u] && parent != -1) {
                    isArticulation[u] = true;
                }

                children++;
            } else {
                low[u] = Math.min(low[u], tin[v]);
            }
        }


        if (parent == -1 && children > 1) {
            isArticulation[u] = true;
        }
    }
}
