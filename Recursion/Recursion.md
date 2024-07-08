# Recursion solved problems

## 509. Fibonacci Number

The Fibonacci numbers, commonly denoted `F(n)` form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,

```
F(0) = 0, F(1) = 1
F(n) = F(n - 1) + F(n - 2), for n > 1.
```

Given n, calculate `F(n)`. Leetcode [Link](https://leetcode.com/problems/fibonacci-number/description/).

#### Example 1:

_Input:_ n = 2
_Output:_ 1

**Explanation:** `F(2) = F(1) + F(0) = 1 + 0 = 1`.

#### Example 2:

_Input:_ n = 3
_Output:_ 2

**Explanation:** `F(3) = F(2) + F(1) = 1 + 1 = 2`.

#### Example 3:

_Input:_ n = 4
_Output:_ 3

**Explanation:** `F(4) = F(3) + F(2) = 2 + 1 = 3`.

**Constraints:**

- `0 <= n <= 30`

```java
class Solution {
    public int fib(int n) {
        int[] arr = new int[n+1];
        Arrays.fill(arr, -1);
        return memo(n, arr);
    }

    int memo(int n, int[] arr) {
        if (n <= 1) return n;

        if (arr[n] != -1) return arr[n];

        return arr[n] = memo(n-1, arr) + memo(n-2, arr);
    }
}
```

---

## 70. Climbing Stairs

You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?. Leetcode [Link](https://leetcode.com/problems/climbing-stairs/).

#### Example 1:

_Input:_ n = 2
_Output:_ 2

**Explanation:** There are two ways to climb to the top.

1. 1 step + 1 step
2. 2 steps

#### Example 2:

_Input:_ n = 3
_Output:_ 3

**Explanation:** There are three ways to climb to the top.

1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step

**Constraints:**

- `1 <= n <= 45`

```java
class Solution {
    public int climbStairs(int n) {
        int[] arr = new int[n+1];
        Arrays.fill(arr, -1);
        return memo(n, arr);
    }

    int memo(int n, int[] arr) {
        if (n <= 1) return 1;
        if (n <= 2) return n;

        if (arr[n] != -1) return arr[n];

        return arr[n] = memo(n - 2, arr) + memo(n - 1, arr);
    }
}
```

---

## 39. Combination Sum

Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the
frequency
of at least one of the chosen numbers is different.

The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input. Leetcode [Link](https://leetcode.com/problems/combination-sum/).

### Example 1:

_Input:_ candidates = `[2,3,6,7]`, target = 7
_Output:_ `[[2,2,3],[7]]`

**Explanation:**
2 and 3 are candidates, and `2 + 2 + 3 = 7.` Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.

### Example 2:

_Input:_ candidates = `[2,3,5]`, target = 8
_Output:_ `[[2,2,2,2],[2,3,3],[3,5]]`

### Example 3:

_Input:_ candidates = `[2]`, target = 1
_Output:_ `[]`

**Constraints:**

- `1 <= candidates.length <= 30`
- `2 <= candidates[i] <= 40`
- All elements of candidates are distinct.
- `1 <= target <= 40`

```java
import java.util.ArrayList;
import java.util.List;

public class Solution {

    // This list will store the final result, which is a list of all possible combinations
    List<List<Integer>> result = new ArrayList<>();
    // This list will store the current combination
    List<Integer> ds = new ArrayList<>();

    // The main function that initiates the process and returns the result
    public List<List<Integer>> combinationSum(int[] input, int target) {
        backTracking(input, target, 0); // Start backtracking from index 0
        return result; // Return the result containing all combinations
    }

    // A helper function to perform the backtracking
    public void backTracking(int[] arr, int target, int start) {
        // If the target becomes 0, a valid combination is found
        if (target == 0) {
            result.add(new ArrayList<>(ds)); // Add a copy of the current combination to the result
            return; // Exit the function as we've found a valid combination
        }

        // Iterate over the array starting from the 'start' index
        for (int i = start; i < arr.length; i++) {
            // Only proceed if the current element is less than or equal to the target
            if (arr[i] <= target) {
                ds.add(arr[i]); // Add the current element to the current combination
                // Recursively call backTracking with the updated target and current index
                backTracking(arr, target - arr[i], i);
                ds.remove(ds.size() - 1); // Remove the last element to backtrack
            }
        }
    }

    // A main method to test the solution
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] input = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> combinations = solution.combinationSum(input, target);
        System.out.println(combinations); // Output: [[2, 2, 3], [7]]
    }
}
```

---

## 40. Combination Sum II

Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

_Note:_ The solution set must not contain duplicate combinations. Leetcode [Link](https://leetcode.com/problems/combination-sum-ii/description/).

#### Example 1:

_Input:_ candidates = `[10,1,2,7,6,1,5]`, target = 8
_Output:_
`[[1,1,6], [1,2,5], [1,7], [2,6]]`

#### Example 2:

_Input:_ candidates = `[2,5,2,1,2]`, target = 5
_Output:_
`[[1,2,2], [5]]`

**Constraints:**

- `1 <= candidates.length <= 100`
- `1 <= candidates[i] <= 50`
- `1 <= target <= 30`

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    // This list will store the final result, which is a list of all possible combinations
    List<List<Integer>> result = new ArrayList<>();
    // This list will store the current combination
    List<Integer> ds = new ArrayList<>();

    // The main function that initiates the process and returns the result
    public List<List<Integer>> combinationSum2(int[] input, int target) {
        Arrays.sort(input); // Sort the input array to handle duplicates
        backTracking(input, target, 0); // Start backtracking from index 0
        return result; // Return the result containing all combinations
    }

    // A helper function to perform the backtracking
    public void backTracking(int[] arr, int target, int start) {
        // If the target becomes 0, a valid combination is found
        if (target == 0) {
            result.add(new ArrayList<>(ds)); // Add a copy of the current combination to the result
            return; // Exit the function as we've found a valid combination
        }

        // Iterate over the array starting from the 'start' index
        for (int i = start; i < arr.length; i++) {
            // Skip duplicates in the sorted array
            if (i > start && arr[i] == arr[i - 1]) continue;
            // If the current element is greater than the target, no need to proceed further
            if (arr[i] > target) return;
            ds.add(arr[i]); // Add the current element to the current combination
            // Recursively call backTracking with the updated target and next index
            backTracking(arr, target - arr[i], i + 1);
            ds.remove(ds.size() - 1); // Remove the last element to backtrack
        }
    }

    // A main method to test the solution
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] input = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        List<List<Integer>> combinations = solution.combinationSum2(input, target);
        System.out.println(combinations); // Output: [[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]
    }
}
```

---

## 78. Subsets

Given an integer array nums of unique elements, return all possible
subsets
(the power set).

The solution set must not contain duplicate subsets. Return the solution in any order. Leetcode [Link](https://leetcode.com/problems/subsets/description/).

#### Example 1:

_Input:_ nums = `[1,2,3]`
_Output:_ `[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]`

#### Example 2:

_Input:_ nums = `[0]`
_Output:_ `[[],[0]]`

**Constraints:**

- `1 <= nums.length <= 10`
- `-10 <= nums[i] <= 10`
- All the numbers of nums are unique.

```java
import java.util.ArrayList;
import java.util.List;

class Solution {

    // List to store the final result of all subsets
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        // Start the backtracking process from index 0 with an empty list as the initial subset
        subSequence(0, nums, new ArrayList<>());
        return result;
    }

    void subSequence(int index, int[] nums, List<Integer> ds) {
        // If index is out of bounds, add the current subset to the result list
        if (index >= nums.length) {
            result.add(new ArrayList<>(ds));
            return;
        }

        // Include the current element in the subset
        ds.add(nums[index]);
        // Recur with the next index and the current subset including the current element
        subSequence(index + 1, nums, ds);
        // Backtrack by removing the last added element
        ds.remove(ds.size() - 1);
        // Recur with the next index and the current subset excluding the current element
        subSequence(index + 1, nums, ds);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1, 2, 3};
        List<List<Integer>> subsets = sol.subsets(nums);
        System.out.println(subsets); // output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
    }
}
```

---

## 90. Subsets II

Given an integer array nums that may contain duplicates, return all possible
subsets
(the power set).

The solution set must not contain duplicate subsets. Return the solution in any order. Leetcode [Link](https://leetcode.com/problems/subsets-ii/description/).

#### Example 1:

_Input:_ nums = `[1,2,2]`
_Output:_ `[[],[1],[1,2],[1,2,2],[2],[2,2]]`

#### Example 2:

_Input:_ nums = `[0]`
_Output:_ `[[],[0]]`

**Constraints:**

- `1 <= nums.length <= 10`
- `-10 <= nums[i] <= 10`

```java
class Solution {

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> ds = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        subSet2(0, nums);
        return result;
    }

    void subSet2(int index, int[] nums) {

        result.add(new ArrayList<>(ds));

        for (int i = index; i < nums.length; i++) {
            if (i != index && nums[i] == nums[i-1]) continue;
            ds.add(nums[i]);
            subSet2(i+1, nums);
            ds.remove(ds.size() - 1);
        }
    }
}
```

---

## 46. Permutations

Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order. Leetcode [Link](https://leetcode.com/problems/permutations/description/).

#### Example 1:

_Input:_ nums = `[1,2,3]`
_Output:_ `[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]`

#### Example 2:

_Input:_ nums = `[0,1]`
_Output:_ `[[0,1],[1,0]]`

#### Example 3:

_Input:_ nums = `[1]`
_Output:_ `[[1]]`

**Constraints:**

- `1 <= nums.length <= 6`
- `-10 <= nums[i] <= 10`
- All the integers of nums are unique.

```java
import java.util.ArrayList;
import java.util.List;

