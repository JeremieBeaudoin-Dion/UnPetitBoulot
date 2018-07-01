package addGameObjectsHere.menus;

import addResourceLoaderHere.ImageLoader;
import jGameFramework.core.Game;
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
 * @author Jérémie Beaudoin-Dion
 */
public class DayNightButton extends PhysicalObject implements MouseInteractingPhysicalObject {

    private boolean isDay;

    private static final int START_WIDTH = 192;
    private static final int START_HEIGHT = 108;

    /**
     * Constructor
     */
    public DayNightButton(boolean isDay) {
        super(new BoundingArea(Game.WINDOW_WIDTH - 20 - START_WIDTH,
                Game.WINDOW_HEIGHT - 20 - START_HEIGHT, START_WIDTH, START_HEIGHT), false);

        this.isDay = isDay;
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> allImages = new TreeSet<>();

        if (isDay) {
            allImages.add(new DisplayableImage(getPosition(), 1, imageLoader.getDayButton()));
        } else {
            allImages.add(new DisplayableImage(getPosition(), 0, imageLoader.getNightButton()));
        }

        return allImages;
    }

    @Override
    public List<GameEvent> getAction() {
        return null;
    }

    @Override
    public boolean dispose() {
        return false;
    }

    public boolean isDay() {
        return isDay;
    }


    @Override
    public void doLeftMousePressed(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doLeftMouseReleased(Position mousePositionCollidingWithObject) {
        isDay = !isDay;
    }

    @Override
    public void doRightMousePressed(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doRightMouseReleased(Position mousePositionCollidingWithObject) {

    }
}
