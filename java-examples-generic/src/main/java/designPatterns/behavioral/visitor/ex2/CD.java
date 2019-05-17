package designPatterns.behavioral.visitor.ex2;


/**
 * Created by DannyG on 28/01/2015.
 */
public class CD implements  Visitable {

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
