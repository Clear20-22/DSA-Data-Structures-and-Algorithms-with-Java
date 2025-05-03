/*
LeetCode 2097. Valid Arrangement of Pairs
------------------------------------------

You are given a 0-indexed 2D integer array `pairs` where pairs[i] = [start_i, end_i]. 
An arrangement of pairs is valid if for every index i where 1 <= i < pairs.length, 
we have end_{i-1} == start_i.

Your task:
Return any valid arrangement of the pairs.

Note: The inputs will be generated such that there exists a valid arrangement.

Examples:
---------

Example 1:
Input: pairs = [[5,1],[4,5],[11,9],[9,4]]
Output: [[11,9],[9,4],[4,5],[5,1]]
Explanation: 
This is a valid arrangement since end_i-1 always equals start_i:
end0 = 9 == 9 = start1 
end1 = 4 == 4 = start2
end2 = 5 == 5 = start3

Example 2:
Input: pairs = [[1,3],[3,2],[2,1]]
Output: [[1,3],[3,2],[2,1]]
Explanation:
This is a valid arrangement since:
end0 = 3 == 3 = start1
end1 = 2 == 2 = start2
Other valid arrangements are also possible.

Example 3:
Input: pairs = [[1,2],[1,3],[2,1]]
Output: [[1,2],[2,1],[1,3]]
Explanation:
end0 = 2 == 2 = start1
end1 = 1 == 1 = start2

Constraints:
------------
- 1 <= pairs.length <= 10^5
- pairs[i].length == 2
- 0 <= start_i, end_i <= 10^9
- start_i != end_i
- No two pairs are exactly the same.
- There exists a valid arrangement of pairs.
*/


import java.util.*;

public class Valid_Arrangement_of_Pairs {
    public static void main(String[] args) {
        Scanner take = new Scanner(System.in);
        int n = take.nextInt();
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            int x = take.nextInt();
            int y = take.nextInt();
            pairs[i] = new int[]{x, y};
        }

        int[][] result = validArrangement(pairs);
        for (int[] pair : result) {
            System.out.println(Arrays.toString(pair));
        }
        take.close();
    }

    static int[][] validArrangement(int[][] pairs) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        Map<Integer, Integer> in = new HashMap<>();
        Map<Integer, Integer> out = new HashMap<>();
        Set<Integer> nodes = new HashSet<>();
        List<Integer> path = new ArrayList<>();

        for (int[] pair : pairs) {
            int u = pair[0], v = pair[1];
            nodes.add(u);
            nodes.add(v);

            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);

            out.put(u, out.getOrDefault(u, 0) + 1);
            in.put(v, in.getOrDefault(v, 0) + 1);
        }

        int start = pairs[0][0];
        for (int node : nodes) {
            int outDeg = out.getOrDefault(node, 0);
            int inDeg = in.getOrDefault(node, 0);
            if (outDeg - inDeg == 1) {
                start = node;
                break;
            }
        }

        dfs(start, adj, out, path);

        Collections.reverse(path);

        for (int i = 0; i < path.size() - 1; i++) {
            pairs[i][0] = path.get(i);
            pairs[i][1] = path.get(i + 1);
        }

        return Arrays.copyOf(pairs, path.size() - 1);
    }

    static void dfs(int v, Map<Integer, List<Integer>> adj, Map<Integer, Integer> out, List<Integer> path) {
        List<Integer> list = adj.get(v);
        while (out.getOrDefault(v, 0) > 0) {
            int lastIndex = out.get(v) - 1;
            int next = list.get(lastIndex);
            out.put(v, lastIndex);
            dfs(next, adj, out, path);
        }
        path.add(v);
    }
}
