package algorithms.stack.getMin.method_1_two_stacks;

// Java implementation of SpecialStack
// Note : here we use Stack class for
// Stack implementation

import algorithms.stack.getMin.MinSupportedStack;

import java.util.Stack;

/* A class that supports all the stack operations and one additional
operation getMin() that returns the minimum element from stack at any time.
This class inherits from the stack class and uses
an auxiliary stack that holds minimum elements

Complexity Analysis:
Time Complexity:
For insert operation: O(1) (As insertion ‘push’ in a stack takes constant time)
For delete operation: O(1) (As deletion ‘pop’ in a stack takes constant time)
For ‘Get Min’ operation: O(1) (As we have used an auxiliary stack which has it’s top as the minimum element)

Auxiliary Space: O(n). Use of auxiliary stack for storing values.

*/

public class MinSupportedStack_Method1_TwoStacks extends Stack<Integer> implements MinSupportedStack {
    protected Stack<Integer> minimumsStack = new Stack<>();

    /* SpecialStack's member method to insert an element to it. This method
    makes sure that the min stack is also updated with appropriate minimum values */
    public void push(int valToPush)  {
        if (isEmpty()) {
            super.push(valToPush);
            minimumsStack.push(valToPush);
        }
        else {
            super.push(valToPush);
            //Peek - Looks at the object at the top of this stack without removing it from the stack.
            int peekedMinimum = minimumsStack.peek();
            if (valToPush < peekedMinimum) {
                minimumsStack.push(valToPush);
            }
            else {
                    minimumsStack.push(peekedMinimum);
                }
        }
    }

    /* SpecialStack's member method to insert an element to it.
     This method makes sure that the min stack is also
     updated with appropriate minimum values */
    public Integer pop()  {
        if (isEmpty()){
            //otherwise  throwing EmptyStackException - Assuming returning null is OK
            return null;
        }
        int poppedValue = super.pop();
        //Popped also from min stack
        minimumsStack.pop();
        return poppedValue;
    }

    /* SpecialStack's member method to get minimum element from it. */

    public Integer getMinimum(){
        if (minimumsStack.isEmpty()){
            //Note - calling peek (and also pop) on empty list will cause throwing EmptyStackException –
            return null;
        }
        return minimumsStack.peek();
    }

 }

