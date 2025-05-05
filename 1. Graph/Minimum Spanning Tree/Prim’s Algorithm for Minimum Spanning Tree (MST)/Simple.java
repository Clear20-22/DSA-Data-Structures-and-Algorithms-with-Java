// package Minimum Spanning Tree.Prim’s Algorithm for Minimum Spanning Tree (MST);

/*
Minimum Spanning Tree
Difficulty: Medium | Accuracy: 47.82% | Submissions: 160K+ | Points: 4 | Average Time: 25m

Given a weighted, undirected, and connected graph with V vertices and E edges,
your task is to find the sum of the weights of the edges in the Minimum Spanning Tree (MST) of the graph.

The graph is represented by an adjacency list, where each element adj[i] is a vector containing vectors of integers.
Each vector represents an edge, with the first integer denoting the endpoint of the edge
and the second integer denoting the weight of the edge.

Input:
3 3
0 1 5
1 2 3
0 2 1

Output:
4

Explanation:
The Spanning Tree resulting in a weight of 4 is shown above.

Input:
2 1
0 1 5

Output:
5

Explanation:
Only one Spanning Tree is possible which has a weight of 5.

Constraints:
- 2 ≤ V ≤ 1000
- V - 1 ≤ E ≤ (V * (V - 1)) / 2
- 1 ≤ w ≤ 1000
- The graph is connected and doesn't contain self-loops or multiple edges.
*/


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Simple {
    static class Pair implements Comparable<Pair>{
        int v,w;
        Pair(int a,int b){
            v = a;
            w = b;
        }

        @Override
        public int compareTo(Pair that){
            return this.w - that.w;
        }
    }
    public static void main(String[] args) {
        Scanner take = new Scanner(System.in);

        int n = take.nextInt();
        int m = take.nextInt();

        int x,y,w;

        List<List<Pair>> adj = new ArrayList<>();
        int[] vis = new int[n];

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        for(int i=0;i<m;i++){
            x = take.nextInt();
            y = take.nextInt();
            w = take.nextInt();

            adj.get(x).add(new Pair(y, w));
            adj.get(y).add(new Pair(x, w));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>();

        pq.add(new Pair(0, 0));
        int ans = 0;

        while(!pq.isEmpty()){
            Pair old = pq.poll();
            if(vis[old.v] == 1) continue;
            vis[old.v] = 1; 
            ans += old.w;

            for(Pair nw: adj.get(old.v)){
                if(vis[nw.v] == 0){
                    pq.add(new Pair(nw.v, nw.w));
                }
            }
        }
        System.out.println(ans);

        take.close();
    }
    
}
