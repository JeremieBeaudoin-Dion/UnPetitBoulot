package addGameObjectsHere.controller.windows;

import addGameObjectsHere.controller.gameButtons.BoxedGameButton;
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
     * Constructor for a form with no "accept" button
     */
    public Form(BoundingArea boundingArea, DisplayableDepth depth, String acceptName) {
        super(boundingArea, false, depth);

        buttons = new LinkedList<>();

        createButtons(acceptName);
    }

    public Form(BoundingArea boundingArea, DisplayableDepth depth) {
        super(boundingArea, false, depth);

        buttons = new LinkedList<>();

        createButtons(null);
    }

    private void createButtons(String acceptName) {
        if (acceptName != null) {
            buttons.add(new BoxedGameButton(ButtonID.Accept, getButtonPosition(ButtonID.Accept), null, getDepth().add(1), acceptName));
        }

        buttons.add(new BoxedGameButton(ButtonID.Close, getButtonPosition(ButtonID.Close), null, getDepth().add(2)));
    }

    private Position getButtonPosition(ButtonID buttonID) {
        if (buttonID.equals(ButtonID.Accept)) {
            return new Position(getPosition().getX(),
                    getPosition().getY() + getBoundingArea().getHeight() - GameButton.DEFAULT_HEIGHT);
        } else {
            return new Position(getPosition().getX() + getBoundingArea().getWidth() - GameButton.DEFAULT_WIDTH,
                    getPosition().getY() + getBoundingArea().getHeight() - GameButton.DEFAULT_HEIGHT);
        }
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

        images.addAll(getInfoImages(cameraPosition, imageLoader));

        return images;
    }

    protected abstract TreeSet<Displayable> getInfoImages(Position cameraPosition, ImageLoader imageLoader);

    @Override
    public void setPositionTo(Position newPosition) {

        super.setPositionTo(newPosition);

        for (GameButton button: buttons) {
            button.setPositionTo(getButtonPosition(button.getButtonID()));
        }
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
