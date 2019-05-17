package algorithms.KthLargestElement;

import java.util.Arrays;
import java.util.PriorityQueue;

//PriorityQueue - An unbounded priority queue based on a priority heap
//The elements of the priority queue are ordered according to their natural ordering, or by a Comparator provided at queue construction time

//A priority queue does not permit null elements.

//The head of this queue is the least element with respect to the specified ordering

public class KLargestElementUsingPriorityQueue {

    public static void main(String[] args) {
        int  k = 2;
        int secondLargestElement = findKthLargest ( new int [] {3 , 2, 1 ,5 ,6 ,4} , k);
        System.out.println("2th largest element = " + secondLargestElement);
}

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>(k);
        for(int i: nums){
            //Inserts the specified element into this priority queue.
            q.offer(i);

            //The q size should remain like the size of k
            //Therfore taking out the value of the head - with the least element
            if(q.size()>k){
                //Poll - Retrieves and removes the head of this queue, or returns null if this queue is empty
                q.poll();
            }
        }

        //Peek-Retrieves, but does not remove, the head of this queue,
        // or returns null if this queue is empty.

        //K remain with k elements - least in the head
        System.out.println("After iterations - q contains: " + Arrays.toString(q.toArray()));
        return q.peek();
    }
}
