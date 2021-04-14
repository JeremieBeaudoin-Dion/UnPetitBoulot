package addGameObjectsHere.model.characters.generator;

import addGameObjectsHere.model.characters.client.Client;
import addGameObjectsHere.model.characters.villager.Villager;

/**
 * @author Mia Beaudoin-Dion
 */
public class VillagerGenerator {

    public Villager generateVillager(Client client) {
        return new Villager(client);
    }
}
