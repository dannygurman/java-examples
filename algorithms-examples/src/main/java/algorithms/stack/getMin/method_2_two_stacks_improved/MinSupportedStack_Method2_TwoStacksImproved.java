package algorithms.stack.getMin.method_2_two_stacks_improved;

import algorithms.stack.getMin.MinSupportedStack;
import algorithms.stack.getMin.method_1_two_stacks.MinSupportedStack_Method1_TwoStacks;

import java.util.Stack;

/*
Space Optimized Version
The Method 1  approach can be optimized.
We can limit the number of elements in the auxiliary stack.
We can push only when the incoming element of the main stack
is smaller than or EQUAL to the top of the auxiliary stack.

Similarly during pop, if the pop-off element EQUAL to the top of the auxiliary stack,
remove the top element of the auxiliary stack.

Complexity Analysis:

Time Complexity:
For Insert operation: O(1) (As insertion ‘push’ in a stack takes constant time)
For Delete operation: O(1) (As deletion ‘pop’ in a stack takes constant time)
For ‘Get Min’ operation: O(1) (As we have used an auxiliary which has it’s top as the minimum element)

Auxiliary Space: O(n).
The complexity in the worst case is the same as above(!) but in other cases,
it will take SLIGHTLY less space than the above approach as repetition is neglected.


*/

public class MinSupportedStack_Method2_TwoStacksImproved extends MinSupportedStack_Method1_TwoStacks {

    /* SpecialStack's member method to  insert an element to it.
    This method makes sure that the min stack is also updated with appropriate minimum values */
    @Override
    public void push(int valToPush) {
        if (isEmpty()) {
            super.push(valToPush);
            minimumsStack.push(valToPush);
        } else {
            super.push(valToPush);

            //Peek - Looks at the object at the top of this stack without removing it from the stack.
            int peekedMinimum = minimumsStack.peek();

            //* push only when the incoming element of main stack is smaller
            //than OR EQUAL to top of auxiliary stack
            //My comment- Including equal so when popping we could now if the popped item is the minimal *
            if (valToPush <= peekedMinimum) {
                minimumsStack.push(valToPush);
            }
        }
    }

    /* SpecialStack's member method to insert an element to it.
     This method makes sure that the min stack is also
     updated with appropriate minimum values */
    public Integer pop() {
        int poppedValue = super.pop();
        //Popped also from min stack
        int poppedMinimum = minimumsStack.pop();
        // Push the poppedMinimum  back only if it is not equal to poppedValue !

        if (poppedMinimum != poppedValue) {
            minimumsStack.push(poppedMinimum);
        }
        return poppedValue;
    }

}
