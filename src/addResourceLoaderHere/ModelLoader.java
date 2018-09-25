package addResourceLoaderHere;

import addGameObjectsHere.controller.dayNight.DayInteractingObject;
import addGameObjectsHere.controller.windows.AdventurerWindow;
import addGameObjectsHere.controller.windows.InnWindow;
import addGameObjectsHere.model.inn.Inn;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.util.LinkedList;
import java.util.List;

/**
 * A class which helps determine all PhysicalObjects needed for the game.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ModelLoader {

    private AdventurerWindow adventurerWindow;
    private InnWindow innWindow;

    public ModelLoader() {
        Inn inn = new Inn();

        adventurerWindow = new AdventurerWindow(inn, new Position(100, 100), 100);
        innWindow = new InnWindow(inn, new Position(200, 200), 200);
    }

    List<PhysicalObject> getAllDayPhysicalObjects() {
        LinkedList<PhysicalObject> physicalObjects = new LinkedList<>();

        physicalObjects.add(adventurerWindow);
        physicalObjects.add(innWindow);

        return physicalObjects;
    }

    List<DayInteractingObject> getAllInteractingObjects() {
        LinkedList<DayInteractingObject> interactingObjects = new LinkedList<>();

        interactingObjects.add(adventurerWindow);

        return interactingObjects;
    }

}
