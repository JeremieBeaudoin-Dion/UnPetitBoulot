package addResourceLoaderHere;

import addGameObjectsHere.controller.dayNight.DayInteractingObject;
import addGameObjectsHere.controller.windows.AdventurerWindow;
import addGameObjectsHere.model.inn.Inn;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class ModelLoader {

    private AdventurerWindow adventurerWindow;

    public ModelLoader() {
        Inn inn = new Inn();

        adventurerWindow = new AdventurerWindow(inn, new Position(100, 100), 100);
    }

    List<PhysicalObject> getAllDayPhysicalObjects() {
        LinkedList<PhysicalObject> physicalObjects = new LinkedList<>();

        physicalObjects.add(adventurerWindow);

        return physicalObjects;
    }

    List<DayInteractingObject> getAllInteractingObjects() {
        LinkedList<DayInteractingObject> interactingObjects = new LinkedList<>();

        interactingObjects.add(adventurerWindow);

        return interactingObjects;
    }

}
