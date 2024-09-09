# Stacks and Queues:

## Leetcode: 20. Valid Parentheses

In this problem, you need to determine if a string consisting of parentheses is valid based on these rules:

1. Every opening bracket must have a corresponding closing bracket of the same type.
2. Open brackets must be closed in the correct order.

The string can only contain the characters `'('`, `')'`, `'{'`, `'}'`, `'['`, and `']'`.

### Examples

#### Example 1:

**Input:** `s = "()"`  
**Output:** `true`  
**Explanation:** The string contains matching parentheses in the correct order.

#### Example 2:

**Input:** `s = "()[]{}"`  
**Output:** `true`  
**Explanation:** Each pair of parentheses, square brackets, and curly braces is correctly matched.

#### Example 3:

**Input:** `s = "(]"`  
**Output:** `false`  
**Explanation:** The opening bracket `(` is not closed by a matching closing bracket `)`.

#### Example 4:

**Input:** `s = "([])"`  
**Output:** `true`  
**Explanation:** The opening brackets are closed correctly, even though different types are nested.

---

### Approach

We can solve this problem by using a **stack**. Here's the process:

1. Traverse through each character in the string.
2. Push any opening brackets (`'('`, `'{'`, `'['`) onto the stack.
3. For any closing bracket (`')'`, `'}'`, `']'`), check if it matches the top element of the stack:
   - If it matches, pop the top of the stack.
   - If it does not match or if the stack is empty, the string is invalid.
4. After processing all characters, if the stack is empty, the string is valid.

---

### Solution Code (Java):

```java
import java.util.Stack;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean isValid(String s) {
        char[] str = s.toCharArray();
        int n = str.length;

        // Stack to store opening brackets
        Stack<Character> stack = new Stack<>();

        // HashMap to map closing brackets to their corresponding opening brackets
        Map<Character, Character> hMap = new HashMap<>();
        hMap.put(')', '(');
        hMap.put(']', '[');
        hMap.put('}', '{');

        // Traverse the string character by character
        for (int i = 0; i < n; i++) {
            char curr = str[i];

            // If it's an opening bracket, push to the stack
            if (hMap.containsValue(curr)) {
                stack.push(curr);
            }
            // If it's a closing bracket, check the top of the stack
            else {
                if (stack.isEmpty()) {
                    return false;  // No opening bracket for the current closing bracket
                }

                // Check if the top of the stack matches the current closing bracket
                if (hMap.get(curr) == stack.peek()) {
                    stack.pop();  // Match found, pop the stack
                } else {
                    return false;  // Mismatch found
                }
            }
        }

        // If the stack is empty, all brackets are balanced
        return stack.isEmpty();
    }
}
```

### Explanation:

1. **Stack**: Used to store the most recent opening brackets encountered in the string.
2. **HashMap**: Maps each closing bracket to its corresponding opening bracket for quick look-up.
3. **Process**:
   - For each character in the string:
     - Push opening brackets to the stack.
     - For closing brackets, check if they match the top element of the stack (last seen opening bracket).
   - If any mismatch is found or the stack is not empty at the end, the string is invalid.

---

### Time and Space Complexity:

- **Time Complexity**: **O(n)**, where `n` is the length of the string. We process each character exactly once.
- **Space Complexity**: **O(n)** in the worst case, if all the characters are opening brackets, they will be stored in the stack.

---

## Leetcode: 503. Next Greater Element II

You are given a **circular integer array** `nums`, where the next element of `nums[nums.length - 1]` is `nums[0]`. The task is to return the **next greater number** for every element in `nums`. The next greater number for an element `x` is the first greater number to its right (considering the array circularly). If there isn't any, return `-1` for that element.

### Example 1:

**Input:**

```text
nums = [1, 2, 1]
```

**Output:**

```text
[2, -1, 2]
```

**Explanation:**

- The next greater number for the first `1` is `2`.
- The number `2` has no greater number on its right, so it returns `-1`.
- The second `1` also has `2` as its next greater number, considering the array circularly.

### Example 2:

**Input:**

