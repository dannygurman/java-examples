package designPatterns.behavioral.command.ex2;

/**
 * Created by Gurman on 22/04/2016.
 */
//Concrete classes implementing the Order interface.
public class BuyStock implements Order {
    private Stock abcStock;

    public BuyStock(Stock abcStock){
        this.abcStock = abcStock;
    }

    public void execute() {
        abcStock.buy();
    }
}