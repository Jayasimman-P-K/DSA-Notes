# Sliding Windows

### Max Sum Subarray of size K

Problem [Link](https://www.geeksforgeeks.org/problems/max-sum-subarray-of-size-k5313/1).

Given an array of integers Arr of size N and a number K. Return the maximum sum of a subarray of size K.

**NOTE**: A subarray is a contiguous part of any given array.

##### Example 1:

Input: N = 4, K = 2
Arr = [100, 200, 300, 400]
Output: 700

**Explanation:**
Arr3 + Arr4 =700,
which is maximum.

##### Example 2:

Input: N = 4, K = 4
Arr = [100, 200, 300, 400]
Output: 1000

**Explanation:**
Arr1 + Arr2 + Arr3 + Arr4 =1000, which is maximum.

**Your Task:**

You don't need to read input or print anything. Your task is to complete the function maximumSumSubarray() which takes the integer K, vector Arr with size N, containing the elements of the array and returns the maximum sum of a subarray of size K.

**Expected Time Complexity:** O(N)
**Expected Auxiliary Space:** O(1)

**Constraints:**
1 <= N <= 105
1 <= Arri <= 105
1 <= K <= N

```java
class Solution{
    static long maximumSumSubarray(int K, ArrayList<Integer> Arr,int N){
        long currentSum = 0;
        long maxSum = 0;

        for(int i = 0; i<K; i++) {
            currentSum += Arr.get(i);
        }

        maxSum = currentSum;

        for(int i=1; i<N-K+1; i++) {
            currentSum = currentSum - Arr.get(i-1) + Arr.get(i+K-1);
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }

        return maxSum;
    }
}
```

### 1423. Maximum Points You Can Obtain from Cards

There are several cards arranged in a row, and each card has an associated number of points. The points are given in the integer array `cardPoints`.

In one step, you can take one card from the beginning or from the end of the row. You have to take exactly `k` cards.

Your score is the sum of the points of the cards you have taken.

Given the integer array `cardPoints` and the integer `k`, return the maximum score you can obtain. Problem [link](https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/description/).

#### Examples

##### Example 1:

**Input**: `cardPoints = [1,2,3,4,5,6,1]`, `k = 3`  
**Output**: `12`  
**Explanation**: After the first step, your score will always be 1. However, choosing the rightmost card first will maximize your total score. The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.

##### Example 2:

**Input**: `cardPoints = [2,2,2]`, `k = 2`  
**Output**: `4`  
**Explanation**: Regardless of which two cards you take, your score will always be 4.

##### Example 3:

**Input**: `cardPoints = [9,7,7,9,7,7,9]`, `k = 7`  
**Output**: `55`  
**Explanation**: You have to take all the cards. Your score is the sum of points of all cards.

#### Constraints

- `1 <= cardPoints.length <= 105`
- `1 <= cardPoints[i] <= 104`
- `1 <= k <= cardPoints.length`

#### Solution

```java
public class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int leftSum = 0, rightSum = 0;
        int maxSum = Integer.MIN_VALUE;

        // Calculate the initial sum of the first k cards from the left
        for (int i = 0; i < k; i++) {
            leftSum += cardPoints[i];
        }
        maxSum = leftSum;

        // Use a sliding window to find the maximum sum by including cards from the right
        int rightIndex = cardPoints.length - 1;
        for (int i = k - 1; i >= 0; i--) {
            leftSum -= cardPoints[i];
            rightSum += cardPoints[rightIndex];
            rightIndex--;

            maxSum = Math.max(maxSum, leftSum + rightSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        System.out.println(solution.maxScore(new int[]{1, 2, 3, 4, 5, 6, 1}, 3)); // Output: 12
        System.out.println(solution.maxScore(new int[]{2, 2, 2}, 2)); // Output: 4
        System.out.println(solution.maxScore(new int[]{9, 7, 7, 9, 7, 7, 9}, 7)); // Output: 55
    }
}
```

### 3. Longest Substring Without Repeating Characters

Given a string `s`, find the length of the longest substring without repeating characters. Problem [link](https://leetcode.com/problems/longest-substring-without-repeating-characters/description/).

#### Examples

##### Example 1:

**Input**: `s = "abcabcbb"`  
**Output**: `3`  
**Explanation**: The answer is "abc", with the length of 3.

##### Example 2:

**Input**: `s = "bbbbb"`  
**Output**: `1`  
**Explanation**: The answer is "b", with the length of 1.

##### Example 3:

**Input**: `s = "pwwkew"`  
**Output**: `3`  
**Explanation**: The answer is "wke", with the length of 3. Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

#### Solution

```java
import java.util.Arrays;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        // Size of the String
        int size = s.length();

        // Initialize the array to store the last index of each character with -1
        int[] index = new int[128]; // ASCII size is 128
        Arrays.fill(index, -1);

        // Initialize pointers for the sliding window
        int maxLen = 0;
        int start = 0, end = 0;

        // Iterate over the String
        while (end < size) {
            char currChar = s.charAt(end);

            // Update start pointer to the right of the last occurrence of currChar
            if (index[currChar] >= start) {
                start = index[currChar] + 1;
            }

            // Update the maximum length
            maxLen = Math.max(maxLen, end - start + 1);

            // update the index of the currChar
            index[currChar] = end;
            end++;
        }

        return maxLen;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb")); // Output: 3
        System.out.println(solution.lengthOfLongestSubstring("bbbbb")); // Output: 1
        System.out.println(solution.lengthOfLongestSubstring("pwwkew")); // Output: 3
    }
}
```

### 1004. Max Consecutive Ones III

Given a binary array `nums` and an integer `k`, return the maximum number of consecutive 1's in the array if you can flip at most `k` 0's. Problem [link](https://leetcode.com/problems/max-consecutive-ones-iii/description/).

#### Examples

##### Example 1:

**Input**: `nums = [1,1,1,0,0,0,1,1,1,1,0]`, `k = 2`  
**Output**: `6`  
**Explanation**: [1,1,1,0,0,1,1,1,1,1,1]  
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.

##### Example 2:

**Input**: `nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1]`, `k = 3`  
**Output**: `10`  
**Explanation**: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]  
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.

#### Constraints

- `1 <= nums.length <= 105`
- `nums[i]` is either `0` or `1`.
- `0 <= k <= nums.length`

#### Solution

```java
public class Solution {
    public int longestOnes(int[] nums, int k) {
        int size = nums.length;

        // Pointers for the sliding window
        int left = 0, right = 0;
        // To store the maximum length of consecutive 1's
        int maxLen = 0;
        // To count the number of 0's flipped within the current window
        int flippedZeroes = 0;

        // Iterate over the array using the right pointer
        while (right < size) {
            // If the current element is 0, increment the count of flipped zeroes
            if (nums[right] == 0) flippedZeroes++;

            // If the number of flipped zeroes exceeds k, move the left pointer to the right
            while (flippedZeroes > k) {
                // If the element at the left pointer is 0, decrement the count of flipped zeroes
                if (nums[left] == 0) flippedZeroes--;
                // Move the left pointer to the right
                left++;
            }

            // Calculate the length of the current window and update maxLen if necessary
            maxLen = Math.max(maxLen, right - left + 1);

            // Move the right pointer to the right
            right++;
        }

        return maxLen;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        System.out.println(solution.longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2)); // Output: 6
        System.out.println(solution.longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3)); // Output: 10
    }
}
```

### 1358. Number of Substrings Containing All Three Characters

#### Problem Statement

Given a string `s` consisting only of characters 'a', 'b', and 'c', return the number of substrings containing at least one occurrence of all these characters 'a', 'b', and 'c'. Problem [Link](https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/submissions/1306894251/).

#### Examples

##### Example 1:

**Input**: `s = "abcabc"`  
**Output**: `10`  
**Explanation**: The substrings containing at least one occurrence of the characters 'a', 'b', and 'c' are `"abc"`, `"abca"`, `"abcab"`, `"abcabc"`, `"bca"`, `"bcab"`, `"bcabc"`, `"cab"`, `"cabc"`, and `"abc"` (again).

##### Example 2:

**Input**: `s = "aaacb"`  
**Output**: `3`  
**Explanation**: The substrings containing at least one occurrence of the characters 'a', 'b', and 'c' are `"aaacb"`, `"aacb"`, and `"acb"`.

##### Example 3:

**Input**: `s = "abc"`  
**Output**: `1`  
**Explanation**: The substring `"abc"` contains all characters 'a', 'b', and 'c'.

#### Constraints

- `3 <= s.length <= 5 * 10^4`
- `s` only consists of 'a', 'b', or 'c' characters.

#### Solution

Here is the Java solution to the problem:

```java
public class Solution {
    public int numberOfSubstrings(String s) {
        char[] arr = s.toCharArray();
        int l = 0, r = 0, unq = 0, count = 0;
        int[] freq = new int[3]; // To store the frequency of 'a', 'b', and 'c'

        while (r < arr.length) {
            freq[arr[r] - 'a'] += 1;
            if (freq[arr[r] - 'a'] == 1) unq++; // Increase unique count when a character is first encountered

            while (unq == 3) { // While the window contains all three characters
                count += arr.length - r; // Add the number of valid substrings ending at 'r'
                freq[arr[l] - 'a']--; // Decrease the count of the character at the left pointer
                if (freq[arr[l] - 'a'] == 0) unq--; // Decrease unique count if a character count goes to zero
                l++; // Move the left pointer to shrink the window
            }
            r++; // Move the right pointer to expand the window
        }
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        System.out.println(solution.numberOfSubstrings("abcabc")); // Output: 10
        System.out.println(solution.numberOfSubstrings("aaacb")); // Output: 3
        System.out.println(solution.numberOfSubstrings("abc")); // Output: 1
    }
}
```

### 424. Longest Repeating Character Replacement

#### Problem Statement

You are given a string `s` and an integer `k`. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most `k` times.

Return the length of the longest substring containing the same letter you can get after performing the above operations. Problem [Link](https://leetcode.com/problems/longest-repeating-character-replacement/description/).

#### Examples

##### Example 1:

**Input**: `s = "ABAB"`, `k = 2`  
**Output**: `4`  
**Explanation**: Replace the two 'A's with two 'B's or vice versa.

##### Example 2:

**Input**: `s = "AABABBA"`, `k = 1`  
**Output**: `4`  
**Explanation**: Replace the one 'A' in the middle with 'B' and form "AABBBBA". The substring "BBBB" has the longest repeating letters, which is 4.

#### Constraints

- `1 <= s.length <= 105`
- `s` consists of only uppercase English letters.
- `0 <= k <= s.length`

#### Solution

```java
public class Solution {
    public int characterReplacement(String s, int k) {
        char[] arr = s.toCharArray();
        int[] freq = new int[26]; // To store the frequency of each character
        int left = 0, right = 0; // Left and right pointers of the sliding window
        int maxLen = 0, maxFreq = 0; // maxLen for the longest valid substring, maxFreq for the highest frequency of any character in the current window

        while (right < arr.length) {
            freq[arr[right] - 'A']++; // Increment the frequency of the current character
            maxFreq = Math.max(maxFreq, freq[arr[right] - 'A']); // Update the maxFreq

            // If the number of characters to be replaced is greater than k, shrink the window from the left
            if ((right - left + 1) - maxFreq > k) {
                freq[arr[left] - 'A']--; // Decrement the frequency of the leftmost character
                left++; // Move the left pointer to the right
            }

            maxLen = Math.max(maxLen, right - left + 1); // Update the maximum length of the valid substring
            right++; // Move the right pointer to the right
        }

        return maxLen;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        System.out.println(solution.characterReplacement("ABAB", 2)); // Output: 4
        System.out.println(solution.characterReplacement("AABABBA", 1)); // Output: 4
    }
}
```
