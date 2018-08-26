package addGameObjectsHere.gameUi;

import addGameObjectsHere.ImagesID;
import addGameObjectsHere.Inn;
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
 * This pannel shows to the player some useful information about their inn (eg. money, fame, etc.)
 *
 * @author Jérémie Beaudoin-Dion
 */
public class InnPannelBottom extends PhysicalObject implements MouseInteractingPhysicalObject {

    private static final int START_WIDTH = 1080;
    private static final int START_HEIGHT = 155;

    private Inn inn;

    /**
     * Constructor
     */
    public InnPannelBottom(Inn inn) {
        super(new BoundingArea(0, GameInformation.WINDOW_HEIGHT - START_HEIGHT, START_WIDTH, START_HEIGHT),
                false);

        this.inn = inn;
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> images = new TreeSet<>();

        images.add(new DisplayableImage(getPositionAccordingToCamera(cameraPosition), 50, imageLoader.getInnUiImage(ImagesID.pannelBottom)));



        return images;
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

    }

    @Override
    public void doRightMousePressed(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doRightMouseReleased(Position mousePositionCollidingWithObject) {

    }
}
