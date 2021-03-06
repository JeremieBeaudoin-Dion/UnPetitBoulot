package addGameObjectsHere.model.characters.adventurers.stats.statsChange;

import addGameObjectsHere.model.characters.adventurers.stats.BaseStat;
import addGameObjectsHere.model.characters.adventurers.stats.statsChange.perks.BaseStatCreationPerk;
import addGameObjectsHere.model.characters.adventurers.stats.statsChange.perks.DummyPerk;

/**
 * Creates the perk object from ID
 */
public class PerkGenerator {

    public static Perk getPerk(PerkId perkId) {
        switch(perkId) {
            case Rugged:
                return new BaseStatCreationPerk(perkId, BaseStat.Con, 2, true);
            case Nimble:
                return new BaseStatCreationPerk(perkId, BaseStat.Dex, 2, true);
            case Tough:
                return new BaseStatCreationPerk(perkId, BaseStat.Att, 2, true);
            case Miserly:
                return new BaseStatCreationPerk(perkId, BaseStat.Cut, 5, true);
            case Humble:
                return new BaseStatCreationPerk(perkId, BaseStat.Cut, -5, true);

        }

        // In case of unimplemented, do not throw, create dummy one instead.
        return new DummyPerk(perkId);
    }




}
