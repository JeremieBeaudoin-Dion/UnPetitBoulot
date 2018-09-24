package addGameObjectsHere.controller.gameButtons;

import addGameObjectsHere.controller.dayNight.DayInteractingObject;
import addResourceLoaderHere.GameInformation;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableImage;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.Position;

import java.util.List;
import java.util.TreeSet;

/**
 * The night button is a Quit button which has a specific Bounding Area.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class NightButton extends GameButtonQuit {

    private static final int START_WIDTH = 192;
    private static final int START_HEIGHT = 108;

    private List<DayInteractingObject> objectsToUpdate;

    public NightButton(List<DayInteractingObject> dayInteractingObjects) {
        super(ButtonID.Night, new BoundingArea(GameInformation.WINDOW_WIDTH - 20 - START_WIDTH,
                GameInformation.WINDOW_HEIGHT - 20 - START_HEIGHT, START_WIDTH, START_HEIGHT),
                new DisplayableDepth(DisplayableDepth.HIGHEST));

        objectsToUpdate = dayInteractingObjects;
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        images.add(new DisplayableImage(getPositionAccordingToCamera(cameraPosition),
                getDepth(), imageLoader.getButtonImage(ButtonID.Night)));

        return images;
    }

    @Override
    public void doLeftMouseReleased(Position mousePositionCollidingWithObject) {
        for (DayInteractingObject objectToUpdate: objectsToUpdate) {
            objectToUpdate.isDay();
        }

        super.doLeftMouseReleased(mousePositionCollidingWithObject);
    }
}
