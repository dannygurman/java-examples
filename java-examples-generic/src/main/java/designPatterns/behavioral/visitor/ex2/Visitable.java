package designPatterns.behavioral.visitor.ex2;

/**
 * Created by DannyG on 28/01/2015.
 */
//Element interface
public interface Visitable
{
    public void accept(Visitor visitor);
}