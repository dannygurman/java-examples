package designPatterns.behavioral.visitor.ex1;

/**
 * Created by DannyG on 28/01/2015.
 */
interface ICarElement {
    void accept(ICarElementVisitor visitor); // CarElements have to provide accept().
}