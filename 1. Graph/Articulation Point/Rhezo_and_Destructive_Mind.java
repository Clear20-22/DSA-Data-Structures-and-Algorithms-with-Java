/*
Problem:
Rhezo has bought a graph with N nodes and M edges. He has a destructive mind and often deletes some nodes (and of course its edges to other nodes of the graph).

He will ask you Q queries. Each query is denoted by an integer X, meaning that he will delete the node X. Since he has a destructive mind, he gets satisfied if the new graph with the deleted node has more number of connected components than the original graph.

If satisfied, he wants you to print "Satisfied" (without quotes) and the number of edges that were removed when deleting node X, else print "Not Satisfied" (without quotes). All queries are different, and the original graph is restored after each query.

Input:
- First line contains two integers N and M — the number of nodes and edges.
- Each of the next M lines contains two integers A and B, meaning that there is an edge between node A and node B.
- Next line contains a single integer Q, denoting the number of queries.
- Each of the next Q lines contains a single integer X as described in the problem statement.

Output:
For each of the Q queries:
- If Rhezo is satisfied (i.e., number of connected components increases after deletion), print "Satisfied" and the number of edges deleted (separated by space).
- Otherwise, print "Not Satisfied".

Constraints:
- 1 ≤ N ≤ 10^5
- 0 ≤ M ≤ 10^5
- 1 ≤ Q ≤ N
- 1 ≤ X ≤ N

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
Not Satisfied
Satisfied 2
Satisfied 3
Not Satisfied
Not Satisfied

Explanation:
For the second query, if we remove node 2, the graph disconnects and 2 connected components are formed. 
One having only {1} and the other having {3, 4, 5}. Clearly, the number of edges removed is 2.
*/

import java.util.*;
public class Rhezo_and_Destructive_Mind {
    public static void main(String args[] ) throws Exception {
        Scanner take = new Scanner(System.in);

        int n = take.nextInt(), m = take.nextInt();

        List<List<Integer>> adj = new ArrayList<>();
        List<List<Integer>> scc = new ArrayList<>();

        for(int i=0;i<=n;i++){
            adj.add(new ArrayList<>());
            scc.add(new ArrayList<>());
        }

        int u,v;

        for(int i=0;i<m;i++){
            u = take.nextInt();
            v = take.nextInt();

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] vis = new int[n+1];
        int[] tin = new int[n+1];
        int[] low = new int[n+1];
        Map<Integer,Integer> ans = new HashMap<>();

        for(int i=1;i<=n;i++){
            if(vis[i] == 0){
                dfs(i, -1, adj, vis, tin, low, ans);
            }
        }

        int t = take.nextInt();
        int x;
        for(int i=0;i<t;i++){
            x = take.nextInt();

            if(!ans.containsKey(x)){
                System.out.println("Not Satisfied");
            } else {
                System.out.println("Satisfied "+ans.get(x));
            }
        }
    }

   static int time = 1;

    static void dfs(int v,int parent,List<List<Integer>> adj,int[] vis,int[] tin,int[] low,Map<Integer,Integer> ans){
        vis[v] = 1;
        low[v] = tin[v] = time++;
        int child = 0;
        for(int nv:adj.get(v)){
            if(nv == parent) continue;
            if(vis[nv] == 0){
                dfs(nv,v,adj,vis,tin,low,ans);
                low[v] = Math.min(low[nv],low[v]);
                if(low[nv] >= tin[v] && parent!=-1){
                    ans.put(v,adj.get(v).size());
                }
                child++;
            } else {
                low[v] = Math.min(low[v],tin[nv]);
            }
        }

        if(child > 1 && parent == -1) ans.put(v,adj.get(v).size());

    }
}

