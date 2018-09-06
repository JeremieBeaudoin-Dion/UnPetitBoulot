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
 * Will handle the UI of the night
 *
 * @author Jérémie Beaudoin-Dion
 */
public class NightUI extends PhysicalObject {

    /**
     * Constructor
     */
    public NightUI() {
        super(new BoundingArea(0, 0, GameInformation.WINDOW_WIDTH, GameInformation.WINDOW_HEIGHT), false);
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> setOfImages = new TreeSet<>();

        setOfImages.add(new DisplayableImage(getPositionAccordingToCamera(cameraPosition), 0,
                imageLoader.getInnUiImage(InnImagesID.backgroundNight)));

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
