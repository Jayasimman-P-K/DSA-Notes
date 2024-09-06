# DP on Subsequences

## Problem: Partition Equal Subset Sum (Leetcode 416)

**Problem Description:**

Given an integer array `nums`, return `true` if you can partition the array into two subsets such that the sum of the elements in both subsets is equal. Otherwise, return `false`.

**Examples:**

- **Example 1:**

  - Input: `nums = [1,5,11,5]`
  - Output: `true`
  - Explanation: The array can be partitioned as `[1, 5, 5]` and `[11]`.

- **Example 2:**
  - Input: `nums = [1,2,3,5]`
  - Output: `false`
  - Explanation: The array cannot be partitioned into equal sum subsets.

**Constraints:**

- `1 <= nums.length <= 200`
- `1 <= nums[i] <= 100`

---

### Recursive Approach with Memoization

```java
class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int totalSum = 0;

        // Calculate the total sum of the array
        for (int num : nums) {
            totalSum += num;
        }

        // If the total sum is odd, it's not possible to split into two equal subsets
        if (totalSum % 2 != 0) return false;

        // Target sum for each subset
        int target = totalSum / 2;

        // Call the helper function to check if there's a subset with the target sum
        return subsetSumToK(n, target, nums);
    }

    public boolean subsetSumToK(int n, int target, int[] nums) {
        // Initialize the memoization array with -1 (indicating unvisited states)
        int[][] dp = new int[n][target + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        // Helper function to determine if a subset with the given target sum exists
        return helperFunc(n - 1, target, dp, nums);
    }

    public boolean helperFunc(int index, int target, int[][] dp, int[] nums) {
        // Base case: if the target is zero, a subset with the desired sum is found
        if (target == 0) return true;

        // If at the start of the array, check if the element equals the target
        if (index == 0) return nums[0] == target;

        // Check if the result is already computed
        if (dp[index][target] != -1) return dp[index][target] == 1;

        // Recursively check both possibilities: not including or including the current element
        boolean notTake = helperFunc(index - 1, target, dp, nums);
        boolean take = target >= nums[index] ? helperFunc(index - 1, target - nums[index], dp, nums) : false;

        // Memoize the result
        dp[index][target] = (take || notTake) ? 1 : 0;
        return take || notTake;
    }
}
```

### Tabulation Approach

```java
class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int totalSum = 0;

        // Calculate the total sum of the array
        for (int num : nums) totalSum += num;

        // If the total sum is odd, it's not possible to split into two equal subsets
        if (totalSum % 2 != 0) return false;

        // Target sum for each subset
        int target = totalSum / 2;

        // Initialize the DP table
        boolean[][] dp = new boolean[n][target + 1];

        // Base case: Subset with sum 0 is always possible
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        // Base case: Check if first element can form the target subset
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }

        // Fill the DP table
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= target; j++) {
                boolean notTake = dp[i - 1][j];
                boolean take = j >= nums[i] ? dp[i - 1][j - nums[i]] : false;
                dp[i][j] = take || notTake;
            }
        }

        // Return whether we can form a subset with the target sum
        return dp[n - 1][target];
    }
}
```

### Tabulation with Space Optimization

```java
class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int totalSum = 0;

        // Calculate the total sum of the array
        for (int num : nums) totalSum += num;

        // If the total sum is odd, it's not possible to split into two equal subsets
        if (totalSum % 2 != 0) return false;

        // Target sum for each subset
        int target = totalSum / 2;

        // Initialize the DP array
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        // Fill the DP array
        for (int i = 0; i < n; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }

        // Return whether we can form a subset with the target sum
        return dp[target];
    }
}
```

### Explanation:

- **Recursive Approach with Memoization:**

  - This approach checks if a subset with sum `target` exists by exploring all possibilities recursively. Memoization is used to store results of subproblems to avoid redundant calculations.

- **Tabulation Approach:**

  - The DP table is built iteratively from the bottom up, where `dp[i][j]` indicates whether a subset with sum `j` can be formed using the first `i` elements.

- **Tabulation with Space Optimization:**
  - Instead of using a 2D DP table, this approach uses a 1D array to store the possible subset sums, reducing the space complexity.

---

## Problem: Coin Change Problem (Leetcode 322)

The problem is to determine the minimum number of coins needed to make up a given amount of money using coins of different denominations. If it's impossible to make up the amount, the function should return `-1`.

### Problem Breakdown

- **Input:**

  - `coins`: An array of integers representing different denominations.
  - `amount`: An integer representing the total amount of money.

- **Output:**
  - Return the fewest number of coins needed to make up the amount. Return `-1` if it's not possible.

### Recursive Approach

The recursive approach with memoization is implemented as follows:

