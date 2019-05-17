/*****************************************************************/
/* Copyright 2013 Code Strategies                                */
/* This code may be freely used and distributed in any project.  */
/* However, please do not remove this credit if you publish this */
/* code in paper or electronic form, such as on a web site.      */
/*****************************************************************/

package designPatterns.structural.flyweight.ex2;

import java.util.HashMap;
import java.util.Map;

public class FlyweightFactory {

	private static FlyweightFactory flyweightFactory;

	private Map<String, Flyweight> flyweightPool;

	private FlyweightFactory() {
		flyweightPool = new HashMap<String, Flyweight>();
	}

	public static FlyweightFactory getInstance() {
		if (flyweightFactory == null) {
			flyweightFactory = new FlyweightFactory();
		}
		return flyweightFactory;
	}

	public Flyweight getFlyweight(String key) {
		if (flyweightPool.containsKey(key)) {
			return flyweightPool.get(key);
		} else {
			Flyweight flyweight;
			if ("add".equals(key)) {
				flyweight = new FlyweightAdder();
			} else {
				flyweight = new FlyweightMultiplier();
			}
			flyweightPool.put(key, flyweight);
			return flyweight;
		}
	}

}
