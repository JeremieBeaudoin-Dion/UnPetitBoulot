package addGameObjectsHere.model.characters.generator;

import addGameObjectsHere.model.characters.client.ClientID;
import addGameObjectsHere.model.quests.challenges.fight.CharacterDamage;
import addGameObjectsHere.model.quests.challenges.fight.CharacterHealth;
import addGameObjectsHere.model.quests.challenges.fight.CharacterLine;
import addGameObjectsHere.model.quests.challenges.fight.CharacterStats;

import java.util.Random;

/**
 * @author Jérémie Beaudoin-Dion
 */
class UnitStatGenerator {

    private Random random;

    /**
     * Constructor
     */
    UnitStatGenerator() {
        random = new Random();
    }

    CharacterStats generate(ClientID clientID) {
        return getBaseStat(clientID);
    }

    private CharacterStats getBaseStat(ClientID clientID) {

        switch (clientID) {
            case Warrior:
                return new CharacterStats(new CharacterDamage(5, 0, true),
                        CharacterLine.front, new CharacterHealth(75, 3));

            case Rogue:
                return new CharacterStats(new CharacterDamage(10, 5, true),
                        CharacterLine.middle, new CharacterHealth(25, 3));

            case Ranger:
                return new CharacterStats(new CharacterDamage(15, 0, true),
                        CharacterLine.back, new CharacterHealth(25, 3));
        }

        throw new IllegalArgumentException("The class: " + clientID + "does not exists");
    }
}
