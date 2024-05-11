# Arrays Solved Problems

## 1752. Check if Array Is Sorted and Rotated

Given an array nums, return true if the array was originally sorted in non-decreasing order, then rotated some number of positions (including zero). Otherwise, return false.

There may be duplicates in the original array.

**Note:** An array A rotated by x positions results in an array B of the same length such that `A[i] == B[(i+x) % A.length]`, where `%` is the modulo operation. Leetcode [Link](https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/).

##### Example 1:

_Input:_ nums = `[3,4,5,1,2]`
_Output:_ true

**Explanation:** `[1,2,3,4,5]` is the original sorted array.
You can rotate the array by `x = 3` positions to begin on the the element of value 3: `[3,4,5,1,2]`.

##### Example 2:

_Input:_ nums = `[2,1,3,4]`
_Output:_ false

**Explanation:** There is no sorted array once rotated that can make nums.

##### Example 3:

_Input:_ nums = `[1,2,3]`
_Output:_ true

**Explanation:** `[1,2,3]` is the original sorted array.
You can rotate the array by `x = 0` positions (i.e. no rotation) to make nums.

**Constraints:**

- `1 <= nums.length <= 100`
- `1 <= nums[i] <= 100`

```java
class Solution {
    public boolean check(int[] nums) {
        int count = 0;
        int last = nums.length - 1;

        for(int i = 0; i < last; i++) {
            if (nums[i] > nums[i+1]) count++;
        }

        if (count == 0) return true;
        else if (count > 1) return false;
        else if (nums[0] >= nums[last]) return true;
        else return false;
    }
}
```

## 26. Remove Duplicates from Sorted Array

Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same. Then return the number of unique elements in nums.

Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:

- Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
- `Return k`.

**Custom Judge:**

The judge will test your solution with the following code:

```java
int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length

int k = removeDuplicates(nums); // Calls your implementation

assert k == expectedNums.length;
for (int i = 0; i < k; i++) {
assert nums[i] == expectedNums[i];
}
```

If all assertions pass, then your solution will be accepted.
Leetcode [Link](https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/).

##### Example 1:

_Input:_ nums = `[1,1,2]`
_Output:_ 2, nums = `[1,2,_]`

**Explanation:** Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).

##### Example 2:

_Input:_ nums = `[0,0,1,1,1,2,2,3,3,4]`
_Output:_ 5, nums = `[0,1,2,3,4,_,_,_,_,_]`

**Explanation:** Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).

**Constraints:**

- `1 <= nums.length <= 3 * 104`
- `-100 <= nums[i] <= 100`
- nums is sorted in non-decreasing order.

```java
class Solution {
    public int removeDuplicates(int[] arr) {
        int i = 0;
        for (int j = 1; j < arr.length; j++) {
            if (arr[i] != arr[j]) {
                i++;
                arr[i] = arr[j];
            }
        }
        // returns the length of the resultant array
        return i+1;
    }
}
```

## 189. Rotate Array

