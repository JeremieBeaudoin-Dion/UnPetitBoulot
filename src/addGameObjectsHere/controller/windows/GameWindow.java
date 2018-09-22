package addGameObjectsHere.controller.windows;

import addGameObjectsHere.model.images.BoxCreator;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableImage;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.*;

import java.awt.*;
import java.util.List;
import java.util.TreeSet;

/**
 * This represents the menus of the game which are made to be moved around and resized.
 *
 * When clicking on their WindowButton, the player can move them around or expand/retract the window.
 *
 * @author Jérémie Beaudoin-Dion
 */
public abstract class GameWindow extends PhysicalObjectUpdating implements MouseInteractingPhysicalObject {

    public static final int BORDER_HEIGHT = 20;
    public static final int MAX_FRAME_BUTTON_COUNTER_VALUE = 30;

    private static final int MINIMUM_WIDTH = 400;
    private static final int MINIMUM_HEIGHT = 300;

    private WindowButton windowButton;
    private boolean showDetails;
    private WindowID windowID;

    private WindowsCounter counter;

    private static final Font TITLE_FONT =  new Font("Century Schoolbook", Font.BOLD, 35);

    /**
     * Constructor
     */
    public GameWindow(WindowID windowID, Position position, int baseDepth) {
        super(new BoundingArea(position.getX(), position.getY(), MINIMUM_WIDTH, MINIMUM_HEIGHT), false,
                new VisionRectangle(MINIMUM_WIDTH, MINIMUM_HEIGHT), new DisplayableDepth(baseDepth));

        windowButton = new WindowButton(windowID, getPosition(),baseDepth + 50);
        showDetails = true;
        this.windowID = windowID;

        counter = new WindowsCounter(MAX_FRAME_BUTTON_COUNTER_VALUE);
    }

    /**
     * Method called every frame
     */
    @Override
    public TreeSet<PhysicalObject> update(TreeSet<PhysicalObject> surroundings) {
        windowButton.update(surroundings);

        if (!windowButton.getPosition().equals(this.getPosition())) {
            this.setPositionTo(windowButton.getPosition());
            updatePosition();
        }

        counter.update();

        return null;
    }

    /**
     * Called every time the position of the window is changed.
     */
    protected abstract void updatePosition();

    /**
     * Adds necessary images
     */
    public abstract TreeSet<Displayable> getAdditionalImages(Position cameraPosition, ImageLoader imageLoader);

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> images = new TreeSet<>();

        images.addAll(windowButton.getImageObjects(cameraPosition, imageLoader));

        if (showDetails) {

            images.add(new DisplayableImage(this, cameraPosition,
                    imageLoader.getBox(new Position(getWidth(), getHeight()), BoxCreator.Background.Paper)));

            images.add(new DisplayableText(getPositionOfTitle(cameraPosition), getDepth().add(10),
                    windowID.toString(), TITLE_FONT, Color.BLACK));

            images.add(new DisplayableImage(getPositionAccordingToCamera(cameraPosition), getDepth().add(20),
                    imageLoader.getBox(new Position(getWidth(), BORDER_HEIGHT), BoxCreator.Background.Wood)));

            images.addAll(getAdditionalImages(cameraPosition, imageLoader));
        }

        return images;
    }

    private Position getPositionOfTitle(Position cameraPosition) {

        Position posAccordingToCamera = getPositionAccordingToCamera(cameraPosition);

        Position posPlusWindow = posAccordingToCamera.add(new Position(windowButton.getBoundingArea().getWidth(), 0));

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

    protected Position getImagesOffset() {
        return new Position(windowButton.getBoundingArea().getWidth()/5, windowButton.getBoundingArea().getHeight());
    }

    /*
     * Mouse interacting methods
     */
    @Override
    public void doLeftMousePressed(Position mousePositionCollidingWithObject) {
        if (windowButton.isColliding(mousePositionCollidingWithObject)) {
            windowButton.doLeftMousePressed(mousePositionCollidingWithObject);

            counter.start();
        }
    }

    @Override
    public void doLeftMouseReleased(Position mousePositionCollidingWithObject) {
        if (windowButton.isColliding(mousePositionCollidingWithObject)) {
            windowButton.doLeftMouseReleased(mousePositionCollidingWithObject);

            showOrHideDetails();
            counter.stop();
        }
    }

    private void showOrHideDetails() {
        if (!counter.isDone()) {
            showDetails = !showDetails;

            if (showDetails) {
                expand();
            } else {
                retract();
            }
        }
    }

    private void expand() {
        setWidthAndHeight(MINIMUM_WIDTH, MINIMUM_HEIGHT);
    }

    private void retract() {
        setWidthAndHeight(windowButton.getBoundingArea().getWidth(), windowButton.getBoundingArea().getHeight());
    }

    @Override
    public void doRightMousePressed(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doRightMouseReleased(Position mousePositionCollidingWithObject) {

    }
}
