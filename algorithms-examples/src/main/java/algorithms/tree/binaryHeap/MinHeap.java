package algorithms.tree.binaryHeap;

import algorithms.utils.AlgorithmUtils;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * <p/>
 * A HEAP is a specialized tree-based ABSTRACT DATA TYPE that satisfies the heap property:
 * min-heap: All non-leaf elements are either smaller than or equal to their left and right child.
 * max-heap: All non-leaf elements are either greater than or equal to their left and right child.
 * <p/>
 * Often implemented as an array, where the children of the element at index i are at index
 * 2i+1 (left child) and 2i+2 (right child).
 * <p/>
 * The first element (minimum or maximum, depending on chosen order) can be found in O(1).
 * Each successor can be found in O(log n). The algorithm in minHeapify() takes O(log n) time
 * Therefore, buildMinHeap() would take O(n log n) time BUT IF OBSERVED CAREFULLY IT TAKES 0(N) TIME.
 * <p/>
 * Used in the HeapSort algorithm. Also can be used to implement a PriorityQueue.
 *
 * @author: ramswaroop
 * @date: 8/2/15
 * @time: 11:57 AM
 * @see: http://staff.ustc.edu.cn/~csli/graduate/algorithms/book6/chap07.htm
 */
public class MinHeap extends BinaryHeap {




    public MinHeap(int[] heap) {
        super(heap);
    }

    /**
     * Makes the array   satisfy the min heap property starting from
     * {@param index} till the end of array (last leaf).
     * Time complexity: O(log n).
     *
     * @param index
     */
    public void minHeapify(int index) {
        int indexWithSmallestValue = index;
        int leftIndex = getLeftChildIndex (index);
        int rightIndex = getRightChildIndex(index);

        //Value in index is "Sink down"
        //Checking if value in left child smaller - updating indexWithSmallestValue accordingly
        if ( (leftIndex < size) && (heap[index] > heap[leftIndex]) ) {
            indexWithSmallestValue = leftIndex;
        }
        if ( (rightIndex < size) && (heap[indexWithSmallestValue] > heap[rightIndex]) ) {
            indexWithSmallestValue = rightIndex;
        }

        if (indexWithSmallestValue != index) {
            //If current index does not have smallest value
            swap( index, indexWithSmallestValue);

            //calling minHeapify with the index of the  child (that was smaller then its parent before swap)
            minHeapify(indexWithSmallestValue);
        }
        //System.out.println("End minHeapify.Heap:");
       // printHeap();
    }

    public void replaceValueAndMinHeapify(int index , int newVal) {
        heap[index] = newVal;
        minHeapify(index);
    }


    /**
     * Converts array {@param a} in to a min heap.
     * <p/>
     * Time complexity: O(n) and is not O(n log n).
     */
    public void buildMinHeap() {
       // System.out.println("Start buildMinHeap.Heap size" + size);
        int lastIndex = size - 1;//bottom most right leaf
        //From bottom most right leaf backward to root
        for (int i = getParentIndex(lastIndex); i >= 0; i--) {
           // System.out.println("Calling MinHeapify from buildMinHeap  with index:" + i);
            minHeapify(i);
        }
    }




    public void insert(int elem) {
        size++;//increasing heap  size

        //copy to new array (with increased size in 1) - padded with zero on last index
        heap = Arrays.copyOf(heap, size);

        int i = size-1;//last leaf
        int parentIndex = getParentIndex(i);
        // move up through the heap till you find the right position
        while (i > 0 && elem < heap[parentIndex]) {
            heap[i] = heap[parentIndex];
            i = parentIndex;
            parentIndex =  getParentIndex(i);//
        }
        //place found - setting the new value
        heap[i] = elem;

    }


    //The min is the root element
    public int findMin() {
        if (size == 0) {
            return -1;
        } else {
            return heap[0];
        }
    }

    //
    public int extractMin() {
        if (size == 0) return -1;

        int min = heap[0];
        //Removing min value from  root and replacing with last index (right leaf)
        heap[0] = heap[size - 1];
        //Reducing heap size
        size--;
        //Performing  heapify from root
        minHeapify(0);

        //copying the heap without last index
        heap = Arrays.copyOf(heap, size );

        return min;
    }




    // test cases
    public static void main(String[] args) {
        int[] a = new int[]{2, 4, 5, 1, 6, 7, 8};
        MinHeap minHeap = new MinHeap(a);
        System.out.println("Before buildingHeap:");
        minHeap.printHeap();
        minHeap.buildMinHeap();
        System.out.println("After buildingHeap:");
        minHeap.printHeap();
        minHeap.extractMin();
        System.out.println("After extractMin:");
        minHeap.printHeap();
        minHeap.insert(0);
        System.out.println("After insert 0:");
        minHeap.printHeap();
    }
}