```text
nums = [1, 2, 3, 4, 3]
```

**Output:**

```text
[2, 3, 4, -1, 4]
```

### Constraints:

- `1 <= nums.length <= 10^4`
- `-10^9 <= nums[i] <= 10^9`

---

### Approach: Stack and Circular Array Traversal

To solve this efficiently, we can use a **monotonic stack**. Here's the process:

1. We'll traverse the array twice (because it's circular), but treat it as if it's traversed once.
2. The stack will store indices of elements. For each element in the array, we'll compare it with the element at the index on top of the stack.
   - If the current element is greater than the element at the top of the stack, we have found the **next greater element** for the element represented by the index on top of the stack. We can pop the stack and record this value.
3. If the stack is empty or the current element is not greater, we'll push the current index onto the stack.
4. By iterating over the array twice, we effectively handle the **circular** nature of the array.

---

### Solution Code (Java):

```java
import java.util.Stack;

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] nge = new int[n];  // Result array to store the next greater elements
        Stack<Integer> st = new Stack<>();  // Stack to store the value

        // Traverse the array twice to handle the circular condition
        for (int i = 2 * n - 1; i >= 0; i--) {
            // If the current element is greater than the stack's top, pop the stack
            while (!st.isEmpty() && nums[i % n] >= st.peek()) {
                st.pop();
            }

            // If we are in the original array (not the duplicate), update the result array
            if (i < n) {
                nge[i] = st.isEmpty() ? -1 : st.peek();
            }

            // Push the current element to the stack
            st.push(nums[i % n]);
        }

        return nge;
    }
}
```

### Explanation:

1. **Stack**: We use a stack to store the indices of elements. This helps in finding the next greater element in constant time.
2. **Two-Pass Traversal**: We iterate over the array twice (`2 * n`):
   - For each index `i`, we use the modulo operator (`i % n`) to simulate the circular nature of the array.
3. **Next Greater Element**:
   - For each element, while the stack is not empty and the current element is greater than the element represented by the index at the top of the stack, we pop the stack and update the result for that index.
4. **Circular Behavior**:
   - The second pass ensures that even the last elements in the array can check elements from the beginning of the array, simulating a circular traversal.

### Time and Space Complexity:

- **Time Complexity**: **O(n)**. Each element is pushed and popped from the stack at most once, and we traverse the array twice (but treat it as a single pass).
- **Space Complexity**: **O(n)**. The stack can store up to `n` elements in the worst case, and we also use an array of size `n` to store the result.

---

## Leetcode: 42. Trapping Rain Water

Given an array `height` representing an elevation map where each element is the height of a bar, compute how much water can be trapped after raining.

### Example 1:

