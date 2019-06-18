package addGameObjectsHere.model.quests;

import addGameObjectsHere.model.characters.Unit;
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

    private List<Unit> characters;

    public Party (List<Unit> characters) {
        this.characters = characters;
    }

    public void handleChallenge(Challenge challenge) {

    }

    private void fight(List<Unit> enemies) {

        for (Unit character : characters) {
            character.startFight();
        }

        InitiativeQueue initiativeQueue = new InitiativeQueue(characters, enemies);
        Battleground battleground = new Battleground(characters, enemies);

        while (battleground.isActive()) {
            battleground.doDamage(initiativeQueue.getNext());
        }

        for (Unit character: characters) {
            if (character.isDead()) {
                characters.remove(character);
            }
        }

    }

    public boolean isActive() {
        return characters.isEmpty();
    }

}
