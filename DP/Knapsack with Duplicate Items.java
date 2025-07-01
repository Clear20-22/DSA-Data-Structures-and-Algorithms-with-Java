/*
Knapsack with Duplicate Items

Difficulty: Medium

Given a set of items, each with a weight and a value, represented by the array wt and val respectively. Also, a knapsack with a weight limit capacity.
The task is to fill the knapsack in such a way that we can get the maximum profit. Return the maximum profit.

Note: Each item can be taken any number of times.

Examples:

Input: val = [1, 1], wt = [2, 1], capacity = 3
Output: 3
Explanation: The optimal choice is to pick the 2nd element 3 times.

Input: val[] = [6, 1, 7, 7], wt[] = [1, 3, 4, 5], capacity = 8
Output: 48
Explanation: The optimal choice is to pick the 1st element 8 times.

Input: val[] = [6, 8, 7, 100], wt[] = [2, 3, 4, 5], capacity = 1
Output: 0
Explanation: We can't pick any element, hence total profit is 0.

Constraints:
1 <= val.size() = wt.size() <= 1000
1 <= capacity <= 1000
1 <= val[i], wt[i] <= 100
*/


// User function Template for Java

class Solution {
    static int knapSack(int val[], int wt[], int capacity) {
        // code here
        int[][] dp = new int[val.length][capacity+1];
        
        for(int i = 0;i<=capacity;i++)
        dp[0][i] = (i/wt[0])*val[0];
        
        for(int i=1;i<val.length;i++){
            for(int j=0;j<=capacity;j++){
               int notake = dp[i-1][j];
               int take = Integer.MIN_VALUE;
                
                if(wt[i]<=j){
                    take = val[i] + dp[i][j-wt[i]];
                }
                
                dp[i][j] = Math.max(take,notake);
            }
        }
        
        return dp[val.length-1][capacity];
    }
}