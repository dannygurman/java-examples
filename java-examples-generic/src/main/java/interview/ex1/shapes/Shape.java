package interview.ex1.shapes;

import java.text.DecimalFormat;

public abstract class Shape {

    private int value;

    private String name;

    public Shape(int value) {
        setValue(value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract double calculateFunction();

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.#");
        return value + "," + name + "," + df.format(calculateFunction());
    }

}
