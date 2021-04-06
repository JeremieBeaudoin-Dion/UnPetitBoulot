package addGameObjectsHere.model.characters.generator;

import addGameObjectsHere.model.characters.adventurers.Adventurer;
import addGameObjectsHere.model.characters.adventurers.stats.BaseStat;
import addGameObjectsHere.model.characters.adventurers.stats.CharacterStats;
import addGameObjectsHere.model.characters.adventurers.stats.statsChange.*;
import addGameObjectsHere.model.characters.adventurers.stats.statsChange.perks.BaseStatCreationPerk;
import addGameObjectsHere.model.characters.adventurers.stats.statsChange.perks.DummyPerk;
import addGameObjectsHere.model.characters.client.Client;
import addGameObjectsHere.model.characters.client.ClientID;
import addResourceLoaderHere.EnumHelper;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;

/**
 * A generator that creates adventurers
 *
 * @author Jérémie Beaudoin-Dion
 */
public class AdventurerGenerator extends RandomGenerator {

    public AdventurerGenerator() {
        super();
    }

    public Adventurer generateAdventurer(Client client) {
        return new Adventurer(client, getBaseStat(client));
    }

    private CharacterStats getBaseStat(Client client) {

        EnumMap<BaseStat, Integer> baseStats = new EnumMap<>(BaseStat.class);

        baseStats.put(BaseStat.Hp, getRndInt(45, 55));
        baseStats.put(BaseStat.Hon, getRndInt(3, 5));
        baseStats.put(BaseStat.Cut, getRndInt(19, 21));

        switch (client.getClientID()) {
            case Warrior:
                baseStats.put(BaseStat.Att, getRndInt(3, 5));
                baseStats.put(BaseStat.Dex, getRndInt(1, 3));
                baseStats.put(BaseStat.Con, getRndInt(2, 4));
                break;

            case Rogue:
                baseStats.put(BaseStat.Att, getRndInt(2, 4));
                baseStats.put(BaseStat.Dex, getRndInt(3, 5));
                baseStats.put(BaseStat.Con, getRndInt(1, 3));
                break;

            case Ranger:
                baseStats.put(BaseStat.Att, getRndInt(1, 3));
                baseStats.put(BaseStat.Dex, getRndInt(2, 4));
                baseStats.put(BaseStat.Con, getRndInt(3, 5));
                break;

            default:
                throw new IllegalArgumentException("The class: " + client.getClientID() + "is not implemented.");
        }

        return new CharacterStats(baseStats, getPerks(client));
    }

    private List<Perk> getPerks(Client client) {
        List<Perk> listToReturn = new LinkedList<>();

        switch (client.getClientID()) {
            case Warrior:
                listToReturn.add(new DummyPerk(PerkId.Tank));
                listToReturn.add(new BaseStatCreationPerk(PerkId.Tough, BaseStat.Att, 2, true));
                break;

            case Rogue:
                listToReturn.add(new DummyPerk(PerkId.LockPicker));
                listToReturn.add(new BaseStatCreationPerk(PerkId.Nimble, BaseStat.Dex, 2, true));
                break;

            case Ranger:
                listToReturn.add(new DummyPerk(PerkId.Survivalist));
                listToReturn.add(new BaseStatCreationPerk(PerkId.Rugged, BaseStat.Con, 2, true));
                break;
        }

        PerkId randomPosQuirk = EnumHelper.randomEnum(PerkId.class);
        while (PerkId.getPerkType(randomPosQuirk) != PerkType.positive) {
            randomPosQuirk = EnumHelper.randomEnum(PerkId.class);
        }

        listToReturn.add(new DummyPerk(randomPosQuirk));

        if (randomPosQuirk != PerkId.Stable) {
            PerkId randomNegQuirk = EnumHelper.randomEnum(PerkId.class);
            while (PerkId.getPerkType(randomNegQuirk) != PerkType.negative) {
                randomNegQuirk = EnumHelper.randomEnum(PerkId.class);
            }

            listToReturn.add(new DummyPerk(randomNegQuirk));
        }

        return listToReturn;
    }

}
