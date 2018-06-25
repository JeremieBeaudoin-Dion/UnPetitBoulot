package addGameObjectsHere.characters.generator;

import addGameObjectsHere.characters.AdventurerClassEnum;
import addGameObjectsHere.characters.UnitStat;

import java.util.LinkedList;
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

    UnitStat generate(AdventurerClassEnum adventurerClassEnum) {
        return getBaseStat(adventurerClassEnum);
    }

    private UnitStat getBaseStat(AdventurerClassEnum characterClass) {

        switch (characterClass) {
            case Fighter:
                return new UnitStat(75, 5, 10, 0.3, 1, new LinkedList<>());

            case Rogue:
                return new UnitStat(25, 10, 15, 0.3, 2, new LinkedList<>());

            case Ranger:
                return new UnitStat(25, 15, 10, 0.3, 3, new LinkedList<>());
        }

        throw new IllegalArgumentException("The class: " + characterClass + "does not exists");
    }
}
