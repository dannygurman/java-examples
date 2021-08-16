package algorithms.stack.getMin.method_3_minElementVariable;

import algorithms.stack.getMin.MinSupportedStack;

import java.util.Stack;

/*Java program to implement a stack that supports getMinimum() n addition to push() and pop()
in O(1)time and O(1) (!) extra space.*/

public class MinSupportedStack_Method3_MinElementVariable implements MinSupportedStack {

    private Stack<Integer> internalStack;

    //stores the minimum element in the stack.
    private Integer minEle;


    // Constructor
    public  MinSupportedStack_Method3_MinElementVariable() {
        internalStack = new Stack<>();
    }

    @Override
    public void push(final int newVal) {
        if (internalStack.isEmpty()) {
            minEle = newVal;
            internalStack.push(newVal);
            return;
        }
        // If new number is less than original minEle
        if (newVal < minEle) {
            internalStack.push(2 * newVal - minEle);
            minEle = newVal;
        } else {
            internalStack.push(newVal);
        }
    }

    @Override
    public Integer pop() {
        if (internalStack.isEmpty()) {
            System.out.println("Stack is empty");
            return null;//Assuming is OK to return Null - Alternative Optional<Integer>
        }
        //removing Top Most Element
        Integer poppedElement = internalStack.pop();

        // Minimum will change as the minimum elementof the stack is being removed.
        if (poppedElement < minEle) {
            minEle = 2 * minEle - poppedElement;
        }
        return poppedElement;
    }

    @Override
    // Get minimum element
    public Integer getMinimum() {
        if (internalStack.isEmpty()) {
            System.out.println("Note - stack is empty (null is returned)");
        }
        return minEle;
    }

}