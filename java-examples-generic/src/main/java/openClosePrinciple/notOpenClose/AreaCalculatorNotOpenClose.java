package openClosePrinciple.notOpenClose;

public class AreaCalculatorNotOpenClose {
  /**
    AreaCalculator isn’t closed for modification as we need to change it in order to
   extend it. Or in other words: it isn’t open for extension.
     */

    public double getArea(Shape1[] shapes){
        double area = 0;
        for (Shape1 shape:  shapes){
            if (shape instanceof Rectangle1) {
                Rectangle1 rectangle = (Rectangle1) shape;
                area += rectangle.width * rectangle.height;

            }
            else
            {
                Circle1 circle = (Circle1)shape;
                area += circle.radius * circle.radius * Math.PI;
            }
        }

        return area;
    }
}
