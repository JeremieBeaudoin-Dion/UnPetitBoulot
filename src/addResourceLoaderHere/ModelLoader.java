package addResourceLoaderHere;

import addGameObjectsHere.controller.dayNight.DayInteractingObject;
import addGameObjectsHere.controller.windows.AdventurerWindow;
import addGameObjectsHere.model.characters.generator.AdventurerGenerator;
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
        Inn testInn = createTestInn();

        adventurerWindow = new AdventurerWindow(testInn, new Position(100, 100), 100);
    }

    private Inn createTestInn() {
        Inn inn = new Inn();

        AdventurerGenerator adventurerGenerator = new AdventurerGenerator();

        inn.addAdventurer(adventurerGenerator.generateAdventurer(0));
        inn.addAdventurer(adventurerGenerator.generateAdventurer(0));
        inn.addAdventurer(adventurerGenerator.generateAdventurer(0));

        return inn;
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
