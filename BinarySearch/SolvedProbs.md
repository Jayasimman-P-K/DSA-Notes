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

##

##

##

##

##
