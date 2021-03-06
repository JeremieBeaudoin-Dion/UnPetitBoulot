package addGameObjectsHere.view.threadInn.characters;

import addGameObjectsHere.view.threadInn.inn.Barrel;
import addGameObjectsHere.view.threadInn.inn.Bed;
import addGameObjectsHere.view.threadInn.inn.ServingTablePhysicalObject;
import addGameObjectsHere.view.threadInn.other.ServiceHandler;
import addResourceLoaderHere.GameThreadID;
import addResourceLoaderHere.ModelObjectLoader;
import jGameFramework.collections.SetHelper;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.coreActions.GameThreadEventAddNew;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * The player requires its own PhysicalObject in order to be able to receive
 * the moving actions when pressing keys. (See ActionLoader)
 *
 * @author Mia Beaudoin-Dion
 */
public class PlayerPhysicalObject extends CharacterObjectMovingWithShadow {

    private Position startingPosition;

    private boolean startConversation;

    private ServiceHandler serviceHandler;

    private boolean interact;
    private ModelObjectLoader modelObjectLoader;

    /**
     * Constructor
     */
    public PlayerPhysicalObject(ModelObjectLoader modelObjectLoader, int x, int y, DisplayableDepth depth) {
        super(x, y, depth, modelObjectLoader.getPlayerHandler().getImageID());

        this.modelObjectLoader = modelObjectLoader;
        startingPosition = new Position(x, y);

        serviceHandler = new ServiceHandler(modelObjectLoader);
        interact = false;
    }

    @Override
    public TreeSet<PhysicalObject> update(TreeSet<PhysicalObject> surroundings) {

        TreeSet<PhysicalObject> updateObjects = new TreeSet<>();
        SetHelper.addAllIfNotNull(updateObjects, super.update(surroundings));

        if (this.isMoving()) {
            serviceHandler.reset();
            interact = false;
        }

        serviceHandler.update();

        if (interact) {
            if (!serviceHandler.isLoading()){
                SetHelper.addAllIfNotNull(updateObjects,
                        handleClosestObject(getClosestObject(trimSurroundings(surroundings))));
            } else {
                interact = false;
                serviceHandler.reset();
            }
        }

        return updateObjects;
    }


    private TreeSet<PhysicalObject> trimSurroundings(TreeSet<PhysicalObject> surroundings) {
        TreeSet<PhysicalObject> interactiveSurroundings = new TreeSet<>();

        for (PhysicalObject object : surroundings) {
            if (object instanceof ServingTablePhysicalObject) {
                interactiveSurroundings.add(object);

            } else if (object instanceof ClientPhysicalObject){
                interactiveSurroundings.add(object);

            } else if (object instanceof Barrel) {
                interactiveSurroundings.add(object);

            } else if (object instanceof Bed) {
                interactiveSurroundings.add(object);
            }
        }

        return interactiveSurroundings;
    }

    private Set<PhysicalObject> handleClosestObject(PhysicalObject closestObject) {

        if (closestObject instanceof ServingTablePhysicalObject) {
            if (((ServingTablePhysicalObject) closestObject).hasClientsToServe()) {
                interact = false;
                serviceHandler.setTableForLoading((ServingTablePhysicalObject) closestObject);
                return SetHelper.getSetFromObject(serviceHandler.setLoadingBar(this.getPosition(), this.getDepth()));
            }
        }

        if (closestObject instanceof Barrel) {
            interact = false;
            serviceHandler.setBarrelForLoading((Barrel) closestObject);
            return SetHelper.getSetFromObject(serviceHandler.setLoadingBar(this.getPosition(), this.getDepth()));
        }

        if (closestObject instanceof Bed) {
            doNight((Bed) closestObject);
        }

        if (closestObject instanceof ClientPhysicalObject) {
            modelObjectLoader.setCurrentConversationClient(((ClientPhysicalObject) closestObject).getClient());
            startConversation = true;
        }

        // By default will no longer interact and not return anything.
        interact = false;
        serviceHandler.reset();
        return null;
    }


    private void doNight(Bed closestObject) {
        closestObject.setNight();
    }

    /**
     * Public method to toggle interact with other objects
     */
    public void setInteract() {
        interact = !interact;
    }

    public void resetPlayer() {
        super.createdShadow = false;
        super.removeAllMovingOrders();
        this.setPositionTo(startingPosition.clone());
    }

    @Override
    public List<GameEvent> getAction() {
        if (startConversation) {
            List<GameEvent> list = new LinkedList<>();

            list.add(new GameThreadEventAddNew(GameThreadID.Conversation));
            startConversation = false;
            
            return list;
        }

        return super.getAction();
    }
}
