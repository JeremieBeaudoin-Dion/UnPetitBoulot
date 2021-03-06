package addResourceLoaderHere.physicalObjectsHelper;

import addGameObjectsHere.model.characters.client.Client;
import addGameObjectsHere.view.threadAll.PhysicalObjectBackground;
import addGameObjectsHere.view.threadInn.characters.ClientImageID;
import addGameObjectsHere.view.threadInn.characters.ClientPhysicalObject;
import addGameObjectsHere.view.threadInn.characters.PlayerPhysicalObject;
import addGameObjectsHere.view.threadInn.inn.Bed;
import addGameObjectsHere.view.threadInn.inn.InnImagesID;
import addGameObjectsHere.view.threadInn.inn.ServingTablePhysicalObject;
import addGameObjectsHere.view.threadInn.ui.MoneyUI;
import addResourceLoaderHere.DepthHandler;
import addResourceLoaderHere.ModelObjectLoader;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.util.*;

/**
 * Helper class for PhysicalObjectLoader to load the current inn.
 *
 * @author Mia Beaudoin-Dion
 */
public class InnLoader {

    private ModelObjectLoader modelObjectLoader;
    private ObjectFileLoader fileLoader;

    public InnLoader(ModelObjectLoader model) {
        modelObjectLoader = model;
        fileLoader = new ObjectFileLoader();
    }

    public TreeSet<PhysicalObject> getPhysicalObjects(PlayerPhysicalObject player) {
        // Get innID from model
        TreeSet<PhysicalObject> objects = new TreeSet<>();

        objects.add(new PhysicalObjectBackground(InnImagesID.getImageID(modelObjectLoader.getInnHandler().getCurrentInnID()),
                2240, 1540));

        objects.add(fileLoader.getWall(modelObjectLoader.getInnHandler().getCurrentInnID()));
        objects.add(new Bed(DepthHandler.INN_OBJECT_DEPTH));
        objects.add(fileLoader.getBarrel(modelObjectLoader.getInnHandler().getCurrentInnID()));

        objects.addAll(fileLoader.getAllTables(modelObjectLoader.getInnHandler().getCurrentInnID()));
        // needed to be added manually - TODO: Fix this
        objects.add(new ServingTablePhysicalObject(new BoundingArea(1782, 242, 129, 117)));


        player.resetPlayer();
        objects.add(player);

        objects.addAll(getAllClients());

        objects.add(new MoneyUI(modelObjectLoader));

        return objects;
    }

    private List<ClientPhysicalObject> getAllClients() {
        List<ClientPhysicalObject> listToReturn = new LinkedList<>();

        List<Position> startingPositions = fileLoader.getAdventurerStartingPositions(modelObjectLoader.getInnHandler().getCurrentInnID());
        Collections.shuffle(startingPositions);

        int num = 0;
        for (Client client : modelObjectLoader.generateDayClients()) {
            listToReturn.add(new ClientPhysicalObject(startingPositions.get(num).getX(),
                    startingPositions.get(num).getY(), DepthHandler.ADVENTURER_DEPTH,
                    client, ClientImageID.getID(client.getClientID())));
            num++;
        }

        return listToReturn;

    }

}
