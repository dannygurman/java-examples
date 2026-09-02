package interview.shapesSort.factory;

import interview.shapesSort.shapes.Circle;
import interview.shapesSort.shapes.Shape;
import interview.shapesSort.shapes.Square;

public class ShapeFactory {
    public Shape create(Integer initialValue) {
        if (initialValue % 2 == 0)
            return new Square(initialValue);
        if (initialValue % 2 != 0)
            return new Circle(initialValue);

        throw new RuntimeException("Not supported initial value");
    }


}
