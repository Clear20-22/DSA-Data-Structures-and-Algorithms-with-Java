/*
Problem: Police Checkposts

Your city has n junctions. There are m one-way roads between the junctions.
As the mayor of the city, you have to ensure the security of all the junctions.

To ensure the security, you have to build some police checkposts. 
Checkposts can only be built in a junction. A checkpost at junction i 
can protect junction j if either i = j, or the police patrol car can 
go to j from i and then come back to i.

Building checkposts costs some money. As some areas of the city are 
more expensive than others, building a checkpost at some junctions 
might cost more money than others.

You have to determine the minimum possible money needed to ensure the 
security of all the junctions. Also, you have to find the number of 
ways to ensure the security in minimum price and in addition in 
minimum number of checkposts. Two ways are different if any of the 
junctions contains a checkpost in one of them and does not contain it in the other.

Input:
- First line: An integer n, number of junctions (1 ≤ n ≤ 10^5).
- Second line: n space-separated integers where the ith integer is the cost 
  of building a checkpost at the ith junction (0 ≤ cost ≤ 10^9).
- Third line: An integer m (0 ≤ m ≤ 3·10^5), the number of roads.
- Next m lines: Each contains two integers u and v (1 ≤ u, v ≤ n; u ≠ v) 
  indicating a one-way road from u to v.

Note: There will not be more than one road between two nodes in the same direction.

Output:
Print two integers separated by a space:
1. The minimum total cost required to ensure security.
2. The number of ways to do it modulo 10^9 + 7 (1000000007).
*/


import java.util.*;

public class Main {
    static final long MOD = 1000000007;
    static final long INF = Long.MAX_VALUE;

    static List<List<Integer>> adj, scc, ans;
    static boolean[] vis, vis1;
    static Stack<Integer> st;
    static long[] values;

    static void dfs(int v) {
        vis[v] = true;
        for (int nv : adj.get(v)) {
            if (!vis[nv]) {
                dfs(nv);
            }
        }
        st.push(v);
    }

    static void dfs1(int v, List<Integer> store) {
        vis1[v] = true;
        for (int nv : scc.get(v)) {
            if (!vis1[nv]) {
                dfs1(nv, store);
            }
        }
        store.add(v);
    }

    public static void solve(Scanner sc) {
        int n = sc.nextInt();
        values = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            values[i] = sc.nextLong();
        }

        int m = sc.nextInt();

        adj = new ArrayList<>();
        scc = new ArrayList<>();
        ans = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
            scc.add(new ArrayList<>());
        }

        vis = new boolean[n + 1];
        vis1 = new boolean[n + 1];
        st = new Stack<>();

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            adj.get(a).add(b);
            scc.get(b).add(a); 
        }

        for (int i = 1; i <= n; i++) {
            if (!vis[i]) {
                dfs(i);
            }
        }

        while (!st.isEmpty()) {
            int v = st.pop();
            if (!vis1[v]) {
                List<Integer> store = new ArrayList<>();
                dfs1(v, store);
                ans.add(store);
            }
        }

        long minNeed = 0, path = 1;

        for (List<Integer> component : ans) {
            long minVal = INF;
            int count = 0;

            for (int node : component) {
                minVal = Math.min(minVal, values[node]);
            }

            for (int node : component) {
                if (values[node] == minVal) count++;
            }

            minNeed += minVal;
            path = (path * count) % MOD;
        }

        System.out.println(minNeed + " " + path);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        solve(sc);
    }
}
