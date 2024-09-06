# DP on Strings

---

## Problem: Longest Common Subsequence (Leetcode 1143)

The task is to find the length of the longest common subsequence (LCS) between two given strings, `text1` and `text2`. The LCS is a sequence that appears in both strings in the same relative order but not necessarily consecutively.

### Problem Breakdown

- **Input:**

  - `text1` and `text2`: Two strings consisting of lowercase English characters.

- **Output:**
  - Return the length of their longest common subsequence. If no common subsequence exists, return `0`.

### Example

- **Example 1:**
  - **Input:** `text1 = "abcde"`, `text2 = "ace"`
  - **Output:** `3`
  - **Explanation:** The LCS is "ace", and its length is 3.
- **Example 2:**

  - **Input:** `text1 = "abc"`, `text2 = "abc"`
  - **Output:** `3`
  - **Explanation:** The LCS is "abc", and its length is 3.

- **Example 3:**
  - **Input:** `text1 = "abc"`, `text2 = "def"`
  - **Output:** `0`
  - **Explanation:** There is no common subsequence between `text1` and `text2`.

### Recursive Approach with Memoization

This approach uses recursion with memoization to avoid repeated calculations by storing results in a 2D table.

```java
class Solution {
    public int longestCommonSubsequence(String str1, String str2) {
        int n1 = str1.length(), n2 = str2.length();
        int[][] dp = new int[n1][n2];
        for (int[] arr : dp) Arrays.fill(arr, -1);

        return helper(n1 - 1, n2 - 1, dp, str1.toCharArray(), str2.toCharArray());
    }

    public int helper(int index1, int index2, int[][] dp, char[] str1, char[] str2) {
        if (index1 < 0 || index2 < 0) return 0;
        if (dp[index1][index2] != -1) return dp[index1][index2];
        if (str1[index1] == str2[index2]) return dp[index1][index2] = 1 + helper(index1 - 1, index2 - 1, dp, str1, str2);

        return dp[index1][index2] = 0 + Math.max(helper(index1 - 1, index2, dp, str1, str2), helper(index1, index2 - 1, dp, str1, str2));
    }
}
```

### Tabulation Approach (Dynamic Programming)

In this approach, we use a bottom-up dynamic programming table to compute the LCS length iteratively.

```java
class Solution {
    public int longestCommonSubsequence(String str1, String str2) {
        int n1 = str1.length(), n2 = str2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n1][n2];
    }
}
```

### Space-Optimized Tabulation Approach

Since we only need the previous row and the current row to compute the LCS, we can reduce the space complexity by using two 1D arrays instead of a full 2D table.

```java
class Solution {
    public int longestCommonSubsequence(String str1, String str2) {
        int n1 = str1.length(), n2 = str2.length();
        int[] prev = new int[n2 + 1];
        int[] curr = new int[n2 + 1];

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    curr[j] = 1 + prev[j - 1];
                } else {
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }
            prev = curr.clone();
        }

        return prev[n2];
    }
}
```

### Explanation

- **Recursive Approach with Memoization:**

  - We recursively compare characters of `str1` and `str2` starting from the last index and check if they match.
  - If they do, we move both indices back by 1 and add 1 to the result.
  - If they don't, we try excluding characters from either `str1` or `str2` and take the maximum result.
  - Memoization is used to avoid recalculating overlapping subproblems.

- **Tabulation Approach (DP Table):**

  - We initialize a DP table where `dp[i][j]` represents the length of the LCS between `str1[0...i-1]` and `str2[0...j-1]`.
  - We iteratively fill the table by checking if the characters at `str1[i-1]` and `str2[j-1]` are equal.
  - If equal, `dp[i][j] = 1 + dp[i-1][j-1]`, otherwise `dp[i][j] = max(dp[i-1][j], dp[i][j-1])`.

- **Space-Optimized Tabulation:**
  - Instead of storing the entire DP table, we only store the previous and current rows at any point in time, which reduces the space complexity.

