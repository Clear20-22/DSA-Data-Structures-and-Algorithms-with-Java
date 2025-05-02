/*There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
Example 2:

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
Example 3:

Input: numCourses = 1, prerequisites = []
Output: [0]*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i < numCourses;i++){
            adj.add(new ArrayList<>());
        }

        for(int[] node:prerequisites){
            adj.get(node[1]).add(node[0]);
        }

        ArrayList<Integer> path = new ArrayList<>();
        int[] visit = new int[numCourses];
        Arrays.fill(visit,0);

        for(int i=0; i < numCourses; i++){
            if(!dfs(i,adj,visit,path)){
                return new int[0];
            }
        }

        Collections.reverse(path);
                int[] result = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            result[i] = path.get(i);
        }
        return result;
    }

    boolean dfs(int v,List<List<Integer>> adj,int[] visit,ArrayList<Integer> path){
        if(visit[v] == 1) return false;
        if(visit[v] == 2) return true;

        visit[v] = 1;
        for(int nv:adj.get(v)){
            if( !dfs(nv,adj,visit,path))
            return false;

        }

        visit[v] = 2;
        path.add(v);
        return true;
    }
}
