package algorithms.stack.getMin.method_4_UsingNodeClass;

import algorithms.stack.getMin.MinSupportedStack;

import java.util.Stack;

/**
 * create a class node which has two variables val and min.
 * val will store the actual value that we are going to insert in stack
 * , where as min will store the min value SO FAR seen upto that node.
 */

public class MinSupportedStack_Method4_UsingNodeClass implements MinSupportedStack {

    private class Node {

        int val;
        int min;

        private Node(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }

    private Stack<Node> internalStack;

    public MinSupportedStack_Method4_UsingNodeClass() {
        this.internalStack = new Stack<Node>();
    }

    @Override
    public void push(final int valueToAdd) {
        if (internalStack.isEmpty()) {
            this.internalStack.push(new Node(valueToAdd, valueToAdd));
        } else {
            int min = Math.min(this.internalStack.peek().min, valueToAdd);
            this.internalStack.push(new Node(valueToAdd, min));
        }
    }

    @Override
    public Integer pop() {
        if (internalStack.isEmpty()) {
            //Avoiding EmptyStackException - Assuming Null is legit
            return null;
        } else {
            return internalStack.pop().val;
        }
    }

    @Override
    public Integer getMinimum() {
        if (internalStack.isEmpty()) {
            //Avoiding EmptyStackException - Assuming Null is legit
            return null;
        } else {
            return internalStack.pop().min;
        }
    }

}