### Complexity

- **Time Complexity:** \(O(n1 \times n2)\) for all approaches, where `n1` is the length of `text1` and `n2` is the length of `text2`.
- **Space Complexity:**
  - Recursive approach with memoization: \(O(n1 \times n2)\).
  - Tabulation approach: \(O(n1 \times n2)\).
  - Space-optimized tabulation: \(O(n2)\) since we use only two 1D arrays.

---

## Problem: Longest Palindromic Subsequence (Leetcode 516)

#### Problem:

Given a string `s`, find the longest palindromic subsequence's length in `s`.

A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

#### Example 1:

- **Input**: `s = "bbbab"`
- **Output**: `4`
- **Explanation**: One possible longest palindromic subsequence is `"bbbb"`.

#### Example 2:

- **Input**: `s = "cbbd"`
- **Output**: `2`
- **Explanation**: One possible longest palindromic subsequence is `"bb"`.

#### Constraints:

- \(1 \leq s.length \leq 1000\)
- `s` consists only of lowercase English letters.

#### Approach:

If we find the longest common subsequence for the given string and the reverse of that string, we can find the solution for the longest palindromic subsequence.

### Solution:

```java
class Solution {
    public int longestPalindromeSubseq(String s) {
        // Reverse the given string 's' to get the reversed string 'str'.
        StringBuffer sbr = new StringBuffer(s);  // StringBuffer helps to reverse the string.
        String str = sbr.reverse().toString();   // Get the reversed string.

        // The problem is reduced to finding the longest common subsequence (LCS)
        // between the original string 's' and its reversed string 'str'.
        return longestCommonSubsequence(s, str);
    }

    // Function to calculate the longest common subsequence (LCS)
    // between two strings 'str1' and 'str2'.
    public int longestCommonSubsequence(String str1, String str2) {
        int n1 = str1.length();  // Length of the original string.
        int n2 = str2.length();  // Length of the reversed string.

        // Create two arrays, 'prev' and 'curr', to store the DP table for LCS.
        // This helps in space optimization by not storing the entire DP matrix.
        int[] prev = new int[n2 + 1];  // Array for the previous row in DP table.
        int[] curr = new int[n2 + 1];  // Array for the current row in DP table.

        // Start filling the DP table to compute the LCS.
        for (int i = 1; i <= n1; i++) {  // Loop through each character of the original string.
            for (int j = 1; j <= n2; j++) {  // Loop through each character of the reversed string.
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    // If characters match, increment the LCS count.
                    curr[j] = 1 + prev[j - 1];  // Add 1 to the result of previous diagonally left value in DP table.
                } else {
                    // If characters don't match, take the maximum of the top or left values from DP table.
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }
            // Move the current row to the previous row for the next iteration.
            prev = curr.clone();  // Clone current row to previous row.
        }

        // The last value in 'prev' array represents the length of the longest palindromic subsequence.
        return prev[n2];
    }
}

```

---

## Leetcode: 1312. Minimum Insertion Steps to Make a String Palindrome

Given a string `s`, you can insert any character at any index in one step. The task is to return the minimum number of steps required to make `s` a palindrome.

A palindrome is a string that reads the same forward and backward.

### **Example 1:**

**Input:** `s = "zzazz"`

**Output:** `0`

**Explanation:** The string `"zzazz"` is already a palindrome, so no insertions are needed.

### **Example 2:**

**Input:** `s = "mbadm"`

**Output:** `2`

**Explanation:** By inserting two characters, the string can be made palindrome, for example, `"mbdadbm"` or `"mdbabdm"`.

### **Example 3:**

**Input:** `s = "leetcode"`

**Output:** `5`

**Explanation:** By inserting five characters, the string can be made palindrome. One possible result is `"leetcodocteel"`.

### **Constraints:**

- 1 ≤ `s.length` ≤ 500
- `s` consists of lowercase English letters.

