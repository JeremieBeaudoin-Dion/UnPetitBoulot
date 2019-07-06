package addGameObjectsHere.view.threadAll.camera;

import addGameObjectsHere.view.threadInn.adventurers.AdventurerObjectMovingWithShadow;
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
        super(new VelocitySquare(AdventurerObjectMovingWithShadow.VELOCITY, AdventurerObjectMovingWithShadow.VELOCITY));

        playerReference = player;

        setPlayerInCenterOfScreen();
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
     * When updated, makes sure the player is at the center of the screen
     */
    @Override
    public TreeSet<PhysicalObject> update(TreeSet<PhysicalObject> surroundings) {
        if (playerIsNotAtCenterOfScreen()) {
            addMovingOrdersToSetPlayerAtCenter();
        } else {
            super.removeAllMovingOrders();
        }

        super.updateAndMove(surroundings);

        return null;
    }

    private boolean playerIsNotAtCenterOfScreen(){
        Position centerOfCamera = getBoundingArea().getCenterPosition();
        Position playerPosition = playerReference.getBoundingArea().getCenterPosition();

        return !playerPosition.equals(centerOfCamera);
    }

    private void addMovingOrdersToSetPlayerAtCenter() {
        Position centerOfCamera = getBoundingArea().getCenterPosition();
        Position playerPosition = playerReference.getBoundingArea().getCenterPosition();

        if (centerOfCamera.getX() > playerPosition.getX()) {
            super.addToMovingOrders(Velocity.Direction.LEFT);
        } else {
            super.removeToMovingOrders(Velocity.Direction.LEFT);
        }

        if (centerOfCamera.getX() < playerPosition.getX()) {
            super.addToMovingOrders(Velocity.Direction.RIGHT);
        } else {
            super.removeToMovingOrders(Velocity.Direction.RIGHT);
        }

        if (centerOfCamera.getY() > playerPosition.getY()) {
            super.addToMovingOrders(Velocity.Direction.UP);
        } else {
            super.removeToMovingOrders(Velocity.Direction.UP);
        }

        if (centerOfCamera.getY() < playerPosition.getY()) {
            super.addToMovingOrders(Velocity.Direction.DOWN);
        } else {
            super.removeToMovingOrders(Velocity.Direction.DOWN);
        }
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

    /**
     * Returns true if the object should be disposed of
     */
    @Override
    public boolean dispose() {
        return false;
    }
}
