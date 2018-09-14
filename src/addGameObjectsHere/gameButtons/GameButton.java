package addGameObjectsHere.gameButtons;

import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableImage;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.MouseInteractingPhysicalObject;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.List;
import java.util.TreeSet;

/**
 * Game buttons will always have the same behaviour:
 *      When clicked, they will change the current GameThread
 *
 * @author Jérémie Beaudoin-Dion
 */
public abstract class GameButton extends PhysicalObject implements MouseInteractingPhysicalObject {

    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;

    private boolean goToMenu;
    private ButtonID buttonID;

    /**
     * Constructor
     *
     * If the bounding area is not specified, the default bounding area will be used.
     *
     * //TODO: Get image from image handler for BoundingArea?
     */
    public GameButton(ButtonID buttonID, Position startingPosition) {
        super(getStartingBoundingArea(startingPosition), false);

        goToMenu = false;
        this.buttonID = buttonID;
    }

    private static BoundingArea getStartingBoundingArea(Position startingPosition) {
        Shape circle = new Ellipse2D.Double(startingPosition.getX(), startingPosition.getY(), WIDTH, HEIGHT);

        return new BoundingArea(circle);
    }

    /**
     * Constructor with specific BoundingArea
     */
    public GameButton(ButtonID buttonID, BoundingArea startBoundingArea) {
        super(startBoundingArea, false);

        goToMenu = false;
        this.buttonID = buttonID;
    }

    protected abstract List<GameEvent> getMenuAction();

    @Override
    public List<GameEvent> getAction() {

        if (goToMenu) {
            goToMenu = false;

            return getMenuAction();
        }

        return null;
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        // private static final Color color = Color.CYAN;
        //images.add(new DisplayableShapeFilled(1000, getBoundingArea(), color));
        //images.add(new DisplayableImage(getPositionAccordingToCamera(cameraPosition), 1000, imageLoader.getBox(new Position(getWidth(), getHeight()))));

        images.add(new DisplayableImage(getPositionAccordingToCamera(cameraPosition),
                100, imageLoader.getButtonImage(buttonID)));

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
