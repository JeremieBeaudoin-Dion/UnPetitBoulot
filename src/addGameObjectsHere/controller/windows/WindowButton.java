package addGameObjectsHere.controller.windows;

import addGameObjectsHere.model.images.BoxCreator;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableImage;
import jGameFramework.physicalObjects.*;

import java.util.List;
import java.util.TreeSet;

/**
 * An object which moves when clicked.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class WindowButton extends DraggablePhysicalObject {

    private static int MINIMUM_WIDTH = 100;
    private static int MINIMUM_HEIGHT = 100;

    private WindowID windowID;

    /**
     * Constructor
     */
    WindowButton(WindowID windowID, Position position, int depth) {
        super(new BoundingArea(position.getX(), position.getY(), MINIMUM_WIDTH, MINIMUM_HEIGHT),
                false, new VisionRectangle(MINIMUM_WIDTH, MINIMUM_HEIGHT), new DisplayableDepth(depth));

        this.windowID = windowID;
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> images = new TreeSet<>();

        images.add(new DisplayableImage(getPosition(), getDepth(),
                imageLoader.getBox(new Position(MINIMUM_WIDTH, MINIMUM_HEIGHT), BoxCreator.Background.Wood)));

        images.add(new DisplayableImage(getPosition(), getDepth().add(2),
                imageLoader.getWindowImage(windowID)));

        return images;
    }

    @Override
    public List<GameEvent> getAction() {
        return null;
    }

    @Override
    public boolean dispose() {
        return false;
    }

    @Override
    protected void hasStoppedMoving() {

    }

    @Override
    protected void hasStartedMoving() {

    }

    @Override
    protected void doUpdate(TreeSet<PhysicalObject> surroundings) {

    }
}
