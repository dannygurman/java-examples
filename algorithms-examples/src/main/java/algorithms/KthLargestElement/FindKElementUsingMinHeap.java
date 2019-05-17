package algorithms.KthLargestElement;

import algorithms.tree.binaryHeap.MinHeap;

import java.util.Arrays;

//MH - Minimum heap

//1) Build a Min Heap MH of the first k elements (arr[0] to arr[k-1]) of the given array. O(k)

//2) For each element, after the kth element (arr[k] to arr[n-1]), compare it with root of MH
    //a) If the element is greater than the root then make it root and call heapify for MH
    //b) Else ignore it. The step 2 is O((n-k)*logk)
    //Finally, MH has k largest elements and root of the MH is the kth largest element.
public class FindKElementUsingMinHeap {

    public static void main(String[] args) {
        int  k = 2;
        int secondLargestElement = findKthLargest ( new int [] {3 , 2, 1 ,5 ,6 ,4} , k);
        System.out.println("2th largest element = " + secondLargestElement);
    }

    public  static int findKthLargest(int[] nums  , int k) {
        int [] firstKElements = Arrays.copyOfRange(nums, 0, k);
        MinHeap minHeap = new MinHeap(firstKElements );
        minHeap.buildMinHeap();

        for ( int i=k ; i < nums.length ;i++){
            int valInMinHeapRoot = minHeap.findMin();//heap[0]

            int curValue = nums[i];

            if (curValue > valInMinHeapRoot) {
                minHeap.replaceValueAndMinHeapify(0 , curValue);
            }
            //System.out.println(curValue);
        }
        minHeap.printHeap();
        //The heap contains  kth  elment with maximum value.
        //The root (heap minimum) contains kth element
        return minHeap.findMin();
    }

}
