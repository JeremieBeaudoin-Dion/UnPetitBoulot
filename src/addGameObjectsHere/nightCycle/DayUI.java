package addGameObjectsHere.nightCycle;

import addGameObjectsHere.inn.InnImagesID;
import addResourceLoaderHere.GameInformation;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableImage;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.util.List;
import java.util.TreeSet;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class DayUI extends PhysicalObject {

    private static final int OFFSET = 155;

    /**
     * Constructor
     */
    public DayUI() {
        super(new BoundingArea(OFFSET, 0, GameInformation.WINDOW_WIDTH - OFFSET,
                GameInformation.WINDOW_HEIGHT - OFFSET), false);
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
}