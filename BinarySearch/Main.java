package BinarySearch;

public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 5, 9, 14, 35, 67};
        int target = 7;

        int index = binarySearch(arr, target);
        System.out.println("Index of the target " + target + " is " + index);
    }

    static int binarySearch(int[] arr, int target) {

        int start = 0, end = arr.length - 1;

        while (start <= end) {
            int mid = start + ((end - start) / 2);

            if (arr[mid] == target) return mid;
            else if (arr[mid] > target) end = mid - 1;
            else start = mid + 1;
        }

        return -1;
    }
}
