package designPatterns.behavioral.visitor.ex1;

/**
 * Created by DannyG on 28/01/2015.
 */
public class VisitorDemo {
    public static void main(String[] args) {
        ICarElement car = new Car();
        car.accept(new CarElementPrintVisitor());
        car.accept(new CarElementDoVisitor());
    }
   /* //Output:
    // Visiting front left wheel
    Visiting front right wheel
    Visiting back left wheel
    Visiting back right wheel
    Visiting body
    Visiting engine
    Visiting car

    Kicking my front left wheel
    Kicking my front right wheel
    Kicking my back left wheel
    Kicking my back right wheel
    Moving my body
    Starting my engine
    Starting my car*/
}