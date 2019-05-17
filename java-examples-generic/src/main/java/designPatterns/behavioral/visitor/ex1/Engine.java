package designPatterns.behavioral.visitor.ex1;

/**
 * Created by DannyG on 28/01/2015.
 */
class Engine implements ICarElement {
    public void accept(ICarElementVisitor visitor) {
        visitor.visit(this);
    }
}
