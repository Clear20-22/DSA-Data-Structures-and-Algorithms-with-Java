// package Minimum Spanning Tree.Kruskal’s Minimum Spanning Tree (MST) Algorithm;
import java.util.*;

class Edge implements Comparable<Edge> {
    int u, v, w;
    Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    // Sort by weight
    public int compareTo(Edge that) {
        return this.w - that.w;
    }
}

public class KruskalMST {

    // Disjoint Set Union (Union-Find) with path compression
    static class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for(int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px == py) return false; // same set → would form a cycle

            if (rank[px] < rank[py]) parent[px] = py;
            else if (rank[px] > rank[py]) parent[py] = px;
            else {
                parent[py] = px;
                rank[px]++;
            }
            return true;
        }
    }

    public static int kruskalMST(int V, List<Edge> edges) {
        Collections.sort(edges); // Sort all edges by weight
        DSU dsu = new DSU(V);
        int mstWeight = 0;

        for (Edge e : edges) {
            if (dsu.union(e.u, e.v)) {
                mstWeight += e.w;
            }
        }
        return mstWeight;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: V vertices, E edges
        int V = sc.nextInt();
        int E = sc.nextInt();

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            edges.add(new Edge(u, v, w));
        }

        int result = kruskalMST(V, edges);
        System.out.println(result);
        sc.close();
    }
}
