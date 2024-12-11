# Greedy Algorithm:

## Leetcode: 455. Assign Cookies

You have children with different greed factors and cookies of various sizes. Each child i has a greed factor `g[i]`, which represents the minimum size of a cookie that would satisfy the child. Each cookie j has a size `s[j]`. Your goal is to maximize the number of children who can be made content by giving them a cookie.

### Problem Details:

- You can assign at most one cookie to each child.
- A child is content if their greed factor is less than or equal to the size of the assigned cookie.
- The task is to maximize the number of content children.

### Example 1:
```text
Input: g = [1, 2, 3], s = [1, 1]
Output: 1
Explanation: 
You have 3 children with greed factors 1, 2, and 3, and 2 cookies of size 1. 
You can only make the first child content because the other children have higher greed factors than the available cookies.
```

### Example 2:
```text
Input: g = [1, 2], s = [1, 2, 3]
Output: 2
Explanation: 
You have 2 children with greed factors 1 and 2, and 3 cookies of sizes 1, 2, and 3. 
Both children can be content as their greed factors can be satisfied with available cookies.
```

### Constraints:

- `1 <= g.length <= 3 * 10^4`
- `0 <= s.length <= 3 * 10^4`
- `1 <= g[i], s[j] <= 2^31 - 1`

---

### Approach:

To maximize the number of children who are content, we can follow a greedy approach. The idea is to:
1. Sort both the greed factors (`g[]`) and cookie sizes (`s[]`).
2. Try to assign the smallest available cookie that satisfies each child, starting with the child with the lowest greed factor.

### Steps:

1. **Sort the greed factors and cookie sizes**. This allows us to efficiently match the smallest available cookie to the least greedy child.
2. **Iterate through both the greed and cookie arrays**. For each child, check if the current cookie can satisfy their greed factor. If so, assign the cookie to the child and move to the next child and cookie.
3. If a cookie can't satisfy the current child, move on to the next larger cookie.

### Solution Code:

```java
import java.util.Arrays;

class Solution {
    public int findContentChildren(int[] g, int[] s) {
        // Sort the greed factors and the cookie sizes
        Arrays.sort(g);
        Arrays.sort(s);

        int n = g.length;  // Number of children
        int m = s.length;  // Number of cookies
        int child = 0;     // Pointer for children
        int cookie = 0;    // Pointer for cookies

        // Try to assign cookies to children
        while (child < n && cookie < m) {
            // If the current cookie satisfies the child's greed
            if (s[cookie] >= g[child]) {
                // Move to the next child
                child++;
            }
            // Move to the next cookie regardless of whether the child was satisfied
            cookie++;
        }

        // Return the number of children that were made content
        return child;
    }
}
```

### Explanation:
- **Sorting**: Sorting both arrays helps us match the smallest available cookie to the least greedy child, which is an optimal approach to maximize content children.
- **Greedy Assignment**: We iterate over both sorted arrays and try to match each child to the smallest possible cookie. If a match is found, we move to the next child and cookie. If the cookie is too small, we discard it and move to the next cookie.
- **Time Complexity**: Sorting both arrays takes \(O(n \log n + m \log m)\), and the greedy matching process takes \(O(n + m)\), where `n` is the number of children and `m` is the number of cookies.
- **Space Complexity**: The space complexity is \(O(1)\), as we are only using a constant amount of extra space besides the input arrays.

---

## Leetcode: 860. Lemonade Change

You are running a lemonade stand where each lemonade costs $5. Customers can pay with a $5, $10, or $20 bill, and you must provide the correct change with the money you have on hand. The goal is to determine if you can successfully give change to every customer in the queue.

### Problem Details:

- **Input**: An integer array `bills` where `bills[i]` is the bill paid by the ith customer.
- **Output**: Return `true` if you can provide correct change to every customer, otherwise return `false`.

### Approach:

You need to keep track of how many $5 and $10 bills you have to give change. 
- For each customer:
  1. If the customer pays with a $5 bill, you don't need to give any change.
  2. If the customer pays with a $10 bill, you need to give back a $5 bill. Therefore, you need at least one $5 bill on hand.
  3. If the customer pays with a $20 bill, you have two options:
     - Preferably, give one $10 and one $5 as change if you have both.
     - If you don't have a $10 bill, you can give three $5 bills as change. If neither option is available, return `false`.

