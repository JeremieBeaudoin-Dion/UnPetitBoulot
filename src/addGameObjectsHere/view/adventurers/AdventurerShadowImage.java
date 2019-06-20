package addGameObjectsHere.view.adventurers;

import addGameObjectsHere.view.gameObjects.ObjectShadowImageMoving;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableImage;
import jGameFramework.physicalObjects.Position;

import java.util.List;
import java.util.TreeSet;

/**
 * Adventurer image representation. See Object Shadow image for more information.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class AdventurerShadowImage extends ObjectShadowImageMoving {

    private static final Position SIZE = new Position(0, 0);
    private static final Position OFFSET = new Position(0, 0);

    /**
     * Constructor
     */
    public AdventurerShadowImage(Adventurer parentObject) {
        super(parentObject, SIZE, OFFSET);
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        Adventurer parentObject = (Adventurer) this.getParentObject();

        images.add(new DisplayableImage(this, cameraPosition,
                imageLoader.getAdventurerImage(parentObject.getAdventurerImageID())));

        return images;
    }

    /**
     * Returns any action that the GameThread should handle
     */
    @Override
    public List<GameEvent> getAction() {
        return null;
    }

    /**
     * Returns true if the object should be disposed of
     */
    @Override
    public boolean dispose() {
        return false;
    }
}
