package designPatterns.creational.prototype.ex2;

/**
 * Created by DannyG on 26/01/2015.
 */
public class ConcretePrototype1 extends Prototype {
    @Override
    public Prototype clone() {
        return this.clone();
    }
}