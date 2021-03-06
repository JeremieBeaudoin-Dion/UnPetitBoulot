package jGameFramework.core;

import addResourceLoaderHere.GameThreadID;

/**
 * A Loader is a class who has the method get(GameThreadID gameThreadID)
 * that returns the necessary objects for the game to run the desired
 * GameThread
 *
 * @author Mia Beaudoin-Dion
 */
public interface Loader<T> {

    /**
     * Necessary method to return the desired resources for
     * the game to load a GameThread.
     */
    T get(GameThreadID gameThreadID);

}
