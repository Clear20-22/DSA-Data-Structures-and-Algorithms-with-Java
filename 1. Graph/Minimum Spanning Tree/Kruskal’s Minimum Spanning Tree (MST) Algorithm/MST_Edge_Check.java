/*
CSES Problem: MST Edge Check

Time limit: 1.00 s
Memory limit: 512 MB

Problem Description:
--------------------
Given an undirected weighted graph, determine for each edge if it can be included 
in a minimum spanning tree (MST). The graph is connected and simple, meaning each 
pair of nodes has at most one edge between them.

Input:
------
- The first line contains two integers n and m:
    n: the number of nodes (1 ≤ n ≤ 10^5)
    m: the number of edges (1 ≤ m ≤ 2 * 10^5)

- The next m lines each contain three integers a, b, w:
    a and b are nodes connected by an edge with weight w (1 ≤ w ≤ 10^9)

Output:
-------
- For each edge in the input order, print:
    "YES" if it can be part of any MST,
    "NO" otherwise.

Constraints:
------------
- The graph is guaranteed to be connected.
- Each edge appears at most once.
- Edge weights are positive integers.

Example Input:
--------------
5 6
1 2 4
1 3 2
2 4 2
3 4 1
3 5 3
4 5 3

Example Output:
---------------
NO
YES
YES
YES
YES
YES

Explanation:
------------
The edge 1-2 with weight 4 cannot be part of any MST as there are other edges that 
connect the components with a smaller total weight. All other edges can be included 
in at least one MST.
*/

import java.util.*;

public class MST_Edge_Check {
    static class Edge implements Comparable<Edge> {
        int u, v, w, idx;
        Edge(int u, int v, int w, int idx) {
            this.u = u;
            this.v = v;
            this.w = w;
            this.idx = idx;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }

    static class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px == py) return;
            if (rank[px] < rank[py]) {
                parent[px] = py;
            } else {
                parent[py] = px;
                if (rank[px] == rank[py]) {
                    rank[px]++;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        Edge[] edges = new Edge[m];
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            edges[i] = new Edge(u, v, w, i);
        }

        Arrays.sort(edges);
        String[] result = new String[m];
        DSU dsu = new DSU(n);

        int i = 0;
        while (i < m) {
            int j = i;
            List<Edge> group = new ArrayList<>();
            while (j < m && edges[j].w == edges[i].w) {
                group.add(edges[j]);
                j++;
            }

            List<Edge> canUse = new ArrayList<>();
            for (Edge e : group) {
                if (dsu.find(e.u) != dsu.find(e.v)) {
                    result[e.idx] = "YES";
                    canUse.add(e);
                } else {
                    result[e.idx] = "NO";
                }
            }

            for (Edge e : canUse) {
                dsu.union(e.u, e.v);
            }
            i = j;
        }

        for (String res : result) {
            System.out.println(res);
        }
        sc.close();
    }
}
