/*
Flight Routes Check

There are n cities and m flight connections. Your task is to check if you can travel from any city to any other city using the available flights.

Input:
The first input line has two integers n and m: the number of cities and flights. The cities are numbered 1, 2, ..., n.
After this, there are m lines describing the flights. Each line has two integers a and b: there is a flight from city a to city b.
All flights are one-way flights.

Output:
Print "YES" if all routes are possible, and "NO" otherwise.
In the latter case also print two cities a and b such that you cannot travel from city a to city b.
If there are several possible solutions, you can print any of them.

Constraints:
1 ≤ n ≤ 10^5
1 ≤ m ≤ 2 × 10^5
1 ≤ a, b ≤ n

Example:
Input:
4 5
1 2
2 3
3 1
1 4
3 4

Output:
NO
4 2
*/



import java.util.*;

public class Flight_Routes_Check {
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

        if(ans.size()==1){
            System.out.println("YES");
        } else {
            System.out.println("NO");
            System.out.println(ans.get(1).get(0)+" "+ans.get(0).get(0));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        solve(sc);
    }
}
