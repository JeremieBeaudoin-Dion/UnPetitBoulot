package jGameFramework.collections;

import jGameFramework.display.Displayable;

import java.util.TreeSet;

/**
 * Helper method for methods used frequently.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class TreesetHelper {

    public static void addAllIfNotNull(TreeSet<Displayable> desiredSet, TreeSet<Displayable> setToAdd){
        if (setToAdd != null && !setToAdd.isEmpty()){
            desiredSet.addAll(setToAdd);
        }
    }

}