### Solution Code:

```java
class Solution {
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0; // To keep track of the number of $5 and $10 bills

        for (int bill : bills) {
            if (bill == 5) {
                five++; // Collect $5 bills
            } else if (bill == 10) {
                if (five > 0) {
                    five--; // Give one $5 as change
                    ten++;  // Collect the $10 bill
                } else {
                    return false; // No $5 bill to give as change
                }
            } else { // When the customer pays with a $20 bill
                if (ten > 0 && five > 0) {
                    ten--; // Prefer to give one $10 and one $5 as change
                    five--;
                } else if (five >= 3) {
                    five -= 3; // Otherwise give three $5 bills as change
                } else {
                    return false; // Not enough change to give
                }
            }
        }

        return true; // If we managed to give correct change to all customers
    }
}
```

### Explanation:

1. **Tracking change**: We maintain two variables, `five` and `ten`, which count the number of $5 and $10 bills respectively.
2. **Handling each bill**:
   - For a $5 bill, we simply increment the `five` count as no change is needed.
   - For a $10 bill, we check if we have a $5 bill to give as change. If so, we decrement `five` and increment `ten`.
   - For a $20 bill, we first try to give one $10 and one $5 bill (preferred). If that's not possible, we attempt to give three $5 bills. If neither option is possible, we return `false`.
3. **Return**: If we successfully provide change for every customer, we return `true`.

### Time Complexity:
- **O(n)**, where `n` is the number of customers (or the length of the `bills` array). We iterate through the array once.

### Space Complexity:
- **O(1)**, since we're only using a constant amount of extra space for the `five` and `ten` variables.

---

## Leetcode: 55. Jump Game

You are given an integer array `nums`. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Your task is to return `true` if you can reach the last index, or `false` otherwise.

### Example 1:

**Input**:  
`nums = [2,3,1,1,4]`

**Output**:  
`true`

**Explanation**:  
Jump 1 step from index 0 to 1, then 3 steps to the last index.

### Example 2:

**Input**:  
`nums = [3,2,1,0,4]`

**Output**:  
`false`

**Explanation**:  
You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.

### Constraints:

- `1 <= nums.length <= 10^4`
- `0 <= nums[i] <= 10^5`

---

### Solution:

We solve this problem using a **greedy approach**, where we track the **maximum index** we can reach at any point as we iterate through the array.

#### Approach:

1. **Initialize** a variable `maxInd` to 0. This will store the farthest index we can reach at any given point.
2. **Iterate** over each index in the array:
   - If at any point the current index `i` is greater than `maxInd` (i.e., `i > maxInd`), then it's impossible to proceed further, and we return `false`.
   - Otherwise, update `maxInd` to the maximum of the current `maxInd` and `i + nums[i]` (i.e., the maximum distance we can jump from the current position).
3. After the loop, if we successfully finish iterating through the array, return `true` because it means we can reach the last index.

#### Solution Code:

```java
class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int maxInd = 0; // Tracks the farthest index we can reach
        
        for (int i = 0; i < n; i++) {
            if (i > maxInd) return false; // If current index is beyond reachable, return false
            maxInd = Math.max(maxInd, i + nums[i]); // Update the farthest reachable index
        }
        
        return true; // If we can iterate through the array, return true
    }
}
```

---

### Explanation:

1. **Initialization**:  
   - `maxInd = 0` means the farthest we can reach initially is the first index.
   
2. **Loop through the array**:
   - At each index `i`, check if it's reachable by verifying `i <= maxInd`. If it is not reachable, return `false`.
   - If reachable, update `maxInd` to be the maximum of the current `maxInd` and `i + nums[i]` (i.e., how far we can jump from index `i`).
   
3. **Final Step**:  
   - If the loop completes without returning `false`, it means the last index is reachable, so return `true`.

This solution has a time complexity of **O(n)** since we are iterating through the array once.

---

