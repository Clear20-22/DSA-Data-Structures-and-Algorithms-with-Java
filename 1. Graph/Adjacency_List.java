import java.util.*;

class Graph{
    int n;
    List<List<Integer>> graph;
    Graph(int n){
        this.n = n;
        graph = new ArrayList<>(n);
        for(int i=0;i<n;i++){
            graph.add(new ArrayList<>());
        }
    }

    void addEdge(int u,int v){
        graph.get(u).add(v);
        graph.get(v).add(u);
    }

    void printGraph(){
        for(int i=0;i<n;i++){
            System.out.print(i + ": ");
            for(int element: graph.get(i)){
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}


public class  Adjacency_List {
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
