# Sliding Windows

### Max Sum Subarray of size K

Problem [Link](https://www.geeksforgeeks.org/problems/max-sum-subarray-of-size-k5313/1).

```java
class Solution{
    static long maximumSumSubarray(int K, ArrayList<Integer> Arr,int N){
        // code here
        long currentSum = 0;
        long maxSum = 0;

        for(int i = 0; i<K; i++) {
            currentSum += Arr.get(i);
        }

        maxSum = currentSum;

        for(int i=1; i<N-K+1; i++) {
            currentSum = currentSum - Arr.get(i-1) + Arr.get(i+K-1);
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }

        return maxSum;
    }
}
```
