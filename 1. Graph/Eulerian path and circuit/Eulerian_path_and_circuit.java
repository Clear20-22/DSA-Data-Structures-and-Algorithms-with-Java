/**
 * Eulerian_path_and_circuit
 * 
 * This program determines whether an undirected graph contains:
 *  - 0 : No Eulerian Path or Circuit
 *  - 1 : Eulerian Path only (but not a circuit)
 *  - 2 : Eulerian Circuit
 * 
 * Input Format:
 * n m               // number of vertices and edges
 * x1 y1             // m lines: undirected edges between x and y
 * ...
 * xm ym
 * start             // starting node for DFS traversal
 * 
 * Output:
 * 0 (no Eulerian path or circuit)
 * 1 (Eulerian path exists)
 * 2 (Eulerian circuit exists)
 */

import java.util.*;

public class Eulerian_path_and_circuit {
    public static void main(String[] args) {
        Scanner take = new Scanner(System.in);
        List<List<Integer>> adj = new ArrayList<>();

        int n = take.nextInt();
        int m = take.nextInt();

        int x,y;
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        for(int i=0;i<m;i++){
            x = take.nextInt();
            y = take.nextInt();

            adj.get(x).add(y);
            adj.get(y).add(x);
        }

        int[] vis = new int[n];

        int start = take.nextInt();

        dfs(start, vis, adj);
        System.out.println(FinalAns(adj, vis));
        take.close();
    }

    static int FinalAns(List<List<Integer>> adj,int[] vis){
        int odd = 0;
        for(int i = 0;i<vis.length;i++){
            if(vis[i] == 0 && adj.get(i).size() > 0) return 0;
            else if(adj.get(i).size() % 2 != 0) odd++;
        }

        return (odd == 2)? 1:2;
    }

    static void dfs(int v,int[] vis,List<List<Integer>> adj){
        vis[v] = 1;

        for(int nv:adj.get(v)){
            if(vis[nv] == 0)
            dfs(nv, vis, adj);
        }
    }
}
