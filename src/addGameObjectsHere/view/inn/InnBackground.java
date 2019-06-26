package addGameObjectsHere.view.inn;

import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableImage;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.util.List;
import java.util.TreeSet;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class InnBackground extends PhysicalObject {

    private static final int WIDTH = 2240;
    private static final int HEIGHT = 1540;

    /**
     * Constructor
     */
    public InnBackground(DisplayableDepth depth) {
        super(new BoundingArea(0, 0, WIDTH, HEIGHT), false, depth);
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> setOfImages = new TreeSet<>();

        // TODO: Change this depending on the InnID.
        setOfImages.add(new DisplayableImage(this, cameraPosition, imageLoader.getImageFromID(InnImagesID.floor1)));

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
