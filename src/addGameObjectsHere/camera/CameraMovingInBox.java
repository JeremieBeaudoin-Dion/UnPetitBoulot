package addGameObjectsHere.camera;

import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.physicalObjects.*;

import java.util.List;
import java.util.TreeSet;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class CameraMovingInBox extends Camera {

    /**
     * Constructor
     */
    public CameraMovingInBox() {
        super(new VelocitySquare(3, 3));
    }

    /**
     * Method called every frame
     *
     * @param surroundings
     * @return any new PhysicalObject created by this one
     */
    @Override
    public TreeSet<PhysicalObject> update(TreeSet<PhysicalObject> surroundings) {
        updateAndMove(surroundings);
        return null;
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        return null;
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
