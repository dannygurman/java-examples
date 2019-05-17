package designPatterns.behavioral.visitor.ex2;

/**
 * Created by DannyG on 28/01/2015.
 */
//concrete element
public class Book implements Visitable
{
    private double price;
    private double weight;


    private boolean isFree;

    //accept the visitor
    public void accept(Visitor visitor)
    {
       if(!this.isFree )        {
          visitor.visit(this);
        }
    }

    public double getPrice()
    {
        return price;
    }

    public double getWeight()
    {
        return weight;
    }
    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean isFree) {
        this.isFree = isFree;
    }

}
