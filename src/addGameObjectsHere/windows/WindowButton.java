package addGameObjectsHere.windows;

import addGameObjectsHere.images.BoxCreator;
import addResourceLoaderHere.GameInformation;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.core.MouseHandler;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableImage;
import jGameFramework.physicalObjects.*;

import java.util.List;
import java.util.TreeSet;

/**
 * An object which moves when clicked.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class WindowButton extends PhysicalObjectUpdating implements MouseInteractingPhysicalObject {

    private static int MINIMUM_WIDTH = 100;
    private static int MINIMUM_HEIGHT = 100;

    private boolean isMoving;
    private Position offset;
    private int depth;
    private WindowID windowID;

    /**
     * Constructor
     */
    WindowButton(WindowID windowID, Position position, int depth) {
        super(new BoundingArea(position.getX(), position.getY(), MINIMUM_WIDTH, MINIMUM_HEIGHT),
                false, new VisionRectangle(MINIMUM_WIDTH, MINIMUM_HEIGHT));

        isMoving = false;
        this.depth = depth;
        this.windowID = windowID;
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> images = new TreeSet<>();

        images.add(new DisplayableImage(getPosition(), depth,
                imageLoader.getBox(new Position(MINIMUM_WIDTH, MINIMUM_HEIGHT), BoxCreator.Background.Wood)));

        images.add(new DisplayableImage(getPosition(), depth + 2,
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
    public void doLeftMousePressed(Position mousePositionCollidingWithObject) {
        if (!isMoving) {
            isMoving = true;

            offset = mousePositionCollidingWithObject.add(getPosition().reverse());
        } else {
            isMoving = false;
        }
    }

    @Override
    public void doLeftMouseReleased(Position mousePositionCollidingWithObject) {
        isMoving = false;
    }

    @Override
    public void doRightMousePressed(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doRightMouseReleased(Position mousePositionCollidingWithObject) {

    }

    /**
     * Method called every frame
     */
    @Override
    public TreeSet<PhysicalObject> update(TreeSet<PhysicalObject> surroundings) {

        if (isMoving) {
            if (newPositionIsOutsideBoundaries()) {
                isMoving = false;

            } else {
                setPositionTo(getNewPosition());
            }
        }


        return new TreeSet<>();
    }

    private Position getNewPosition() {
        return MouseHandler.getMousePositionRelativeToScreen().add(offset.reverse());
    }

    private boolean newPositionIsOutsideBoundaries() {
        Position newPosition = getNewPosition();

        if (newPosition.getX() < 0) {
            return true;
        }

        if (newPosition.getY() < 0) {
            return true;
        }

        if (newPosition.getX() > GameInformation.WINDOW_WIDTH - getWidth()) {
            return true;
        }

        if (newPosition.getY() > GameInformation.WINDOW_HEIGHT - getHeight()) {
            return true;
        }

        return false;
    }
}
