/*
LeetCode 354: Russian Doll Envelopes

🧠 Problem:
Given a list of envelopes with widths and heights: envelopes[i] = [wi, hi],
find the maximum number of envelopes you can "Russian doll" — i.e., put one inside another.
You cannot rotate the envelopes. One envelope can fit into another only if:
    - its width is strictly smaller
    - its height is strictly smaller

🎯 Goal:
Return the maximum number of envelopes that can be nested.

🧩 Observations:
- This is similar to finding the Longest Increasing Subsequence (LIS), but in 2D.
- Both width and height must strictly increase.
- If we only sort by width, we may mistakenly include envelopes with the same width.

🔧 Strategy:
1. Sort the envelopes:
    - First by width in ascending order.
    - Then by height in **descending** order if widths are equal.
    
    Why descending for height when width is same?
    -> Because we want to avoid selecting envelopes with the same width
       when applying LIS on height. If heights are in descending order for
       same width, only one of them can be included in LIS.

2. After sorting, apply Longest Increasing Subsequence (LIS) on the heights:
    - This ensures both width and height increase strictly due to our sorting rule.
    - Use binary search for LIS to keep time complexity optimal.

⏱️ Time Complexity:
- Sorting: O(n log n)
- LIS with binary search: O(n log n)
- Total: O(n log n)

✅ Efficient enough for n up to 100,000
*/


import java.util.*;

class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            else return Integer.compare(b[1], a[1]); 
        });

        List<Integer> lis = new ArrayList<>();

        for (int[] envelope : envelopes) {
            int height = envelope[1];
            int idx = Collections.binarySearch(lis, height);
            if (idx < 0) idx = -(idx + 1);
            if (idx == lis.size()) lis.add(height);
            else lis.set(idx, height);
        }

        return lis.size();
    }
}
