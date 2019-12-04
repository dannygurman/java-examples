package openClosePrinciple.openCloseAbides;

public class Circle2 extends Shape2 {
    int radius = 0;

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
         return radius * radius * Math.PI;
    }
}
