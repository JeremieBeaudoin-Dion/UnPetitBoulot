package addGameObjectsHere.menus;

import jGameFramework.display.Displayable;

import java.util.TreeSet;

/**
 * Objects which appear in a menu
 *
 * @author Jérémie Beaudoin-Dion
 */
interface MenuObject {

    TreeSet<Displayable> getImageRepresentation();

}
