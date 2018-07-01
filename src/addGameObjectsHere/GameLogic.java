package addGameObjectsHere;

import addGameObjectsHere.menus.DayNightButton;
import addResourceLoaderHere.GameInformation;
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
 * This game will be made for now without collisions. Therefore, most of the game's logic will be handled
 * in the GameLogic class
 *
 * The GameLogic class holds the 3 base classes
 *     The Inn
 *     The recruiting board
 *     The quest handler
 *
 * @author Jérémie Beaudoin-Dion
 */
public class GameLogic extends PhysicalObject implements MouseInteractingPhysicalObject {

    private DayNightButton dayNightButton;

    /**
     * Constructor
     */
    public GameLogic() {
        super(new BoundingArea(0, 0, GameInformation.WINDOW_WIDTH, GameInformation.WINDOW_HEIGHT), false);

        dayNightButton = new DayNightButton(true);
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> allImages = new TreeSet<>();

        if (dayNightButton.isDay()) {
            allImages.addAll(getDayObjects(cameraPosition, imageLoader));

        } else {
            allImages.addAll(getNightObjects(cameraPosition, imageLoader));
        }

        allImages.addAll(dayNightButton.getImageObjects(cameraPosition, imageLoader));

        return allImages;
    }

    private TreeSet<Displayable> getDayObjects(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> allImages = new TreeSet<>();

        allImages.add(new DisplayableImage(getPosition().add(new Position(150, 0)), 0, imageLoader.getGameDayBackground()));

        return allImages;
    }

    private TreeSet<Displayable> getNightObjects(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> allImages = new TreeSet<>();

        allImages.add(new DisplayableImage(getPosition(), 0, imageLoader.getGameNightBackground()));

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

    @Override
    public void doLeftMousePressed(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doLeftMouseReleased(Position mousePositionCollidingWithObject) {
        if (dayNightButton.isColliding(mousePositionCollidingWithObject)) {
            dayNightButton.doLeftMouseReleased(mousePositionCollidingWithObject);
        }
    }

    @Override
    public void doRightMousePressed(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doRightMouseReleased(Position mousePositionCollidingWithObject) {

    }
}
