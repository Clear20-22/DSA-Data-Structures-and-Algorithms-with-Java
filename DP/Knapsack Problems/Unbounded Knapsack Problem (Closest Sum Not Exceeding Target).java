/*
Unbounded Knapsack Problem (Closest Sum Not Exceeding Target)

Given an array of integers and a target sum, determine the sum nearest to 
but not exceeding the target that can be created. 
To create the sum, use any element of your array zero or more times.

For example, if arr = [3, 4, 4, 5] and your target sum is 10, 
you might select 3 + 3 + 4 = 10 or 5 + 5 = 10. 
In this case, you can arrive at exactly the target.

Function Description:

Complete the unboundedKnapsack function below. 
It must return an integer that represents the sum nearest to, 
but not exceeding, the target value.

Function Signature:
int unboundedKnapsack(int k, int[] arr)

Parameters:
- k: an integer (target sum)
- arr: an array of integers

Input Format:
- The first line contains an integer t, the number of test cases.
- Each of the next t pairs of lines are as follows:
    - The first line contains two integers n and k:
        - n: the size of the array
        - k: the target sum
    - The second line contains n space-separated integers representing the array elements.

Constraints:
- 1 ≤ t ≤ 10
- 1 ≤ n ≤ 200
- 1 ≤ k ≤ 2000
- 1 ≤ arr[i] ≤ 2000

Output Format:
- Print the maximum sum for each test case which is as near as possible, 
  but not exceeding, the target sum on a separate line.
*/

import java.util.*;

public static int unboundedKnapsack(int k, List<Integer> arr) {
        // Remove duplicates to avoid redundant work
        Set<Integer> set = new HashSet<>(arr);
        int[] dp = new int[k + 1];
        for (int item : set) {
            for (int j = item; j <= k; j++) {
                dp[j] = Math.max(dp[j], dp[j - item] + item);
            }
        }
        return dp[k];
}