class Solution {

    // List to store the final result of all permutations
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        // Start the recursion process from index 0
        recursion(nums, 0);
        return result;
    }

    void recursion(int[] nums, int pointer) {
        // Base case: If the pointer reaches the end of the array, add the current permutation to the result
        if (pointer == nums.length) {
            List<Integer> ds = new ArrayList<>();
            for (int i : nums) ds.add(i);
            result.add(new ArrayList<>(ds));
            return;
        }

        // Iterate through the array starting from the current pointer
        for (int i = pointer; i < nums.length; i++) {
            // Swap the current element with the element at the pointer
            swap(i, pointer, nums);
            // Recur with the next pointer
            recursion(nums, pointer + 1);
            // Backtrack by swapping the elements back to their original positions
            swap(i, pointer, nums);
        }
    }

    // Helper method to swap elements at indices i and j in the array
    void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1, 2, 3};
        List<List<Integer>> permutations = sol.permute(nums);
        System.out.println(permutations); // output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
    }
}

```

---

## 51. N-Queens

The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively. Leetcode [Link](https://leetcode.com/problems/n-queens/description/).

#### Example 1:

![img](https://assets.leetcode.com/uploads/2020/11/13/queens.jpg)

_Input:_ n = 4
_Output:_ `[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]`

**Explanation:** There exist two distinct solutions to the 4-queens puzzle as shown above

#### Example 2:

_Input:_ n = 1
_Output:_ `[["Q"]]`

**Constraints:**

- `1 <= n <= 9`

```java
class Solution {

