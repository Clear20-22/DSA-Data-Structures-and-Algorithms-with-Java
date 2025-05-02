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
