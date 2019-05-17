package compareSort;

import java.util.Arrays;

/**
 * Created by dannyg on 01/08/2016.
 */
public class ArraySortStringExample {

    public static void main(String[] args) {
        String[] fruits = new String[] {"Pineapple","Apple", "Orange", "Banana"};

        Arrays.sort(fruits);

        int i=0;
        for(String temp: fruits){
            System.out.println("fruits " + ++i + " : " + temp);
        }
    }
}
