/*Problem statement:
You are given a directed graph with N nodes and E edges. Your task is to analyze the graph based on a given node X and provide specific information.

Input:
The first line contains two integers N (number of nodes) and E (number of edges).
The next E lines each contain two integers A and B, indicating a directed edge from node A to node B.
The last line contains a single integer X (1 ≤ X ≤ N), representing the node from which the analysis is to be performed.
Output:
Print a single integer representing the number of adjacent nodes of node X (i.e., nodes to which X has a directed edge).
Print N space-separated integers, where the i-th value represents the minimum number of edges required to reach node i from X. If a node is not reachable from X, print -1 for that node. */

import java.util.*;

class Graph{
    ArrayList<ArrayList<Integer>> graph;
    int sz;

    Graph(int n){
        this.sz = n;
        graph = new ArrayList<>(n);
        for(int i=0;i<n;i++){
            graph.add(new ArrayList<>());
        }
    }

    void addEdgie(int u,int v){
        graph.get(u).add(v);
        graph.get(v).add(u);
    }

    int bfs(int sc,int end){

        if(sc == end)
        return 0;
        
        boolean[] visited = new boolean[sz];
        Queue<Integer> q = new LinkedList<>();

        int ans = 0;

        q.add(sc);
        visited[sc] = true;

        while(!q.isEmpty()){
            int n = q.size();
            ans++;

            for(int i=0;i<n;i++){
                int ov = q.poll();

                for(int nv: graph.get(ov)){
                    if(nv == end)
                    return ans;

                    if(!visited[nv]){
                        visited[nv] = true;
                        q.add(nv);
                    }
                }
            }
        }
        
        return -1;
    }

}

public class BFS{
    public static void main(String[] args) {
        Scanner take = new Scanner(System.in);
        int n = take.nextInt();
        int m = take.nextInt();
        int u,v;
        Graph ans = new Graph(n+1);
        for(int i=0;i<m;i++){
            u = take.nextInt();
            v = take.nextInt();

            ans.addEdgie(u, v);
        }

        int k = take.nextInt();

        System.out.println("Child of  source: "+ans.graph.get(k).size());

        for(int i=1;i<=n;i++)
        System.out.println(k+"->"+i+": "+ans.bfs(k, i));
        take.close();
    }
}