package jGameFramework.core;

import jGameFramework.display.Displayable;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeImageSet {

    private SortedSet<Displayable> setOfImages;
    private ReadWriteLock rwLock;

    public ThreadSafeImageSet() {
        setOfImages = Collections.synchronizedSortedSet(new TreeSet<>());
        rwLock = new ReentrantReadWriteLock();
    }

    public void startReading() {
        rwLock.readLock().lock();
    }

    public SortedSet<Displayable> read() {
        return setOfImages;
    }

    public void stopReading() {
        rwLock.readLock().unlock();
    }

    public void write(SortedSet<Displayable> desiredImages) {
        rwLock.writeLock().lock();
        setOfImages.clear();
        setOfImages.addAll(desiredImages);
        rwLock.writeLock().unlock();
    }

}