```java
public int minimumCoins(int index, int amount, int[][] dp, int[] coins) {
    if (index == 0) {
        // If only one type of coin is left, check if it can exactly divide the amount
        if (amount % coins[index] == 0) return amount / coins[index];
        else return (int) 1e9;  // Return a large number if it's not possible
    }

    if (dp[index][amount] != -1) return dp[index][amount]; // Check if already computed

    // Option 1: Do not take the current coin
    int notTake = 0 + minimumCoins(index - 1, amount, dp, coins);

    // Option 2: Take the current coin (if possible)
    int take = coins[index] <= amount ? 1 + minimumCoins(index, amount - coins[index], dp, coins) : (int) 1e9;

    // Return the minimum of both options
    return dp[index][amount] = Math.min(notTake, take);
}
```

### Tabulation Approach (Dynamic Programming)

This approach uses a DP array to iteratively build the solution:

```java
public int coinChange(int[] coins, int amount) {
    int n = coins.length;

    // dp array where dp[i] will be storing the minimum number of coins
    // required for i amount of money.
    int[] dp = new int[amount + 1];

    // Fill the dp array with a large number (equivalent to infinity)
    Arrays.fill(dp, amount + 1);

    // Base case: no coin is needed to make up amount 0
    dp[0] = 0;

    // Iterate over each coin and fill the dp array
    for (int i = 0; i < n; i++) {
        for (int j = coins[i]; j <= amount; j++) {
            dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
        }
    }

    // If dp[amount] is still equal to amount + 1, it means it's not possible to make the amount
    return dp[amount] > amount ? -1 : dp[amount];
}
```

### Explanation

- **Base Case:** `dp[0] = 0` because zero coins are needed to make the amount 0.
- **DP Transition:**
  - For each coin, iterate through possible amounts from that coin's value to the target amount.
  - Update the DP array to store the minimum coins needed for each amount.
- **Final Answer:** The value in `dp[amount]` will be the minimum coins required. If `dp[amount]` is still a large number, return `-1`.

### Complexity

- **Time Complexity:** \(O(n \times \text{amount})\) where `n` is the number of coins.
- **Space Complexity:** \(O(\text{amount})\) as we use a single-dimensional array to store the results.

## Problem: Coin Change II Problem (Leetcode 518)

The goal of this problem is to determine the number of combinations that make up a given `amount` using the coins of different denominations provided in an array `coins`. Each coin can be used an infinite number of times.

### Problem Breakdown

- **Input:**

  - `amount`: An integer representing the total amount of money.
  - `coins`: An array of integers representing different coin denominations.

- **Output:**
  - Return the number of possible combinations that sum up to the `amount`. If there are no possible combinations, return `0`.

### Recursive Approach with Memoization

This approach involves recursively checking whether including or excluding the current coin leads to a valid solution and memoizing the results to avoid recomputation:

```java
class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        // Initialize the dp array with -1 indicating uncomputed states
        for (int[] i : dp) Arrays.fill(i, -1);

        return countNoOfWays(n - 1, amount, dp, coins);
    }

    public int countNoOfWays(int index, int amount, int[][] dp, int[] coins) {
        if (index == 0) return amount % coins[0] == 0 ? 1 : 0;
        if (dp[index][amount] != -1) return dp[index][amount];

        // Exclude the current coin
        int notTake = countNoOfWays(index - 1, amount, dp, coins);

        // Include the current coin if possible
        int take = coins[index] <= amount ? countNoOfWays(index, amount - coins[index], dp, coins) : 0;

        // Store the result in the memoization table
        return dp[index][amount] = notTake + take;
    }
}
```

### Tabulation Approach (Dynamic Programming)

This approach uses a bottom-up DP table to iteratively calculate the number of ways to make each amount using the given coins:

```java
public int change(int amount, int[] coins) {
    int n = coins.length;
    int[][] dp = new int[n][amount + 1];

    // Base case: Fill the first row
    for (int t = 0; t <= amount; t++) {
        dp[0][t] = t % coins[0] == 0 ? 1 : 0;
    }

    // Fill the DP table
    for (int index = 1; index < n; index++) {
        for (int t = 0; t <= amount; t++) {
            int notTake = dp[index - 1][t];
            int take = coins[index] <= t ? dp[index][t - coins[index]] : 0;
            dp[index][t] = notTake + take;
        }
    }

    return dp[n - 1][amount];
}
```

### Space Optimized Tabulation Approach

To further optimize space, we can use a 1D array instead of a 2D DP table:

```java
public int change(int amount, int[] coins) {
    int n = coins.length;
    int[] dp = new int[amount + 1];

    // Initialize base case
    dp[0] = 1;

    // Update the dp array for each coin
    for (int coin : coins) {
        for (int t = coin; t <= amount; t++) {
            dp[t] += dp[t - coin];
        }
    }

    return dp[amount];
}
```

### Explanation

- **Base Case:**
  - `dp[0] = 1` because there's one way to make up the amount `0`, by choosing no coins.
- **DP Transition:**

  - For each coin, iterate over possible amounts from the coin's value to the target amount.
  - Update the DP array to accumulate the number of combinations to form each amount.

- **Final Answer:** The value in `dp[amount]` will be the total number of ways to make up the amount using the given coins.

### Complexity

- **Time Complexity:** \(O(n \times \text{amount})\), where `n` is the number of coins.
- **Space Complexity:** \(O(\text{amount})\), as we use a 1D array to store the results.

---
