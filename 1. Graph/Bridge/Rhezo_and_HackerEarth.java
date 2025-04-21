/*
Problem:
Rhezo has just joined HackerEarth as an intern. Like most companies, HackerEarth too has a lot of computers and some departments.
For efficient communication, it is very important that every computer can connect with each other in the same department,
with the connection not being necessarily direct.

When each computer can connect with any other computer in the same department, that department is said to be happy.
Initially all departments are happy.

Rhezo's friend Lonewolf wants to have some fun, and will cut some connection between any two computers for Q days.
You being the Happy Manager of HackerEarth, need to tell for each of the Q days, if some department becomes unhappy.

Input:
- First line contains two integers N and M, denoting the number of computers and number of wire connections at HackerEarth.
- Each of the next M lines contains two integers u and v, meaning that computer number u is connected to computer number v.
- Next line contains an integer Q, denoting the number of days Lonewolf cuts a connection between 2 computers at HackerEarth.
- Each of the next Q lines contains a single integer P (1-based index), which means that connection at index P in the input list of edges is cut.

Output:
- For each of the Q days, print:
    - "Happy" if all departments remain connected.
    - "Unhappy" if removing the connection causes any department to become disconnected.

Constraints:
- 1 <= N <= 10^5
- 1 <= M <= 10^5
- 1 <= Q <= M

Sample Input:
5 5
1 2
2 3
3 4
4 5
3 5
5
1
2
3
4
5

Sample Output:
Unhappy
Unhappy
Happy
Happy
Happy

Explanation:
For the 3rd query, when we delete the connection between computer 3 and computer 4,
these two computers can still connect via this connection: 3 - 5 - 4.
So the department remains connected and is still happy.
*/


import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner take = new Scanner(System.in);

        int n = take.nextInt();
        int m = take.nextInt();

        List<List<Integer>> adj = new ArrayList<>();
        List<int[]> store = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int u = take.nextInt() - 1;
            int v = take.nextInt() - 1;
            adj.get(u).add(v);
            adj.get(v).add(u);
            store.add(new int[]{u, v});
        }

        int[] vis = new int[n];
        int[] tin = new int[n];
        int[] low = new int[n];
        int[] time = new int[]{1}; 

        for (int i = 0; i < n; i++) {
            if (vis[i] == 0) {
                dfs(i, -1, adj, vis, tin, low, ans, time);
            }
        }

        int x = take.nextInt();

        for (int i = 0; i < x; i++) {
            int y = take.nextInt();

            if (y < 1 || y > store.size()) {
                System.out.println("Invalid query");
                continue;
            }

            int a = store.get(y - 1)[0];
            int b = store.get(y - 1)[1];
            boolean found = false;

            for (List<Integer> edge : ans) {
                int u = edge.get(0), v = edge.get(1);
                if ((u == a && v == b) || (u == b && v == a)) {
                    System.out.println("Unhappy");
                    found = true;
                    break;
                }
            }

            if (!found) System.out.println("Happy");
        }

        take.close();
    }

    static void dfs(int v, int parent, List<List<Integer>> adj, int[] vis, int[] tin, int[] low,
                    List<List<Integer>> ans, int[] time) {
        vis[v] = 1;
        tin[v] = low[v] = time[0]++;

        for (int nv : adj.get(v)) {
            if (nv == parent) continue;

            if (vis[nv] == 0) {
                dfs(nv, v, adj, vis, tin, low, ans, time);
                low[v] = Math.min(low[v], low[nv]);

                if (low[nv] > tin[v]) {
                    ans.add(Arrays.asList(v, nv));
                }
            } else {
                low[v] = Math.min(low[v], tin[nv]);
            }
        }
    }
}
