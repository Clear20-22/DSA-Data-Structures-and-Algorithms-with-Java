/******************************************************************************
 * Problem: 1160. Network
 *
 * Description:
 *   Andrew is establishing a new network of N hubs in his company.
 *   He can choose from M possible cable connections between hubs.
 *   The goal is to create a plan that connects all hubs (i.e., forms a connected
 *   network) using only the allowed connections, such that the maximum cable
 *   length used is minimized.
 *
 *   Not all hubs can be connected directly due to compatibility and geometry
 *   constraints. However, it's guaranteed that at least one way exists to
 *   connect all hubs.
 *
 * Input:
 *   - The first line contains two integers:
 *       N (2 ≤ N ≤ 1000) — the number of hubs
 *       M (1 ≤ M ≤ 15000) — the number of possible connections
 *   - The next M lines contain three integers each:
 *       u, v — hubs that can be connected
 *       w — length of the cable (1 ≤ w ≤ 10^6)
 *     Each connection is unique and undirected. No self-loops.
 *
 * Output:
 *   - First line: the maximum cable length used in the final network
 *   - Second line: the number of connections used (should be N - 1)
 *   - Next lines: list of the used connections as pairs of hub numbers
 *
 * Sample Input:
 *   4 6
 *   1 2 1
 *   1 3 1
 *   1 4 2
 *   2 3 1
 *   3 4 1
 *   2 4 1
 *
 * Sample Output:
 *   1
 *   4
 *   1 2
 *   1 3
 *   2 3
 *   3 4
 *
 * Approach:
 *   - This is a classical Minimum Spanning Tree (MST) problem.
 *   - Use Kruskal’s algorithm to select the edges that connect all hubs
 *     while minimizing the maximum edge weight.
 ******************************************************************************/


import java.util.*;

public class Network {

    static class Pair implements Comparable<Pair> {
        int u, v, w;

        Pair(int a, int b, int c) {
            u = a;
            v = b;
            w = c;
        }

        @Override
        public int compareTo(Pair that) {
            return Integer.compare(this.w, that.w);
        }
    }

    static class dsu {
        int[] parent, rank;

        dsu(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 1; i <= n; i++) parent[i] = i;
        }

        int find(int v) {
            if (v != parent[v]) parent[v] = find(parent[v]);
            return parent[v];
        }

        boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB) return false;

            if (rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB;
            } else if (rank[rootA] > rank[rootB]) {
                parent[rootB] = rootA;
            } else {
                parent[rootB] = rootA;
                rank[rootA]++;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Scanner take = new Scanner(System.in);

        int n = take.nextInt();
        int m = take.nextInt();

        List<Pair> adj = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int x = take.nextInt();
            int y = take.nextInt();
            int w = take.nextInt();
            adj.add(new Pair(x, y, w));
        }

        Collections.sort(adj);
        dsu d = new dsu(n);
        List<Pair> result = new ArrayList<>();
        int maxEdge = 0;

        for (Pair edge : adj) {
            if (d.union(edge.u, edge.v)) {
                result.add(edge);
                maxEdge = Math.max(maxEdge, edge.w);
                if (result.size() == n - 1) break;
            }
        }

        System.out.println(maxEdge);
        System.out.println(result.size());
        for (Pair p : result) {
            System.out.println(p.u + " " + p.v);
        }

        take.close();
    }
}
