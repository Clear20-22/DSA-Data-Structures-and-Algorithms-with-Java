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

public class MST_CHECK {
    static class Pair implements Comparable<Pair>{
        int u,v,w,pos;
        Pair(int u,int v,int w,int pos){
            this.u = u;
            this.v = v;
            this.w = w;
            this.pos = pos;
        }
        Pair(int v,int w,int pos){
            this.v = v;
            this.w = w;
            this.pos = pos;
        }
        Pair(int v,int w){
            this.v = v;
            this.w = w;
        }
        @Override
        public int compareTo(Pair o) {
            return this.w - o.w;
        }
    }
    public static void main(String[] args) {
        Scanner take = new Scanner(System.in);
        int n = take.nextInt(), m= take.nextInt();

        int u,v,w;
        List<List<Pair>> adj = new ArrayList<>();
        List<Pair> store = new ArrayList<>();
        int[] vis = new int[n+1];
        Arrays.fill(vis, 0);
        for(int i=0;i<=n;i++) adj.add(new ArrayList<>());

        for(int i =0;i<m;i++){
            u = take.nextInt();
            v = take.nextInt();
            w = take.nextInt();
            adj.get(u).add(new Pair(v, w,i));
            adj.get(v).add(new Pair(u, w,i));
            store.add(new Pair(u, v, w, i));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(1, 0,m));
        int[] yes = new int[m+1];
        Arrays.fill(yes, 0);
        while(!pq.isEmpty()){
            Pair ov = pq.poll();
            if(vis[ov.v] == 1) continue;
            vis[ov.v] = 1;
            yes[ov.pos] = 1;
            for(Pair nv:adj.get(ov.v)){
                if(vis[nv.v] == 0){
                    pq.add(new Pair(nv.v, nv.w,nv.pos));
                    
                }
            }
        }

        for(int i=0;i<m;i++)
        {
            if(yes[i] == 1){
                System.out.println("YES");
            }
            else System.out.println("NO");
        }
        take.close();
    } 
 }