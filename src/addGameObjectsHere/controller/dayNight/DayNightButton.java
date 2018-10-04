package addGameObjectsHere.controller.dayNight;

import addGameObjectsHere.controller.gameButtons.ButtonID;
import addResourceLoaderHere.GameInformation;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableImage;
import jGameFramework.physicalObjects.*;

import java.util.List;
import java.util.TreeSet;

/**
 * The night button is a Quit button which has a specific Bounding Area.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class DayNightButton extends PhysicalObjectUpdating implements MouseInteractingPhysicalObject {

    private static final int START_WIDTH = 192;
    private static final int START_HEIGHT = 108;

    private boolean changeDay;
    private ButtonID buttonID;
    private List<GameEvent> actions;

    /**
     * Constructor
     */
    public DayNightButton(ButtonID buttonID, List<GameEvent> actions) {
        super(new BoundingArea(GameInformation.WINDOW_WIDTH - 20 - START_WIDTH,
                        GameInformation.WINDOW_HEIGHT - 20 - START_HEIGHT, START_WIDTH, START_HEIGHT), false,
                new VisionAll(), new DisplayableDepth(DisplayableDepth.HIGHEST));

        changeDay = false;
        this.actions = actions;
        this.buttonID = buttonID;
    }

    @Override
    public void doLeftMousePressed(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doLeftMouseReleased(Position mousePositionCollidingWithObject) {
        changeDay = true;
    }

    @Override
    public void doRightMousePressed(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doRightMouseReleased(Position mousePositionCollidingWithObject) {

    }

    @Override
    public TreeSet<PhysicalObject> update(TreeSet<PhysicalObject> surroundings) {
        if (changeDay) {
            for (PhysicalObject physicalObject : surroundings) {
                if (physicalObject instanceof DayNightInteractingObject) {
                    ((DayNightInteractingObject) physicalObject).changeCycle();
                }
            }
        }

        return null;
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        images.add(new DisplayableImage(this, cameraPosition, imageLoader.getButtonImage(buttonID)));

        return images;
    }

    @Override
    public List<GameEvent> getAction() {
        if (!changeDay) {
            return null;
        }

        changeDay = false;

        return actions;
    }

    @Override
    public boolean dispose() {
        return false;
    }
}
