import java.util.*;

class Graph{
    int n;
    int [][] graph;
    Graph(int n){
        this.n = n;
        graph = new int[n][n];
    }

    void addEdge(int u,int v){
        graph[u][v] = 1;
        graph[v][u] = 1;
    }

    void printGraph(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(graph[i][j]+" ");
            }
            System.out.println();
        }
    }
}


public class  Adjacency_Matrix  {
    public static void main(String[] args) {
        Scanner take = new Scanner(System.in);

        int n = take.nextInt();
        int m = take.nextInt();

        Graph g = new Graph(n+1);

        int u,v;
        for(int i=0;i<m;i++){
            u = take.nextInt();
            v = take.nextInt();
            g.addEdge(u,v);
        }   

        g.printGraph();

        take.close();
    }
}