    List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        // Initialize the chessboard with empty cells represented by '.'
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        // Arrays to track if a row or diagonal is under attack
        int[] leftRow = new int[n];            // To track rows
        int[] upperDiagonal = new int[2 * n - 1]; // To track upper diagonals (top-left to bottom-right)
        int[] lowerDiagonal = new int[2 * n - 1]; // To track lower diagonals (bottom-left to top-right)

        // Start solving the N-Queens problem recursively
        solve(board, 0, leftRow, upperDiagonal, lowerDiagonal);

        // Return all solutions found
        return result;
    }

    // Recursive method to solve the N-Queens problem
    void solve(char[][] board, int col, int[] leftRow, int[] upperDiagonal, int[] lowerDiagonal) {
        // If all queens are placed successfully, add the current configuration to result
        if (col == board.length) {
            result.add(build(board));
            return;
        }

        // Try placing a queen in each row of the current column
        for (int row = 0; row < board.length; row++) {
            // Check if the current position is safe from attacks
            if (leftRow[row] == 0 && lowerDiagonal[row + col] == 0 && upperDiagonal[board.length - 1 + col - row] == 0) {
                // Place the queen
                board[row][col] = 'Q';

                // Mark the row and diagonals as under attack
                leftRow[row] = 1;
                lowerDiagonal[row + col] = 1;
                upperDiagonal[board.length - 1 + col - row] = 1;

                // Recursively solve for the next column
                solve(board, col + 1, leftRow, upperDiagonal, lowerDiagonal);

                // Backtrack: Remove the queen and marks
                board[row][col] = '.';
                leftRow[row] = 0;
                lowerDiagonal[row + col] = 0;
                upperDiagonal[board.length - 1 + col - row] = 0;
            }
        }
    }

    // Utility method to build the solution format as a list of strings
    List<String> build(char[][] board) {
        List<String> ans = new LinkedList<>();
        for (char[] row : board) {
            ans.add(new String(row));
        }
        return ans;
    }
}

