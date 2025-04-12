/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
*/








class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0;i<numCourses;i++) adj.add(new ArrayList<>());

        for(int i=0;i<prerequisites.length;i++){
            adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        int[] visit = new int[numCourses];

        for(int i=0;i<numCourses;i++){
            if(!dfs(i, adj, visit)){
                return false;
            }
        }

        return true;
    }


    boolean dfs(int v,List<List<Integer>> adj, int[] visit){
        if(visit[v] == 1) return false;
        if(visit[v] == 2) return true;

        visit[v] = 1;
        for(int nv:adj.get(v)){
            if(!dfs(nv,adj,visit))
            return false;
        }

        visit[v] = 2;
        return true;
    }
}
