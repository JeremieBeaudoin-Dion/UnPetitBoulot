package addResourceLoaderHere;

import addGameObjectsHere.view.adventurers.Player;
import addGameObjectsHere.view.camera.CameraFollowingPlayer;
import addGameObjectsHere.view.camera.CameraStill;
import addGameObjectsHere.view.inn.InnBackground;
import addGameObjectsHere.view.inn.InnId;
import addGameObjectsHere.view.inn.InnImagesID;
import addResourceLoaderHere.innObjectLoader.AdventurerPhysicalObjectLoader;
import addResourceLoaderHere.innObjectLoader.ObjectFileLoader;
import jGameFramework.core.Loader;
import jGameFramework.display.DisplayableDepth;
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

    private Player player;

    private ModelObjectLoader modelObjectLoader;
    private AdventurerPhysicalObjectLoader adventurerLoader;
    private DepthHandler depthHandler;

    /**
     * Constructor
     */
    public PhysicalObjectLoader(ImageLoader imageLoader){
        this.imageLoader = imageLoader;
        fileLoader = new ObjectFileLoader();
        modelObjectLoader = new ModelObjectLoader();
        depthHandler = new DepthHandler();

        adventurerLoader = new AdventurerPhysicalObjectLoader(modelObjectLoader.getInn(), depthHandler);

        player = new Player(400, 600, DepthHandler.PLAYER_DEPTH);
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

        objects.add(player);

        objects.add(fileLoader.getWall(InnId.floor1));

        objects.addAll(adventurerLoader.get(fileLoader.getAdventurerStartingPositions(InnId.floor1)));

        return objects;
    }

    private TreeSet<PhysicalObject> getNightObjects() {
        TreeSet<PhysicalObject> objects = new TreeSet<>();
        return objects;
    }

    private TreeSet<PhysicalObject> getTestObjects() {
        TreeSet<PhysicalObject> objects = new TreeSet<>();

        return objects;
    }

    /**
     * The camera will be static for now, independently of gameThread
     */
    public Camera getCamera(GameThreadID gameThreadID){
        if (gameThreadID == GameThreadID.Floor1) {
            return new CameraFollowingPlayer(player);
        }

        return new CameraStill();
    }

}
