package addGameObjectsHere.view.threadAll.camera;

import addGameObjectsHere.view.threadInn.characters.CharacterObjectMovingWithShadow;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.physicalObjects.*;

import java.util.List;
import java.util.TreeSet;

/**
 * A camera that follows a moving physical object
 *
 * @author Mia Beaudoin-Dion
 */
public class CameraFollowingPlayer extends Camera {

    private PhysicalObjectMoving playerReference;

    private static final int POSITION_TOLERANCE = 2;

    public CameraFollowingPlayer(PhysicalObjectMoving player){
        super(new VelocitySquare(CharacterObjectMovingWithShadow.VELOCITY, CharacterObjectMovingWithShadow.VELOCITY));

        playerReference = player;

        setPlayerInCenterOfScreen();
        super.removeAllMovingOrders();
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
        if (!moveToSetPlayerAtCenterOfScreen()) {
            super.removeAllMovingOrders();
        }

        super.updateAndMove(surroundings);

        return null;
    }

    /**
     * Adds necessary moving orders to set player at center of screen.
     *
     * @return true if the move was needed.
     */
    private boolean moveToSetPlayerAtCenterOfScreen() {
        Position centerOfCamera = getBoundingArea().getCenterPosition();
        Position playerPosition = playerReference.getBoundingArea().getCenterPosition();

        boolean xIsWithinTolerance =  Math.abs(playerPosition.getX() - centerOfCamera.getX()) < POSITION_TOLERANCE;
        boolean yIsWithinTolerance = Math.abs(playerPosition.getY() - centerOfCamera.getY()) < POSITION_TOLERANCE;

        if (!xIsWithinTolerance) {
            addXMovingOrdersToSetPlayerAtCenter(centerOfCamera, playerPosition);
        } else {
            super.removeToMovingOrders(Velocity.Direction.LEFT);
            super.removeToMovingOrders(Velocity.Direction.RIGHT);
        }

        if (!yIsWithinTolerance) {
            addYMovingOrdersToSetPlayerAtCenter(centerOfCamera, playerPosition);
        } else {
            super.removeToMovingOrders(Velocity.Direction.UP);
            super.removeToMovingOrders(Velocity.Direction.DOWN);
        }

        return !(xIsWithinTolerance && yIsWithinTolerance);
    }

    private void addXMovingOrdersToSetPlayerAtCenter(Position centerOfCamera, Position playerPosition) {
        if (centerOfCamera.getX() > playerPosition.getX() + POSITION_TOLERANCE) {
            super.addToMovingOrders(Velocity.Direction.LEFT);
        } else {
            super.removeToMovingOrders(Velocity.Direction.LEFT);
        }

        if (centerOfCamera.getX() < playerPosition.getX() + POSITION_TOLERANCE) {
            super.addToMovingOrders(Velocity.Direction.RIGHT);
        } else {
            super.removeToMovingOrders(Velocity.Direction.RIGHT);
        }
    }

    private void addYMovingOrdersToSetPlayerAtCenter(Position centerOfCamera, Position playerPosition) {
        if (centerOfCamera.getY() > playerPosition.getY() + POSITION_TOLERANCE) {
            super.addToMovingOrders(Velocity.Direction.UP);
        } else {
            super.removeToMovingOrders(Velocity.Direction.UP);
        }

        if (centerOfCamera.getY() < playerPosition.getY() + POSITION_TOLERANCE) {
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
