package addGameObjectsHere.view.threadInn.characters;

import addGameObjectsHere.view.threadInn.other.ObjectShadowImageMoving;
import addResourceLoaderHere.DepthHandler;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableImage;
import jGameFramework.physicalObjects.Position;

import java.util.List;
import java.util.TreeSet;

/**
 * ClientPhysicalObject image representation. See Object Shadow image for more information.
 *
 * @author Mia Beaudoin-Dion
 */
public class CharacterShadowImage extends ObjectShadowImageMoving {

    private CharacterObjectMovingWithShadow parentObject;

    /**
     * Constructor
     */
    public CharacterShadowImage(CharacterObjectMovingWithShadow parentObject, Position size, Position offset) {
        super(parentObject, size, offset, DepthHandler.ADVENTURER_SHADOW_DEPTH);

        this.parentObject = parentObject;
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        CharacterObjectMovingWithShadow parentObject = (CharacterObjectMovingWithShadow) this.getParentObject();

        images.add(new DisplayableImage(this, cameraPosition,
                imageLoader.getImageFromID(parentObject.getImageID())));

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
        return parentObject.dispose();
    }
}
