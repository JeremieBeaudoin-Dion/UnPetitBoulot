package addGameObjectsHere.model.quests;

import addGameObjectsHere.model.characters.adventurers.Unit;
import addGameObjectsHere.model.quests.fight.Battleground;
import addGameObjectsHere.model.quests.fight.InitiativeQueue;

import java.util.*;

/**
 * During an adventure, a party consists of a set of adventurers
 * who will endure the following threats:
 *      - Fights
 *      - Traps
 *      - Etc.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Party {

    private List<Unit> unitAdventurers;

    public Party () {
        this.unitAdventurers = new LinkedList<>();
    }

    public void addUnit(Unit unit) {
        this.unitAdventurers.add(unit);
    }

    public void handleChallenge(Challenge challenge) {

    }

    private void fight(List<Unit> enemies) {

        for (Unit character : unitAdventurers) {
            character.startFight();
        }

        InitiativeQueue initiativeQueue = new InitiativeQueue(unitAdventurers, enemies);
        Battleground battleground = new Battleground(unitAdventurers, enemies);

        while (battleground.isActive()) {
            battleground.doDamage(initiativeQueue.getNext());
        }

        for (Unit character: unitAdventurers) {
            if (character.isDead()) {
                unitAdventurers.remove(character);
            }
        }

    }

    public boolean isActive() {
        return unitAdventurers.isEmpty();
    }

}