**Input:**
![img](https://assets.leetcode.com/uploads/2018/10/22/rainwatertrap.png)

```text
height = [0,1,0,2,1,0,1,3,2,1,2,1]
```

**Output:**

```text
6
```

**Explanation:**

The elevation map can trap 6 units of rainwater between the bars.

### Example 2:

**Input:**

```text
height = [4,2,0,3,2,5]
```

**Output:**

```text
9
```

### Constraints:

- `n == height.length`
- `1 <= n <= 2 * 10^4`
- `0 <= height[i] <= 10^5`

---

### Approach: Two Pointers

The goal is to compute how much water can be trapped above each index in the elevation map.

1. **Idea**: The amount of water trapped at a given index depends on the heights of the tallest bars to the left and right of that index. The water trapped at index `i` is given by:
   \[
   \text{water trapped at i} = \min(\text{left max}, \text{right max}) - \text{height[i]}
   \]
   This formula holds because the water level is constrained by the shorter of the tallest bars on either side.

2. **Two Pointers**: To efficiently calculate the trapped water, we can use two pointers (`left` and `right`) to traverse the array from both ends simultaneously:
   - If the height at the left pointer is less than or equal to the height at the right pointer, we update the left maximum and calculate the water trapped at the left index.
   - Otherwise, we update the right maximum and calculate the water trapped at the right index.
   - This way, we avoid needing to scan the array multiple times.

### Solution Code (Java):

```java
class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int leftMax = 0, rightMax = 0, total = 0;
        int left = 0, right = n - 1;

        while (left < right) {
            if (height[left] <= height[right]) {
                if (leftMax > height[left]) {
                    total += leftMax - height[left];  // Water trapped at left index
                } else {
                    leftMax = height[left];  // Update left maximum
                }
                left++;
            } else {
                if (rightMax > height[right]) {
                    total += rightMax - height[right];  // Water trapped at right index
                } else {
                    rightMax = height[right];  // Update right maximum
                }
                right--;
            }
        }

        return total;
    }
}
```

### Explanation:

1. **Initialization**:

   - `leftMax` and `rightMax` track the maximum height seen so far from the left and right, respectively.
   - `left` and `right` pointers start at the beginning and end of the array, respectively.
   - `total` keeps track of the total amount of trapped water.

2. **Loop through the array**:

   - If `height[left]` is smaller or equal to `height[right]`, it means we can potentially trap water at the left pointer based on the left side.
     - If `leftMax` is greater than `height[left]`, we calculate how much water can be trapped at that position.
     - Otherwise, we update `leftMax`.
     - Then, move the `left` pointer to the next position.
   - If `height[right]` is smaller, we do the same from the right side.
     - If `rightMax` is greater than `height[right]`, we calculate how much water can be trapped at that position.
     - Otherwise, update `rightMax`.
     - Then, move the `right` pointer to the previous position.

3. **Result**:
   - The loop continues until the `left` pointer meets the `right` pointer. The `total` will contain the total trapped water.

---

### Time and Space Complexity:

- **Time Complexity**: **O(n)** — We traverse the array once with the two pointers.
- **Space Complexity**: **O(1)** — We use only a constant amount of extra space for the pointers and variables.

---

## Leetcode: 735. Asteroid Collision

You are given an array of integers `asteroids`, where each element represents an asteroid. The absolute value of the integer represents the asteroid's size, while the sign represents its direction:

- A positive value means the asteroid is moving right.
- A negative value means the asteroid is moving left.

When two asteroids collide, the smaller one explodes. If both asteroids are the same size, both explode. Asteroids moving in the same direction never collide.

### Example 1:

**Input**:

```text
asteroids = [5,10,-5]
```

**Output**:

```text
[5,10]
```

**Explanation**:
The 10 and -5 collide, resulting in 10 (since 10 is larger). The 5 and 10 never collide because they are moving in the same direction.

### Example 2:

**Input**:

```text
asteroids = [8,-8]
```

**Output**:

```text
[]
```

**Explanation**:
The 8 and -8 collide, and both explode because they are of equal size.

### Example 3:

**Input**:

```text
asteroids = [10,2,-5]
```

**Output**:

```text
[10]
```

**Explanation**:
The 2 and -5 collide, resulting in -5. The 10 and -5 collide, resulting in 10.

### Constraints:

- `2 <= asteroids.length <= 10^4`
- `-1000 <= asteroids[i] <= 1000`
- `asteroids[i] != 0`

---

### Approach: Stack-Based Solution

We can simulate the asteroid collisions using a **stack**. Here's how:

1. Traverse through the asteroid array.
2. If an asteroid is moving to the right (positive), push it onto the stack.
3. If an asteroid is moving to the left (negative):
   - Check the top of the stack to see if there is a right-moving asteroid to collide with.
   - If the top asteroid is smaller than the current one (i.e., the left-moving asteroid is larger), pop the right-moving asteroid and continue checking.
   - If the top asteroid is equal to the current one, both asteroids explode.
   - If the top asteroid is larger, the left-moving asteroid explodes.
4. If the stack is empty or the top asteroid is left-moving, push the left-moving asteroid onto the stack.

At the end, the stack will contain the state of the asteroids after all collisions.

### Solution Code (Java):

```java
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int asteroid : asteroids) {
            // Handle collision only when a right-moving asteroid meets a left-moving one
            if (asteroid > 0) {
                stack.push(asteroid);
            } else {
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < Math.abs(asteroid)) {
                    stack.pop();  // Explode the smaller right-moving asteroid
                }
                // If there is a same-size asteroid, both explode
                if (!stack.isEmpty() && stack.peek() == Math.abs(asteroid)) {
                    stack.pop();  // Both explode
                }
                // If no collision happened or all right-moving asteroids exploded
                else if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(asteroid);  // Push the left-moving asteroid
                }
            }
        }

        // Convert stack to int array
        int[] result = new int[stack.size()];
        for (int i = 0; i < stack.size(); i++) {
            result[i] = stack.get(i);
        }

        return result;
    }
}
```

### Explanation:

1. **Initialization**:

   - We use a stack to store the asteroids that are still intact.
   - We traverse the array of asteroids.

2. **Collision Handling**:

   - If an asteroid is positive (right-moving), we push it onto the stack.
   - If an asteroid is negative (left-moving), we check the top of the stack:
     - If the top is a smaller right-moving asteroid, it pops from the stack (explodes).
     - If the top is the same size but opposite direction, both explode.
     - If the stack is empty or contains only left-moving asteroids, the current asteroid is pushed.

3. **Result**:
   - After traversing the array, we convert the stack into an array of integers representing the final state of asteroids after all collisions.

---

### Time and Space Complexity:

- **Time Complexity**: **O(n)**, where `n` is the number of asteroids. Each asteroid is pushed and popped from the stack at most once.
- **Space Complexity**: **O(n)**, where `n` is the number of asteroids. The stack holds at most `n` elements in the worst case.

---

## Leetcode: 402. Remove K Digits

Given a string `num` representing a non-negative integer and an integer `k`, you are required to return the smallest possible integer after removing `k` digits from `num`.

### Examples:

#### Example 1:

**Input**:

```text
num = "1432219", k = 3
```

**Output**:

```text
"1219"
```

**Explanation**:
By removing digits `4`, `3`, and `2`, the smallest number we can form is `1219`.

#### Example 2:

**Input**:

```text
num = "10200", k = 1
```

**Output**:

```text
"200"
```

**Explanation**:
Remove the leading `1`, leaving `200`. Note that there are no leading zeros in the result.

#### Example 3:

**Input**:

```text
num = "10", k = 2
```

**Output**:

```text
"0"
```

**Explanation**:
After removing all digits, the number becomes `0`.

### Constraints:

- `1 <= k <= num.length <= 10^5`
- `num` consists of only digits.
- `num` does not have any leading zeros except for the number zero itself.

---

### Approach: Stack-Based Greedy Solution

The problem asks for the smallest possible integer after removing `k` digits, which suggests that we should minimize the digits from left to right. We can use a **greedy approach** combined with a **stack** to make sure we are always keeping the smallest digits possible.

#### Key Observations:

1. To maintain a small number, whenever a larger digit is followed by a smaller one, it's beneficial to remove the larger digit.
2. Once we remove `k` digits, the result will be formed by the remaining digits.
3. We must also handle cases where leading zeros appear, and they should be removed.

#### Algorithm:

1. **Use a Stack**: Traverse the digits of `num`. For each digit, if it's smaller than the top of the stack and we still need to remove digits (`k > 0`), we pop the top of the stack (remove the larger digit).
2. **Remove Leading Zeros**: After constructing the result using the stack, ignore leading zeros.
3. **Edge Cases**:
   - If all digits are removed (`k == num.length`), return `"0"`.
   - After processing all digits, if we haven't removed enough (`k`), continue popping from the stack.

### Solution Code (Java):

```java
class Solution {
    public String removeKdigits(String num, int k) {
        // If k equals to the length of num, return "0"
        if (k == num.length()) return "0";

        Stack<Character> stack = new Stack<>();
        int charPopped = 0;  // Count of characters removed

        // Traverse each character in num
        for (char ch : num.toCharArray()) {
            // Pop elements from the stack if the current character is smaller than the top of the stack
            // And if we haven't removed enough digits yet
            while (!stack.isEmpty() && stack.peek() > ch && charPopped < k) {
                stack.pop();
                charPopped++;
            }

            // Push the current character into the stack if it's not leading zero or the stack is not empty
            if (!stack.isEmpty() || ch != '0') {
                stack.push(ch);
            }
        }

        // If we still need to remove characters, pop more from the stack
        while (!stack.isEmpty() && charPopped < k) {
            stack.pop();
            charPopped++;
        }

        // Build the final string from the stack
        StringBuilder ans = new StringBuilder();
        while (!stack.isEmpty()) {
            ans.insert(0, stack.pop());  // Construct the number from the stack
        }

        // If ans is empty, return "0"
        return ans.length() == 0 ? "0" : ans.toString();
    }
}
```

### Explanation:

1. **Initialization**:

   - We use a stack to maintain the sequence of digits forming the smallest number.
   - We traverse the digits in `num`.

2. **Greedy Deletion**:

   - For each digit, we pop the stack while the current digit is smaller than the top of the stack, and we haven’t removed `k` digits yet.
   - This ensures that we always keep the smallest possible number at each step.

3. **Handling Zeros**:

   - Skip pushing leading zeros unless the stack already contains digits.

4. **Edge Handling**:

   - After processing all digits, if we haven’t removed enough digits (`charPopped < k`), we continue removing from the stack.

5. **Result Construction**:
   - Convert the remaining digits in the stack to a string.
   - Return `"0"` if the stack is empty after popping.

### Time and Space Complexity:

- **Time Complexity**: **O(n)**, where `n` is the length of `num`. Each digit is pushed and popped from the stack at most once.
- **Space Complexity**: **O(n)**, where `n` is the length of `num`, for storing the result in the stack.

---

## Leetcode: 84. Largest Rectangle in Histogram

Given an array of integers `heights` representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.

#### Example 1:

![img1](https://assets.leetcode.com/uploads/2021/01/04/histogram.jpg)

```
Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The largest rectangle is shown in the red area, which has an area = 10 units.
```

#### Example 2:

![img2](https://assets.leetcode.com/uploads/2021/01/04/histogram-1.jpg)

```
Input: heights = [2,4]
Output: 4
```

#### Constraints:

- 1 <= heights.length <= 10^5
- 0 <= heights[i] <= 10^4

---

### Solution (Java):

```java
import java.util.Stack;

class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> st = new Stack<>();
        int[] leftSmall = new int[n];   // Array to store the index of the nearest smaller element on the left
        int[] rightSmall = new int[n];  // Array to store the index of the nearest smaller element on the right

        // Find the nearest smaller element to the left for each bar
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                st.pop();
            }
            if (st.isEmpty()) {
                leftSmall[i] = 0;  // If no smaller element, set left boundary to 0
            } else {
                leftSmall[i] = st.peek() + 1;  // Set left boundary to the index after the nearest smaller element
            }
            st.push(i);  // Push the current index onto the stack
        }

        // Clear the stack for reuse
        st.clear();

        // Find the nearest smaller element to the right for each bar
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                st.pop();
            }
            if (st.isEmpty()) {
                rightSmall[i] = n - 1;  // If no smaller element, set right boundary to n-1
            } else {
                rightSmall[i] = st.peek() - 1;  // Set right boundary to the index before the nearest smaller element
            }
            st.push(i);  // Push the current index onto the stack
        }

        // Calculate the maximum area
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = rightSmall[i] - leftSmall[i] + 1;  // Width of the rectangle
            int area = heights[i] * width;  // Area = height * width
            maxArea = Math.max(maxArea, area);  // Track the maximum area
        }

        return maxArea;
    }
}
```

---

### Explanation of the Solution:

1. **Left Small Array**: For each bar, we find the nearest bar to the left that is smaller. This helps to determine how far to extend the rectangle to the left.
2. **Right Small Array**: Similarly, we find the nearest smaller bar to the right, determining how far the rectangle can extend to the right.

3. **Using Stack**: The stack is used to efficiently compute the nearest smaller elements to the left and right. We keep pushing indices to the stack and pop them when we find a smaller element.

4. **Area Calculation**: For each bar, we calculate the possible area using the formula:
   \[
   \text{Area} = \text{height of bar} \times (\text{right boundary} - \text{left boundary} + 1)
   \]
   and then track the maximum area.

5. **Time Complexity**: The solution runs in O(n) time, where n is the length of the `heights` array, as each element is pushed and popped from the stack only once.

---

## Leetcode: 85. Maximal Rectangle

You are given a binary matrix filled with `'0'`s and `'1'`s, and you are required to find the largest rectangle containing only `'1'`s and return its area.

### Example 1:

```text
Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 6
Explanation: The largest rectangle containing only 1's has an area of 6 (as shown in the matrix).
```

### Example 2:

```text
Input: matrix = [["0"]]
Output: 0
```

### Example 3:

```text
Input: matrix = [["1"]]
Output: 1
```

### Constraints:

- `rows == matrix.length`
- `cols == matrix[i].length`
- `1 <= rows, cols <= 200`
- `matrix[i][j]` is `'0'` or `'1'`.

---

### Solution Approach

This problem can be viewed as an extension of the **largest rectangle in a histogram** problem. If we treat each row of the matrix as the base of a histogram and calculate the heights of the histogram from each row, we can solve the problem by applying the "largest rectangle in a histogram" approach for each row.

#### Key Idea:

1. For each row, treat it as the bottom of a histogram where the height of each bar corresponds to the number of consecutive `1`s directly above it.
2. For each row, compute the largest rectangle area using the heights of the histogram formed by that row.
3. The final answer is the maximum rectangle area over all rows.

#### Steps:

1. Build a **heights array** where each element in the array represents the height of consecutive `1`s up to that row in the matrix.
2. For each row, treat the heights array as a histogram and calculate the maximum rectangle area using the **largest rectangle in a histogram** algorithm.
3. Keep track of the largest rectangle area found across all rows.

---

### Solution Code:

```java
import java.util.Stack;

class Solution {
    public int maximalRectangle(char[][] matrix) {
        // Base case: If the matrix is empty, return 0
        if (matrix.length == 0) return 0;

        int n = matrix.length, m = matrix[0].length;
        int maxArea = 0;
        int[][] heights = new int[n][m];

        // Build heights matrix (calculate the heights of consecutive 1's)
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                if (matrix[i][j] == '1') {
                    heights[i][j] = (i == 0 ? 1 : heights[i - 1][j] + 1);
                } else {
                    heights[i][j] = 0;
                }
            }
        }

        // For each row in heights, calculate the largest rectangle area
        for (int[] row : heights) {
            maxArea = Math.max(maxArea, largestRectangleArea(row));
        }

        return maxArea;
    }

    // Helper function to calculate the largest rectangle area in a histogram
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] > currentHeight) {
                int h = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, h * width);
            }
            stack.push(i);
        }

        return maxArea;
    }
}
```

### Explanation:

1. **Building the heights matrix**:

   - For each column in the matrix, count how many consecutive `1`s are stacked vertically. The result is stored in the `heights` matrix.
   - For example, for a matrix like:
     ```text
     1 0 1 0 0
     1 0 1 1 1
     1 1 1 1 1
     1 0 0 1 0
     ```
     The heights matrix will be:
     ```text
     1 0 1 0 0
     2 0 2 1 1
     3 1 3 2 2
     4 0 0 3 0
     ```

2. **Largest rectangle in histogram**:

   - For each row in the `heights` matrix, treat it as the base of a histogram and calculate the largest rectangle area using a stack-based approach.
   - This is done by iterating through the row and using a stack to keep track of bars in ascending order of height. Whenever we find a shorter bar, we pop from the stack and calculate the area with the popped bar as the height and the current index as the width.

3. **Result**:
   - We track the maximum area obtained from each row and return the largest one.

### Time Complexity:

- Building the `heights` array: **O(n \* m)** where `n` is the number of rows and `m` is the number of columns.
- For each row, calculating the largest rectangle area: **O(m)**.
- Overall complexity: **O(n \* m)**.

### Space Complexity:

- **O(m)** for the stack used in the largest rectangle area calculation.

---
