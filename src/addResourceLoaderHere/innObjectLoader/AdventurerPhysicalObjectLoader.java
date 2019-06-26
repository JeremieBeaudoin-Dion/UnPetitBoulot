package addResourceLoaderHere.innObjectLoader;

import addGameObjectsHere.model.characters.generator.AdventurerGenerator;
import addGameObjectsHere.model.inn.Inn;
import addGameObjectsHere.view.adventurers.AdventurerPhysicalObject;
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
    private DepthHandler depthHandler;

    private AdventurerGenerator adventurerGenerator;

    public AdventurerPhysicalObjectLoader(Inn inn, DepthHandler depthHandler) {
        this.inn = inn;
        this.depthHandler = depthHandler;

        adventurerGenerator = new AdventurerGenerator(inn);
    }

    public List<AdventurerPhysicalObject> get(List<Position> startingPositions) {
        List<AdventurerPhysicalObject> listToReturn = new LinkedList<>();

        Collections.shuffle(startingPositions);

        int numberOfAdventurersToCreate = inn.getNumberOfAdventurersToCreate();

        for (int i=0; i<numberOfAdventurersToCreate; i++) {
            listToReturn.add(new AdventurerPhysicalObject(startingPositions.get(i).getX(),
                    startingPositions.get(i).getY(), depthHandler.getAdventurerDepth(),
                    adventurerGenerator.generateAdventurer()));
        }

        return listToReturn;
    }

}
