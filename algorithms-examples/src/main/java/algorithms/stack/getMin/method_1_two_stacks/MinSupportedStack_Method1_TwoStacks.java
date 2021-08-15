package algorithms.stack.getMin.method_1_two_stacks;

// Java implementation of SpecialStack
// Note : here we use Stack class for
// Stack implementation

import algorithms.stack.getMin.MinSupportedStack;

import java.util.Stack;

/* A class that supports all the stack operations and one additional
operation getMin() that returns the minimum element from stack at any time.
This class inherits from the stack class and uses
an auxiliary stack that holds minimum elements */

public class MinSupportedStack_Method1_TwoStacks extends Stack<Integer> implements MinSupportedStack {
    private Stack<Integer> minimumsStack = new Stack<>();

    /* SpecialStack's member method to insert an element to it. This method
    makes sure that the min stack is also updated with appropriate minimum values */
    public void push(int valToPush)  {
        if (isEmpty()) {
            super.push(valToPush);
            minimumsStack.push(valToPush);
        }
        else {
            super.push(valToPush);
            int poppedMinimum = minimumsStack.pop();
            minimumsStack.push(poppedMinimum);
            if (valToPush < poppedMinimum) {
                minimumsStack.push(valToPush);
            }
            else {
                    minimumsStack.push(poppedMinimum);
                }
        }
    }

    /* SpecialStack's member method to insert an element to it.
     This method makes sure that the min stack is also
     updated with appropriate minimum values */
    public Integer pop()  {
        int poppedValue = super.pop();
        //Popped also from min stack
        minimumsStack.pop();
        return poppedValue;
    }

    /* SpecialStack's member method to get minimum element from it. */

    public Integer getMinimumsStack(){
        return minimumsStack.peek();
    }

 }

