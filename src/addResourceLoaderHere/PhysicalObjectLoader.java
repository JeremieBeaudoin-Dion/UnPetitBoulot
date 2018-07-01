package addResourceLoaderHere;

import addGameObjectsHere.*;
import addGameObjectsHere.camera.CameraStill;
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

    public PhysicalObjectLoader(ImageLoader imageLoader){
        this.imageLoader = imageLoader;
    }

    public TreeSet<PhysicalObject> get(GameThreadID gameThreadID){
        switch (gameThreadID){
            case Menu:
                return getMenuObjects();
        }

        throw new IllegalArgumentException("The GameThreadID: " + gameThreadID + " is not implemented.");
    }

    private TreeSet<PhysicalObject> getMenuObjects() {
        TreeSet<PhysicalObject> objects = new TreeSet<>();

        objects.add(new GameLogic());

        return objects;
    }

    public Camera getCamera(GameThreadID gameThreadID){
        switch (gameThreadID){
            case Menu:
                return new CameraStill();
        }

        throw new IllegalArgumentException("The GameThreadID: " + gameThreadID + " is not implemented.");
    }

}
