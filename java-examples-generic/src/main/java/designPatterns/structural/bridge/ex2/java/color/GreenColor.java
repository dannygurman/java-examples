package designPatterns.structural.bridge.ex2.java.color;

import designPatterns.structural.bridge.ex2.java.color.Color;

/**
 * Created by dannyg on 1/7/2018.
 */
public class GreenColor implements Color {

    public void applyColor(){
        System.out.println("green.");
    }
}