package addGameObjectsHere.view.threadInn.handler;

import addGameObjectsHere.model.characters.adventurers.Adventurer;
import addGameObjectsHere.model.characters.generator.AdventurerGenerator;
import addGameObjectsHere.model.inn.Inn;
import addGameObjectsHere.view.threadInn.characters.AdventurerImageID;
import addGameObjectsHere.view.threadInn.characters.ClientPhysicalObject;
import addResourceLoaderHere.DepthHandler;
import jGameFramework.physicalObjects.Position;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * TODO: Generify this so it does not only creates Adventurers.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ClientPhysicalObjectLoader {

    private Inn inn;
    private List<Position> startingPositions;  // TODO: This will have to be generic, so many positions per Inn.
    private AdventurerGenerator adventurerGenerator;

    public ClientPhysicalObjectLoader(Inn inn, List<Position> startingPositions) {
        this.inn = inn;

        adventurerGenerator = new AdventurerGenerator(inn);
        this.startingPositions = startingPositions;
    }

    public List<ClientPhysicalObject> get() {
        List<ClientPhysicalObject> listToReturn = new LinkedList<>();

        Collections.shuffle(startingPositions);

        int numberOfAdventurersToCreate = inn.getNumberOfAdventurersToCreate();

        Adventurer adventurer;
        for (int i=0; i<numberOfAdventurersToCreate; i++) {
             adventurer = adventurerGenerator.generateAdventurer();

            listToReturn.add(new ClientPhysicalObject(startingPositions.get(i).getX(),
                    startingPositions.get(i).getY(), DepthHandler.ADVENTURER_DEPTH,
                    adventurer, AdventurerImageID.getID(adventurer.getAdventurerClass())));
        }

        return listToReturn;
    }

}
