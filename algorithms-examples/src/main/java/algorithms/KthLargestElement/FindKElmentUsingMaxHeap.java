package algorithms.KthLargestElement;

//Build a Max Heap tree in O(n)
//Use Extract Max k times to get k maximum elements from the Max Heap O(klogn)

import algorithms.tree.binaryHeap.MaxHeap;

public class FindKElmentUsingMaxHeap {

    public static void main(String[] args) {
        int  k = 2;
        int secondLargestElement = findKthLargest ( new int [] {3 , 2, 1 ,5 ,6 ,4} , k);
        System.out.println("2th largest element = " + secondLargestElement);
    }

    public  static int findKthLargest(int[] nums  , int k) {
        MaxHeap maxHeap = new MaxHeap(nums);
        maxHeap.printHeap();
        maxHeap.buildMaxHeap();
        int maxElement = -1;
        for ( int i=1 ;i <=k ; i++) {
            //Extract find max element and remove it
            maxElement = maxHeap.extractMax();
            System.out.println("maxElement" + maxElement);
        }
        return maxElement;
    }
}
