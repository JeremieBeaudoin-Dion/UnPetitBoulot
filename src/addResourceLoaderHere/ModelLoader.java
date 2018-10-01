package addResourceLoaderHere;

import addGameObjectsHere.controller.dayNight.DayInteractingObject;
import addGameObjectsHere.controller.windows.adventurers.AdventurerWindow;
import addGameObjectsHere.controller.windows.inn.InnWindow;
import addGameObjectsHere.controller.windows.quest.QuestWindow;
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
class ModelLoader {

    private AdventurerWindow adventurerWindow;
    private InnWindow innWindow;
    private QuestWindow questWindow;

    ModelLoader() {
        Inn inn = new Inn();

        adventurerWindow = new AdventurerWindow(inn, new Position(100, 100));
        innWindow = new InnWindow(inn, new Position(650, 100));
        questWindow = new QuestWindow(new Position(300, 100));
    }

    List<PhysicalObject> getAllDayPhysicalObjects() {
        LinkedList<PhysicalObject> physicalObjects = new LinkedList<>();

        physicalObjects.add(adventurerWindow);
        physicalObjects.add(innWindow);
        physicalObjects.add(questWindow);

        return physicalObjects;
    }

    List<DayInteractingObject> getAllInteractingObjects() {
        LinkedList<DayInteractingObject> interactingObjects = new LinkedList<>();

        interactingObjects.add(adventurerWindow);
        interactingObjects.add(questWindow);

        return interactingObjects;
    }

}
