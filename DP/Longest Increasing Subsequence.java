// Problem: Longest Increasing Subsequence (LIS)

// Given an array of integers 'nums', 
// we need to find the **length** of the longest subsequence 
// such that all elements of the subsequence are **strictly increasing**.

// A subsequence is a sequence that can be derived from the array 
// by deleting some or no elements without changing the order of the remaining elements.

// --- Example 1 ---
// Input: [10,9,2,5,3,7,101,18]
// Output: 4
// Explanation: One of the LIS is [2,3,7,101]

// --- Example 2 ---
// Input: [0,1,0,3,2,3]
// Output: 4
// Explanation: One of the LIS is [0,1,2,3]

// --- Example 3 ---
// Input: [7,7,7,7,7,7,7]
// Output: 1
// Explanation: All elements are equal, so the LIS is any single element

// --- Constraints ---
// 1 <= nums.length <= 2500
// -10^4 <= nums[i] <= 10^4

import java.util.Arrays;

class Solution {
public int lengthOfLIS(int[] nums) {
    if (nums == null || nums.length == 0) return 0;
    int[] dp = new int[nums.length];
    int ans = 1;
    Arrays.fill(dp, 1);
    for (int i = 1; i < nums.length; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[j] < nums[i]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        ans = Math.max(ans, dp[i]);
    }
    return ans;
}
}