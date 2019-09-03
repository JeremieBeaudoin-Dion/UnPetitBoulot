package addGameObjectsHere.model.quests.challenges;

import addGameObjectsHere.model.characters.adventurers.Unit;
import addGameObjectsHere.model.quests.challenges.fight.Battleground;
import addGameObjectsHere.model.quests.challenges.fight.InitiativeQueue;

import java.util.List;

/**
 * A fight is a type of challenge in which the adventurers will need to
 * overcome enemies.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Fight implements Challenge {

    private List<Unit> enemies;

    /**
     * Constructor
     */
    public Fight(List<Unit> enemies) {
        this.enemies = enemies;
    }

    @Override
    public void doChallenge(List<Unit> adventurerUnits) {
        for (Unit character : adventurerUnits) {
            character.startFight();
        }

        InitiativeQueue initiativeQueue = new InitiativeQueue(adventurerUnits, enemies);
        Battleground battleground = new Battleground(adventurerUnits, enemies);

        while (battleground.isActive()) {
            battleground.doDamage(initiativeQueue.getNext());
        }

        for (Unit character: adventurerUnits) {
            if (character.isDead()) {
                adventurerUnits.remove(character);
            }
        }
    }
}
