package addGameObjectsHere.model.characters.generator;

import addGameObjectsHere.model.characters.adventurers.Adventurer;
import addGameObjectsHere.model.characters.client.Client;

/**
 * A generator that creates adventurers
 *
 * @author Jérémie Beaudoin-Dion
 */
public class AdventurerGenerator {

    private UnitStatGenerator unitStatGenerator;

    public AdventurerGenerator() {
        this.unitStatGenerator = new UnitStatGenerator();
    }

    public Adventurer generateAdventurer(Client client) {
        return new Adventurer(client, unitStatGenerator.generate(client.getClientID()), 20);
    }

}