Given an integer array nums, rotate the array to the right by k steps, where k is non-negative. Leetcode [Link](https://leetcode.com/problems/rotate-array/description/).

##### Example 1:

Input: nums = `[1,2,3,4,5,6,7]`, k = 3
Output: `[5,6,7,1,2,3,4]`

**Explanation:**

- rotate 1 steps to the right: `[7,1,2,3,4,5,6]`
- rotate 2 steps to the right: `[6,7,1,2,3,4,5]`
- rotate 3 steps to the right: `[5,6,7,1,2,3,4]`

##### Example 2:

Input: nums = `[-1,-100,3,99]`, k = 2
Output: `[3,99,-1,-100]`

**Explanation:**

- rotate 1 steps to the right: `[99,-1,-100,3]`
- rotate 2 steps to the right: `[3,99,-1,-100]`

**Constraints:**

- `1 <= nums.length <= 105`
- `-231 <= nums[i] <= 231 - 1`
- `0 <= k <= 105`

```java
class Solution {
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        int n = nums.length;
        // Reverse whole array
        reverse(nums,0,n-1);
        // Reverse second half of an array
        reverse(nums,0,k-1);
        // Reverse first half of an array
        reverse(nums,k,n-1);
    }
    public void reverse(int[] nums, int start, int end) {
        while(start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
```

## 1. Two Sum

Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order. Leetcode [Link](https://leetcode.com/problems/two-sum/description/).

##### Example 1:

_Input:_ nums = `[2,7,11,15]`, target = 9
_Output:_ `[0,1]`

**Explanation:** Because `nums[0] + nums[1] == 9`, we return `[0, 1]`.

##### Example 2:

_Input:_ nums = `[3,2,4]`, target = 6
_Output:_ `[1,2]`

##### Example 3:

_Input:_ nums = `[3,3]`, target = 6
_Output:_ `[0,1]`

**Constraints:**

- `2 <= nums.length <= 104`
- `-109 <= nums[i] <= 109`
- `-109 <= target <= 109`
- Only one valid answer exists.

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Hashmap
        Map <Integer, Integer> Hmap = new HashMap<Integer, Integer>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int complement = target - nums[i];
            if (Hmap.containsKey(complement)) {
                return new int[] {Hmap.get(complement), i};
            }
            Hmap.put(nums[i], i);
        }
        return new int[] {};
    }
}
```

## 167. Two Sum II - Input Array Is Sorted

Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number. Let these two numbers be `numbers[index1]` and `numbers[index2] where 1 <= index1 < index2 <= numbers.length.`

Return the indices of the two numbers, index1 and index2, added by one as an integer array `[index1, index2]` of length 2.

The tests are generated such that there is exactly one solution. You may not use the same element twice.

Your solution must use only constant extra space. Leetcode [Link](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/).

##### Example 1:

_Input:_ numbers = `[2,7,11,15]`, target = 9
_Output:_ `[1,2]`

**Explanation:** The sum of 2 and 7 is 9. Therefore, `index1 = 1, index2 = 2`. We return `[1, 2]`.

##### Example 2:

_Input:_ numbers = `[2,3,4]`, target = 6
_Output:_ `[1,3]`

**Explanation:** The sum of 2 and 4 is 6. Therefore `index1 = 1, index2 = 3`. We return `[1, 3]`.

##### Example 3:

_Input:_ numbers = `[-1,0]`, target = -1
_Output:_ `[1,2]`

**Explanation:** The sum of -1 and 0 is -1. Therefore `index1 = 1, index2 = 2`. We return `[1, 2]`.

**Constraints:**

- `2 <= numbers.length <= 3 \* 104`
- `-1000 <= numbers[i] <= 1000`
- numbers is sorted in non-decreasing order.
- `-1000 <= target <= 1000`
- The tests are generated such that there is exactly one solution.

```java
class Solution {
    public int[] twoSum(int[] arr, int target) {
        // two pointer approach
        int start = 0, end = arr.length - 1;
        while (start < end) {
            int sum = arr[start] + arr[end];
            if (sum == target) return new int[] {start+1, end+1};
            else if (sum > target) end--;
            else start++;
        }
        return new int[] {};
    }
}
```

## 283. Move Zeroes

Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Note that you must do this in-place without making a copy of the array. Leetcode [Link](https://leetcode.com/problems/move-zeroes/description/).

##### Example 1:

_Input:_ nums = `[0,1,0,3,12]`
_Output:_ `[1,3,12,0,0]`

##### Example 2:

_Input:_ nums = `[0]`
_Output:_ `[0]`

**Constraints:**

- `1 <= nums.length <= 104`
- `-231 <= nums[i] <= 231 - 1`

```java
class Solution {
    public void moveZeroes(int[] nums) {
        int start = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[start] = num;
                start++;
            }
        }
        while (start < nums.length) {
            nums[start] = 0;
            start++;
        }
    }
}
```

## 75. Sort Colors

Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function. Leetcode [Link](https://leetcode.com/problems/sort-colors/description/).

##### Example 1:

_Input:_ nums = `[2,0,2,1,1,0]`
_Output:_ `[0,0,1,1,2,2]`

##### Example 2:

_Input:_ nums = `[2,0,1]`
_Output:_ `[0,1,2]`

**Constraints:**

- `n == nums.length`
- `1 <= n <= 300`
- `nums[i]` is either 0, 1, or 2.

```java
class Solution {
    public void sortColors(int[] nums) {
        // Dutch national flag algorithm
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;
        while (mid <= high) {
            if(nums[mid] == 0){
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                swap(nums, mid, high);
                high--;
            }
        }
    }

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
```

## 485. Max Consecutive Ones

Given a binary array nums, return the maximum number of consecutive `1's` in the array. Leetcode [Link](https://leetcode.com/problems/max-consecutive-ones/).

##### Example 1:

_Input:_ nums = `[1,1,0,1,1,1]`
_Output:_ 3

**Explanation:** The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.

##### Example 2:

_Input:_ nums = `[1,0,1,1,0,1]`
_Output:_ 2

**Constraints:**

- `1 <= nums.length <= 105`
- `nums[i]` is either 0 or 1.

```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == 1) {
                count++;
            } else {
                max = Math.max(max, count);
                count = 0;
            }
        }
        return Math.max(max, count);
    }
}
```

## 136. Single Number

Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.

You must implement a solution with a linear runtime complexity and use only constant extra space. Leetcode [Link](https://leetcode.com/problems/single-number/).

##### Example 1:

_Input:_ nums = `[2,2,1]`
_Output:_ 1

##### Example 2:

_Input:_ nums = `[4,1,2,1,2]`
_Output:_ 4

##### Example 3:

_Input:_ nums = `[1]`
_Output:_ 1

**Constraints:**

- `1 <= nums.length <= 3 * 104`
- `-3 * 104 <= nums[i] <= 3 * 104`
- Each element in the array appears twice except for one element which appears only once.

```java
class Solution {
    public int singleNumber(int[] nums) {
        int n = nums.length;
        int xorr = 0;
        for (int i = 0; i < n; i++) {
            xorr = xorr ^ nums[i];
        }
        return xorr;
    }
}
```

## 169. Majority Element

Given an array nums of size n, return the majority element.

The majority element is the element that appears more than `⌊n / 2⌋ times`. You may assume that the majority element always exists in the array. Leetcode [Link](https://leetcode.com/problems/majority-element/description/).

##### Example 1:

_Input:_ nums = `[3,2,3]`
_Output:_ 3

##### Example 2:

_Input:_ nums = `[2,2,1,1,1,2,2]`
_Output:_ 2

**Constraints:**

- `n == nums.length`
- `1 <= n <= 5 * 104`
- `-109 <= nums[i] <= 109`

```java
class Solution {
    public int majorityElement(int[] nums) {
        // step 1: using moore's voting algo to determine the majority element
        int count = 0;
        int maxEl = nums[0];
        for (int i : nums) {
            if (count == 0) {
                count = 1;
                maxEl = i;
            } else if (i == maxEl) count++;
            else count--;
        }

        // step 2: to make sure majority element exists
        int count1 = 0;
        for (int i: nums) {
            if (i == maxEl) count1++;
        }
        if (count1 > (n / 2)) return maxEl;
        return -1;
    }
}
```

## 53. Maximum Subarray

Given an integer array nums, find the subarray with the largest sum, and return its sum. Leetcode [Link](https://leetcode.com/problems/maximum-subarray/).

##### Example 1:

_Input:_ nums = `[-2,1,-3,4,-1,2,1,-5,4]`
_Output:_ 6

**Explanation:** The subarray `[4,-1,2,1]` has the largest sum 6.

##### Example 2:

_Input:_ nums = `[1]`
_Output:_ 1

**Explanation:** The subarray `[1]` has the largest sum 1.

##### Example 3:

_Input:_ nums = `[5,4,-1,7,8]`
_Output:_ 23

**Explanation:** The subarray `[5,4,-1,7,8]` has the largest sum 23.

**Constraints:**

- `1 <= nums.length <= 105`
- `-104 <= nums[i] <= 104`

```java
class Solution {
    public int maxSubArray(int[] nums) {
        // Kadane's Algorithm
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            if (sum > maxSum) {
                maxSum = sum;
            }
            // if sum goes below zero then start a new subarray
            if (sum < 0) {
                sum = 0;
            }
        }
        return maxSum;
    }
}
```

## 121. Best Time to Buy and Sell Stock

You are given an array prices where `prices[i]` is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, `return 0`. Leetcode [Link](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/).

##### Example 1:

_Input:_ prices = `[7,1,5,3,6,4]`
_Output:_ 5

**Explanation:** Buy on `day 2 (price = 1)` and sell on `day 5 (price = 6)`, `profit = 6-1 = 5`. Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

##### Example 2:

_Input:_ prices = `[7,6,4,3,1]`
_Output:_ 0

**Explanation:** In this case, no transactions are done and the max `profit = 0`.

**Constraints:**

- `1 <= prices.length <= 105`
- `0 <= prices[i] <= 104`

```java
class Solution {
    public int maxProfit(int[] prices) {
        int minimum = prices[0];
        int profit = 0;
        for (int i : prices) {
            int todaysProfit = i - minimum;
            profit = Math.max(todaysProfit, profit);
            minimum = Math.min(i, minimum);
        }
        return profit;
    }
}
```

## 2149. Rearrange Array Elements by Sign

You are given a `0-indexed` integer array nums of even length consisting of an equal number of positive and negative integers.

You should rearrange the elements of nums such that the modified array follows the given conditions:

Every consecutive pair of integers have opposite signs.
For all integers with the same sign, the order in which they were present in nums is preserved.
The rearranged array begins with a positive integer.
Return the modified array after rearranging the elements to satisfy the aforementioned conditions. Leetcode [Link](https://leetcode.com/problems/rearrange-array-elements-by-sign/).

##### Example 1:

Input: nums = `[3,1,-2,-5,2,-4]`
Output: `[3,-2,1,-5,2,-4]`

**Explanation:**
The positive integers in nums are `[3,1,2]`. The negative integers are `[-2,-5,-4]`.
The only possible way to rearrange them such that they satisfy all conditions is `[3,-2,1,-5,2,-4]`.
Other ways such as `[1,-2,2,-5,3,-4], [3,1,2,-2,-5,-4], [-2,3,-5,1,-4,2]` are incorrect because they do not satisfy one or more conditions.

##### Example 2:

Input: nums = `[-1,1]`
Output: `[1,-1]`

**Explanation:**
1 is the only positive integer and -1 the only negative integer in nums. So nums is rearranged to `[1,-1].`

**Constraints:**

- `2 <= nums.length <= 2 * 105`
- `nums.length is even
- 1 <= |nums[i]| <= 105`
- nums consists of equal number of positive and negative integers.

```java
class Solution {
    public int[] rearrangeArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int posIndex = 0, negIndex = 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                ans[posIndex] = nums[i];
                posIndex += 2;
            } else {
                ans[negIndex] = nums[i];
                negIndex += 2;
            }
        }
        return ans;
    }
}
```

## 31. Next Permutation

A permutation of an array of integers is an arrangement of its members into a sequence or linear order.

- For example, for `arr = [1,2,3]`, the following are all the permutations of arr: `[1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1]`.

The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).

- For example, the next permutation of `arr = [1,2,3] is [1,3,2]`.
- Similarly, the next permutation of `arr = [2,3,1] is [3,1,2]`.
- While the next permutation of `arr = [3,2,1] is [1,2,3]` because `[3,2,1]` does not have a lexicographical larger rearrangement.

Given an array of integers nums, find the next permutation of nums.

The replacement must be in place and use only constant extra memory. Leetcode [Link](https://leetcode.com/problems/next-permutation/description/).

##### Example 1:

_Input:_ nums = `[1,2,3]`
_Output:_ `[1,3,2]`

##### Example 2:

_Input:_ nums = `[3,2,1]`
_Output:_ `[1,2,3]`

##### Example 3:

_Input:_ nums = `[1,1,5]`
_Output:_ `[1,5,1]`

**Constraints:**

- `1 <= nums.length <= 100`
- `0 <= nums[i] <= 100`

```java
class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length; // size of the array

        // step 1: find the break point
        int index = -1;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i+1]) {
                index = i;
                break;
            }
        }
        // if there is no breaking point
        if (index == -1) {
            // Reverse the whole array
            rev(nums, 0, n-1);
            return;
        }

        // Step 2: Find the next great element and swap with the index element
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] > nums[index]) {
                swap(nums, i, index);
                break;
            }
        }

        // Reverse the right half
        rev(nums, index+1, n-1);
    }

    void rev(int[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start, end);
            start++;
            end--;
        }
    }

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
```

## 128. Longest Consecutive Sequence

Given an unsorted array of integers `nums`, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in `O(n)` time. Leetcode [Link](https://leetcode.com/problems/longest-consecutive-sequence/description/).

##### Example 1:

_Input:_ nums = `[100,4,200,1,3,2]`
_Output:_ 4

**Explanation:** The longest consecutive elements sequence is `[1, 2, 3, 4]`. Therefore its length is 4.

##### Example 2:

_Input:_ nums = `[0,3,7,2,5,8,4,6,0,1]`
_Output:_ 9

**Constraints:**

- `0 <= nums.length <= 105`
- `-109 <= nums[i] <= 109`

```java
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }

        int longest = 1;
        for (int i : set) {
            if (!set.contains(i-1)) {
                int count = 1;
                int x = i;
                while (set.contains(x+1)) {
                    x++;
                    count++;
                }
                longest = Math.max(longest, count);
            }
        }

        return longest;
    }
}
```

## 73. Set Matrix Zeroes

Given an `m x n` integer matrix matrix, if an element is 0, set its entire row and column to 0's.

You must do it in place. Leetcode [Link](https://assets.leetcode.com/uploads/2020/08/17/mat1.jpg).

##### Example 1:

![img](https://assets.leetcode.com/uploads/2020/08/17/mat1.jpg)

_Input:_ matrix = `[[1,1,1],[1,0,1],[1,1,1]]`
_Output:_ `[[1,0,1],[0,0,0],[1,0,1]]`

##### Example 2:

![img](https://assets.leetcode.com/uploads/2020/08/17/mat2.jpg)

_Input:_ matrix = `[[0,1,2,0],[3,4,5,2],[1,3,1,5]]`
_Output:_ `[[0,0,0,0],[0,4,5,0],[0,3,1,0]]`

**Constraints:**

- `m == matrix.length`
- `n == matrix[0].length`
- `1 <= m, n <= 200`
- `-231 <= matrix[i][j] <= 231 - 1`

```java
class Solution {
    public void setZeroes(int[][] matrix) {
        int[] row = new int[matrix.length];
        int[] col = new int[matrix[0].length];

        // iterate through the matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                // if we find 0 mark that row and col
                if (matrix[i][j] == 0) {
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }

        // iterate through the matrix again
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                // if we find the row or col contains 0 then mark the whole row & col
                if (row[i] == 1 || col[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
```

## 48. Rotate Image

You are given an `n x n` 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation. Leetcode [Link](https://leetcode.com/problems/rotate-image/)

##### Example 1:

![img](https://assets.leetcode.com/uploads/2020/08/28/mat1.jpg)

_Input:_ matrix = `[[1,2,3],[4,5,6],[7,8,9]]`
_Output:_ `[[7,4,1],[8,5,2],[9,6,3]]`

##### Example 2:

![img](https://assets.leetcode.com/uploads/2020/08/28/mat2.jpg)

_Input:_ matrix = `[[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]`
_Output:_ `[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]`

**Constraints:**

- `n == matrix.length == matrix[i].length`
- `1 <= n <= 20`
- `-1000 <= matrix[i][j] <= 1000`

```java
class Solution {
    public void rotate(int[][] matrix) {
        int rowL = matrix.length, colL = matrix[0].length;

        // swap all the rows with columns
        for (int i = 0; i < rowL - 1; i++) {
            for (int j = i + 1; j < colL; j++) {
                swap(matrix, i, j);
            }
        }

        // reverse all the rows
        for (int i = 0; i < rowL; i++) {
            reverse(matrix[i]);
        }
    }

    void swap(int[][] matrix, int i, int j) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
    }

    void reverse(int[] arr) {
        int start = 0, end = arr.length - 1;
        while (start <= end) {
            swap(arr,  start, end);
            start++;
            end--;
        }
    }

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
```

## 54. Spiral Matrix

Given an `m x n` matrix, return all elements of the matrix in spiral order. Leetcode [Link](https://leetcode.com/problems/spiral-matrix/description/).

##### Example 1:

![img](https://assets.leetcode.com/uploads/2020/11/13/spiral1.jpg)

_Input:_ matrix = `[[1,2,3],[4,5,6],[7,8,9]]`
_Output:_ `[1,2,3,6,9,8,7,4,5]`

##### Example 2:

![img](https://assets.leetcode.com/uploads/2020/11/13/spiral.jpg)

_Input:_ matrix = `[[1,2,3,4],[5,6,7,8],[9,10,11,12]]`
_Output:_ `[1,2,3,4,8,12,11,10,9,5,6,7]`

**Constraints:**

- `m == matrix.length`
- `n == matrix[i].length`
- `1 <= m, n <= 10`
- `-100 <= matrix[i][j] <= 100`

```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();

        int rowLen = matrix.length, colLen = matrix[0].length;

        int left = 0, right = colLen - 1;
        int top = 0, bottom = rowLen - 1;

        while ( left <= right && top <= bottom) {
            // moving left => right
            for (int i = left; i <= right; i++) {
                ans.add(matrix[top][i]);
            }
            top++;

            // moving top => bottom
            for (int i = top; i <= bottom; i++) {
                ans.add(matrix[i][right]);
            }
            right--;

            // moving right => left
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    ans.add(matrix[bottom][i]);
                }
                bottom--;
            }

            // moving bottom => top
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    ans.add(matrix[i][left]);
                }
                left++;
            }
        }

        return ans;
    }
}
```

## 118. Pascal's Triangle

Given an integer `numRows`, return the first numRows of Pascal's triangle. Leetcode [Link](https://leetcode.com/problems/pascals-triangle/description/).

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

![gif](https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif)

##### Example 1:

_Input:_ numRows = 5
_Output:_ `[[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]`

##### Example 2:

_Input:_ numRows = 1
_Output:_ `[[1]]`

**Constraints:**

- `1 <= numRows <= 30`

```java
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int row = 1; row <= numRows; row++) {
            result.add(generateRow(row));
        }
        return result;
    }

    List<Integer> generateRow(int row) {
        List<Integer> ansRow = new ArrayList<>();

        int ans = 1;
        ansRow.add(1);

        for (int col = 1; col < row; col++) {
            ans *= row - col;
            ans /= col;
            ansRow.add(ans);
        }

        return ansRow;
    }
}
```

## 15. 3Sum

Given an integer array nums, return all the triplets `[nums[i], nums[j], nums[k]]` such that `i != j, i != k, and j != k`, and `nums[i] + nums[j] + nums[k] == 0`.

Notice that the solution set must not contain duplicate triplets. Leetcode [Link](https://leetcode.com/problems/3sum/description/).

##### Example 1:

_Input:_ nums = `[-1,0,1,2,-1,-4]`
_Output:_ `[[-1,-1,2],[-1,0,1]]`

**Explanation:**
`nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0`.
`nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0`.
`nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.`
The distinct triplets are `[-1,0,1]` and `[-1,-1,2].`
Notice that the order of the output and the order of the triplets does not matter.

##### Example 2:

_Input:_ nums = `[0,1,1]`
_Output:_ `[]`

**Explanation:** The only possible triplet does not sum up to 0.

##### Example 3:

Input: nums = `[0,0,0]`
Output: `[[0,0,0]]`

**Explanation:** The only possible triplet sums up to 0.

**Constraints:**

- `3 <= nums.length <= 3000`
- `-105 <= nums[i] <= 105`

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            // skip duplicates
            if (i != 0 && nums[i] == nums[i-1]) continue;

            // moving two pointers
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum > 0) {
                    k--;
                } else if (sum < 0) {
                    j++;
                } else {

                    List<Integer> ans = Arrays.asList(nums[i], nums[j], nums[k]);
                    result.add(ans);
                    j++;
                    k--;

                    //skip the duplicates
                    while (j < k && nums[j] == nums[j - 1]) j++;
                    while (j < k && nums[k] == nums[k + 1]) k--;
                }
            }
        }

        return result;
    }
}
```

## 18. 4Sum

Given an array nums of n integers, return an array of all the unique quadruplets `[nums[a], nums[b], nums[c], nums[d]] `such that:

`0 <= a, b, c, d < n`
a, b, c, and d are distinct.
`nums[a] + nums[b] + nums[c] + nums[d] == target`
You may return the answer in any order. Leetcode [Link](https://leetcode.com/problems/4sum/description/).

##### Example 1:

_Input:_ nums = `[1,0,-1,0,-2,2]`, target = 0
_Output:_ `[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]`

##### Example 2:

_Input:_ nums = `[2,2,2,2,2]`, target = 8
_Output:_ `[[2,2,2,2]]`

**Constraints:**

- `1 <= nums.length <= 200`
- `-109 <= nums[i] <= 109`
- `-109 <= target <= 109`

```java
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            // skip duplicates
            if (i != 0 && nums[i] == nums[i-1]) continue;

            for (int j = i + 1; j < n; j++) {
                // skip duplicates
                if (j != i + 1 && nums[j] == nums[j-1]) continue;

                int k = j + 1, l = n - 1;
                while (k < l) {
                    // long sum = nums[i] + nums[j] + nums[k] + nums[l];

                    long sum = nums[i];
                    sum += nums[j];
                    sum += nums[k];
                    sum += nums[l];

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));

                        k++; l--;

                        // skip duplicates
                        while (k < l && nums[k] == nums[k-1]) k++;
                        while (k < l && nums[l] == nums[l+1]) l--;
                    } else if (sum < target) {
                        k++;
                    } else l--;
                }
            }
        }

        return result;
    }
}
```

## 88. Merge Sorted Array

You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.

**Merge** `nums1` and `nums2` into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of `m + n`, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n. Leetcode [Link](https://leetcode.com/problems/merge-sorted-array/description/).

##### Example 1:

_Input:_ nums1 = `[1,2,3,0,0,0]`, m = 3, nums2 = `[2,5,6]`, n = 3
_Output:_ `[1,2,2,3,5,6]`

**Explanation:** The arrays we are merging are `[1,2,3]` and `[2,5,6]`.
The result of the merge is `[1,2,2,3,5,6]` with the underlined elements coming from nums1.

##### Example 2:

_Input:_ nums1 = `[1]`, m = 1, nums2 = `[]`, n = 0
_Output:_ `[1]`

**Explanation:** The arrays we are merging are `[1]` and `[].`
The result of the merge is `[1]`.

##### Example 3:

_Input:_ nums1 = `[0]`, m = 0, nums2 = `[1]`, n = 1
_Output:_ `[1]`

**Explanation:** The arrays we are merging are `[]` and `[1]`.
The result of the merge is `[1]`.
Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.

**Constraints:**

- `nums1.length == m + n`
- `nums2.length == n`
- `0 <= m, n <= 200`
- `1 <= m + n <= 200`
- `-109 <= nums1[i], nums2[j] <= 109`

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int left = m - 1;
        int right = n - 1;
        int end = m + n - 1;

        while (right >= 0) {
            if (left >= 0 && nums1[left] > nums2[right]) {
                nums1[end--] = nums1[left--];
            } else {
                nums1[end--] = nums2[right--];
            }
        }
    }
}
```
