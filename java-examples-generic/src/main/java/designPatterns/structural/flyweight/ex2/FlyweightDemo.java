/*****************************************************************/
/* Copyright 2013 Code Strategies                                */
/* This code may be freely used and distributed in any project.  */
/* However, please do not remove this credit if you publish this */
/* code in paper or electronic form, such as on a web site.      */
/*****************************************************************/

package designPatterns.structural.flyweight.ex2;

public class FlyweightDemo {

	public static void main(String[] args) {

		FlyweightFactory flyweightFactory = FlyweightFactory.getInstance();

		for (int i = 0; i < 5; i++) {
			Flyweight flyweightAdder = flyweightFactory.getFlyweight("add");
			flyweightAdder.doMath(i, i);

			Flyweight flyweightMultiplier = flyweightFactory.getFlyweight("multiply");
			flyweightMultiplier.doMath(i, i);
		}
	}
}