---

### **Approach:**

The problem can be reduced to finding how many characters need to be inserted to make the string a palindrome. This can be done by utilizing the **longest palindromic subsequence** of the string.

The **minimum insertions** to make a string a palindrome is the difference between the length of the string and the length of its longest palindromic subsequence.

Steps:

1. Find the **longest palindromic subsequence** (LPS) in the string. The LPS is the longest sequence that can be derived from the string which reads the same forwards and backwards.
2. The minimum number of insertions required to make the string a palindrome will be the length of the string minus the length of the longest palindromic subsequence.

To find the longest palindromic subsequence, we can utilize **dynamic programming** by finding the **longest common subsequence (LCS)** between the given string `s` and its reverse `rev_s`.

### **Solution Code:**

```java
class Solution {
    public int minInsertions(String s) {
        // Minimum insertions to make a string palindrome is:
        // Total length of string - Longest Palindromic Subsequence (LPS)
        return s.length() - longestPalindromeSubseq(s);
    }

    // Function to find the longest palindromic subsequence
    public int longestPalindromeSubseq(String s) {
        // Reverse the string to compare for longest common subsequence (LCS)
        StringBuffer sbr = new StringBuffer(s);
        String rev_s = sbr.reverse().toString();

        // Return the LCS of the string and its reverse
        return longestCommonSubsequence(s, rev_s);
    }

    // Function to find the longest common subsequence between two strings
    public int longestCommonSubsequence(String str1, String str2) {
        int n1 = str1.length(), n2 = str2.length();
        // Create two arrays for current and previous rows in DP table
        int[] prev = new int[n2 + 1];
        int[] curr = new int[n2 + 1];

        // Fill the DP table using LCS logic
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    curr[j] = 1 + prev[j - 1];
                } else {
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }
            // Move the current row to previous for the next iteration
            prev = curr.clone();
        }

        // Return the LCS length which is stored in the last cell
        return prev[n2];
    }
}
```

### **Explanation:**

1. **`minInsertions(String s)`**:

   - The function calculates the minimum number of insertions required to make `s` a palindrome.
   - This is done by subtracting the length of the longest palindromic subsequence from the length of the string.

2. **`longestPalindromeSubseq(String s)`**:

   - To find the longest palindromic subsequence, we reverse the string and find the longest common subsequence (LCS) between the original string and its reverse.

3. **`longestCommonSubsequence(String str1, String str2)`**:
   - This function computes the LCS using dynamic programming.
   - It maintains two arrays, `prev` and `curr`, to store the current and previous row of the DP table to optimize space.

### **Time Complexity:**

- The time complexity is **O(n^2)**, where `n` is the length of the string `s`. This is because we are filling up a dynamic programming table of size `n x n` for the LCS calculation.

### **Space Complexity:**

- The space complexity is **O(n)** due to the use of two arrays (`prev` and `curr`) to store the DP states for the current and previous rows.

---

## Leetcode: 583. Delete Operation for Two Strings

Given two strings `word1` and `word2`, return the minimum number of steps required to make `word1` and `word2` the same. In one step, you can delete exactly one character from either string.

### **Examples**

### **Example 1:**

**Input:** `word1 = "sea"`, `word2 = "eat"`  
**Output:** `2`  
**Explanation:** You need one step to delete `'s'` from `"sea"` to get `"ea"`, and another step to delete `'t'` from `"eat"` to get `"ea"`. Therefore, two steps are required.

### **Example 2:**

**Input:** `word1 = "leetcode"`, `word2 = "etco"`  
**Output:** `4`  
**Explanation:** You need to delete four characters in total to make the two strings the same.

### **Constraints**

1 ≤ `word1.length`, `word2.length` ≤ 500  
`word1` and `word2` consist of only lowercase English letters.

---

### **Approach**

