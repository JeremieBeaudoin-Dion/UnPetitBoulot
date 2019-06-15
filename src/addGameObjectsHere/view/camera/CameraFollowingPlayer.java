package addGameObjectsHere.view.camera;

import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.physicalObjects.*;

import java.util.List;
import java.util.TreeSet;

/**
 * A camera that follows a moving physical object
 *
 * @author Jérémie Beaudoin-Dion
 */
public class CameraFollowingPlayer extends Camera {

    private PhysicalObjectMoving playerReference;

    public CameraFollowingPlayer(PhysicalObjectMoving player){
        super(new VelocitySquare(2, 2));

        playerReference = player;

        setPlayerInCenterOfScreen();
    }

    /**
     * When updated, makes sure the player is at the center of the screen
     */
    @Override
    public TreeSet<PhysicalObject> update(TreeSet<PhysicalObject> surroundings) {
        setPlayerInCenterOfScreen();

        return null;
    }

    private void setPlayerInCenterOfScreen(){
        Position centerOfCamera = getBoundingArea().getCenterPosition();
        Position playerPosition = playerReference.getBoundingArea().getCenterPosition();

        Position differenceBetweenCenters = playerPosition.add(new Position(-centerOfCamera.getX(), -centerOfCamera.getY()));

        Position actualCameraPosition = getPosition();
        Position newCameraPosition = actualCameraPosition.add(differenceBetweenCenters);

        setPositionTo(newCameraPosition);
    }

    /**
     * Returns the image representation of this physical object
     * <p>
     * The ImageObject will have a relative position depending
     * on the position of the CameraWithEdges.
     *
     * @param cameraPosition
     * @param imageLoader
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

    /**
     * Returns true if the object should be disposed of
     */
    @Override
    public boolean dispose() {
        return false;
    }
}