```

## 37. Sudoku Solver

Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

1. Each of the digits 1-9 must occur exactly once in each row.
2. Each of the digits 1-9 must occur exactly once in each column.
3. Each of the digits 1-9 must occur exactly once in each of the 9 `3x3` sub-boxes of the grid.

The '.' character indicates empty cells. Problem [Link](https://leetcode.com/problems/sudoku-solver/description/).

#### Example 1:

![img](https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Sudoku-by-L2G-20050714.svg/250px-Sudoku-by-L2G-20050714.svg.png)

_Input:_

```
 board = [
            ["5","3",".",".","7",".",".",".","."],
            ["6",".",".","1","9","5",".",".","."],
            [".","9","8",".",".",".",".","6","."],
            ["8",".",".",".","6",".",".",".","3"],
            ["4",".",".","8",".","3",".",".","1"],
            ["7",".",".",".","2",".",".",".","6"],
            [".","6",".",".",".",".","2","8","."],
            [".",".",".","4","1","9",".",".","5"],
            [".",".",".",".","8",".",".","7","9"]
        ]
```

_Output:_

```
[
    ["5","3","4","6","7","8","9","1","2"],
    ["6","7","2","1","9","5","3","4","8"],
    ["1","9","8","3","4","2","5","6","7"],
    ["8","5","9","7","6","1","4","2","3"],
    ["4","2","6","8","5","3","7","9","1"],
    ["7","1","3","9","2","4","8","5","6"],
    ["9","6","1","5","3","7","2","8","4"],
    ["2","8","7","4","1","9","6","3","5"],
    ["3","4","5","2","8","6","1","7","9"]
]

```

**Explanation:** The input board is shown above and the only valid solution is shown below:

![img](https://upload.wikimedia.org/wikipedia/commons/thumb/3/31/Sudoku-by-L2G-20050714_solution.svg/250px-Sudoku-by-L2G-20050714_solution.svg.png)

**Constraints:**

- `board.length == 9`
- `board[i].length == 9`
- `board[i][j] is a digit or '.'.`
- It is guaranteed that the input board has only one solution.

```java

public class Solution {

    // Method to solve the Sudoku puzzle
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) return; // If the board is invalid, return immediately
        solve(board); // Start solving the board
    }

    // Recursive method to solve the board
    public boolean solve(char[][] board) {
        // Iterate over every cell in the board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // If the cell is empty (represented by '.')
                if (board[i][j] == '.') {
                    // Try placing each number from '1' to '9'
                    for (char c = '1'; c <= '9'; c++) {
                        // Check if placing 'c' is valid in the current cell
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c; // Place 'c' in the cell
                            // Recursively attempt to solve the rest of the board
                            if (solve(board)) return true; // If solution is found, return true
                            else board[i][j] = '.'; // If not, reset the cell and try the next number
                        }
                    }
                    return false; // If no number is valid, return false (trigger backtracking)
                }
            }
        }
        return true; // If all cells are filled correctly, return true
    }

    // Method to check if placing 'c' in board[row][col] is valid
    public boolean isValid(char[][] board, int row, int col, char c) {
        // Check if 'c' is already in the current row or column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == c) return false; // Check row
            if (board[row][i] == c) return false; // Check column
            // Check the 3x3 sub-grid
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false;
        }
        return true; // If 'c' is not found in the row, column, or sub-grid, it is valid
    }
}
```

---

## 131. Palindrome Partitioning

Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s. Leetcode [Link](https://leetcode.com/problems/palindrome-partitioning/description/).

#### Example 1:

_Input:_ s = "aab"
_Output:_ `[["a","a","b"],["aa","b"]]`

#### Example 2:

_Input:_ s = "a"
_Output:_ `[["a"]]`

**Constraints:**

- `1 <= s.length <= 16`
- s contains only lowercase English letters.

```java

class Solution {

    List<List<String>> result = new ArrayList<>();
    List<String> ds = new ArrayList<>();

    public List<List<String>> partition(String s) {
        helperFunc(s, 0);
        return result;
    }

