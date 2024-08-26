# Dynamic Programming Solved Problems

## Steps to solve the problem:

- Try to represent the problem in terms of index
- Do all possible stuffs on that index in according to the problem statement.
- sum of all stuffs => count all ways
- min of all stuffs => find min

## Problem: Fibonacci Number (LeetCode 509)

The Fibonacci numbers, commonly denoted F(n), form a sequence called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. The Fibonacci sequence is defined as follows:

- F(0) = 0
- F(1) = 1
- F(n) = F(n - 1) + F(n - 2), for n > 1

Given `n`, the task is to calculate `F(n)`. Problem [Link](https://leetcode.com/problems/fibonacci-number/description/)

### Examples

#### Example 1:

- **Input:** n = 2
- **Output:** 1
- **Explanation:** F(2) = F(1) + F(0) = 1 + 0 = 1.

#### Example 2:

- **Input:** n = 3
- **Output:** 2
- **Explanation:** F(3) = F(2) + F(1) = 1 + 1 = 2.

#### Example 3:

- **Input:** n = 4
- **Output:** 3
- **Explanation:** F(4) = F(3) + F(2) = 2 + 1 = 3.

### Constraints

- \(0 \leq n \leq 30\)

### Approaches

#### 1. Memoization Approach (Top-Down)

This approach uses recursion and stores the results of subproblems to avoid redundant calculations. This is also known as dynamic programming with memoization.

##### Code Implementation:

```java
public class Fibonacci {
    public int fib(int n) {
        int[] memo = new int[n + 1]; // Array to store results of subproblems
        return fibonacci(n, memo);
    }

    public static int fibonacci(int n, int[] memo) {
        if (n <= 1) return n; // Base cases: F(0) = 0, F(1) = 1
        if (memo[n] != 0) return memo[n]; // Return cached result if already computed
        return memo[n] = fibonacci(n - 1, memo) + fibonacci(n - 2, memo); // Store and return result
    }
}
```

- **Time Complexity:** \(O(n)\) since each Fibonacci number is computed only once.
- **Space Complexity:** \(O(n)\) for the memoization array.

#### 2. Tabulation Approach (Bottom-Up)

This approach iteratively builds the solution from the base cases up to the desired Fibonacci number. It only uses two variables to store the previous two Fibonacci numbers, making it space-efficient.

##### Code Implementation:

```java
public class Fibonacci {
    public int fib(int n) {
        if (n <= 1) return n; // Handle base cases

        int prev2 = 0; // F(0)
        int prev = 1; // F(1)
        for (int i = 2; i <= n; i++) {
            int curr = prev + prev2; // Calculate current Fibonacci number
            prev2 = prev; // Update prev to previous Fibonacci number
            prev = curr; // Update prev2 to current Fibonacci number
        }
        return prev; // F(n)
    }
}
```

- **Time Complexity:** \(O(n)\) as it calculates each Fibonacci number up to n.
- **Space Complexity:** \(O(1)\) since it only uses a constant amount of space.

---

## Problem: House Robber (LeetCode 198)

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, but the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected. If two adjacent houses are broken into on the same night, the security system will automatically alert the police.

Given an integer array `nums` representing the amount of money of each house, you need to return the maximum amount of money you can rob tonight without alerting the police.

### Examples

#### Example 1:

- **Input:** nums = [1, 2, 3, 1]
- **Output:** 4
- **Explanation:** Rob house 1 (money = 1) and then rob house 3 (money = 3).
  Total amount you can rob = 1 + 3 = 4.

#### Example 2:

- **Input:** nums = [2, 7, 9, 3, 1]
- **Output:** 12
- **Explanation:** Rob house 1 (money = 2), rob house 3 (money = 9), and rob house 5 (money = 1).
  Total amount you can rob = 2 + 9 + 1 = 12.

### Constraints

- \(1 \leq \text{nums.length} \leq 100\)
- \(0 \leq \text{nums[i]} \leq 400\)

### Recursive Approach

In the recursive approach, we consider two scenarios for each house:

1. **Rob the current house**: If you rob the current house, you skip the next house and move to the one after that.
2. **Skip the current house**: You simply move to the next house.

The maximum amount of money that can be robbed is the maximum of these two scenarios.

### Recursive Formula:

\[
\text{rob}(i) = \max(\text{nums}[i] + \text{rob}(i + 2), \text{rob}(i + 1))
\]
Where:

- `rob(i)` represents the maximum money that can be robbed starting from house `i`.

### Base Cases:

- If `i` is beyond the last house, return `0` (since there's no house to rob).
- If `i` is the last house, return the money in that house.

### Code Implementation (Recursive Approach):

```java
public class Solution {
    public int rob(int[] nums) {
        return robHouse(nums, 0);
    }

    // Recursive function to calculate the maximum amount that can be robbed starting from house i
    private int robHouse(int[] nums, int i) {
        if (i >= nums.length) return 0; // No more houses to rob

        // Rob the current house and move to the house after the next one
        int robCurrent = nums[i] + robHouse(nums, i + 2);

        // Skip the current house and move to the next one
        int skipCurrent = robHouse(nums, i + 1);

        // Return the maximum of robbing or skipping the current house
        return Math.max(robCurrent, skipCurrent);
    }
}
```

### Explanation

- **Time Complexity:** \(O(2^n)\) because in the worst case, the function explores all possible subsets of houses.
- **Space Complexity:** \(O(n)\) due to the recursion stack.

### Optimized Recursive Approach with Memoization

To avoid recalculating the same subproblems, we can use memoization to store results of previously computed states, thereby optimizing the recursive solution.

### Code Implementation (Optimized Recursive Approach):

```java
public class Solution {
    public int rob(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1); // Initialize memo array with -1 indicating uncomputed states
        return robHouse(nums, 0, memo);
    }

    // Recursive function to calculate the maximum amount that can be robbed starting from house i
    private int robHouse(int[] nums, int i, int[] memo) {
        if (i >= nums.length) return 0; // No more houses to rob
        if (memo[i] != -1) return memo[i]; // Return previously computed result

        // Rob the current house and move to the house after the next one
        int robCurrent = nums[i] + robHouse(nums, i + 2, memo);

        // Skip the current house and move to the next one
        int skipCurrent = robHouse(nums, i + 1, memo);

        // Store the result in memo and return the maximum of robbing or skipping the current house
        memo[i] = Math.max(robCurrent, skipCurrent);
        return memo[i];
    }
}
```

### Explanation

- **Time Complexity:** \(O(n)\) because each subproblem is computed at most once.
- **Space Complexity:** \(O(n)\) due to the memoization array and recursion stack.

### Tabulation approach

This recursive approach with memoization combines the simplicity of recursion with the efficiency of dynamic programming, making it both easy to understand and performant.

This problem is a classic example of dynamic programming. The idea is to maintain an array `dp` where `dp[i]` represents the maximum amount of money that can be robbed from the first `i` houses.

For each house `i`, there are two possibilities:

1. **Don't rob house `i`:** The maximum amount of money is the same as the maximum amount after robbing up to house `i-1`.
2. **Rob house `i`:** The maximum amount is the money in house `i` plus the maximum amount after robbing up to house `i-2`.

Thus, the recurrence relation is:
\[
dp[i] = \max(dp[i-1], nums[i] + dp[i-2])
\]
The base cases are:

- `dp[0] = nums[0]`
- `dp[1] = \max(nums[0], nums[1])`

#### Tabulation Code Implementation

```java
public class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int n = nums.length;
        int[] dp = new int[n]; // dp[i] will store the maximum amount we can rob up to house i

        // Base cases
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        // Fill dp array
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i-1], nums[i] + dp[i-2]);
        }

        // The last element of dp will hold the answer
        return dp[n-1];
    }
}
```

#### Explanation

- **Time Complexity:** \(O(n)\), where \(n\) is the number of houses, because we are iterating through the array once.
- **Space Complexity:** \(O(n)\) because of the `dp` array, which stores the maximum amount that can be robbed up to each house.

#### Optimized Space Complexity Approach

We can further optimize the space complexity by using just two variables to store the results of `dp[i-1]` and `dp[i-2]` instead of an entire `dp` array.

#### Optimized Tabulation Code Implementation

```java
public class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int prev1 = 0; // dp[i-2]
        int prev2 = nums[0]; // dp[i-1]

        for (int i = 1; i < nums.length; i++) {
            int temp = prev2;
            prev2 = Math.max(prev2, nums[i] + prev1); // current dp[i]
            prev1 = temp; // update dp[i-2] to previous dp[i-1]
        }

        return prev2;
    }
}
```

#### Explanation

- **Time Complexity:** \(O(n)\)
- **Space Complexity:** \(O(1)\) since we are only using two variables to keep track of the necessary states.

---

## Problem: House Robber II (LeetCode 213)

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle, meaning the first house is the neighbor of the last one. Adjacent houses have security systems connected, and it will automatically contact the police if two adjacent houses are broken into on the same night.

Given an integer array `nums` representing the amount of money in each house, return the maximum amount of money you can rob tonight without alerting the police.

### Examples

#### Example 1:

- **Input:** nums = [2,3,2]
- **Output:** 3
- **Explanation:** You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.

#### Example 2:

- **Input:** nums = [1,2,3,1]
- **Output:** 4
- **Explanation:** Rob house 1 (money = 1) and then rob house 3 (money = 3). Total amount you can rob = 1 + 3 = 4.

#### Example 3:

- **Input:** nums = [1,2,3]
- **Output:** 3

### Constraints

- \(1 \leq \text{nums.length} \leq 100\)
- \(0 \leq \text{nums[i]} \leq 1000\)

---

### Solution with Detailed Explanations

### Approach:

Given that the houses are arranged in a circle, the first and last houses are adjacent. Therefore, if you rob the first house, you cannot rob the last one. We can break this problem into two scenarios:

1. **Scenario 1:** Rob houses from the first house to the second-last house (i.e., excluding the last house).
2. **Scenario 2:** Rob houses from the second house to the last house (i.e., excluding the first house).

The final answer will be the maximum of the two scenarios.

### 1. Recursive Memoization Approach:

```java
import java.util.Arrays;

public class Solution {

    /**
     * Main function to solve the problem.
     *
     * @param nums Array of integers representing the amount of money in each house.
     * @return The maximum amount of money that can be robbed without alerting the police.
     */
    public int rob(int[] nums) {
        int n = nums.length;

        // If there's only one house, simply rob it
        if (n == 1) return nums[0];

        // Create memoization arrays for two scenarios
        int[] memo1 = new int[n];
        Arrays.fill(memo1, -1);

        int[] memo2 = new int[n];
        Arrays.fill(memo2, -1);

        // Compute the maximum money that can be robbed from two different scenarios
        return Math.max(robHouse(nums, 0, n - 2, memo1), robHouse(nums, 1, n - 1, memo2));
    }

    /**
     * Recursive function to calculate the maximum amount that can be robbed starting from house i up to house end.
     * Uses memoization to store results of previously computed states.
     *
     * @param nums Array of integers representing the amount of money in each house.
     * @param i Current house index to start robbing.
     * @param end Last house index to consider.
     * @param memo Memoization array to store results of subproblems.
     * @return The maximum amount of money that can be robbed from house i to house end.
     */
    private int robHouse(int[] nums, int i, int end, int[] memo) {
        // Base case: no more houses to rob
        if (i > end) return 0;

        // Return the result if it is already computed
        if (memo[i] != -1) return memo[i];

        // Option 1: Rob the current house and move to the house after the next one
        int robCurrent = nums[i] + robHouse(nums, i + 2, end, memo);

        // Option 2: Skip the current house and move to the next one
        int skipCurrent = robHouse(nums, i + 1, end, memo);

        // Store and return the maximum amount of money that can be robbed
        memo[i] = Math.max(robCurrent, skipCurrent);
        return memo[i];
    }
}

```

#### Explanation:

- **Time Complexity:** \(O(n)\), where \(n\) is the number of houses. The solution involves a single pass through the list for each of the two scenarios.
- **Space Complexity:** \(O(1)\) because we use only a constant amount of extra space for storing the state of the dynamic programming calculation (`prev1` and `prev2`).

### 2. Tabulation Approach:

This approach uses an explicit DP array to store the maximum money that can be robbed up to each house.

#### Tabulation Code Implementation:

```java
public class Solution {

    // Main function to solve the problem
    public int rob(int[] nums) {
        int n = nums.length;

        // If there's only one house, simply rob it
        if (n == 1) return nums[0];

        // Calculate the maximum amount of money that can be robbed for two cases:
        return Math.max(robHelper(nums, 0, n - 2), robHelper(nums, 1, n - 1));
    }

    // Helper function to calculate the maximum money that can be robbed using tabulation
    private int robHelper(int[] nums, int start, int end) {
        if (start == end) return nums[start];

        // Create a dp array to store the maximum money that can be robbed up to each house
        int[] dp = new int[end - start + 1];

        // Initialize the first two houses
        dp[0] = nums[start];
        dp[1] = Math.max(nums[start], nums[start + 1]);

        // Fill the dp array
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 1], nums[start + i] + dp[i - 2]);
        }

        // The last element of dp will hold the answer
        return dp[dp.length - 1];
    }
}
```

#### Explanation:

- **Time Complexity:** \(O(n)\), where \(n\) is the number of houses. The solution involves filling the `dp` array for each of the two scenarios.
- **Space Complexity:** \(O(n)\) due to the `dp` array used to store the maximum money that can be robbed up to each house.

### 3. Space-Optimized Tabulation Approach:

This approach further optimizes the space complexity by using only two variables to store the results of the dynamic programming states, thereby eliminating the need for a full DP array.

#### Space-Optimized Tabulation Code Implementation:

```java
public class Solution {

    // Main function to solve the problem
    public int rob(int[] nums) {
        int n = nums.length;

        // If there's only one house, simply rob it
        if (n == 1) return nums[0];

        // Calculate the maximum amount of money that can be robbed for two cases:
        return Math.max(robHelper(nums, 0, n - 2), robHelper(nums, 1, n - 1));
    }

    // Helper function to calculate the maximum money that can be robbed using space-optimized tabulation
    private int robHelper(int[] nums, int start, int end) {
        int prev1 = 0; // This represents dp[i-2] in the previous steps
        int prev2 = 0; // This represents dp[i-1] in the previous steps

        // Iterate over the range from start to end (inclusive)
        for (int i = start; i <= end; i++) {
            // Calculate the maximum money that can be robbed if we include the current house
            int current = Math.max(prev2, nums[i] + prev1);

            // Update prev1 and prev2 for the next iteration
            prev1 = prev2;
            prev2 = current;
        }

        // The variable prev2 now contains the maximum money that can be robbed from the range start to end
        return prev2;
    }
}
```

#### Explanation:

- **Time Complexity:** \(O(n)\), where \(n\) is the number of houses. The solution involves a single pass through the list for each of the two scenarios.
- **Space Complexity:** \(O(1)\) because we use only a constant amount of extra space (`prev1` and `prev2`).

---

## Question: Unique Paths (Leetcode 62)

There is a robot on an `m x n` grid. The robot is initially located at the top-left corner (i.e., `grid[0][0]`). The robot tries to move to the bottom-right corner (i.e., `grid[m-1][n-1]`). The robot can only move either down or right at any point in time.

Given the two integers `m` and `n`, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to `2 * 10^9`.

### Example 1:

![img](https://assets.leetcode.com/uploads/2018/10/22/robot_maze.png)

```
Input: m = 3, n = 7
Output: 28
```

### Example 2:

```
Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down
```

### Constraints:

- \(1 \leq m, n \leq 100\)

### Solution 1: Recursive Approach with Memoization

```java
class Solution {
    public int uniquePaths(int m, int n) {
        // Create a memoization array to store intermediate results
        int[][] memo = new int[m][n];

        // Initialize memo array with -1 indicating uncomputed states
        for (int[] i : memo) {
            Arrays.fill(i, -1);
        }

        // Start from the bottom-right corner and calculate the number of unique paths
        return helperFunc(m - 1, n - 1, memo);
    }

    // Helper function to calculate unique paths using recursion and memoization
    public int helperFunc(int i, int j, int[][] memo) {
        // Base case: If we reach the starting point (0, 0), there is 1 unique path
        if (i == 0 && j == 0) return 1;

        // If we move out of grid boundaries, return 0 paths
        if (i < 0 || j < 0) return 0;

        // If this cell's value has already been calculated, return it from memo
        if (memo[i][j] != -1) return memo[i][j];

        // Calculate the number of paths from the cell above (up) and to the left (left)
        int up = helperFunc(i - 1, j, memo);
        int left = helperFunc(i, j - 1, memo);

        // Store the result in memo and return the total number of paths for this cell
        return memo[i][j] = up + left;
    }
}
```

### Solution 2: Tabulation Approach

```java
class Solution {
    public int uniquePaths(int m, int n) {
        // Create a DP array to store the number of unique paths for each cell
        int[][] dp = new int[m][n];

        // Fill the DP array
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Base case: There is only 1 path to reach the starting point (0, 0)
                if (i == 0 && j == 0) dp[i][j] = 1;
                else {
                    // Calculate the number of paths from the cell above (up) and to the left (left)
                    int up = i > 0 ? dp[i - 1][j] : 0;
                    int left = j > 0 ? dp[i][j - 1] : 0;

                    // Store the sum of paths from above and left cells in the DP array
                    dp[i][j] = up + left;
                }
            }
        }

        // The bottom-right corner will have the total number of unique paths
        return dp[m - 1][n - 1];
    }
}
```

### Explanation:

**Recursive Approach with Memoization:**

- The recursive approach explores all possible paths to the destination by moving either down or right.
- Memoization stores the results of subproblems to avoid redundant calculations, reducing the time complexity to \(O(m \times n)\).

**Tabulation Approach:**

- The tabulation approach iteratively builds up the solution using a 2D `dp` array.
- It avoids recursion, instead of filling the DP table iteratively based on previously computed values, leading to the same time complexity but using an iterative method.

---

## Problem: Unique Paths II

You are given an `m x n` integer array `grid`. There is a robot initially located at the top-left corner (i.e., `grid[0][0]`). The robot tries to move to the bottom-right corner (i.e., `grid[m-1][n-1]`). The robot can only move either down or right at any point in time.

An obstacle and space are marked as `1` or `0` respectively in the grid. A path that the robot takes cannot include any square that is an obstacle.

Return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to `2 * 10^9`.

### Example 1:

```
Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
Output: 2
Explanation: There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
```

### Example 2:

```
Input: obstacleGrid = [[0,1],[0,0]]
Output: 1
```

### Constraints:

- \(m == \text{obstacleGrid.length}\)
- \(n == \text{obstacleGrid[i].length}\)
- \(1 \leq m, n \leq 100\)
- `obstacleGrid[i][j]` is `0` or `1`.

### Solution 1: Recursive Solution

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        // Create a memoization table and initialize with -1
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j] = -1;
            }
        }
        // Call the recursive helper function starting from the bottom-right corner
        return helper(obstacleGrid, m - 1, n - 1, memo);
    }

    private int helper(int[][] obstacleGrid, int i, int j, int[][] memo) {
        // Base cases: if out of bounds, return 0
        if (i < 0 || j < 0) return 0;
        // If there's an obstacle, return 0 (no paths through here)
        if (obstacleGrid[i][j] == 1) return 0;
        // If we reached the start point, return 1 (one path found)
        if (i == 0 && j == 0) return 1;
        // If already calculated, return the stored result
        if (memo[i][j] != -1) return memo[i][j];
        // Recursive calls to explore the paths from the top and left cells
        int pathsFromTop = helper(obstacleGrid, i - 1, j, memo);
        int pathsFromLeft = helper(obstacleGrid, i, j - 1, memo);
        // Store the result in the memoization table and return it
        memo[i][j] = pathsFromTop + pathsFromLeft;
        return memo[i][j];
    }
}
```

### Explanation:

1. **Base Cases:**

   - If the current cell `(i, j)` is out of the grid boundaries, return `0` (no valid path).
   - If the current cell contains an obstacle (`obstacleGrid[i][j] == 1`), return `0` because no path can go through an obstacle.
   - If the robot reaches the starting point `(0, 0)`, return `1` because there's exactly one way to reach the start, which is staying there.

2. **Memoization:**

   - A 2D array `memo` is used to store the number of paths from a specific cell `(i, j)` to the bottom-right corner.
   - If the result for the cell `(i, j)` is already computed, it's returned directly to avoid redundant calculations.

3. **Recursive Case:**

   - The robot can move either from the top (`(i-1, j)`) or from the left (`(i, j-1)`).
   - The function sums up the number of paths from the top and the left cells.

4. **Final Output:**
   - The initial call to `helper` starts at the bottom-right corner of the grid `(m-1, n-1)`, and the recursive process finds the number of unique paths back to the top-left corner.

### Time Complexity:

- The time complexity is \(O(m \times n)\) because every cell in the grid is visited only once.

### Space Complexity:

- The space complexity is \(O(m \times n)\) due to the `memo` array and the recursion stack.

### Solution 2: Tabulation Approach

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // Get the dimensions of the grid
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // If the starting point has an obstacle, return 0 as no paths are possible.
        if (obstacleGrid[0][0] == 1) return 0;

        // Initialize the DP table with zeros
        int[][] dp = new int[m][n];

        // Traverse the grid row by row
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If there's an obstacle at the current cell, no paths are possible through it
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else if (i == 0 && j == 0) {
                    // Start position
                    dp[i][j] = 1;
                } else {
                    // Calculate paths from the top and left cells
                    int up = i > 0 ? dp[i-1][j] : 0;
                    int left = j > 0 ? dp[i][j-1] : 0;
                    dp[i][j] = up + left;
                }
            }
        }

        // The value in the bottom-right cell is the number of unique paths
        return dp[m-1][n-1];
    }
}
```

### Explanation:

1. **Grid Dimensions:**

   - `m` and `n` are used to represent the number of rows and columns in the grid, respectively.

2. **Initial Check:**

   - If the starting position `obstacleGrid[0][0]` has an obstacle, no paths can start from there, so return `0`.

3. **DP Table Initialization:**

   - A 2D array `dp` is initialized to store the number of unique paths to each cell. Initially, all cells are set to `0`.

4. **Filling the DP Table:**

   - Iterate through each cell in the grid:
     - If the cell has an obstacle, set `dp[i][j] = 0` because no path can pass through this cell.
     - If the cell is the starting position `(0, 0)`, set `dp[i][j] = 1`.
     - For other cells, calculate the sum of paths from the cell above (`dp[i-1][j]`) and the cell to the left (`dp[i][j-1]`).

5. **Final Result:**
   - The value at the bottom-right corner `dp[m-1][n-1]` gives the total number of unique paths from the start to the destination, avoiding obstacles.

- This solution is efficient with a time complexity of \(O(m \times n)\) and space complexity of \(O(m \times n)\).

---