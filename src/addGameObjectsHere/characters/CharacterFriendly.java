package addGameObjectsHere.characters;

import addResourceLoaderHere.ImageLoader;
import jGameFramework.display.Displayable;
import jGameFramework.physicalObjects.Position;

import java.util.LinkedList;
import java.util.TreeSet;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class CharacterFriendly extends Character {

    /**
     * Constructors
     */
    public CharacterFriendly(CharacterClass characterClass, double experience) {
        super(getBasicStats(characterClass));
    }

    private static CharacterStat getBasicStats(CharacterClass characterClass) {

        switch (characterClass) {
            case fighter:
                return new CharacterStat(75, 5, 10, 0.3, 1, new LinkedList<>());

            case rogue:
                return new CharacterStat(25, 10, 15, 0.3, 2, new LinkedList<>());

            case ranger:
                return new CharacterStat(25, 15, 10, 0.3, 3, new LinkedList<>());
        }

        throw new IllegalArgumentException("The class: " + characterClass + "does not exists");
    }

    /**
     * Returns the image representation of this physical object
     * <p>
     * The ImageObject will have a relative position depending
     * on the position of the CameraWithEdges.
     *
     * @param cameraPosition
     * @param imageLoader
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        return null;
    }


}
