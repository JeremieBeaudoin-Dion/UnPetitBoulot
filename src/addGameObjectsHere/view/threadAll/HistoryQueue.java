package addGameObjectsHere.view.threadAll;

import javafx.util.Pair;

import java.util.*;

/**
 * @author Mia Beaudoin-Dion
 */
public class HistoryQueue<K, V> {

    private int maxSize;

    private LinkedList<Pair<K, V>> allEntries;

     public HistoryQueue(int maxSize) {
        allEntries = new LinkedList<>();

        this.maxSize = maxSize;
    }

    public void add(K key, V value) {
        if (allEntries.size() == maxSize) {
            allEntries.removeLast();
        }

        allEntries.addFirst(new Pair<>(key, value));
    }

    public V get(K key) {
        for (Pair<K, V> entry : allEntries) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }

        throw new IllegalStateException("Element : " + key + " does not exists and should not be fetched.");
    }

    public boolean exists(K key) {
        for (Pair<K, V> entry : allEntries) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }

        return false;
    }

}
