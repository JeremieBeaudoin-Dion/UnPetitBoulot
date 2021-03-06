package addGameObjectsHere.view.threadInn.other;

import addGameObjectsHere.view.threadInn.characters.ClientPhysicalObject;
import addGameObjectsHere.view.threadInn.inn.Barrel;
import addGameObjectsHere.view.threadInn.inn.ServingTablePhysicalObject;
import addResourceLoaderHere.ModelObjectLoader;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.physicalObjects.Position;


/**
 * @author Mia Beaudoin-Dion
 */
public class ServiceHandler {

    private static final int TIME_IN_MILLIS_TO_INTERACT = 1000;

    private static final Position LOADING_BAR_OFFSET = new Position(-35, 20);

    private LoadingBar currentLoadingBar;
    private ServingTablePhysicalObject currentServingTable;
    private Barrel currentServingBarrel;
    private ModelObjectLoader modelObjectLoader;

    public ServiceHandler(ModelObjectLoader modelObjectLoader) {
        this.modelObjectLoader = modelObjectLoader;
        reset();
    }

    public void reset() {
        deleteLoadingBar();
        currentServingBarrel = null;
        currentServingTable = null;
    }

    public void update() {
        if (isLoading() && currentLoadingBar.isDone()) {
            if (currentServingTable != null) {
                serveAdventurers();
                currentServingTable = null;
            }
            if (currentServingBarrel != null) {
                currentServingBarrel.fill();
                currentServingBarrel = null;
            }
            deleteLoadingBar();
        }
    }

    public boolean isLoading() {
        return currentLoadingBar != null;
    }

    public LoadingBar setLoadingBar(Position position, DisplayableDepth depth) {
        this.currentLoadingBar = new LoadingBar(position.add(LOADING_BAR_OFFSET), depth.add(1),
                TIME_IN_MILLIS_TO_INTERACT);

        return currentLoadingBar;
    }

    private void deleteLoadingBar() {
        if (currentLoadingBar != null) {
            currentLoadingBar.interrupt();
        }
        currentLoadingBar = null;
    }

    public void setTableForLoading(ServingTablePhysicalObject table) {
        currentServingTable = table;
    }

    public void setBarrelForLoading(Barrel barrel) {
        currentServingBarrel = barrel;
    }

    private void serveAdventurers() {
        for (ClientPhysicalObject client : currentServingTable.getSeatedClients()) {
            if (!client.isServed()) {
                client.giveDrink();
                modelObjectLoader.sellDrinkToClient();
            }
        }
    }


}
