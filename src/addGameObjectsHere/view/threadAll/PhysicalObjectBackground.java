package addGameObjectsHere.view.threadAll;

import addResourceLoaderHere.DepthHandler;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableImage;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.util.List;
import java.util.TreeSet;

/**
 * Object used as a background.
 *
 * @author Mia Beaudoin-Dion
 */
public class PhysicalObjectBackground extends PhysicalObject {

    private Enum imageID;

    /**
     * Constructor
     */
    public PhysicalObjectBackground(Enum imageID, int width, int height) {
        super(new BoundingArea(0, 0, width, height), false, DepthHandler.BACKGROUND_DEPTH);

        this.imageID = imageID;
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> setOfImages = new TreeSet<>();

        setOfImages.add(new DisplayableImage(this, cameraPosition, imageLoader.getImageFromID(imageID)));

        return setOfImages;
    }

    @Override
    public List<GameEvent> getAction() {
        return null;
    }

    @Override
    public boolean dispose() {
        return false;
    }

}
