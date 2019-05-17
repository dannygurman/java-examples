package designPatterns.behavioral.visitor.ex1;

/**
 * Created by DannyG on 28/01/2015.
 */
interface ICarElementVisitor {
    void visit(Wheel wheel);
    void visit(Engine engine);
    void visit(Body body);
    void visit(Car car);
}