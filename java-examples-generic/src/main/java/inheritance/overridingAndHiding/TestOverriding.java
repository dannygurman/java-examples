package inheritance.overridingAndHiding;

/**The ability of a subclass to override a method allows a class to inherit from a superclass
 whose behavior is "close enough" and then to modify behavior as needed.
 The overriding method has the same name, number and type of parameters,
 and return type as the method it overrides.

 An overriding method can also return a subtype of the type returned by the overridden method.
 This is called a covariant return type.

When overriding a method, you might want to use the @Override annotation that instructs the compiler
that you intend to override a method in the superclass.
If, for some reason, the compiler detects that the method does not exist in one of the superclasses,
 it will generate an error.


If a subclass defines a class (static) method with the same signature as a class method in the superclass,
 the method in the subclass HIDES the one in the superclass.

The distinction between hiding and overriding has important implications.
The version of the overridden method that gets invoked is the one in the subclass.
 
 The version of the hidden method that gets invoked depends on whether it is invoked
  from the superclass or the subclass
**/
public class TestOverriding {

	public static void main(String[] args) {
		Cat myCat = new Cat();
		Animal myAnimal = myCat;
        System.out.println("1");
		Animal.testClassMethod ();
        System.out.println("2");
        Cat.testClassMethod();
        System.out.println("3");
        myAnimal.testInstanceMethod();

	}

}

//Output:
//The class method in Animal.
//The instance method in Cat.