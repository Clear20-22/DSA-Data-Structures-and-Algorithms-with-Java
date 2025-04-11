
/*
You are given a graph consisting of n vertices and m edges. 
It is not guaranteed that the given graph is connected. 

Some edges are already directed and you can't change their direction. 
Other edges are undirected and you have to choose some direction for all these edges.

Your task is to direct undirected edges in such a way that the resulting graph:
- is fully directed
- is acyclic (no directed cycles)

Note:
- You must direct *all* undirected edges.
- You must not reverse any already directed edge.

You have to answer t independent test cases.

-----------------------------
Input:
-----------------------------
- The first line of input contains one integer t (1 ≤ t ≤ 2 * 10^4): 
  the number of test cases.

Each test case:
- First line contains two integers:
  n (2 ≤ n ≤ 2 * 10^5): number of vertices
  m (1 ≤ m ≤ min(2 * 10^5, n(n−1)/2)): number of edges

- Then follow m lines:
  Each line contains three integers: 
  ti, xi, yi
  where:
    - ti = 1 if the edge is directed (from xi to yi)
    - ti = 0 if the edge is undirected (you can choose direction)
    - 1 ≤ xi, yi ≤ n
  Constraints:
    - No self-loops (xi != yi)
    - No multiple edges (each pair (xi, yi) is unique)

Sum of all n over all test cases ≤ 2 * 10^5
Sum of all m over all test cases ≤ 2 * 10^5

-----------------------------
Output:
-----------------------------
For each test case, print:
- "NO" if it is impossible to orient undirected edges to get a DAG (Directed Acyclic Graph)
- Otherwise, print "YES" and then m lines describing the final directed edges.
  Each line: "u v" meaning a directed edge from u to v.

Note:
- Do not change directions of originally directed edges.
- You may output the directed edges in any order.
- Multiple valid outputs may exist.
*/


import java.util.*;

public class Graph{
  static class edge{
    int type,u,v;
    edge(int t,int u,int v){
      this.type = t;
      this.u = u;
      this.v = v;
    }
  }
    public static void main(String[] args) {
      Scanner take = new Scanner(System.in);
      int t = take.nextInt();
      while(t-->0){
        int n = take.nextInt();
        int m = take.nextInt();

        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0;i<=n;i++){
          adj.add(new ArrayList<>());
        }

        int[] indeg = new int[n+1];

        int type,u,v;
        List<edge> store = new ArrayList<>();
        for(int i=0;i<m;i++){
          type = take.nextInt();
          u = take.nextInt();
          v = take.nextInt();

          store.add(new edge(type, u, v));
          if(type == 1){
            adj.get(u).add(v);
            indeg[v]++;
          }
        }

        int[] topo = new int[n+1];
        int idx = 0;
        Queue<Integer> q = new LinkedList<>();
        for(int i=1;i<=n;i++){
          if(indeg[i]==0)
          q.add(i);
        }

        while(q.size() > 0){
          int ov = q.poll();
          topo [ov] = idx++;
          for(int nv:adj.get(ov)){
            indeg[nv]--;
            if(indeg[nv] == 0)
            q.add(nv);
          }
        }

        if(idx < n){
          System.out.println("NO");
          continue;
        }
        System.out.println("Yes");
        for(edge e:store){
          if(e.type == 0){
            if(topo[e.u] < topo[e.v]){
              System.out.println(e.u+" "+e.v);
            } else {
              System.out.println(e.v+" "+e.u);
            }
          } else {
            System.out.println(e.u+" "+e.v);
          }
        }

      }

    } 
}