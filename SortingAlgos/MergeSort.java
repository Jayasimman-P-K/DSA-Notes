package SortingAlgos;

import java.util.ArrayList;
import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {3, 8, 4, 5, 3, 2, 8, 9, 5, 6};
        int n = arr.length - 1;
        mergeSort(arr, 0, n);
        System.out.println(Arrays.toString(arr));
    }
    
    // function calls itself recursively until it reaches single element
    static void mergeSort(int[] arr, int low, int high) {
        if (low >= high) return; // single element case
        int mid = (low + high) / 2;
        mergeSort(arr, low, mid); // first half 
        mergeSort(arr, mid+1, high); // second half
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
}
