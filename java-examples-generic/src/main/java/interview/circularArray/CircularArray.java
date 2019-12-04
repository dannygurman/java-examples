
package interview.circularArray;
import java.util.Iterator;

public class CircularArray<T>  implements Iterable<T> {
    private T[] items;
    private int head = 0;

    public CircularArray(int size) {
        items = (T[]) new Object[size];
    }

    private int convertToInternalIndex(int index) {
        if (index < 0) {
            index += items.length;
        }
        return (head + index) % items.length;
    }

    public void rotate(int shiftRight) {
        head = convertToInternalIndex(shiftRight);
    }

    public T get(int i) {
        if (i < 0 || i >= items.length) {
            throw new java.lang.ArrayIndexOutOfBoundsException("...");
        }
        return items[convertToInternalIndex(i)];
    }

    public void set(int i, T item) {
        items[convertToInternalIndex(i)] = item;
    }

    public Iterator<T> iterator() {
        return new CircularArrayIterator<T>(this);
    }

    private class CircularArrayIterator<TI> implements Iterator<TI> {
        /* current reflects the offset from the rotated head, not
         * from the actual start of the raw array. */
        private int _current = -1;
        private TI[] _items;

        public CircularArrayIterator(CircularArray<TI> array) {
            _items = array.items;
        }

        @Override
        public boolean hasNext() {
            return _current < items.length - 1;
        }

        @Override
        public TI next() {
            _current++;
            TI item = (TI) _items[convertToInternalIndex(_current)];
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("...");
        }

    }
}