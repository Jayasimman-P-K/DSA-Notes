# Sorting Algorithms:

- Bubble sort
- Selection sort
- Insertion sort
- Merge sort
- Quick sort

### Bubble sort:

##### Time Complexity:

- worst case: `O(N²)`
- avg case: `O(N²)`
- best case: `O(N²)`

### Merge sort:

Merge sort is a divide and conquer algorithm, it divides the given array into two parts recursively until it reaches the single value. Upon reaching the single value it then merge them in a sorted manner.

There are two main functions implemented here:

- `mergeSort()` this function divide the arrays into 2 parts. Low to mid and mid+1 to high.
- `merge()` this function merges the two sorted array.

###### Time Complexity: `O(NlogN)`

###### Space Complexity: `O(N)`

```java

public static void main(String[] args) {
    int[] arr = {3, 8, 4, 5, 3, 2, 8, 9, 5, 6};
    int n = arr.length - 1;
    mergeSort(arr, 0, n);
    System.out.println(Arrays.toString(arr));
    // output: [2, 3, 3, 4, 5, 5, 6, 8, 8, 9]
}

// function calls itself recursively until it reaches single element
static void mergeSort(int[] arr, int low, int high) {
    if (low >= high) return; // base case: single element
    int mid = (low + high) / 2;
    mergeSort(arr, low, mid); // left half array
    mergeSort(arr, mid+1, high); // right half array
    merge(arr, low, mid, high); // merging the sorted halves
}

static void merge(int[] arr, int low, int mid, int high) {
    ArrayList<Integer> temp = new ArrayList<>(); // temporary array

    int left = low; // starting index of the left half array
    int right = mid+1; // starting index of the right half array

    //storing elements in the temporary array in a sorted manner
    while (left <= mid && right <= high) {
        if (arr[left] <= arr[right]) {
            temp.add(arr[left]);
            left++;
        } else {
            temp.add(arr[right]);
            right++;
        }
    }

    // if elements in the left half are still left
    while (left <= mid) {
        temp.add(arr[left]);
        left++;
    }

    // if elements in the right half are still left
    while (right <= high) {
        temp.add(arr[right]);
        right++;
    }

    // transfering all the element from temp to the original array
    for (int i = low; i <= high; i++) {
        arr[i] = temp.get(i - low);
    }
}

```

### Quick sort:

Quick sort is a divide and conquer algorithm same as merge sorg algorithm. But this algorithm does not use extra space for the sorting. From that perspective quick sort is slightly better than merge sort algorithm.

This algorithm follows 2 steps recursively:

- Pick a pivot element and place it in it's correct position in the sorted array.
- Shift smaller elements to the left of the pivot element and larger elements to the right of the pivot element.

###### Time complexity: `O(NlogN)`

###### Space complexity: `O(1)`

```java

public static void main(String[] args) {
    int[] arr = {3, 8, 4, 5, 3, 2, 8, 9, 5, 6};
    int n = arr.length - 1;
    quickSort(arr, 0, n);
    System.out.println(Arrays.toString(arr));
}

static void quickSort(int[] arr, int low, int high) {
    // base case: low < high
    if (low < high) {
        int pIndex = partition(arr, low, high); // returns the index of the pivot element
        quickSort(arr, low, pIndex-1); // sort left half array
        quickSort(arr, pIndex+1, high); // sort right half array
    }
}

static int partition(int[] arr, int low, int high) {
    int pivot = arr[low];
    int i = low;
    int j = high;

    while (i < j) {
        // finding the first greater element than the pivot
        while (arr[i] <= pivot && i < high) {
            i++;
        }
        // finding the first smaller element than the pivot
        while (arr[j] >= pivot && j > low) {
            j--;
        }
        // swap both the greater element and the smaller element
        if (i < j) {
            swap(arr, i, j);
        }
    }

    // swap the pivot element with it's right position
    swap(arr, low, j);
    // return the correct index of the pivot element
    return j;
}

static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}
```
