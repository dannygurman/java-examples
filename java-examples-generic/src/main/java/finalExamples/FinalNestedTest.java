package finalExamples;

import org.junit.Test;

public class FinalNestedTest {

    static class CoordinateSystem {
        private final Point origin = new Point(0, 0);

        public Point getOrigin() { return origin; }
    }

    @Test
    public  void testFinalNested() {
        CoordinateSystem coordinateSystem = new CoordinateSystem();

        //declaring a variable final only means that this variable will point to the same object at any time.
        // The object that the variable points to is not influenced by that final variable though
       coordinateSystem.getOrigin().x = 15;

        assert coordinateSystem.getOrigin().getX() == 15;
    }
}
