package algorithms.stack.getMin;

import algorithms.stack.getMin.method_1_two_stacks.MinSupportedStack_Method1_TwoStacks;
import algorithms.stack.getMin.method_2_two_stacks_improved.MinSupportedStack_Method2_TwoStacksImproved;
import algorithms.stack.getMin.method_3_minElementVariable.MinSupportedStack_Method3_MinElementVariable;
import org.junit.Assert;
import org.junit.Test;

public class MinSupportedStackTest {

    @Test
    public void test_MinSupportedStack_Method1_Using2Stacks() {
        MinSupportedStack stack = new MinSupportedStack_Method1_TwoStacks();
        testMinSupportedStackInternal(stack);
    }

    @Test
    public void test_MinSupportedStack_Method2_Using2Stacks_Improved() {
        MinSupportedStack stack = new MinSupportedStack_Method2_TwoStacksImproved();
        testMinSupportedStackInternal(stack);
    }

    @Test
    public void test_MinSupportedStack_Method3_UsingMinElementVariable() {
        MinSupportedStack stack = new MinSupportedStack_Method3_MinElementVariable();
        testMinSupportedStackInternal(stack);
    }



    private void testMinSupportedStackInternal(MinSupportedStack stack) {
        int minValueA = 10;
        int minValueB = 5;

        stack.push(minValueA);
        stack.push(20);
        stack.push(30);
        long foundMin = stack.getMinimum();
        Assert.assertEquals("Test get min 1", minValueA, foundMin);

        stack.push(minValueB);
        foundMin = stack.getMinimum();
        Assert.assertEquals("Test get min 2", minValueB, foundMin);

        stack.pop();
        foundMin = stack.getMinimum();
        Assert.assertEquals("Test get min 3", minValueA, foundMin);

    }
}
