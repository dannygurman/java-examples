package algorithms.tree.binaryHeap;

import algorithms.utils.AlgorithmUtils;

import java.util.Arrays;

public abstract class BinaryHeap {
//fix git
    protected int[] heap;
    protected int size;

    protected  BinaryHeap(int[] heap) {
        this.size = heap.length;
        this.heap = Arrays.copyOf(heap, size);
    }

    protected int getLeftChildIndex (int i){
        return 2 * i + 1;
    }

    protected int getRightChildIndex (int i){
        return 2 * i + 2;
    }

    protected  int getParentIndex (int index) {
        return  (int) Math.floor( (index- 1) / 2);
    }

    public int getSize() {
        return size;
    }

    public int[] getHeap() {
        return heap;
    }

    protected void swap (int index1 , int index2) {
        AlgorithmUtils.swap(heap,  index1 ,  index2);
    }


    public void printHeap() {
        if (heap == null)
            System.out.print("null");

        int i;
        if (size == 0)
            System.out.print("[]");

        StringBuilder b = new StringBuilder();
        b.append('[');
        for ( i = 0; i < size - 1; i++) {
            b.append(heap[i]);
            b.append(", ");
        }
        System.out.println(b.append(heap[i]).append(']').toString());
    }


}
