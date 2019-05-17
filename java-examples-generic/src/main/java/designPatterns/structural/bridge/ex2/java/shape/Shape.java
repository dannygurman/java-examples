package designPatterns.structural.bridge.ex2.java.shape;

import designPatterns.structural.bridge.ex2.java.color.Color;

/**
 * Created by dannyg on 1/7/2018.
 */
public abstract class Shape {
    //Composition - implementor
    protected Color color;

    //constructor with implementor as input argument
    public Shape(Color c){
        this.color=c;
    }

    abstract public void applyColor();
}