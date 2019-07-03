package addResourceLoaderHere.innObjectLoader;

import addGameObjectsHere.model.characters.generator.AdventurerGenerator;
import addGameObjectsHere.model.inn.Inn;
import addGameObjectsHere.view.threadInn.adventurers.AdventurerPhysicalObject;
import addResourceLoaderHere.DepthHandler;
import jGameFramework.physicalObjects.Position;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class AdventurerPhysicalObjectLoader {

    private Inn inn;

    private AdventurerGenerator adventurerGenerator;

    public AdventurerPhysicalObjectLoader(Inn inn) {
        this.inn = inn;

        adventurerGenerator = new AdventurerGenerator(inn);
    }

    public List<AdventurerPhysicalObject> get(List<Position> startingPositions) {
        List<AdventurerPhysicalObject> listToReturn = new LinkedList<>();

        Collections.shuffle(startingPositions);

        int numberOfAdventurersToCreate = inn.getNumberOfAdventurersToCreate();

        for (int i=0; i<numberOfAdventurersToCreate; i++) {
            listToReturn.add(new AdventurerPhysicalObject(startingPositions.get(i).getX(),
                    startingPositions.get(i).getY(), DepthHandler.ADVENTURER_DEPTH,
                    adventurerGenerator.generateAdventurer()));
        }

        return listToReturn;
    }

}
