/*
A game has n planets, connected by m teleporters. Two planets a and b belong
to the same kingdom exactly when there is a route both from a to b and from b to a.
Your task is to determine for each planet its kingdom.

Input:
- The first input line has two integers n and m: the number of planets and teleporters.
- The planets are numbered 1, 2, ..., n.
- After this, there are m lines describing the teleporters.
- Each line has two integers a and b: you can travel from planet a to planet b through a teleporter.

Output:
- First print an integer k: the number of kingdoms.
- After this, print for each planet a kingdom label between 1 and k.
- You can print any valid solution.

Constraints:
1 ≤ n ≤ 10^5
1 ≤ m ≤ 2⋅10^5
1 ≤ a, b ≤ n

Example:
Input:
5 6
1 2
2 3
3 1
3 4
4 5
5 4

Output:
2
1 1 1 2 2
*/




import java.util.*;

public class Planets_and_Kingdoms {
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
        System.out.println(ans.size());
        List<Integer> fine = new ArrayList<>(Collections.nCopies(n+1, 0));
        for(int i=0;i<ans.size();i++){
            for(int j:ans.get(i)) fine.set(j,i+1);
        }

        for(int i=1;i<=n;i++) System.out.print(fine.get(i)+" ");
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        solve(sc);
    }
}
