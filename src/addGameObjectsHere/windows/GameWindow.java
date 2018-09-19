package addGameObjectsHere.windows;

import addGameObjectsHere.gameButtons.ButtonID;
import addGameObjectsHere.images.BoxCreator;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableImage;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.*;

import java.awt.*;
import java.util.List;
import java.util.TreeSet;

/**
 * This represents the menus of the game which are made to be moved around and resized.
 *
 * When clicking on their WindowButton, the player can move them around or expand the window.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class GameWindow extends PhysicalObjectUpdating implements MouseInteractingPhysicalObject {

    public static final int BORDER_HEIGHT = 20;

    private static final int MINIMUM_WIDTH = 300;
    private static final int MINIMUM_HEIGHT = 300;

    private WindowButton windowButton;
    private boolean showDetails;
    private int baseDepth;
    private WindowID windowID;

    private int counter;
    private boolean implementCounter;

    private static final Font TITLE_FONT =  new Font("Century Schoolbook", Font.BOLD, 35);

    /**
     * Constructor
     */
    public GameWindow(WindowID windowID, Position position, int baseDepth) {
        super(new BoundingArea(position.getX(), position.getY(), 400, 500), false,
                new VisionRectangle(200, 200));

        windowButton = new WindowButton(windowID, getPosition(),baseDepth + 50);
        showDetails = true;
        this.windowID = windowID;

        this.baseDepth = baseDepth;
        implementCounter = false;
        counter = 0;
    }

    /**
     * Method called every frame
     */
    @Override
    public TreeSet<PhysicalObject> update(TreeSet<PhysicalObject> surroundings) {
        windowButton.update(surroundings);

        this.setPositionTo(windowButton.getPosition());

        if (implementCounter) {
            counter++;
        }

        return null;
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> images = new TreeSet<>();

        images.addAll(windowButton.getImageObjects(cameraPosition, imageLoader));

        if (showDetails) {

            images.add(new DisplayableImage(getPosition(), baseDepth,
                    imageLoader.getBox(new Position(getWidth(), getHeight()), BoxCreator.Background.Paper)));

            images.add(new DisplayableText(getPositionOfTitle(), baseDepth + 10,
                    windowID.toString(), TITLE_FONT, Color.BLACK));

            images.add(new DisplayableImage(getPosition(), baseDepth + 20,
                    imageLoader.getBox(new Position(getWidth(), BORDER_HEIGHT), BoxCreator.Background.Wood)));
        }

        return images;
    }

    private Position getPositionOfTitle() {
        Position posPlusWindow = getPosition().add(new Position(windowButton.getBoundingArea().getWidth(), 0));

        return posPlusWindow.add(new Position(10, 70));
    }

    @Override
    public List<GameEvent> getAction() {
        return null;
    }

    @Override
    public boolean dispose() {
        return false;
    }

    /*
     * Mouse interacting methods
     */
    @Override
    public void doLeftMousePressed(Position mousePositionCollidingWithObject) {
        if (windowButton.isColliding(mousePositionCollidingWithObject)) {
            windowButton.doLeftMousePressed(mousePositionCollidingWithObject);

            implementCounter = true;
        }
    }

    @Override
    public void doLeftMouseReleased(Position mousePositionCollidingWithObject) {
        if (windowButton.isColliding(mousePositionCollidingWithObject)) {
            windowButton.doLeftMouseReleased(mousePositionCollidingWithObject);

            checkIfHideDetails();
        }
    }

    private void checkIfHideDetails() {
        if (counter < 30) {
            showDetails = !showDetails;
        }

        implementCounter = false;
        counter = 0;
    }

    @Override
    public void doRightMousePressed(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doRightMouseReleased(Position mousePositionCollidingWithObject) {

    }
}
