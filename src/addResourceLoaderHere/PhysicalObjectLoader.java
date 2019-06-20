package addResourceLoaderHere;

import addGameObjectsHere.view.adventurers.Player;
import addGameObjectsHere.view.camera.CameraFollowingPlayer;
import addGameObjectsHere.view.camera.CameraStill;
import addGameObjectsHere.view.dayNight.DayUI;
import addGameObjectsHere.view.dayNight.NightUI;
import addGameObjectsHere.view.gameButtons.ButtonID;
import addGameObjectsHere.view.inn.Inn;
import addGameObjectsHere.view.inn.InnImagesID;
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
    private ViewLoader viewLoader;
    private PhysicalObjectFileLoader fileLoader;

    private Player player;

    /**
     * Constructor
     */
    public PhysicalObjectLoader(ImageLoader imageLoader){
        this.imageLoader = imageLoader;
        viewLoader = new ViewLoader();
        fileLoader = new PhysicalObjectFileLoader();

        player = new Player(400, 600, new DisplayableDepth(500));
    }

    /**
     * Returns game object to handle according to gameThreadID
     */
    public TreeSet<PhysicalObject> get(GameThreadID gameThreadID){
        switch (gameThreadID){
            case Day:
                return getDayObjects();

            case Night:
                return getNightObjects();

            case Test:
                return getTestObjects();
        }

        throw new IllegalArgumentException("The GameThreadID: " + gameThreadID + " is not implemented.");
    }

    private TreeSet<PhysicalObject> getDayObjects() {
        TreeSet<PhysicalObject> objects = new TreeSet<>();

        objects.add(new DayUI());

        objects.addAll(viewLoader.getAllDayPhysicalObjects());

        objects.add(viewLoader.getButton(ButtonID.Day));
        objects.add(new DayUI());

        return objects;
    }


    private TreeSet<PhysicalObject> getNightObjects() {
        TreeSet<PhysicalObject> objects = new TreeSet<>();

        objects.add(viewLoader.getButton(ButtonID.Night));

        objects.add(new NightUI());

        return objects;
    }

    private TreeSet<PhysicalObject> getTestObjects() {
        TreeSet<PhysicalObject> objects = new TreeSet<>();

        objects.add(new Inn());

        objects.add(player);

        objects.add(fileLoader.getWall(InnImagesID.floor1));

        return objects;
    }

    /**
     * The camera will be static for now, independently of gameThread
     */
    public Camera getCamera(GameThreadID gameThreadID){
        if (gameThreadID == GameThreadID.Test) {
            return new CameraFollowingPlayer(player);
        }

        return new CameraStill();
    }

}
