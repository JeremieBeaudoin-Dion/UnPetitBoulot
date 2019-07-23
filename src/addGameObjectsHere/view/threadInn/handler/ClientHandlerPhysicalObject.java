package addGameObjectsHere.view.threadInn.handler;

import addGameObjectsHere.model.inn.Inn;
import addGameObjectsHere.view.threadInn.characters.ClientPhysicalObject;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.physicalObjects.*;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * Due to the limitations of the JGameEngine, for objects to be saved,
 * this must be a Physical Object.
 *
 * The role of the Client Handler is to remove clients for the night schedule,
 * and add them according to the Inn, Player, etc.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ClientHandlerPhysicalObject extends PhysicalObjectUpdating {

    private List<ClientPhysicalObject> currentClients;
    private boolean reloadAdventurers;

    private ClientPhysicalObjectLoader clientLoader;

    //TODO: Client Helper object to give help on where to go.

    /**
     * Constructor
     */
    public ClientHandlerPhysicalObject(Inn inn, List<Position> startingPositions) {
        super(new BoundingArea(0, 0, 1, 1), false, new VisionNone(),
                new DisplayableDepth(DisplayableDepth.BACKGROUND));

        clientLoader = new ClientPhysicalObjectLoader(inn, startingPositions);

        currentClients = new LinkedList<>();
        createNewClients();
        this.reloadAdventurers = true;
    }

    private void createNewClients() {
        currentClients = clientLoader.get();
    }

    /**
     * Method called every frame
     */
    @Override
    public TreeSet<PhysicalObject> update(TreeSet<PhysicalObject> surroundings) {
        if (reloadAdventurers) {
            reloadAdventurers = false;
            TreeSet<PhysicalObject> setToReturn = new TreeSet<>();

            setToReturn.addAll(currentClients);

            return setToReturn;
        }

        return null;
    }

    public void isNight() {
        this.reloadAdventurers = true;

        deleteOldClients();
        createNewClients();
    }

    private void deleteOldClients() {
        for (ClientPhysicalObject client: currentClients) {
            client.setDispose();
        }

        currentClients = new LinkedList<>();
    }

    /*
    **********************************************************
    * Physical Object Methods
    **********************************************************
     */
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