The idea is to find the **Longest Common Subsequence (LCS)** between the two strings. Once we have the LCS, the characters not part of this subsequence in both `word1` and `word2` must be deleted.

The **minimum number of deletions** required to make `word1` and `word2` the same is:

Deletions = (Length of word1 - Length of LCS) + (Length of word2 - Length of LCS)

Or equivalently:

Deletions = Length of word1 + Length of word2 - 2 × Length of LCS

### **Solution Code**

```java
class Solution {
    public int minDistance(String word1, String word2) {
        // Total number of deletions needed is calculated by subtracting 2 times the LCS
        return word1.length() + word2.length() - (2 * longestCommonSubsequence(word1, word2));
    }

    // Function to compute the longest common subsequence between two strings
    public int longestCommonSubsequence(String str1, String str2) {
        int n1 = str1.length(), n2 = str2.length();
        int[] prev = new int[n2 + 1];
        int[] curr = new int[n2 + 1];

        // DP table construction to find LCS length
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    curr[j] = 1 + prev[j - 1];  // If chars match, increment the subsequence length
                } else {
                    curr[j] = Math.max(prev[j], curr[j - 1]);  // Otherwise, take the max length from previous states
                }
            }
            prev = curr.clone();  // Move current row to previous for the next iteration
        }

        // Return the length of the LCS
        return prev[n2];
    }
}
```

### **Explanation**

- `minDistance(String word1, String word2)`  
  This function computes the minimum number of deletions required to make the two strings equal. The formula used is: `word1.length() + word2.length() - 2 * longestCommonSubsequence(word1, word2)`.

- `longestCommonSubsequence(String str1, String str2)`  
  A dynamic programming approach is used to compute the longest common subsequence (LCS) between `str1` and `str2`. Two arrays, `prev` and `curr`, are used to optimize space and store LCS values for the current and previous iterations. If characters match, the LCS is incremented by 1, otherwise, the maximum value from the previous row/column is taken.

### **Time Complexity**

- O(n1 \* n2), where `n1` and `n2` are the lengths of `word1` and `word2`, respectively. This is due to filling up a DP table of size `n1 x n2`.

### **Space Complexity**

- O(n2), because we are only using two arrays (`prev` and `curr`) of size `n2` to store the DP states for each row.

---

## Leetcode: 1092. Shortest Common Supersequence

Given two strings `str1` and `str2`, return the shortest string that has both `str1` and `str2` as subsequences. If there are multiple valid strings, return any of them.

A string `s` is a subsequence of string `t` if deleting some number of characters from `t` (possibly 0) results in the string `s`.

**Examples:**

1. **Input:**

   - str1 = "abac"
   - str2 = "cab"

   **Output:** "cabac"

   **Explanation:**

   - `str1 = "abac"` is a subsequence of `"cabac"` because we can delete the first `"c"`.
   - `str2 = "cab"` is a subsequence of `"cabac"` because we can delete the last `"ac"`.
   - The answer provided is the shortest such string that satisfies these properties.

2. **Input:**

   - str1 = "aaaaaaaa"
   - str2 = "aaaaaaaa"

   **Output:** "aaaaaaaa"

**Constraints:**

- 1 ≤ str1.length, str2.length ≤ 1000
- str1 and str2 consist of lowercase English letters.

---

**Solution:**

