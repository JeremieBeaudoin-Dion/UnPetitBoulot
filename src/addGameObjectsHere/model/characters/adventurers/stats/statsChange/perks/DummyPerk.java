package addGameObjectsHere.model.characters.adventurers.stats.statsChange.perks;

import addGameObjectsHere.model.characters.adventurers.stats.statsChange.Perk;
import addGameObjectsHere.model.characters.adventurers.stats.statsChange.PerkId;

/**
 * Helper class to let code compile. This perk does nothing.
 */
public class DummyPerk extends Perk {

    public DummyPerk(PerkId id) {
        super(id);
    }

    @Override
    protected void doStartTrigger() {
        // do nothing
    }

    @Override
    protected void doEndTrigger() {
        // do nothing
    }
}
