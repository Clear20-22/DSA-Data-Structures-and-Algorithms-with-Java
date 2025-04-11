import java.util.*;

class Topological_Algorithm {
    ArrayList<Integer>[] adj;
    int[] ans;
    int V;

    Topological_Algorithm(int n) {
        this.V = n;
        adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++)
            adj[i] = new ArrayList<>();
    }

    void addEdge(int u, int v) {
        adj[u].add(v);
    }

    boolean algorithm() {
        int[] indegree = new int[V + 1];
        
        for (int i = 1; i <= V; i++) {
            for (int node : adj[i])
                indegree[node]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= V; i++) {
            if (indegree[i] == 0)
                q.offer(i);
        }

        int[] result = new int[V];
        int idx = 0;

        while (!q.isEmpty()) {
            int v = q.poll();
            result[idx++] = v;

            for (int nv : adj[v]) {
                indegree[nv]--;
                if (indegree[nv] == 0)
                    q.offer(nv);
            }
        }

        if (idx != V) {
            return false;
        }

        this.ans = result;
        return true;
    }

    void printtopo() {
        for (int val : ans) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}

public class Kahns_algo {
    public static void main(String[] args) {
        Scanner take = new Scanner(System.in);
        int V = take.nextInt(); 
        int E = take.nextInt(); 

        Topological_Algorithm topo = new Topological_Algorithm(V);

        for (int i = 0; i < E; i++) {
            int u = take.nextInt();
            int v = take.nextInt();
            topo.addEdge(u, v);
        }

        if (!topo.algorithm()) {
            System.out.println("Graph has a cycle.");
        } else {
            topo.printtopo();
        }
    }
}
