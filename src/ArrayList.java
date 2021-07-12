import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ArrayList<T> implements List<T> {

    T[] arr;
    int size;


    @SuppressWarnings("hiding")
    private class ListIterator<T> implements Iterator<T> {

        private int nextItem;

        public ListIterator() {
            nextItem = 0;
        }

        @Override
        public boolean hasNext() {
            if (nextItem < size)
                return true;
            return false;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            return (T) arr[nextItem++];
        }

    }


    @SuppressWarnings("unchecked")
    public ArrayList() {
        size = 0;
        arr = (T[]) new Object[10];
    }

    public Iterator<T> iterator() {
        return new ListIterator<T>();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    private void growArray() {
        @SuppressWarnings("unchecked")
        T[] newarr = (T[]) new Object[arr.length * 2];
        for (int i = 0; i < arr.length; i++)
            newarr[i] = arr[i];
        arr = newarr;
    }

    @Override
    public boolean add(T item) {  // Best: O(1); Worst: O(1) -- "amortised"?
        if (size == arr.length)  // Array is full, so double the array
            growArray();
        arr[size++] = item;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public void add(int pos, T item) {  // Best: O(1); Worst: O(n)
        if (pos < 0 || pos > size - 1)
            try {
                throw new Exception("List index out of bounds");
            } catch (Exception e) {
                e.printStackTrace();
            }
        if (size == arr.length)
            growArray();
        for (int i = size; i > pos; i--) {
            arr[i] = arr[i - 1];
        }
        arr[pos] = item;
        ++size;
    }

    @Override
    public T get(int pos) {  // // O(1)
        if (pos < 0 || pos > size - 1)
            try {
                throw new Exception("List index out of bounds");
            } catch (Exception e) {
                e.printStackTrace();
            }
        return arr[pos];
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public T remove(int pos) {  // Best O(1), worst O(n)
        if (pos < 0 || pos > size - 1)
            try {
                throw new Exception("List index out of bounds");
            } catch (Exception e) {
                e.printStackTrace();
            }
        T item = arr[pos];
        for (int i = pos; i < size - 1; i++)
            arr[i] = arr[i + 1];
        --size;
        return item;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public java.util.ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public java.util.ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public int size() {  // Best/worst: O(1)

        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }
    public String test(){
        return "hi";
    }
}