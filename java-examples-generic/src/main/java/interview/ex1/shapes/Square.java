package interview.ex1.shapes;

public class Square extends Shape {

    public Square(int value) {
        super(value);
        setName("Square");
    }

    @Override
    public double calculateFunction() {
        return getValue() * getValue();
    }
}
