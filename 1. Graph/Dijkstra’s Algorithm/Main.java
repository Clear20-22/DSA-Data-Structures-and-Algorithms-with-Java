/*
 * Problem: C. Dijkstra?
 * Time limit per test: 1 second
 * Memory limit per test: 64 megabytes
 * 
 * Description:
 * You are given a weighted undirected graph. The vertices are enumerated from 1 to n. 
 * Your task is to find the shortest path between vertex 1 and vertex n.
 * 
 * Input:
 * - The first line contains two integers n and m (2 ≤ n ≤ 10^5, 0 ≤ m ≤ 10^5),
 *   where n is the number of vertices and m is the number of edges.
 * - The next m lines each contain three integers ai, bi, wi (1 ≤ ai, bi ≤ n, 1 ≤ wi ≤ 10^6),
 *   representing an undirected edge between ai and bi with weight wi.
 * - The graph may contain loops and multiple edges between the same pair of vertices.
 * 
 * Output:
 * - If there is no path from vertex 1 to vertex n, output -1.
 * - Otherwise, output the shortest path from vertex 1 to vertex n as a sequence of vertices.
 *   If there are multiple such shortest paths, output any of them.
 * 
 * Examples:
 * Input:
 * 5 6
 * 1 2 2
 * 2 5 5
 * 2 3 4
 * 1 4 1
 * 4 3 3
 * 3 5 1
 * 
 * Output:
 * 1 4 3 5
 * 
 * Input:
 * 5 6
 * 1 2 2
 * 2 5 5
 * 2 3 4
 * 1 4 1
 * 4 3 3
 * 3 5 1
 * 
 * Output:
 * 1 4 3 5
 */



import java.util.*;

public class Main {
    static class Node {
        long v, w; 

        Node(long v, long w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) {
        Scanner take = new Scanner(System.in);

        int n = take.nextInt();
        int m = take.nextInt();

        ArrayList<ArrayList<Node>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int x = take.nextInt();
            int y = take.nextInt();
            int z = take.nextInt();
            adj.get(x).add(new Node(y, z));
            adj.get(y).add(new Node(x, z));
        }

        long[] dis = new long[n + 1];
        int[] path = new int[n + 1];
        Arrays.fill(dis, Long.MAX_VALUE);
        Arrays.fill(path, -1);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(p -> p.w));
        dis[1] = 0;
        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            long u = cur.v;
            long d = cur.w;

            if (d > dis[(int)u]) continue;

            for (Node nei : adj.get((int)u)) {
                long v = nei.v;
                long w = nei.w;

                if (dis[(int)v] > dis[(int)u] + w) {
                    dis[(int)v] = dis[(int)u] + w;
                    path[(int)v] = (int)u;
                    pq.offer(new Node(v, dis[(int)v]));
                }
            }
        }

        if (dis[n] == Long.MAX_VALUE) {
            System.out.println(-1);
        } else {
            ArrayList<Integer> ans = new ArrayList<>();
            for (int cur = n; cur != -1; cur = path[cur]) {
                ans.add(cur);
            }
            Collections.reverse(ans);
            for (int x : ans) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }
}
