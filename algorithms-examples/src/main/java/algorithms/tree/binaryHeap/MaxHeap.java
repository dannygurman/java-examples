package algorithms.tree.binaryHeap;


import algorithms.utils.AlgorithmUtils;

import java.util.Arrays;

/**

 * A HEAP is a specialized tree-based ABSTRACT DATA TYPE that satisfies the heap property:
 * min-heap: All non-leaf elements are either smaller than or equal to their left and right child.
 * max-heap: All non-leaf elements are either greater than or equal to their left and right child.
 * <p/>
 * Often implemented as an array, where the children of the element at index i are at index
 * 2i+1 (left child) and 2i+2 (right child).
 * <p/>
 * The first element (minimum or maximum, depending on chosen order) can be found in O(1).
 * Each successor can be found in O(log n).
 *
 * The algorithm in maxHeapify() takes O(log n) time
 * Therefore, buildMaxHeap() would take O(n log n) time BUT IF OBSERVED CAREFULLY IT TAKES 0(N) TIME.
 *
 * Used in the HeapSort algorithm. A
 * lso can be used to implement a PriorityQueue.

 */
public class MaxHeap extends BinaryHeap {


    public MaxHeap(int[] heap) {
        super(heap);
    }

    /**
     * Makes the array {@param a} satisfy the max heap property starting from
     * {@param index} till the end of array.

     * Time complexity: O(log n).
     *
     * @param index
     */
    public void maxHeapify(int index) {
        int indexWithLargestValue = index;

        int leftIndex = getLeftChildIndex (index);
        int rightIndex = getRightChildIndex(index);

        //Value in index is "Sink down"
        //Checking if value in left child larger - updating indexWithLargestValue accordingly
        if ( (leftIndex < size) &&( heap[index] < heap[leftIndex]) ) {
            indexWithLargestValue = leftIndex;
        }
        //Checking if value in right child larger - updating indexWithLargestValue accordingly
        if ( (rightIndex < size) && (heap[indexWithLargestValue] < heap[rightIndex]) ) {
            indexWithLargestValue = rightIndex;
        }

        //index need to be swapped with child with max value
        if (indexWithLargestValue != index) {
            //If current index does not have largest value - swap with largest value
           swap(index, indexWithLargestValue);

            //calling maxHeapify with the index of the  child (that was larger then its parent before swap)
            maxHeapify(indexWithLargestValue);
        }
    }

    /**
     * Converts array {@param a} in to a max heap.
     * <p/>
     * Time complexity: O(n) and is not O(n log n).
     */
    public void buildMaxHeap() {
        // System.out.println("Start buildMinHeap.Heap size" + size);
        int lastIndex = size - 1;
        //From bottom most right leaf backward to root
        for (int i = getParentIndex(lastIndex); i >= 0; i--) {
            // System.out.println("Calling maxHeapify from buildMinHeap  with index:" + i);
            maxHeapify(i);
        }
    }

    /**
     * Insert a new element into the heap satisfying the heap property.
     *  Time complexity: O(log n) where 'n' is total no. of elements in heap
     */
    public void insert(int elem) {
        size++;//increasing heap  size

        //copy to new array (with increased size in 1) - padded with zero on last index
        heap = Arrays.copyOf(heap, size);
        int i = size-1;//last leaf
        int parentIndex = getParentIndex(i);
        // move up through the heap till you find the right position
        while (i > 0 && elem > heap[parentIndex]) {
            heap[i] = heap[parentIndex];
            i = parentIndex;
            parentIndex =  getParentIndex(i);//
        }
        //place found - setting the new value
        heap[i] = elem;
    }

    //The max is the root element
    public int findMax() {
        if (size == 0) {
            return -1;
        } else {
            return heap[0];
        }
    }

    //
    public int extractMax() {
        if (size == 0) return -1;

        int max = heap[0];
        //Removing max value from  root and replacing with last index (right leaf)
        heap[0] = heap[size - 1];
        //Reducing heap size
        size--;
        //Performing  heapify from root
        maxHeapify(0);

        //copying the heap without last index
        heap = Arrays.copyOf(heap, size );

        return max;
    }









    // test cases
    public static void main(String[] args) {
        int[] a = new int[]{2, 4, 5, 1, 6, 7, 8};
        MaxHeap maxHeap = new MaxHeap(a);
        System.out.println("Before buildingHeap:");
        maxHeap.printHeap();
        maxHeap.buildMaxHeap();
        System.out.println("After buildingHeap:");
        maxHeap.printHeap();
        maxHeap.extractMax();
        System.out.println("After extractMax:");
        maxHeap.printHeap();
        maxHeap.insert(12);
        System.out.println("After insert 12");
        maxHeap.printHeap();
    }
}