package designPatterns.structural.flyweight.ex1;

/**
 * Created by DannyG on 08/01/2015.
 */
public class Tree {

    //id is x and y.
    public int x;
    public int y;

    public int age;

    // no getters/setters this time...

    public Tree(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Tree(int x, int y, int age) {
        this(x,y);
        this.age = age;
    }

    public void show() {
        System.out.println("<tree>");
        System.out.println(" x is:"+x);
        System.out.println(" y is:"+y);
        System.out.println(" age is:"+age);
        System.out.println("</tree>");
    }



}