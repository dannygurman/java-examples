package designPatterns.behavioral.visitor.ex1;

/**
 * Created by DannyG on 28/01/2015.
 */
class CarElementPrintVisitor implements ICarElementVisitor {
    public void visit(Wheel wheel) {
        System.out.println("Visiting " + wheel.getName() + " wheel");
    }

    public void visit(Engine engine) {
        System.out.println("Visiting engine");
    }

    public void visit(Body body) {
        System.out.println("Visiting body");
    }

    public void visit(Car car) {
        System.out.println("Visiting car");
    }
}