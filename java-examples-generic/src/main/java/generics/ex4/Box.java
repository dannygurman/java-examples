package generics.ex4;

/*There may be times when you want to restrict the types that can be used as type arguments 
in a parameterized type. For example,
a method that operates on numbers might only 
want to accept instances of Number or its subclasses. 
This is what bounded type parameters are for.
*/

public class Box<T> {

    private T t;          

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public <U extends Number> void inspect(U u){
        System.out.println("T: " + t.getClass().getName());
        System.out.println("U: " + u.getClass().getName());
    }

    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<Integer>();
        integerBox.set(new Integer(10));
        integerBox.inspect(new Integer(11));
       // integerBox.inspect("some text"); // error: this is still String!
    }
}
