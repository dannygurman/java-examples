package designPatterns.behavioral.command.ex2;

/**
 * Created by Gurman on 22/04/2016.
 */
import java.util.ArrayList;
import java.util.List;

//command invoker class.
public class Broker {
    private List<Order> orderList = new ArrayList<Order>();

    public void takeOrder(Order order){
        orderList.add(order);
    }

    public void placeOrders(){

        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}