```java
class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        int n1 = str1.length(), n2 = str2.length();

        // Create a DP table to store the lengths of the longest common subsequence (LCS)
        // dp[i][j] will hold the LCS length for str1[0...i-1] and str2[0...j-1]
        int[][] dp = new int[n1 + 1][n2 + 1];

        // Fill the DP table using LCS logic
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    // If characters match, add 1 to the value from the diagonal (i-1, j-1)
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    // If they don't match, take the maximum value from (i-1, j) or (i, j-1)
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // To reconstruct the shortest common supersequence, start from the bottom-right corner
        StringBuilder ans = new StringBuilder();
        int i = n1, j = n2;

        // Traverse the DP table to build the result string
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                // If characters match, include it in the result
                ans.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                // If the character from str1 is part of the result, include it and move upwards
                ans.append(str1.charAt(i - 1));
                i--;
            } else {
                // If the character from str2 is part of the result, include it and move leftwards
                ans.append(str2.charAt(j - 1));
                j--;
            }
        }

        // Add remaining characters of str1 or str2 if any are left
        while (i > 0) {
            ans.append(str1.charAt(i - 1));
            i--;
        }
        while (j > 0) {
            ans.append(str2.charAt(j - 1));
            j--;
        }

        // Since we built the string backwards, reverse it to get the final result
        return ans.reverse().toString();
    }
}
```

### Explanation:

1. **DP Table Construction:**

   - We first create a 2D DP table where `dp[i][j]` contains the length of the longest common subsequence (LCS) of `str1[0...i-1]` and `str2[0...j-1]`.
   - We fill this table by comparing the characters of `str1` and `str2`. If the characters match, we take the diagonal value (`dp[i-1][j-1]`) and add 1. Otherwise, we take the maximum of the values from the cell above (`dp[i-1][j]`) or the cell to the left (`dp[i][j-1]`).

2. **Building the Result:**
   - Starting from the bottom-right of the DP table, we backtrack to build the shortest common supersequence.
   - If the characters match, we add that character to the result. If they don’t match, we add the character from either `str1` or `str2`, depending on which direction gives us the longer LCS.
   - After finishing this process, we may still have characters left in either `str1` or `str2`, so we append the remaining characters.
3. **Reversing the Result:**
   - Since we constructed the result from the end of the strings, we need to reverse the string before returning it.

### Time Complexity:

- **O(n1 \* n2)** where `n1` and `n2` are the lengths of `str1` and `str2` respectively. We fill a 2D DP table of size `n1 x n2` and then backtrack to construct the final string.

### Space Complexity:

- **O(n1 \* n2)** for the DP table and extra space for constructing the final string.

---

## Leetcode: 115. Distinct Subsequences

Given two strings `s` and `t`, return the number of distinct subsequences of `s` which equals `t`. A subsequence is a sequence derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

**Examples**

Example 1:  
Input: `s = "rabbbit"`, `t = "rabbit"`  
Output: `3`  
Explanation: There are 3 distinct ways to generate `"rabbit"` from `"rabbbit"`.

Example 2:  
Input: `s = "babgbag"`, `t = "bag"`  
Output: `5`  
Explanation: There are 5 distinct ways to generate `"bag"` from `"babgbag"`.

**Constraints**

1 ≤ `s.length`, `t.length` ≤ 1000  
`s` and `t` consist of English letters.

---

**Approach**

To solve this problem, we can use dynamic programming. We need to count the number of ways to form the string `t` from the string `s`.

### Recursive Solution with Memoization

```java
class Solution {
    public int numDistinct(String s, String t) {
        int n1 = s.length(), n2 = t.length();

        int[][] dp = new int[n1][n2];
        for (int[] arr : dp) Arrays.fill(arr, -1);

        return helper(n1-1, n2-1, dp, s.toCharArray(), t.toCharArray());
    }

    public int helper(int index1, int index2, int[][] dp, char[] str1, char[] str2) {
        if (index2 < 0) return 1;  // If t is empty, there is one way to match it with any prefix of s
        if (index1 < 0) return 0;  // If s is empty and t is not, there are no ways to match
        if (dp[index1][index2] != -1) return dp[index1][index2];
        if (str1[index1] == str2[index2]) {
            // If characters match, two choices: use the match or skip
            return dp[index1][index2] = helper(index1-1, index2-1, dp, str1, str2) + helper(index1-1, index2, dp, str1, str2);
        } else {
            // If characters do not match, skip the character in s
            return dp[index1][index2] = helper(index1-1, index2, dp, str1, str2);
        }
    }
}
```

