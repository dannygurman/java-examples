package generics.ex1;

/**
 * Generic version of the Box class.
 * @param <T> the type of the value being boxed
 * E - Element (used extensively by the Java Collections Framework)
K - Key
N - Number
T - Type
V - Value
S,U,V etc. - 2nd, 3rd, 4th types
 */
public class Box<T> {
	
    public Box(T t) {		
		this.t = t;
	}
	// T stands for "Type"
    private T t;

    public void set(T t) { this.t = t; }
    public T get() { return t; }
}
