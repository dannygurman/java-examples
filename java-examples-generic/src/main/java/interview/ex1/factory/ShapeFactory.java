package interview.ex1.factory;

import interview.ex1.shapes.Circle;
import interview.ex1.shapes.Shape;
import interview.ex1.shapes.Square;

public class ShapeFactory {
    public Shape create(Integer initialValue) {
        if (initialValue % 2 == 0)
            return new Square(initialValue);
        if (initialValue % 2 != 0)
            return new Circle(initialValue);

        throw new RuntimeException("Not supported initial value");
    }


}
