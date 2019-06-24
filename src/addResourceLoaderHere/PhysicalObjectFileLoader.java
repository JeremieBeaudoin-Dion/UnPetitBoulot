package addResourceLoaderHere;

import addGameObjectsHere.view.gameObjects.InvisibleSolidPhysicalObject;
import addGameObjectsHere.view.inn.InnImagesID;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.EnumMap;

/**
 * Helper to create the necessary game objects.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class PhysicalObjectFileLoader {

    private EnumMap<InnImagesID, String> mapFilePaths;

    public PhysicalObjectFileLoader() {
        mapFilePaths = new EnumMap<>(InnImagesID.class);

        loadAllFilePaths();
    }

    private void loadAllFilePaths() {
        mapFilePaths.put(InnImagesID.floor1, "Resources/WallMaps/InvisibleWall.oos");
    }

    /**
     * Returns the desired object.
     */
    public PhysicalObject getWall(InnImagesID mapId) {
        return new InvisibleSolidPhysicalObject(readObjectFromFile(mapFilePaths.get(mapId)));
    }

    /**
     * Helper method to get an object from file.
     */
    private BoundingArea readObjectFromFile(String filepath) {
        try {
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            BoundingArea obj = (BoundingArea) objectIn.readObject();

            objectIn.close();
            return obj;

        } catch (Exception e) {
            System.out.println("PhysicalObjectFileLoader couldn't load file: " + filepath);
            e.printStackTrace();
            return null;
        }
    }

}
