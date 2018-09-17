package addGameObjectsHere.gameButtons;

import addGameObjectsHere.images.BoxCreator;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableImage;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.MouseInteractingPhysicalObject;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.util.List;
import java.util.TreeSet;

/**
 * Game buttons will always have the same behaviour:
 *      When clicked, they will change the current GameThread
 *
 * @author Jérémie Beaudoin-Dion
 */
public class GameButton extends PhysicalObject implements MouseInteractingPhysicalObject {

    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;

    private boolean goToMenu;
    private ButtonID buttonID;

    private List<GameEvent> eventActions;

    /**
     * Constructor
     *
     * If the bounding area is not specified, the default bounding area will be used.
     */
    public GameButton(ButtonID buttonID, Position startingPosition, List<GameEvent> eventActions) {
        super(getStartingBoundingArea(startingPosition), false);

        goToMenu = false;
        this.buttonID = buttonID;
        this.eventActions = eventActions;
    }

    private static BoundingArea getStartingBoundingArea(Position startingPosition) {
        return new BoundingArea(startingPosition.getX(), startingPosition.getY(), WIDTH, HEIGHT);
    }

    /**
     * Constructor with specific BoundingArea
     */
    public GameButton(ButtonID buttonID, BoundingArea startBoundingArea, List<GameEvent> eventActions) {
        super(startBoundingArea, false);

        goToMenu = false;
        this.buttonID = buttonID;
        this.eventActions = eventActions;
    }

    @Override
    public List<GameEvent> getAction() {

        if (goToMenu) {
            goToMenu = false;

            return eventActions;
        }

        return null;
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        images.add(new DisplayableImage(getPositionAccordingToCamera(cameraPosition), 100,
                imageLoader.getBox(new Position(getWidth(), getHeight()), BoxCreator.Background.Wood)));

        images.add(new DisplayableImage(getPositionAccordingToCamera(cameraPosition),
                200, imageLoader.getButtonImage(buttonID)));

        return images;
    }

    @Override
    public boolean dispose() {
        return false;
    }

    @Override
    public void doLeftMousePressed(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doLeftMouseReleased(Position mousePositionCollidingWithObject) {
        goToMenu = true;
    }

    @Override
    public void doRightMousePressed(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doRightMouseReleased(Position mousePositionCollidingWithObject) {

    }

}
