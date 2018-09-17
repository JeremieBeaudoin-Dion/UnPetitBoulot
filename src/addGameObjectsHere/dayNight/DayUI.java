package addGameObjectsHere.dayNight;

import addGameObjectsHere.inn.InnImagesID;
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
 * @author Jérémie Beaudoin-Dion
 */
public class DayUI extends PhysicalObject implements MouseInteractingPhysicalObject {

    /**
     * Constructor
     */
    public DayUI() {
        super(new BoundingArea(0, 0, GameInformation.WINDOW_WIDTH,
                GameInformation.WINDOW_HEIGHT), false);
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> setOfImages = new TreeSet<>();

        setOfImages.add(new DisplayableImage(getPositionAccordingToCamera(cameraPosition), 0,
                imageLoader.getInnUiImage(InnImagesID.backgroundDay)));

        return setOfImages;
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
        System.out.println(mousePositionCollidingWithObject);
    }

    @Override
    public void doLeftMouseReleased(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doRightMousePressed(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doRightMouseReleased(Position mousePositionCollidingWithObject) {

    }
}