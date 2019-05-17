package interview.ex1.shapes;

public class Circle extends Shape {

    public Circle(int value) {
        super(value);
        setName("Circle");
    }

    @Override
    public double calculateFunction() {
        return Math.PI * getValue() * getValue();
    }
}
