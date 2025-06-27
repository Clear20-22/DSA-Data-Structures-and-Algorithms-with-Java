# Dynamic Programming Algorithms – Analysis Guide

This guide provides concise analyses for the most important dynamic programming (DP) algorithms in competitive programming and academic exams.

---

## 1. Sequence Problems

### a) Longest Increasing Subsequence (LIS)

**Definition:**  
Find the longest strictly increasing subsequence in a sequence of numbers.

**DP Approach:**  
- State: `dp[i]` = length of LIS ending at index `i`
- Recurrence: For each `i`, check all `j < i` and if `arr[j] < arr[i]`, update `dp[i] = max(dp[i], dp[j]+1)`

**Analysis:**  
- **Time:** O(n²) (improvable to O(n log n) with binary search)
- **Space:** O(n)
- **Optimal Substructure:** LIS at `i` depends on LIS at previous positions
- **Overlapping Subproblems:** Many overlapping subarrays share subproblems

**Use Cases:**  
Longest progression detection in time series, genetics, and coding interviews.

---

### b) Longest Decreasing Subsequence (LDS)

**Similar to LIS, but looks for decreasing sequences.**

- State/Recurrence: Analogous to LIS, but `arr[j] > arr[i]`
- **Time/Space:** Same as LIS

---

### c) Longest Common Subsequence (LCS)

**Definition:**  
Find the longest subsequence present in both strings.

**DP Approach:**  
- State: `dp[i][j]` = LCS for first `i` chars of s1 and first `j` chars of s2
- Recurrence: If `s1[i-1] == s2[j-1]`, add 1; else, take the max of skipping a character from either string

**Analysis:**  
- **Time/Space:** O(n*m) for strings of length n and m
- **Optimal Substructure:** LCS of prefixes helps build LCS of larger strings

**Use Cases:**  
Diff tools, bioinformatics, plagiarism checkers.

---

### d) Longest Palindromic Subsequence

**Definition:**  
Find the longest subsequence which is a palindrome.

**DP Approach:**  
- State: `dp[i][j]` = length of longest palindromic subsequence in substring s[i:j+1]
- Recurrence: If s[i]==s[j], 2 + inner; else, max excluding either end

**Analysis:**  
- **Time/Space:** O(n²)
- **Optimal Substructure:** Based on smaller substrings

**Use Cases:**  
Text analysis, DNA sequence studies.

---

### e) Edit Distance (Levenshtein Distance)

**Definition:**  
Minimum number of insertions, deletions, and substitutions to convert one string to another.

**DP Approach:**  
- State: `dp[i][j]` = min edits for first `i` chars of s1 to match first `j` chars of s2
- Recurrence: Consider insert, delete, replace

**Analysis:**  
- **Time/Space:** O(n*m)
- **Optimal Substructure:** Build up edits from prefixes

**Use Cases:**  
Spell checkers, genetics, approximate string matching.

---

### f) Minimum Insertions/Deletions to Convert String

**Definition:**  
Find min operations to convert string A to B (insert or delete only).

**DP Approach:**  
- Related to LCS: Min operations = len(A) + len(B) - 2 * LCS(A, B)

**Analysis:**  
- **Time/Space:** O(n*m) via LCS computation

**Use Cases:**  
Text editors, file diff tools.

---

### g) Longest Palindromic Substring

**Definition:**  
Find the longest contiguous palindromic substring.

**DP Approach:**  
- State: `dp[i][j] = True` if substring s[i:j+1] is palindrome
- Recurrence: s[i]==s[j] and inner substring is palindrome

**Analysis:**  
- **Time/Space:** O(n²)
- **Optimal Substructure:** Palindrome property builds from inner substrings

**Use Cases:**  
Text processing, DNA sequence analysis.

---

## 2. Partition & Subset Problems

### a) Subset Sum Problem

**Definition:**  
Determine if a subset of numbers sums to a given target.

**DP Approach:**  
- State: `dp[i][s]` = True if sum `s` can be made with first `i` numbers

**Analysis:**  
- **Time/Space:** O(n*sum)
- **Optimal Substructure:** Subset with/without current number

**Use Cases:**  
Resource allocation, knapsack, finance.

---

### b) Partition Equal Subset Sum

**Definition:**  
Check if an array can be split into two subsets with equal sum.

**DP Approach:**  
- Reduce to subset sum with target = sum/2

