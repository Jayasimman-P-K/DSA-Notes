package SortingAlgos;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {3, 8, 4, 5, 3, 2, 8, 9, 5, 6};
        int n = arr.length - 1;
        quickSort(arr, 0, n);
        System.out.println(Arrays.toString(arr));
    }
    
    static void quickSort(int[] arr, int low, int high) {
        // base case: low < high
        if (low < high) {
            int pIndex = partition(arr, low, high); // index of the pivot element
            quickSort(arr, low, pIndex-1); // left half array
            quickSort(arr, pIndex+1, high); // right half array
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
}
