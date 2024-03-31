# Binary Search

## 704. Binary Search

Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, `return -1`.

You must write an algorithm with `O(log n)` runtime complexity. Leetcode [Link](https://leetcode.com/problems/binary-search/).

##### Example 1:

_Input:_ nums = `[-1,0,3,5,9,12]`, target = 9
_Output:_ 4

**Explanation:** 9 exists in nums and its index is 4

##### Example 2:

_Input:_ nums = `[-1,0,3,5,9,12]`, target = 2
_Output:_ -1

**Explanation:** 2 does not exist in nums so return -1

**Constraints:**

- `1 <= nums.length <= 104`
- `-104 < nums[i], target < 104`
- All the integers in nums are unique.
- nums is sorted in ascending order.

```java
class Solution {
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        return recursionBinarySearch(nums, target, start, end);

    }

    int recursionBinarySearch(int[] arr, int target, int start, int end) {

        if (start > end) {
            return -1;
        }

        int mid = start + (end-start) / 2;

        if(arr[mid] == target) {
            return mid;
        }

        if(arr[mid] < target) {
            return recursionBinarySearch(arr, target, mid+1, end);
        }

        return recursionBinarySearch(arr, target, start, mid-1);
    }
}
```

## 35. Search Insert Position

Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You must write an algorithm with `O(log n)` runtime complexity. Leetcode [Link](https://leetcode.com/problems/search-insert-position/).

##### Example 1:

_Input:_ nums = [1,3,5,6], target = 5
_Output:_ 2

##### Example 2:

_Input:_ nums = [1,3,5,6], target = 2
_Output:_ 1

##### Example 3:

_Input:_ nums = [1,3,5,6], target = 7
_Output:_ 4

**Constraints:**

- `1 <= nums.length <= 104`
- `-104 <= nums[i] <= 104`
- nums contains distinct values sorted in ascending order.
- `-104 <= target <= 104`

```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        int ans = nums.length;
        int start = 0, end = nums.length - 1;
        while (start <= end ) {
            int mid = start + ((end - start) / 2);
            if (nums[mid] >= target) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return ans;
    }
}
```

## 34. Find First and Last Position of Element in Sorted Array

Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

If target is not found in the array, `return [-1, -1]`.

You must write an algorithm with `O(log n)` runtime complexity. Leetcode [Link](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/).

##### Example 1:

_Input:_ nums = `[5,7,7,8,8,10]`, target = 8
_Output:_ `[3,4]`

##### Example 2:

_Input:_ nums = `[5,7,7,8,8,10],` target = 6
_Output:_ `[-1,-1]`

##### Example 3:

_Input:_ nums = [], target = 0
_Output:_ `[-1,-1]`

**Constraints:**

- `0 <= nums.length <= 105`
- `-109 <= nums[i] <= 109`
- nums is a non-decreasing array.
- `-109 <= target <= 109`

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        return new int[]{firstIndex(nums, target), secondIndex(nums, target)};
    }

    int firstIndex(int[] arr, int target) {
        int start = 0, end = arr.length - 1;

        int index = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] < target) {
                start = mid + 1;
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else {
                index = mid;
                end = mid - 1;
            }
        }

        return index;
    }

    int secondIndex(int[] arr, int target) {
        int start = 0, end = arr.length - 1;

        int index = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] < target) {
                start = mid + 1;
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else {
                index = mid;
                start = mid + 1;
            }
        }

        return index;
    }
}
```

## 33. Search in Rotated Sorted Array

There is an integer array nums sorted in ascending order (with distinct values).

Prior to being passed to your function, nums is possibly rotated at an unknown `pivot index k (1 <= k < nums.length)` such that the resulting array is `[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed)`. For example, `[0,1,2,4,5,6,7]` might be rotated at pivot index 3 and become `[4,5,6,7,0,1,2]`.

Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with `O(log n)` runtime complexity. Leetcode [Link](https://leetcode.com/problems/search-in-rotated-sorted-array/description/).

##### Example 1:

_Input:_ nums = `[4,5,6,7,0,1,2]`, target = 0
_Output:_ 4

##### Example 2:

_Input:_ nums = `[4,5,6,7,0,1,2]`, target = 3
_Output:_ -1

##### Example 3:

_Input:_ nums = `[1]`, target = 0
_Output:_ -1

**Constraints:**

- `1 <= nums.length <= 5000`
- `-104 <= nums[i] <= 104`
- All values of nums are unique.
- nums is an ascending array that is possibly rotated.
- `-104 <= target <= 104`

```java


