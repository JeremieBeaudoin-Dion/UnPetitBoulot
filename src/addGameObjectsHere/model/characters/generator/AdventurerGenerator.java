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
    private AdventurerTraitGenerator adventurerTraitGenerator;

    public AdventurerGenerator() {
        this.unitStatGenerator = new UnitStatGenerator();
        this.adventurerTraitGenerator = new AdventurerTraitGenerator();
    }

    public Adventurer generateAdventurer(Client client) {
        int cost = 0;

        switch(client.getClientID()) {
            case Ranger:
                cost = 100;
            case Rogue:
                cost = 110;
            case Warrior:
                cost = 130;
        }

        return new Adventurer(client, unitStatGenerator.generate(client.getClientID()),
                adventurerTraitGenerator.generate(0, 2), cost, 0);
    }

}
