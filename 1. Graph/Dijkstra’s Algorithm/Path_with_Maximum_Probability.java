/*
Leetcode Problem 1514: Path with Maximum Probability
Difficulty: Medium

You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where
edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].

Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.

If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.

Examples:

Input: 
n = 3, edges = [[0,1],[1,2],[0,2]], 
succProb = [0.5,0.5,0.2], 
start = 0, end = 2
Output: 0.25000
Explanation: Two paths exist: 0→2 (0.2) and 0→1→2 (0.5×0.5 = 0.25). Max = 0.25

Input: 
n = 3, edges = [[0,1],[1,2],[0,2]], 
succProb = [0.5,0.5,0.3], 
start = 0, end = 2
Output: 0.30000

Input: 
n = 3, edges = [[0,1]], 
succProb = [0.5], 
start = 0, end = 2
Output: 0.00000
Explanation: No path exists from node 0 to node 2.

Constraints:
- 2 <= n <= 10^4
- 0 <= start, end < n
- start != end
- 0 <= a, b < n
- a != b
- 0 <= succProb.length == edges.length <= 2 * 10^4
- 0 <= succProb[i] <= 1
- There is at most one edge between any two nodes
*/

import java.util.*;

public class Path_with_Maximum_Probability{

    public static void main(String[] args) {
        Scanner take = new Scanner(System.in);
        int n = take.nextInt();

        ArrayList<int[]> store = new ArrayList<>();

        for(int i=0;i<n;i++){
            int x = take.nextInt();
            int y = take.nextInt();

            store.add(new int[]{x,y});
        }

        ArrayList<Double> succ = new ArrayList<>();

        for(int i=0;i<n;i++){
            Double x = take.nextDouble();
            succ.add(x);
        }

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
 
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());
        int idx = 0;

        for(int[] node:store){
            adj.get(node[0]).add(new Pair(node[1], succ.get(idx)));
            adj.get(node[1]).add(new Pair(node[0], succ.get(idx++)));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)-> Double.compare(b.prob, a.prob));
        double[] dis = new double[n];
        Arrays.fill(dis, 0.0);

        int start = take.nextInt();
        int end = take.nextInt();

        dis[start] = 1.0;
        pq.add(new Pair(start, 1.0));

        while(!pq.isEmpty()){
            Pair node = pq.poll();

            if(node.v == end) {
                System.out.println(node.prob);
                return;
            }

            for(Pair nv:adj.get(node.v)){
                if(dis[nv.v] < dis[node.v]*nv.prob){
                    dis[nv.v] = dis[node.v]*nv.prob;
                    pq.add(new Pair(nv.v,dis[nv.v]));
                }
            }
        }

        System.out.println(0.0);
        
        take.close();
    }

    static class Pair{
        double prob;
        int v,u;
        Pair(int c,double p){
            v = c;
            prob = p;
        }

        Pair(int a,int b){
            this.u = a;
            this.v = b;
        }
    }
}