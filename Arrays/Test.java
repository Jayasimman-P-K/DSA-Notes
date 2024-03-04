package Arrays;

import java.util.ArrayList;
// import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {10,11,12}
        };
        
        // for (int i = 0; i < matrix.length; i++) {
        //     reverse(matrix[i]); 
        // }

        // for (int i = 0; i < matrix.length; i++) {
        //     System.out.println(Arrays.toString(matrix[i]));
        // }
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
        System.out.println(ans);
    }

    static void reverse(int[] arr) {
        int start = 0, end = arr.length - 1;
        while (start <= end) {
            swap(arr,  start, end);
            start++;
            end--;
        }
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j]; 
        arr[j] = temp; 
    }
}
