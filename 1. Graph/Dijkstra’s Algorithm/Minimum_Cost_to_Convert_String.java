// 2976. Minimum Cost to Convert String I
// Problem: You are given two 0-indexed strings source and target, both of length n and consisting of lowercase English letters.
// You are also given two 0-indexed character arrays original and changed, and an integer array cost, where cost[i] represents 
// the cost of changing the character original[i] to the character changed[i].

// You start with the string source. In one operation, you can pick a character x from the string and change it to the character y 
// at a cost of z if there exists any index j such that cost[j] == z, original[j] == x, and changed[j] == y.

// The task is to return the minimum cost to convert the string source to the string target using any number of operations. 
// If it is impossible to convert source to target, return -1.

// Constraints:
// - 1 <= source.length == target.length <= 10^5
// - source, target consist of lowercase English letters.
// - 1 <= cost.length == original.length == changed.length <= 2000
// - original[i], changed[i] are lowercase English letters.
// - 1 <= cost[i] <= 10^6
// - original[i] != changed[i]

// Example 1:
// Input: source = "abcd", target = "acbe", original = ["a","b","c","c","e","d"], changed = ["b","c","b","e","b","e"], cost = [2,5,5,1,2,20]
// Output: 28
// Explanation: To convert the string "abcd" to string "acbe":
// - Change value at index 1 from 'b' to 'c' at a cost of 5.
// - Change value at index 2 from 'c' to 'e' at a cost of 1.
// - Change value at index 2 from 'e' to 'b' at a cost of 2.
// - Change value at index 3 from 'd' to 'e' at a cost of 20.
// The total cost incurred is 5 + 1 + 2 + 20 = 28.
// It can be shown that this is the minimum possible cost.

// Example 2:
// Input: source = "aaaa", target = "bbbb", original = ["a","c"], changed = ["c","b"], cost = [1,2]
// Output: 12
// Explanation: To change the character 'a' to 'b' change the character 'a' to 'c' at a cost of 1, followed by changing 
// the character 'c' to 'b' at a cost of 2, for a total cost of 1 + 2 = 3. To change all occurrences of 'a' to 'b', 
// a total cost of 3 * 4 = 12 is incurred.

// Example 3:
// Input: source = "abcd", target = "abce", original = ["a"], changed = ["e"], cost = [10000]
// Output: -1
// Explanation: It is impossible to convert source to target because the value at index 3 cannot be changed from 'd' to 'e'.


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Minimum_Cost_to_Convert_String {
    public static void main(String[] args) {
        Scanner take = new Scanner(System.in);
        String sourse = take.nextLine();
        String target = take.nextLine();
        int n = take.nextInt();
        List<Character> or = new ArrayList<>(), cng = new ArrayList<>();
        List<Integer>cost = new ArrayList<>();
        List<Pair> find = new ArrayList<>();

        char x;
        int y;
        for(int i = 0;i<n;i++){
            x = take.next().charAt(0);
            or.add(x);
        }
        for(int i = 0;i<n;i++){
            x = take.next().charAt(0);
            cng.add(x);
        }        
        for(int i = 0;i<n;i++){
            y = take.nextInt();
            cost.add(y);
        }
        for(int i = 0;i<sourse.length();i++){
           if(sourse.charAt(i) != target.charAt(i)){
            find.add(new Pair(sourse.charAt(i), target.charAt(i)));
           } 
        }  

        Map<Character,List<Pair>> adj = new HashMap<>();

         for(int i = 0;i<n;i++){
            adj.computeIfAbsent(or.get(i), k -> new ArrayList<>()).add(new Pair(cng.get(i), cost.get(i)));
        }

        int ans = 0;
        for(Pair v:find){
            int p = bfs(v.u, v.v, adj);
            if(p == -1){
                ans = -1;
                break;
            }
            ans += p;
        }

        System.out.println(ans);
        
        take.close();
    }

    static class Pair implements Comparable<Pair>{
        char v,u;
        int w;
        Pair(char a,int b){
            v = a;
            w = b;
        }
        Pair(char a,char b){
            u = a;
            v = b;
        }
        @Override
        public int compareTo(Pair o) {
            return Integer.compare(this.w, o.w);
        }
    }

    static int bfs(char a,char b,Map<Character,List<Pair>> adj){
        int[] dis = new int[200];
        Arrays.fill(dis, Integer.MAX_VALUE);
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        dis[(int) a] = 0;
        pq.add(new Pair(a, 0));
        while(!pq.isEmpty()){
            Pair ov = pq.poll();
            if(dis[(int)ov.v] < ov.w) continue;
            if(ov.v == b) return dis[(int) b];
            for(Pair nv:adj.get(ov.v)){
                if(dis[nv.v] > dis[ov.v] + nv.w){
                    dis[nv.v] = dis[ov.v] + nv.w;
                    pq.add(new Pair(nv.v, dis[nv.v]));
                }
            }
        }
        return -1;
    }
}
