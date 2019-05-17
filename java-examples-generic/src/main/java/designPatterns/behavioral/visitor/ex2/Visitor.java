package designPatterns.behavioral.visitor.ex2;

/**
 * Created by DannyG on 28/01/2015.
 */
public interface Visitor
{
    public void visit(Book book);
    //visit other concrete items
    public void visit(CD cd);
    public void visit(DVD dvd);
}
