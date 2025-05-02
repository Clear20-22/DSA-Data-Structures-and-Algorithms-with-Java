/*
743. Network Delay Time
-----------------------

You are given a network of n nodes, labeled from 1 to n.
You are also given `times`, a list of travel times as directed edges, where:
    times[i] = (ui, vi, wi)
        - ui is the source node,
        - vi is the target node,
        - wi is the time it takes for a signal to travel from ui to vi.

We will send a signal from a given node `k`.
Return the **minimum time** it takes for **all** the `n` nodes to receive the signal.
If it is **impossible** for all the n nodes to receive the signal, return -1.

---------------------------------------------------------------
Examples:

Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
Output: 2
Explanation: All nodes receive the signal in 2 units of time.

Input: times = [[1,2,1]], n = 2, k = 1
Output: 1

Input: times = [[1,2,1]], n = 2, k = 2
Output: -1
Explanation: Node 1 never receives the signal from node 2.

---------------------------------------------------------------
Constraints:
- 1 <= k <= n <= 100
- 1 <= times.length <= 6000
- times[i].length == 3
- 1 <= ui, vi <= n
- ui != vi
- 0 <= wi <= 100
- All the pairs (ui, vi) are unique. (i.e., no multiple edges)

Approach Tip:
Use Dijkstra’s algorithm or Bellman-Ford for shortest path computation.
*/


import java.util.*;

public class Network_Delay_Time {
    public static void main(String[] args) {
        Scanner take = new Scanner(System.in);
        int n = take.nextInt();
        int k = take.nextInt();

        int[][] time = new int[n+1][3];

        int x,y,w;

        for(int i = 0;i < n-1;i++){
            x = take.nextInt();
            y = take.nextInt();
            w = take.nextInt();

            time[i] = new int[]{x,y,w};
        }

        System.out.println(networkDelayTime(time, n, k));
        take.close();


    }

    static int networkDelayTime(int[][] times, int n, int k) {

        int[] dist = new int[n+1];
        int inf = Integer.MAX_VALUE;
        Arrays.fill(dist, inf);
        dist[k] = 0;

        for(int i=0;i<n-1;i++){
            for(int[] node:times){
                if(dist[node[0]] != inf && dist[node[0]] + node[2] < dist[node[1]]){
                    dist[node[1]] = dist[node[0]] + node[2];
                }
            }
        }

        for(int[] node: times){
             if(dist[node[0]] != inf && dist[node[0]] + node[2] < dist[node[1]]){
                    dist[node[1]] = dist[node[0]] + node[2];
                    return -1;
                }
        }

        int ans = -inf;

        for(int i=1;i<=n;i++) {
            ans = Math.max(ans, dist[i]);
        
            if(dist[i] == inf) return -1;
        }

        return ans;
        
    }
}
