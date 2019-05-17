
package designPatterns.creational.prototype.ex2;

// Prototype pattern

//This pattern creates the kind of object using its prototype. 
//In other words, while creating the object of Prototype object, 
//the class actually creates a clone of it and returns it as prototype.

//You can see here, we have used Clone method to clone the prototype when required.

 
    public abstract class Prototype {
		public abstract Prototype clone();
	}

