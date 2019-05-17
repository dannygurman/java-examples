package designPatterns.creational.factory.factoryMethod.ex1.store;


import designPatterns.creational.factory.factoryMethod.ex1.pizza.ChicagoStyleCheesePizza;
import designPatterns.creational.factory.factoryMethod.ex1.pizza.ChicagoStyleVeggiePizza;
import designPatterns.creational.factory.factoryMethod.ex1.pizza.Pizza;

public class ChicagoPizzaStore extends PizzaStore {

	Pizza createPizza(String item) {
        	if (item.equals("cheese")) {
            		return new ChicagoStyleCheesePizza();
        	} else if (item.equals("veggie")) {
        	    	return new ChicagoStyleVeggiePizza();
//        	} else if (item.equals("clam")) {
//        	    	return new ChicagoStyleClamPizza();
//        	} else if (item.equals("pepperoni")) {
//            		return new ChicagoStylePepperoniPizza();
        	} else return null;
	}
}
