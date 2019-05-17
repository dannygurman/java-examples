package designPatterns.behavioral.command.ex2;

/**
 * Created by Gurman on 22/04/2016.
 */
//Use the Broker class to take and execute commands

    //Result
//Stock [ Name: ABC, Quantity: 10 ] bought
//Stock [ Name: ABC,  Quantity: 10 ] sold

public class CommandPatternDemo {
    public static void main(String[] args) {
        Stock abcStock = new Stock();

        BuyStock buyStockOrder = new BuyStock(abcStock);
        SellStock sellStockOrder = new SellStock(abcStock);

        Broker broker = new Broker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);

        broker.placeOrders();
    }
}

