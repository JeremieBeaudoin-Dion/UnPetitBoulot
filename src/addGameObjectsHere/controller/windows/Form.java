package addGameObjectsHere.controller.windows;

import addGameObjectsHere.controller.gameButtons.ButtonID;
import addGameObjectsHere.controller.gameButtons.GameButton;
import addGameObjectsHere.model.images.BoxCreator;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableImage;
import jGameFramework.physicalObjects.*;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * A type of physical object that has a yes/no button and information.
 *
 * When the accept button is pressed, the doAcceptAction() is called.
 *
 * @author Jérémie Beaudoin-Dion
 */
public abstract class Form extends PhysicalObject implements MouseInteractingPhysicalObject {

    private List<GameButton> buttons;

    /**
     * Constructor
     */
    public Form(BoundingArea boundingArea, DisplayableDepth depth, boolean isAcceptable) {
        super(boundingArea, false, depth);

        buttons = new LinkedList<>();
        createButtons(isAcceptable);
    }

    private void createButtons(boolean isAcceptable) {
        int buttonWidth = GameButton.DEFAULT_WIDTH;
        int buttonHeight = GameButton.DEFAULT_HEIGHT;

        if (isAcceptable) {
            buttons.add(new GameButton(ButtonID.Accept,
                    new Position(getPosition().getX(),
                            getPosition().getY() + getBoundingArea().getHeight() - buttonHeight), null, getDepth().add(1)));
        }

        buttons.add(new GameButton(ButtonID.Close,
                new Position(getPosition().getX() + getBoundingArea().getWidth() - buttonWidth,
                        getPosition().getY() + getBoundingArea().getHeight() - buttonHeight), null, getDepth().add(2)));
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> images = new TreeSet<>();

        images.add(new DisplayableImage(this, cameraPosition,
                imageLoader.getBox(new Position(getWidth(), getHeight()), BoxCreator.Background.Paper)));

        for (GameButton button: buttons) {
            images.addAll(button.getImageObjects(cameraPosition, imageLoader));
        }

        return images;
    }

    protected abstract TreeSet<Displayable> getInfoImages(Position cameraPosition, ImageLoader imageLoader);

    @Override
    public void setPositionTo(Position newPosition) {

        for (GameButton button: buttons) {
            button.setPositionTo(newPosition);
        }

        super.setPositionTo(newPosition);
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
        for (GameButton button: buttons) {
            if (button.getIsPressed()) {
                return true;
            }
        }

        return false;
    }

    public boolean isAccepted() {
        for (GameButton button : buttons) {
            if (button.getButtonID().equals(ButtonID.Accept)) {
                return button.getIsPressed();
            }
        }

        return false;
    }

    @Override
    public void doLeftMousePressed(Position mousePositionCollidingWithObject) {
        for (GameButton button: buttons) {
            if (button.isColliding(mousePositionCollidingWithObject)) {
                button.doLeftMousePressed(mousePositionCollidingWithObject);
                break;
            }
        }
    }

    @Override
    public void doLeftMouseReleased(Position mousePositionCollidingWithObject) {
        for (GameButton button: buttons) {
            if (button.isColliding(mousePositionCollidingWithObject)) {
                button.doLeftMouseReleased(mousePositionCollidingWithObject);
                break;
            }
        }
    }

    @Override
    public void doRightMousePressed(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doRightMouseReleased(Position mousePositionCollidingWithObject) {

    }
}
