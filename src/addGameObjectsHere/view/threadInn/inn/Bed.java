package addGameObjectsHere.view.threadInn.inn;

import addResourceLoaderHere.GameThreadID;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.coreActions.GameThreadEventSetNew;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableImage;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * Physical object of a bed. Will change the day/night cycle.
 *
 * @author Mia Beaudoin-Dion
 */
public class Bed extends PhysicalObject {

    private boolean isNight;

    /**
     * Constructor
     */
    public Bed(DisplayableDepth depth) {
        super(getArea(), true, depth);

        isNight = false;
    }

    private static BoundingArea getArea() {
        return new BoundingArea(495, 90, 150, 200);
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        images.add(new DisplayableImage(this, cameraPosition, imageLoader.getImageFromID(InnImagesID.Bed)));

        return images;
    }

    public void setNight() {
        isNight = true;
    }

    /**
     * Returns any action that the GameThread should handle
     */
    @Override
    public List<GameEvent> getAction() {
        if (isNight) {
            List<GameEvent> list = new LinkedList<>();

            list.add(new GameThreadEventSetNew(GameThreadID.Night));
            isNight = false;

            return list;
        }

        return null;
    }

    /**
     * Returns true if the object should be disposed of
     */
    @Override
    public boolean dispose() {
        return false;
    }
}