class Solution {
    public int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end-start) / 2;

            if(nums[mid] == target) {
                return mid;
            }

            // mid is in left part of the rotated array
            if (nums[start] <= nums[mid]) {
                if (nums[start] <= target && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }

            // mid is in right part of the rotated array
            else {
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return -1;
    }
}
```

## 81. Search in Rotated Sorted Array II

There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).

Before being passed to your function, nums is rotated at an unknown pivot index `k (0 <= k < nums.length)` such that the resulting array is `[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]` (0-indexed). For example, `[0,1,2,4,4,4,5,6,6,7]` might be rotated at pivot index 5 and become `[4,5,6,6,7,0,1,2,4,4]`.

Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.

You must decrease the overall operation steps as much as possible. Leetcode [Link](https://leetcode.com/problems/search-in-rotated-sorted-array-ii/).

#### Example 1:

_Input:_ nums = `[2,5,6,0,0,1,2]`, target = 0
_Output:_ true

#### Example 2:

_Input:_ nums = `[2,5,6,0,0,1,2]`, target = 3
_Output:_ false

**Constraints:**

- `1 <= nums.length <= 5000`
- `-104 <= nums[i] <= 104`
- nums is guaranteed to be rotated at some pivot.
- `-104 <= target <= 104`

```java


class Solution {
    public boolean search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;

        while (start <= end) {
            int mid = start + ((end - start) / 2);

            if (nums[mid] == target) return true;

            if (nums[start] == nums[mid] && nums[mid] == nums[end]) {
                start++;
                end--;
                continue;
            };

            if (nums[start] <= nums[mid]) {
                // mid is in the left sorted array
                if (nums[start] <= target && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                // mid is in the right sorted array
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return false;
    }
}
```

## 153. Find Minimum in Rotated Sorted Array

Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array `nums = [0,1,2,4,5,6,7]` might become:

- `[4,5,6,7,0,1,2]` if it was rotated 4 times.
- `[0,1,2,4,5,6,7]` if it was rotated 7 times.

Notice that rotating an array `[a[0], a[1], a[2], ..., a[n-1]]` 1 time results in the array `[a[n-1], a[0], a[1], a[2], ..., a[n-2]]`.

Given the sorted rotated array nums of unique elements, return the minimum element of this array.

You must write an algorithm that runs in O(log n) time. Leetcode [Link](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/).

#### Example 1:

_Input:_ nums = `[3,4,5,1,2]`
_Output:_ 1

**Explanation:** The original array was `[1,2,3,4,5]` rotated 3 times.

#### Example 2:

_Input:_ nums = `[4,5,6,7,0,1,2]`
_Output:_ 0

**Explanation:** The original array was `[0,1,2,4,5,6,7]` and it was rotated 4 times.

#### Example 3:

_Input:_ nums = `[11,13,15,17]`
_Output:_ 11

**Explanation:** The original array was `[11,13,15,17]` and it was rotated 4 times.

**Constraints:**

- `n == nums.length`
- `1 <= n <= 5000`
- `-5000 <= nums[i] <= 5000`
- All the integers of nums are unique.
- nums is sorted and rotated between 1 and n times.

```java


class Solution {
    public int findMin(int[] nums) {
        int start = 0, end = nums.length - 1, ans = Integer.MAX_VALUE;

        while (start <= end) {
            int mid = start + ((end - start) / 2);

            if (nums[start] <= nums[mid]) {
                ans = Math.min(ans, nums[start]);
                start = mid + 1;
            } else {
                ans = Math.min(ans, nums[mid]);
                end = mid - 1;
            }
        }

        return ans;
    }
}
```

## 540. Single Element in a Sorted Array

You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.

Return the single element that appears only once.

Your solution must run in `O(log n)` time and `O(1)` space. Leetcode [Link](https://leetcode.com/problems/single-element-in-a-sorted-array/description/).

#### Example 1:

_Input:_ nums = `[1,1,2,3,3,4,4,8,8]`
_Output:_ 2

#### Example 2:

_Input:_ nums = `[3,3,7,7,10,11,11]`
_Output:_ 10

**Constraints:**

- `1 <= nums.length <= 105`
- `0 <= nums[i] <= 105`

```java


class Solution {
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;

        if (n == 1) return nums[0];
        if (nums[0] != nums[1]) return nums[0];
        if (nums[n-1] != nums[n-2]) return nums[n-1];

        int start = 1, end = n - 2;

        while (start <= end) {
            int mid = start + ((end-start) / 2);

            if (nums[mid] != nums[mid+1] && nums[mid] != nums[mid-1]) return nums[mid];

            if ((mid % 2 == 1 && nums[mid] == nums[mid-1]) || (mid % 2 == 0 && nums[mid+1] == nums[mid])) {
                // left half of the array
                start = mid + 1;
            } else {
                // right half of the array
                end = mid - 1;
            }
        }

        return -1;
    }
}
```

## 162. Find Peak Element

A peak element is an element that is strictly greater than its neighbors.

Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.

You may imagine that `nums[-1] = nums[n] = -∞`. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.

You must write an algorithm that runs in O(log n) time. Leetcode [Link](https://leetcode.com/problems/find-peak-element/description/).

#### Example 1:

_Input:_ nums = `[1,2,3,1]`
_Output:_ 2

**Explanation:** 3 is a peak element and your function should return the index number 2.

#### Example 2:

_Input:_ nums = `[1,2,1,3,5,6,4]`
_Output:_ 5

**Explanation:** Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.

**Constraints:**

- `1 <= nums.length <= 1000`
- `-231 <= nums[i] <= 231 - 1`
- `nums[i] != nums[i + 1] for all valid i.`

```java


class Solution {
    public int findPeakElement(int[] arr) {
        int n = arr.length;

        if (n == 1) return 0;
        if (arr[0] > arr[1]) return 0;
        if (arr[n-1] > arr[n-2]) return n-1;

        int start = 1, end = n - 2;

        while (start <= end) {
            int mid = start + ((end - start) / 2);

            if (arr[mid] > arr[mid-1] && arr[mid] > arr[mid+1]) return mid;

            if (arr[mid] > arr[mid-1]) {
                // left sorted
                start = mid + 1;
            } else {
                // right sorted
                end = mid - 1;
            }
        }

        return -1;
    }
}
```

## 1283. Find the Smallest Divisor Given a Threshold

Given an array of integers nums and an integer threshold, we will choose a positive integer divisor, divide all the array by it, and sum the division's result. Find the smallest divisor such that the result mentioned above is less than or equal to threshold.

Each result of the division is rounded to the nearest integer greater than or equal to that element. (For example: 7/3 = 3 and 10/2 = 5).

The test cases are generated so that there will be an answer. Leetcode [Link](https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/description/).

#### Example 1:

_Input:_ nums = `[1,2,5,9]`, threshold = 6
_Output:_ 5

**Explanation:** We can get a sum to 17 (1+2+5+9) if the divisor is 1.
If the divisor is 4 we can get a sum of 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2).

#### Example 2:

_Input:_ nums = `[44,22,33,11,1]`, threshold = 5
_Output:_ 44

**Constraints:**

- `1 <= nums.length <= 5 * 104`
- `1 <= nums[i] <= 106`
- nums.length <= threshold <= 106

```java


class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int start = 1, end = 1000000, ans = -1;

