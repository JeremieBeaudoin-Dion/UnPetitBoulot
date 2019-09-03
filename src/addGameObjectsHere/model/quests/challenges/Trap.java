package addGameObjectsHere.model.quests.challenges;

import addGameObjectsHere.model.characters.adventurers.Unit;
import addGameObjectsHere.model.quests.challenges.fight.CharacterDamage;

import java.util.List;

/**
 * A trap will do a certain amount of damage to the party
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Trap implements Challenge {

    private CharacterDamage damage;

    public Trap(CharacterDamage damage) {
        this.damage = damage;
    }

    @Override
    public void doChallenge(List<Unit> adventurerUnits) {
        for (Unit adventurerUnit: adventurerUnits) {
            adventurerUnit.doDamage(damage);
        }
    }
}
