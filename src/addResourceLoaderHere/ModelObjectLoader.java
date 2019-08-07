package addResourceLoaderHere;

import addGameObjectsHere.model.characters.adventurers.Adventurer;
import addGameObjectsHere.model.characters.client.Client;
import addGameObjectsHere.model.characters.generator.AdventurerGenerator;
import addGameObjectsHere.model.characters.generator.ClientGenerator;
import addGameObjectsHere.model.inn.Inn;
import addGameObjectsHere.view.threadInn.inn.InnId;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Creates and holds necessary model objects.
 *
 * The model objects keep necessary game information between threads. This
 * can contain, for example, how much money the player will have.
 *
 * GameEvents to SAVE the game will only save ModelObjectLoader and current PhysicalObjects on screen.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ModelObjectLoader implements Serializable {

    private Inn inn;
    private ClientGenerator clientGenerator;

    public ModelObjectLoader() {
        this.inn = new Inn();
        clientGenerator = new ClientGenerator(inn);
    }

    public Inn getInn() {
        return inn;
    }

    public InnId getCurrentInnID() {
        return InnId.floor1; // TODO: Fix this
    }

    public List<Client> getCurrentClients() {
        // Depends on the InnID
        int maxNumberOfAdventurers = inn.getNumberOfAdventurersToCreate();
        List<Client> listToReturn = new LinkedList<>();

        for (int i=0; i<maxNumberOfAdventurers; i++) {
            listToReturn.add(clientGenerator.getNextClient());
        }

        return listToReturn;
    }


}
