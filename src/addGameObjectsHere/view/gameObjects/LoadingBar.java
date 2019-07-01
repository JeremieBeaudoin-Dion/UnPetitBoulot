package addGameObjectsHere.view.gameObjects;

import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.util.List;
import java.util.TreeSet;

/**
 * A bar that shows a
 *
 * @author Jérémie Beaudoin-Dion
 */
public class LoadingBar extends PhysicalObject {

    private static final Position SIZE = new Position(0,0);

    private boolean interrupted;

    private long timeMillisAtStart;
    private int timeMillisLength;

    /**
     * Constructor
     */
    public LoadingBar(int x, int y, DisplayableDepth depth, int timeInMillis) {
        super(new BoundingArea(x, y, SIZE.getX(), SIZE.getY()), false, depth);

        interrupted = false;
        timeMillisLength = timeInMillis;

        timeMillisAtStart = System.currentTimeMillis();
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        return null;
    }

    /**
     * Returns any action that the GameThread should handle
     */
    @Override
    public List<GameEvent> getAction() {
        return null;
    }

    public boolean isDone() {
        return timeMillisLength <= System.currentTimeMillis() - timeMillisAtStart;
    }

    /**
     * Returns true if the object should be disposed of
     */
    @Override
    public boolean dispose() {
        return interrupted || isDone();
    }

    public void interrupt() {
        this.interrupted = true;
    }
}