### Tabulation Solution

```java
class Solution {
    public int numDistinct(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] dp = new int[n+1][m+1];

        // Base case: An empty t can be matched with any prefix of s
        for (int i = 0; i <= n; i++) dp[i][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    // If characters match, two choices: include or exclude the character
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                } else {
                    // If characters do not match, exclude the character
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[n][m];
    }
}
```

### Tabulation with Space Optimization

```java
class Solution {
    public int numDistinct(String s, String t) {
        int n = s.length(), m = t.length();

        int[] prev = new int[m+1];
        int[] curr = new int[m+1];

        // Base case: An empty t can be matched with any prefix of s
        prev[0] = curr[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    curr[j] = prev[j-1] + prev[j];
                } else {
                    curr[j] = prev[j];
                }
            }
            prev = curr.clone();
        }

        return prev[m];
    }
}
```

### Optimized Space Approach

```java
class Solution {
    public int numDistinct(String s, String t) {
        int n = s.length(), m = t.length();

        int[] prev = new int[m+1];
        prev[0] = 1;  // Base case: An empty t can be matched with any prefix of s

        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= 1; j--) {
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    prev[j] = prev[j-1] + prev[j];
                }
            }
        }

        return prev[m];
    }
}
```

**Explanation**

1. **Recursive Solution with Memoization**: Uses recursion to count the number of ways to form `t` from `s`, storing intermediate results to avoid redundant computations.

2. **Tabulation Solution**: Constructs a DP table to store the number of ways to form each prefix of `t` from each prefix of `s`, iteratively building up from smaller subproblems.

3. **Tabulation with Space Optimization**: Uses two 1D arrays to store the DP values instead of a full 2D table, reducing space complexity.

4. **Optimized Space Approach**: Further optimizes space by using a single 1D array and updating it in reverse to ensure correct values for each state.

**Time Complexity**: O(n \* m), where `n` and `m` are the lengths of `s` and `t`, respectively. This is due to filling up the DP table.

**Space Complexity**:

- Recursive Solution: O(n \* m) due to the DP table used for memoization.
- Tabulation Solution: O(n \* m) due to the DP table.
- Space-Optimized Solutions: O(m) due to the 1D arrays used for storing intermediate results.

---

## Leetcode: 72. Edit Distance

The goal of this problem is to find the minimum number of operations required to convert one string (`word1`) into another string (`word2`). You are allowed three types of operations:

- Insert a character
- Delete a character
- Replace a character

### Example 1:

Input: `word1 = "horse"`, `word2 = "ros"`  
Output: `3`  
Explanation:

- `horse -> rorse` (replace 'h' with 'r')
- `rorse -> rose` (remove 'r')
- `rose -> ros` (remove 'e')

### Example 2:

Input: `word1 = "intention"`, `word2 = "execution"`  
Output: `5`  
Explanation:

- `intention -> inention` (remove 't')
- `inention -> enention` (replace 'i' with 'e')
- `enention -> exention` (replace 'n' with 'x')
- `exention -> exection` (replace 'n' with 'c')
- `exection -> execution` (insert 'u')

### Constraints:

- 0 ≤ word1.length, word2.length ≤ 500
- `word1` and `word2` consist of lowercase English letters.

---

### Recursive Solution with Memoization:

```java
class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();

        int[][] dp = new int[n][m];
        for (int[] arr : dp) Arrays.fill(arr, -1);

        return helper(n-1, m-1, dp, word1, word2);
    }

    public int helper(int i, int j, int[][] dp, String str1, String str2) {
        // Base case: if one string is empty
        if (i < 0) return j+1; // If word1 is exhausted, insert remaining chars of word2
        if (j < 0) return i+1; // If word2 is exhausted, delete remaining chars of word1
        if (dp[i][j] != -1) return dp[i][j]; // Check if result is already computed

        if (str1.charAt(i) == str2.charAt(j))
            return dp[i][j] = helper(i-1, j-1, dp, str1, str2); // If chars match, move to next

        // If chars don't match, consider the three operations
        return dp[i][j] = 1 + Math.min(helper(i, j-1, dp, str1, str2),  // Insert
                                Math.min(helper(i-1, j, dp, str1, str2),  // Delete
                                         helper(i-1, j-1, dp, str1, str2)));  // Replace
    }
}
```