**Analysis:**  
- **Time/Space:** O(n*sum)

**Use Cases:**  
Load balancing, fair division.

---

### c) Count of Subsets with Given Sum

**Definition:**  
Count the number of subsets with sum equal to target.

**DP Approach:**  
- State: `dp[i][s]` = number of ways to get sum `s` with first `i` numbers

**Analysis:**  
- **Time/Space:** O(n*sum)

**Use Cases:**  
Probability, combinatorics.

---

### d) Minimum Subset Sum Difference

**Definition:**  
Partition array into two subsets with minimum possible difference of their sums.

**DP Approach:**  
- Use subset sum DP to check all possible sums up to total/2, minimize `|total - 2*s|`

**Analysis:**  
- **Time/Space:** O(n*sum)

**Use Cases:**  
Optimization, fair division.

---

### e) Target Sum

**Definition:**  
Assign + or - to array elements to reach a target sum.

**DP Approach:**  
- Reduce to subset sum variant

**Analysis:**  
- **Time/Space:** O(n*sum)

**Use Cases:**  
Sign assignment, expression evaluation.

---

## 3. Knapsack Problems

### a) 0/1 Knapsack

**Definition:**  
Maximize value with given capacity, each item can be taken once.

**DP Approach:**  
- State: `dp[i][w]` = max value with first `i` items and capacity `w`

**Analysis:**  
- **Time/Space:** O(n*W)

**Use Cases:**  
Resource allocation.

---

### b) Unbounded Knapsack

**Definition:**  
Maximize value, each item can be taken unlimited times.

**DP Approach:**  
- Similar to 0/1, but can reconsider same item

**Analysis:**  
- **Time/Space:** O(n*W)

**Use Cases:**  
Cutting rods, making change.

---

### c) Fractional Knapsack (Greedy)

**Definition:**  
Items can be broken into fractions; maximize value.

**Approach:**  
- Greedy: Take highest value/weight first

**Analysis:**  
- **Time:** O(n log n) (sorting)
- **Space:** O(1)

**Use Cases:**  
Investment, resource splitting.

---

### d) Multi-dimensional Knapsack

**Definition:**  
Knapsack with more than one constraint (e.g., weight and volume).

**DP Approach:**  
- State: `dp[i][w][v]` = max value with first `i` items, weight `w`, volume `v`

**Analysis:**  
- **Time/Space:** O(n*W*V)

**Use Cases:**  
Complex resource planning.

---

### e) Subset Sum (Knapsack Variant)

**See above under Partition & Subset Problems.**

---

## 4. Coin Change Problems

### a) Minimum Coins to Make Amount

**Definition:**  
Minimum coins to make a target amount from denominations.

**DP Approach:**  
- State: `dp[a]` = min coins for amount `a`

**Analysis:**  
- **Time/Space:** O(n*amount)

**Use Cases:**  
Cashier algorithms, finance.

---

### b) Number of Ways to Make Amount

**Definition:**  
Count the number of ways to make amount with coins.

**DP Approach:**  
- State: `dp[a]` = number of ways for amount `a`

**Analysis:**  
- **Time/Space:** O(n*amount)

**Use Cases:**  
Combinatorics, probability.

---

## 5. Grid & Path Problems

### a) Unique Paths in a Grid

**Definition:**  
Count the number of ways to move from top-left to bottom-right in a grid, only right or down.

**DP Approach:**  
- State: `dp[i][j]` = ways to reach cell (i, j)

**Analysis:**  
- **Time/Space:** O(m*n)

**Use Cases:**  
Robotics, lattice path counting.

---

### b) Minimum Path Sum

**Definition:**  
Find the path with minimum sum in a grid from top-left to bottom-right.

**DP Approach:**  
- State: `dp[i][j]` = min sum to cell (i, j)

**Analysis:**  
- **Time/Space:** O(m*n)

**Use Cases:**  
Pathfinding, cost minimization.

---

### c) Grid with Obstacles

**Definition:**  
Unique paths or min path sum, but some cells are blocked.

**DP Approach:**  
- Modify DP to skip blocked cells

**Analysis:**  
- **Time/Space:** O(m*n)

**Use Cases:**  
Robotics, navigation.

---

### d) Maximum Path Sum

**Definition:**  
Find the path with maximum sum in a grid (direction may vary).

**DP Approach:**  
- State: `dp[i][j]` = max sum to cell (i, j)

