package addGameObjectsHere.model.quests;

import addGameObjectsHere.model.characters.adventurers.Adventurer;
import addGameObjectsHere.model.characters.adventurers.AdventurerDailyHistory;
import addGameObjectsHere.model.characters.adventurers.Unit;
import addGameObjectsHere.model.quests.challenges.Challenge;

import java.util.*;

/**
 * During an adventure, a party consists of a set of adventurers
 * who will overcome the following threats:
 *      - Fights
 *      - Traps
 *      - Etc.
 *
 * @author Mia Beaudoin-Dion
 */
public class Party {

    private List<Adventurer> adventurers;

    public Party () {
        this.adventurers = new LinkedList<>();
    }

    public void add(Adventurer adventurer) {
        this.adventurers.add(adventurer);
    }

    public void handleChallenge(Challenge challenge) {
        //challenge.doChallenge(createUnitListFromAdventurers());
    }

    private List<Unit> createUnitListFromAdventurers() {
        List<Unit> unitList = new LinkedList<>();

        for (Adventurer adventurer : adventurers) {
            if (!adventurer.getUnit().isDead()) {
                unitList.add(adventurer.getUnit());
            }
        }

        return unitList;
    }

    public List<AdventurerDailyHistory> getCurrentHistory() {
        List<AdventurerDailyHistory> unitList = new LinkedList<>();

        for (Adventurer adventurer : adventurers) {
            unitList.add(new AdventurerDailyHistory(adventurer));
        }

        return unitList;
    }

    public boolean isActive() {
        for (Adventurer adventurer : adventurers) {
            if (adventurer.getUnit().isDead()) {
                return false;
            }
        }

        return true;
    }

}
