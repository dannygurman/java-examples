package algorithms.stack.getMin;

import algorithms.stack.getMin.method_1_two_stacks.MinSupportedStack_Method1_TwoStacks;
import org.junit.Assert;
import org.junit.Test;

public class MinSupportedStackTest {

    @Test
    public void test_MinSupportedStack_Method1_Using2Stacks() {
        MinSupportedStack stack = new MinSupportedStack_Method1_TwoStacks();
        testMinSupportedStackInternal(stack);

    }

    private void testMinSupportedStackInternal(MinSupportedStack stack){
        int minValueA = 10;
        int minValueB = 5;

        stack.push(minValueA);
        stack.push(20);
        stack.push(30);
        long foundMin = stack.getMinimumsStack();
        Assert.assertEquals("Test get min 1", minValueA, foundMin);

        stack.push(minValueB);
        foundMin = stack.getMinimumsStack();
        Assert.assertEquals("Test get min 2", minValueB, foundMin);

        stack.pop();
        foundMin = stack.getMinimumsStack();
        Assert.assertEquals("Test get min 3", minValueA, foundMin);


    }
}
