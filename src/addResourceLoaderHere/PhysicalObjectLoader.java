package addResourceLoaderHere;

import addGameObjectsHere.view.threadInn.adventurers.PlayerPhysicalObject;
import addGameObjectsHere.view.threadAll.camera.CameraFollowingPlayer;
import addGameObjectsHere.view.threadAll.camera.CameraStill;
import addGameObjectsHere.view.threadInn.inn.Bed;
import addGameObjectsHere.view.threadInn.inn.InnBackground;
import addGameObjectsHere.view.threadInn.inn.InnId;
import addGameObjectsHere.view.threadInn.inn.ServingTablePhysicalObject;
import addGameObjectsHere.view.threadInn.ui.MoneyUI;
import addGameObjectsHere.view.threadNight.NightUI;
import addResourceLoaderHere.innObjectLoader.AdventurerPhysicalObjectLoader;
import addResourceLoaderHere.innObjectLoader.ObjectFileLoader;
import jGameFramework.core.Loader;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.Camera;
import jGameFramework.physicalObjects.PhysicalObject;

import java.util.TreeSet;

/**
 * Loads all game objects to create GameThreads.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class PhysicalObjectLoader implements Loader<TreeSet<PhysicalObject>> {

    private ImageLoader imageLoader;
    private ObjectFileLoader fileLoader;

    private PlayerPhysicalObject playerPhysicalObject;

    private ModelObjectLoader modelObjectLoader;
    private AdventurerPhysicalObjectLoader adventurerLoader;

    /**
     * Constructor
     */
    public PhysicalObjectLoader(ImageLoader imageLoader){
        this.imageLoader = imageLoader;
        fileLoader = new ObjectFileLoader();
        modelObjectLoader = new ModelObjectLoader();

        adventurerLoader = new AdventurerPhysicalObjectLoader(modelObjectLoader.getInn());

        playerPhysicalObject = new PlayerPhysicalObject(modelObjectLoader.getInn(),
                320, 300, DepthHandler.ADVENTURER_DEPTH);  // TODO: Setup player starting position
    }

    /**
     * Returns game object to handle according to gameThreadID
     */
    public TreeSet<PhysicalObject> get(GameThreadID gameThreadID){
        switch (gameThreadID){
            case Floor1:
                return getFloor1Objects();

            case Night:
                return getNightObjects();

            case Test:
                return getTestObjects();
        }

        throw new IllegalArgumentException("The GameThreadID: " + gameThreadID + " is not implemented.");
    }

    private TreeSet<PhysicalObject> getFloor1Objects() {
        TreeSet<PhysicalObject> objects = new TreeSet<>();

        objects.add(new InnBackground(DepthHandler.BACKGROUND_DEPTH));

        playerPhysicalObject.resetPlayer();
        objects.add(playerPhysicalObject);

        objects.add(fileLoader.getWall(InnId.floor1));

        objects.addAll(adventurerLoader.get(fileLoader.getAdventurerStartingPositions(InnId.floor1)));

        objects.addAll(fileLoader.getAllTables(InnId.floor1));

        // needed to be added manually
        objects.add(new ServingTablePhysicalObject(new BoundingArea(1782, 242, 129, 117)));

        objects.add(fileLoader.getBarrel(InnId.floor1));

        objects.add(new MoneyUI(modelObjectLoader.getInn()));

        objects.add(new Bed(DepthHandler.INN_OBJECT_DEPTH));

        return objects;
    }

    private TreeSet<PhysicalObject> getNightObjects() {
        TreeSet<PhysicalObject> objects = new TreeSet<>();

        objects.add(new NightUI());

        return objects;
    }

    private TreeSet<PhysicalObject> getTestObjects() {
        TreeSet<PhysicalObject> objects = new TreeSet<>();

        return objects;
    }

    /**
     * Depending on gameThread, returns the correct camera.
     */
    public Camera getCamera(GameThreadID gameThreadID){
        if (gameThreadID == GameThreadID.Floor1) {
            return new CameraFollowingPlayer(playerPhysicalObject);
        }

        return new CameraStill();
    }

}
