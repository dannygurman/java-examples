package designPatterns.behavioral.command.ex2;

/**
 * Created by Gurman on 22/04/2016.
 */
//a request class.
public class Stock {

    private String name = "ABC";
    private int quantity = 10;

    public void buy(){
        System.out.println("Stock [ Name: "+name+", Quantity: " + quantity +" ] bought");
    }
    public void sell(){
        System.out.println("Stock [ Name: "+name+",  Quantity: " + quantity +" ] sold");
    }
}
