package addGameObjectsHere.model.characters.generator;

import addGameObjectsHere.model.characters.AdventurerClassEnum;
import addGameObjectsHere.model.quests.fight.CharacterDamage;
import addGameObjectsHere.model.quests.fight.CharacterHealth;
import addGameObjectsHere.model.quests.fight.CharacterLine;
import addGameObjectsHere.model.quests.fight.CharacterStats;

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

    CharacterStats generate(AdventurerClassEnum adventurerClassEnum) {
        return getBaseStat(adventurerClassEnum);
    }

    private CharacterStats getBaseStat(AdventurerClassEnum characterClass) {

        switch (characterClass) {
            case Fighter:
                return new CharacterStats(new CharacterDamage(5, 0, true),
                        CharacterLine.front, new CharacterHealth(75, 3));

            case Rogue:
                return new CharacterStats(new CharacterDamage(10, 5, true),
                        CharacterLine.middle, new CharacterHealth(25, 3));

            case Ranger:
                return new CharacterStats(new CharacterDamage(15, 0, true),
                        CharacterLine.back, new CharacterHealth(25, 3));
        }

        throw new IllegalArgumentException("The class: " + characterClass + "does not exists");
    }
}