**Analysis:**  
- **Time/Space:** O(m*n)

**Use Cases:**  
Game scoring, reward maximization.

---

## 6. Optimization Problems

### a) Matrix Chain Multiplication

**Definition:**  
Find the order of multiplying matrices to minimize multiplications.

**DP Approach:**  
- State: `dp[i][j]` = min cost to multiply matrices i to j

**Analysis:**  
- **Time/Space:** O(n³)

**Use Cases:**  
Compiler optimization, graphics.

---

### b) Rod Cutting

**Definition:**  
Maximize profit by cutting a rod and selling pieces.

**DP Approach:**  
- State: `dp[i]` = max profit for length `i`

**Analysis:**  
- **Time/Space:** O(n²)

**Use Cases:**  
Manufacturing, production.

---

### c) Egg Dropping Puzzle

**Definition:**  
Find minimum trials to determine from which floor an egg will break.

**DP Approach:**  
- State: `dp[eggs][floors]` = min trials needed

**Analysis:**  
- **Time/Space:** O(eggs*floors²)

**Use Cases:**  
Testing, reliability.

---

### d) Palindrome Partitioning

**Definition:**  
Min cuts needed to split string into palindromic substrings.

**DP Approach:**  
- State: `dp[i]` = min cuts for prefix of length `i`

**Analysis:**  
- **Time/Space:** O(n²)

**Use Cases:**  
Text processing.

---

### e) Optimal Binary Search Tree

**Definition:**  
Build BST with minimal search cost given key probabilities.

**DP Approach:**  
- State: `dp[i][j]` = min cost for keys i to j

**Analysis:**  
- **Time/Space:** O(n³)

**Use Cases:**  
Database indexing.

---

## 7. Game Theory & Decision

### a) Nim Game (Grundy Numbers)

**Definition:**  
Determine winner and strategy in stone removal game.

**DP Approach:**  
- Grundy number represents state value; use DP to compute

**Analysis:**  
- **Time/Space:** O(n*maxMove)

**Use Cases:**  
AI, game design.

---

### b) Stone Game

**Definition:**  
Two players pick stones; maximize difference.

**DP Approach:**  
- State: `dp[i][j]` = best score difference for range i...j

**Analysis:**  
- **Time/Space:** O(n²)

**Use Cases:**  
Game AI.

---

### c) DP on Games (Minimax, Sprague-Grundy)

**Definition:**  
Use DP for turn-based games to compute best/worst outcomes.

**Approach & Analysis:**  
- **Time/Space:** Varies, usually O(n²) or O(n³)
- **Optimal Substructure:** Game state transitions

**Use Cases:**  
Chess, checkers, puzzles.

---

## Other Important DP Topics

### a) Fibonacci Numbers (DP approach)
- Classic: `dp[n] = dp[n-1] + dp[n-2]`
- **Time/Space:** O(n)

### b) Catalan Numbers
- Classic DP for counting trees, parenthesis, etc.
- **Time/Space:** O(n²)

### c) Digit DP
- DP over digits of a number for constraints (e.g., counting numbers with certain digits).
- **Time/Space:** O(pos * tight * ...)

### d) Bitmask DP
- Use bitmasks for state compression (e.g., TSP).
- **Time/Space:** O(n*2ⁿ)

### e) DP on Trees
- DP per node/subtree for tree properties.
- **Time/Space:** O(n)

### f) DP with Bitmasks
- See Bitmask DP above.

### g) Interval DP
- DP over intervals (e.g., merging stones).
- **Time/Space:** O(n³)

### h) Memoization and Tabulation
- Top-down (recursion + cache) vs bottom-up (iteration)

### i) State Compression DP
- Compress DP state for efficiency.

### j) Space Optimization
- Reduce DP arrays where possible (e.g., 2D to 1D).

---

## Classic Problems to Practice

- **House Robber & Circular House Robber:** Max sum of non-adjacent numbers (O(n))
- **Climbing Stairs:** Ways to reach top, like Fibonacci (O(n))
- **Jump Game:** Can you reach the end? (O(n))
- **Decode Ways:** Ways to decode digit string (O(n))
- **Best Time to Buy and Sell Stock:** Max profit from price array (O(n))
- **Maximum Subarray Sum (Kadane’s Algorithm):** Largest sum subarray (O(n))
- **Longest Bitonic Subsequence:** Increasing then decreasing subsequence (O(n²))

---

# End of Analysis Guide