        while (start <= end) {
            int mid = start + ((end - start) / 2);
            if(sumOfDivisor(mid, nums, threshold)) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return ans;
    }

    static boolean sumOfDivisor(int n, int[] arr, int t) {
        int sum = 0;
        for (int i : arr) {
            sum += (i - 1) / n + 1; // same as ceil(i / n)
        }
        return sum <= t;
    }
}
```

## 1011. Capacity To Ship Packages Within D Days

A conveyor belt has packages that must be shipped from one port to another within days days.

The ith package on the conveyor belt has a weight of `weights[i]`. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.

Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days. Leetcode [Link](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/description/).

#### Example 1:

_Input:_ weights = `[1,2,3,4,5,6,7,8,9,10]`, days = 5
_Output:_ 15

**Explanation:** A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10

Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.

#### Example 2:

_Input:_ weights = `[3,2,2,4,1,4]`, days = 3
_Output:_ 6

**Explanation:** A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
1st day: 3, 2
2nd day: 2, 4
3rd day: 1, 4

#### Example 3:

_Input:_ weights = `[1,2,3,1,1]`, days = 4
_Output:_ 3

**Explanation:**
1st day: 1
2nd day: 2
3rd day: 3
4th day: 1, 1

**Constraints:**

- `1 <= days <= weights.length <= 5 * 104`
- `1 <= weights[i] <= 500`

```java


class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int maxEl = weights[0], total = 0;
        for (int i : weights) {
            if (i > maxEl) maxEl = i;
            total += i;
        }

        int start = maxEl, end = total;

        while (start <= end) {
            int mid = start + ((end - start) / 2);

            if(countDays(weights, mid, days)) end = mid - 1;
            else start = mid + 1;
        }

        return start;
    }

    static boolean countDays(int[] arr, int mid, int D) {
        int total = 0, day = 1;

        for (int i : arr) {
            if (total + i > mid) {
                day += 1;
                total = i;
            } else {
                total += i;
            }
        }

        return day <= D;
    }
}
```

## 1539. Kth Missing Positive Number

Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.

Return the kth positive integer that is missing from this array. leetcode [Link](https://leetcode.com/problems/kth-missing-positive-number/description/).

#### Example 1:

_Input:_ arr = `[2,3,4,7,11]`, k = 5
_Output:_ 9

**Explanation:** The missing positive integers are `[1,5,6,8,9,10,12,13,...]`. The 5th missing positive integer is 9.

#### Example 2:

_Input:_ arr = `[1,2,3,4]`, k = 2
_Output:_ 6

**Explanation:** The missing positive integers are `[5,6,7,...]`. The 2nd missing positive integer is 6.

**Constraints:**

- `1 <= arr.length <= 1000`
- `1 <= arr[i] <= 1000`
- `1 <= k <= 1000`
- `arr[i] < arr[j] for 1 <= i < j <= arr.length`

```java


class Solution {
    public int findKthPositive(int[] arr, int k) {
        int start = 0, end = arr.length - 1;

        while (start <= end) {
            int mid = start + ((end - start) / 2);
            int missing = arr[mid] - (mid + 1);
            if (missing < k) start = mid + 1;
            else end = mid - 1;
        }

        return start + k;
    }
}
```

##
