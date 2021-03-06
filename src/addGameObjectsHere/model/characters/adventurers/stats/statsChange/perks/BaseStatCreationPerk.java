package addGameObjectsHere.model.characters.adventurers.stats.statsChange.perks;

import addGameObjectsHere.model.characters.adventurers.Adventurer;
import addGameObjectsHere.model.characters.adventurers.stats.BaseStat;
import addGameObjectsHere.model.characters.adventurers.stats.statsChange.Perk;
import addGameObjectsHere.model.characters.adventurers.stats.statsChange.PerkId;
import addGameObjectsHere.model.characters.adventurers.stats.statsChange.StatBuff;
import addGameObjectsHere.model.characters.adventurers.stats.statsChange.Trigger;

/**
 * A perk that, on character creation, changes a specific stat.
 */
public class BaseStatCreationPerk extends Perk {

    private Adventurer owner;
    private StatBuff buffToAdd;

    public BaseStatCreationPerk(PerkId id, BaseStat statToChange, int amount, boolean isAddition) {
        super(id, Trigger.CharacterCreation, null);
        buffToAdd = new StatBuff(statToChange, amount, isAddition);
    }

    public void setOwner(Adventurer adventurer) {
        owner = adventurer;
    }

    @Override
    protected void doStartTrigger() {
        buffToAdd.setDelete(false);
        owner.getUnit().getStats().addBuff(buffToAdd);
    }

    @Override
    protected void doEndTrigger() {
        buffToAdd.setDelete(true);
    }
}
