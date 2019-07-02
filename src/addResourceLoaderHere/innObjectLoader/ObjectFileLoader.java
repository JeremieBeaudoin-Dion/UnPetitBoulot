package addResourceLoaderHere.innObjectLoader;

import addGameObjectsHere.view.gameObjects.InvisibleSolidPhysicalObject;
import addGameObjectsHere.view.inn.InnId;
import addGameObjectsHere.view.inn.InnImagesID;
import addGameObjectsHere.view.innObjects.ServingTablePhysicalObject;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.EnumMap;
import java.util.List;

/**
 * Helper to create the necessary game objects.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ObjectFileLoader {

    private EnumMap<InnId, String> invisibleWallMapFilePaths;
    private EnumMap<InnId, String> adventurerPositionMapFilePaths;
    private EnumMap<InnId, String> tableMapFilePaths;

    public ObjectFileLoader() {
        invisibleWallMapFilePaths = new EnumMap<>(InnId.class);
        adventurerPositionMapFilePaths = new EnumMap<>(InnId.class);
        tableMapFilePaths = new EnumMap<>(InnId.class);

        loadAllFilePaths();
    }

    private void loadAllFilePaths() {
        invisibleWallMapFilePaths.put(InnId.floor1, "Resources/WallMaps/InvisibleWall.oos");

        adventurerPositionMapFilePaths.put(InnId.floor1, "Resources/WallMaps/Floor1Positions.oos");

        tableMapFilePaths.put(InnId.floor1, "Resources/WallMaps/Floor1Tables.oos");
    }

    /**
     * Returns the desired invisible wall
     */
    public PhysicalObject getWall(InnId mapId) {
        return new InvisibleSolidPhysicalObject((BoundingArea) readObjectFromFile(invisibleWallMapFilePaths.get(mapId)));
    }

    /**
     * Helper method to get an object from file.
     */
    private Object readObjectFromFile(String filepath) {
        try {
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();

            objectIn.close();
            return obj;

        } catch (Exception e) {
            System.out.println("ObjectFileLoader couldn't load file: " + filepath);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Returns the desired list of positions
     */
    @SuppressWarnings("unchecked")
    public List<Position> getAdventurerStartingPositions(InnId mapId) {
        return (List<Position>) readObjectFromFile(adventurerPositionMapFilePaths.get(mapId));
    }

    /**
     * Returns all the tables
     */
    @SuppressWarnings("unchecked")
    public List<ServingTablePhysicalObject> getAllTables(InnId mapId) {
        return (List<ServingTablePhysicalObject>) readObjectFromFile(tableMapFilePaths.get(mapId));
    }

}
