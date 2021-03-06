package addGameObjectsHere.all;

import java.util.List;

/**
 * Handling ListIterators was a pain because of the fact that the iterator
 * was always pointing between items and not at them.
 *
 * This iterator points at a specific item and returns it on get().
 *
 * @author Mia Beaudoin-Dion
 */
public class ListIteratorOnObject<E> {

    private int index;
    private List<E> list;

    public ListIteratorOnObject(List<E> list) {
        index = 0;
        this.list = list;
    }

    public E get() {
        return list.get(index);
    }

    public boolean hasNext() {
        return index + 1 < list.size();
    }

    public E next() {
        index += 1;
        return list.get(index);
    }

    public boolean hasPrevious() {
        return index > 0;
    }

    public E previous() {
        index -= 1;
        return list.get(index);
    }

    public int nextIndex() {
        return index + 1;
    }

    public int previousIndex() {
        return index - 1;
    }
}
