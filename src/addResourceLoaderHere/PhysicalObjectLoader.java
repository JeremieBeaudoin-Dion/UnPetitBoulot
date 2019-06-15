package addResourceLoaderHere;

import addGameObjectsHere.view.camera.CameraStill;
import addGameObjectsHere.view.dayNight.DayUI;
import addGameObjectsHere.view.dayNight.NightUI;
import addGameObjectsHere.view.gameButtons.ButtonID;
import jGameFramework.core.Loader;
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

    /**
     * Constructor
     */
    public PhysicalObjectLoader(ImageLoader imageLoader){
        this.imageLoader = imageLoader;
        viewLoader = new ViewLoader();
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

        objects.add(new DayUI());

        //objects.add(new AdventurerRecruitWindow(new Adventurer(), new BoundingArea(50, 50, 300, 300), new DisplayableDepth(200)));

        return objects;
    }

    /**
     * The camera will be static for now, independently of gameThread
     */
    public Camera getCamera(GameThreadID gameThreadID){
        return new CameraStill();
    }

}