    void helperFunc(String s, int index) {
        if (index == s.length()) {
            result.add(new ArrayList<>(ds));
            return;
        }

        for (int i = index; i < s.length(); i++) {
            if (isPalindrome(s, index, i)) {
                ds.add(s.substring(index, i+1));
                helperFunc(s, i+1);
                ds.remove(ds.size() - 1);
            }
        }
    }

    boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) return false;
            start++; end--;
        }
        return  true;
    }
}
```

## Rat in a Maze

Consider a rat placed at (0, 0) in a square matrix of order N \* N. It has to reach the destination at (N - 1, N - 1). Find all possible paths that the rat can take to reach from source to destination. The directions in which the rat can move are 'U'(up), 'D'(down), 'L' (left), 'R' (right). Value 0 at a cell in the matrix represents that it is blocked and rat cannot move to it while value 1 at a cell in the matrix represents that rat can be travel through it.
Note: In a path, no cell can be visited more than one time. If the source cell is 0, the rat cannot move to any other cell. GFG [Link](https://www.geeksforgeeks.org/problems/rat-in-a-maze-problem/1).

#### Example 1:

_Input:_
N = 4

```
m[][] = {
            {1, 0, 0, 0},
            {1, 1, 0, 1},
            {1, 1, 0, 0},
            {0, 1, 1, 1}
            }
```

_Output:_ DDRDRR DRDDRR

**Explanation:**
The rat can reach the destination at (3, 3) from (0, 0) by two paths - DRDDRR and DDRDRR, when printed in sorted order we get DDRDRR DRDDRR.

```java

class Solution {

    // Main function to find all paths from top-left to bottom-right in the maze
    public static ArrayList<String> findPath(int[][] m, int n) {
        // Initialize a visited array to keep track of visited cells
        int[][] visited = new int[n][n];

        // List to store all valid paths
        ArrayList<String> result = new ArrayList<>();

        // Start from (0,0) if the starting cell is not blocked
        if (m[0][0] == 1) {
            helperFunc(0, 0, "", m, visited, n, result);
        }

        return result; // Return all the paths found
    }

    // Helper function to perform the recursive backtracking
    static void helperFunc(int row, int col, String path, int[][] m, int[][] vis, int n, ArrayList<String> result) {
        // If the destination is reached, add the path to the result
        if (row == n - 1 && col == n - 1) {
            result.add(path);
            return;
        }

        // Mark the current cell as visited
        vis[row][col] = 1;

        // Move downward if possible
        if (row + 1 < n && m[row + 1][col] == 1 && vis[row + 1][col] == 0) {
            helperFunc(row + 1, col, path + "D", m, vis, n, result);
        }

        // Move left if possible
        if (col - 1 >= 0 && m[row][col - 1] == 1 && vis[row][col - 1] == 0) {
            helperFunc(row, col - 1, path + "L", m, vis, n, result);
        }

        // Move right if possible
        if (col + 1 < n && m[row][col + 1] == 1 && vis[row][col + 1] == 0) {
            helperFunc(row, col + 1, path + "R", m, vis, n, result);
        }

        // Move upward if possible
        if (row - 1 >= 0 && m[row - 1][col] == 1 && vis[row - 1][col] == 0) {
            helperFunc(row - 1, col, path + "U", m, vis, n, result);
        }

        // Unmark the current cell (backtrack)
        vis[row][col] = 0;
    }
}
```

---

## 60. Kth Permutation Sequence

The set `[1, 2, 3, ..., n]` contains a total of `n!` unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

1. `"123"`
2. `"132"`
3. `"213"`
4. `"231"`
5. `"312"`
6. `"321"`

Given n and k, return the kth permutation sequence. Leetcode [Link]().

#### Example 1:

_Input:_ n = 3, k = 3
_Output:_ `"213"`

#### Example 2:

_Input:_ n = 4, k = 9
_Output:_ `"2314"`

#### Example 3:

_Input:_ n = 3, k = 1
_Output:_ `"123"`

**Constraints:**

- `1 <= n <= 9`
- `1 <= k <= n!`

```java

class Solution {
    public String getPermutation(int n, int k) {
        int fact = 1;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            fact = fact * i;
            list.add(i);
        }
        list.add(n);
        String result = "";
        k = k-1;
        while (true) {
            result += list.get(k / fact);
            list.remove(k / fact);
            if (list.size() == 0) {
                break;
            }
            k %= fact;
            fact /= list.size();
        }
        return result;
    }
}

```
