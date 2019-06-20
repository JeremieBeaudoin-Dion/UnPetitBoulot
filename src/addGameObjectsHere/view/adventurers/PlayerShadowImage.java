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
 * The player is a special snowflake. It has its own image.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class PlayerShadowImage extends ObjectShadowImageMoving {

    public static final Position SIZE = new Position(144, 144); // Setting position and size as final for now
    public static final Position OFFSET = new Position(-40, -118);

    /**
     * Constructor
     */
    public PlayerShadowImage(Player parentObject) {
        super(parentObject, SIZE, OFFSET);
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        Player parentObject = (Player) this.getParentObject();

        images.add(new DisplayableImage(this, cameraPosition,
                imageLoader.getPlayerImage(parentObject.getImageID())));

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
