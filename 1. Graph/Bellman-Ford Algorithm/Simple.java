import java.util.*;

public class Simple{
    static class Edge {
        int u, v, weight;
        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    public static class Result {
        int[] prev;
        int[] dist;
        Result(int[] prev, int[] dist) {
            this.prev = prev;
            this.dist = dist;
        }
    }

    public static Result bellmanFord(int vertices, List<Edge> edges, int source) {
        int[] dist = new int[vertices];
        int[] prev = new int[vertices];
        int INF = Integer.MAX_VALUE;

        Arrays.fill(dist, INF);
        Arrays.fill(prev, -1);
        dist[source] = 0;

        for (int i = 0; i < vertices - 1; i++) {
            for (Edge edge : edges) {
                int u = edge.u;
                int v = edge.v;
                int w = edge.weight;

                if (dist[u] != INF && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    prev[v] = u;
                }
            }
        }

        for (Edge edge : edges) {
            if (dist[edge.u] != INF && dist[edge.u] + edge.weight < dist[edge.v]) {
                System.out.println("Negative weight cycle detected!");
                return null;
            }
        }
        

        return new Result(prev, dist);
    }

    // Sample usage
    public static void main(String[] args) {
        int n = 5;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 6));
        edges.add(new Edge(0, 2, 7));
        edges.add(new Edge(1, 2, 8));
        edges.add(new Edge(1, 3, 5));
        edges.add(new Edge(1, 4, -4));
        edges.add(new Edge(2, 3, -3));
        edges.add(new Edge(2, 4, 9));
        edges.add(new Edge(3, 1, -2));
        edges.add(new Edge(4, 0, 2));
        edges.add(new Edge(4, 3, 7));

        Result result = bellmanFord(n, edges, 0);

        System.out.println("Distances: " + Arrays.toString(result.dist));
        System.out.println("Predecessors: " + Arrays.toString(result.prev));
    }
}
