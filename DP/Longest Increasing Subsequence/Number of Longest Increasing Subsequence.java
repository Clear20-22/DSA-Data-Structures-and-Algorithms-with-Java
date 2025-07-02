/*
Leetcode 673: Number of Longest Increasing Subsequence

🧠 Problem Statement:
Given an integer array `nums`, return the number of longest increasing subsequences (LIS).
A subsequence must be strictly increasing.

📌 Example 1:
Input: nums = [1,3,5,4,7]
Output: 2
Explanation:
- The longest increasing subsequences are [1,3,4,7] and [1,3,5,7], both of length 4.

📌 Example 2:
Input: nums = [2,2,2,2,2]
Output: 5
Explanation:
- The longest increasing subsequence length is 1.
- Every individual element forms a subsequence of length 1.
- So there are 5 such subsequences.

🔧 Approach:
We use dynamic programming with two arrays:

1. `dp[i]` = length of the longest increasing subsequence ending at index i.
2. `count[i]` = number of LIS of length dp[i] ending at index i.

We iterate through the array and for each index `i`, we check all previous indices `j < i`:
    - If nums[j] < nums[i], then nums[i] can be appended to the subsequence ending at j.
    - If dp[j] + 1 > dp[i], we found a longer subsequence → update dp[i] and reset count[i] = count[j].
    - If dp[j] + 1 == dp[i], we found another way to form same-length LIS → add count[j] to count[i].

After building dp[] and count[]:
- Find the maximum value in dp[] → this is the length of the LIS.
- Sum up all count[i] for which dp[i] == max length → this is the result.

⏱️ Time Complexity: O(n^2)
  - Two nested loops for filling dp and count arrays

⏳ Space Complexity: O(n)
  - Two arrays of size n for dp and count

✅ Constraints are small enough (n ≤ 2000) for this approach to work efficiently.
*/

import java.util.*;

class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];   
        int[] count = new int[n];  

        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);

        int maxLen = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j]; 
                    } else if (dp[j] + 1 == dp[i]) {
                        count[i] += count[j]; 
                    }
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxLen) {
                result += count[i];
            }
        }

        return result;
    }
}