### Tabulation Solution:

```java
class Solution {
    public int minDistance(String str1, String str2) {
        int n = str1.length(), m = str2.length();

        int[][] dp = new int[n+1][m+1];

        // Initialize base cases
        for (int i = 0; i <= n; i++) dp[i][0] = i; // Deleting all chars from word1
        for (int j = 0; j <= m; j++) dp[0][j] = j; // Inserting all chars of word2

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1]; // No operation needed
                else
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], // Replace
                                Math.min(dp[i-1][j],       // Delete
                                         dp[i][j-1]));     // Insert
            }
        }

        return dp[n][m];
    }
}
```

### Tabulation with Space Optimization:

```java
class Solution {
    public int minDistance(String str1, String str2) {
        int n = str1.length(), m = str2.length();

        int[] prev = new int[m+1];
        int[] curr = new int[m+1];

        // Initialize base case for the first row
        for (int j = 0; j <= m; j++) prev[j] = j;

        for (int i = 1; i <= n; i++) {
            curr[0] = i; // Initialize base case for the first column
            for (int j = 1; j <= m; j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1))
                    curr[j] = prev[j-1]; // No operation needed
                else
                    curr[j] = 1 + Math.min(prev[j-1], // Replace
                                Math.min(prev[j],   // Delete
                                         curr[j-1])); // Insert
            }
            prev = curr.clone(); // Move the current row to the previous row for next iteration
        }

        return prev[m];
    }
}
```

---

### Explanation:

1. **Recursive Solution with Memoization**:

   - Recursively checks each character of both strings from the end.
   - Uses memoization to store the results of subproblems to avoid recomputation.

2. **Tabulation Solution**:

   - Builds a dynamic programming (DP) table where `dp[i][j]` represents the minimum number of operations to convert the first `i` characters of `word1` to the first `j` characters of `word2`.
   - Fills the table iteratively based on the current characters.

3. **Tabulation with Space Optimization**:
   - Instead of using a full DP table, this approach only uses two arrays to store the previous and current rows, significantly reducing space complexity.

---

### Time Complexity:

- All approaches have a time complexity of O(n \* m), where `n` is the length of `word1` and `m` is the length of `word2`, as we compute the result for every combination of characters in the two strings.

### Space Complexity:

- **Recursive with Memoization**: O(n \* m) for the memoization table and recursive stack.
- **Tabulation**: O(n \* m) for the DP table.
- **Space-Optimized Tabulation**: O(m) for storing only two arrays.

---

## Leetcode: 44. Wildcard Matching

This problem requires you to implement a pattern matching algorithm for strings, with two special wildcard characters:

- `'?'` matches exactly one character.
- `'*'` matches any sequence of characters (including the empty sequence).

The goal is to determine if the pattern `p` matches the entire string `s`.

---

### Problem Breakdown

- **'?'** matches exactly one character.
- **'\*'** matches any sequence of characters, including no characters.

We need to evaluate whether the pattern `p` completely matches the string `s`.

---

### Examples

#### Example 1:

**Input:** `s = "aa"`, `p = "a"`  
**Output:** `false`  
**Explanation:** The pattern "a" does not match the entire string "aa" because it is missing one character.

#### Example 2:

**Input:** `s = "aa"`, `p = "*"`  
**Output:** `true`  
**Explanation:** The '\*' matches the entire string "aa" since '\*' can represent any sequence of characters.

#### Example 3:

**Input:** `s = "cb"`, `p = "?a"`  
**Output:** `false`  
**Explanation:** The '?' matches 'c', but 'a' in the pattern does not match 'b' in the string.

---

### Recursive Solution with Memoization

This approach uses a recursive helper function with memoization to avoid recomputation of subproblems. It checks each character of `p` and `s`, and depending on the current character, it either moves to the next position or processes the wildcard.

```java
class Solution {
    public boolean isMatch(String str2, String str1) {
        int n = str1.length(), m = str2.length();

        int[][] dp = new int[n][m];
        for (int[] arr : dp) Arrays.fill(arr, -1);

        return helper(n-1, m-1, dp, str1, str2) == 1 ? true : false;
    }

    public int helper(int i, int j, int[][] dp, String str1, String str2) {
        if (i < 0 && j < 0) return 1;  // Both strings are exhausted
        if (i < 0 && j >= 0) return 0;  // Pattern exhausted but string is not
        if (j < 0 && i >= 0) {  // Check if remaining pattern can match empty string
            for (int k = 0; k <= i; k++)
                if (str1.charAt(k) != '*') return 0;
            return 1;
        }
        if (dp[i][j] != -1) return dp[i][j];  // Memoization check

        if (str1.charAt(i) == str2.charAt(j) || str1.charAt(i) == '?')  // Exact match or '?'
            return dp[i][j] = helper(i-1, j-1, dp, str1, str2);

        if (str1.charAt(i) == '*')  // '*' can match zero or more characters
            return dp[i][j] = (helper(i-1, j, dp, str1, str2) == 1 || helper(i, j-1, dp, str1, str2) == 1) ? 1 : 0;

        return dp[i][j] = 0;  // No match
    }
}
```

---

### Tabulation Solution (Dynamic Programming)

This solution uses a dynamic programming table to iteratively fill in whether the pattern `p` matches the string `s`. The table `dp[i][j]` indicates whether the first `i` characters of `p` match the first `j` characters of `s`.

```java
class Solution {
    public boolean isMatch(String str2, String str1) {
        int n = str1.length(), m = str2.length();

        boolean[][] dp = new boolean[n+1][m+1];

        // Base cases
        dp[0][0] = true;  // Empty pattern matches empty string
        for (int j = 1; j <= m; j++) dp[0][j] = false;  // Pattern is empty, string is not
        for (int i = 1; i <= n; i++) {
            boolean allStars = true;
            for (int k = 1; k <= i; k++) {
                if (str1.charAt(k-1) != '*') {
                    allStars = false;
                    break;
                }
            }
            dp[i][0] = allStars;  // Only '*' can match an empty string
        }

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1) || str1.charAt(i-1) == '?') {
                    dp[i][j] = dp[i-1][j-1];  // Characters match or '?'
                } else if (str1.charAt(i-1) == '*') {
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];  // '*' can match zero or more characters
                } else {
                    dp[i][j] = false;  // No match
                }
            }
        }

        return dp[n][m];
    }
}
```

---

### Explanation of the Approach

1. **Recursive with Memoization**:

   - The function `helper(i, j)` checks if `p[0..i]` matches `s[0..j]`.
   - If the character at `p[i]` is '\*', it can match an empty sequence (i.e., ignore the character in `p`) or match one character from `s` and recurse.
   - This solution is memoized to avoid recomputation, making it more efficient than plain recursion.

2. **Tabulation**:
   - In the tabulation approach, we build a 2D DP table where `dp[i][j]` indicates whether the first `i` characters of `p` match the first `j` characters of `s`.
   - The table is filled in by considering the base cases and the current characters of `p` and `s`.

### Time Complexity:

- Both approaches have a time complexity of **O(n \* m)**, where `n` is the length of the pattern `p` and `m` is the length of the string `s`.

### Space Complexity:

- **Recursive with Memoization**: **O(n \* m)** for the memoization table.
- **Tabulation**: **O(n \* m)** for the DP table.

---
