package addGameObjectsHere.controller.windows;

import addGameObjectsHere.model.characters.Adventurer;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.Position;

import java.util.TreeSet;

/**
 * A type of form which takes an adventurer and shows information about that adventurer.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class AdventurerForm extends Form{

    private Adventurer adventurer;

    /**
     * Constructor
     */
    public AdventurerForm(Adventurer adventurer, BoundingArea boundingArea, DisplayableDepth depth) {
        super(boundingArea, depth);

        this.adventurer = adventurer;
    }

    @Override
    protected TreeSet<Displayable> getInfoImages(Position cameraPosition, ImageLoader imageLoader) {
        return null;
    }

}
