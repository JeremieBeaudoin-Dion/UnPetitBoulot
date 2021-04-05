package addGameObjectsHere.model.characters.generator;

import addGameObjectsHere.model.characters.adventurers.stats.*;
import addGameObjectsHere.model.characters.client.ClientID;
import addResourceLoaderHere.EnumHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Mia Beaudoin-Dion
 */
class UnitStatGenerator extends RandomGenerator {

    CharacterStats generate(ClientID clientID) {
        return getBaseStat(clientID);
    }

    private CharacterStats getBaseStat(ClientID clientID) {

        switch (clientID) {
            case Warrior:
                return new CharacterStats(getRdnHealth(),
                        getRndInt(3, 5),
                        getRndInt(1, 3),
                        getRndInt(2, 4),
                        getRndInt(3, 5),
                        getPerks(clientID));

            case Rogue:
                return new CharacterStats(getRdnHealth(),
                        getRndInt(2, 4),
                        getRndInt(3, 5),
                        getRndInt(1, 3),
                        getRndInt(3, 5),
                        getPerks(clientID));

            case Ranger:
                return new CharacterStats(getRdnHealth(),
                        getRndInt(1, 3),
                        getRndInt(2, 4),
                        getRndInt(3, 5),
                        getRndInt(3, 5),
                        getPerks(clientID));
        }

        throw new IllegalArgumentException("The class: " + clientID + "is not implemented.");
    }

    private List<Perk> getPerks(ClientID clientID) {
        List<Perk> listToReturn = new LinkedList<>();

        switch (clientID) {
            case Warrior:
                listToReturn.add(new ClassPerk(ClassPerkId.Tank));
                listToReturn.add(new ClassPerk(ClassPerkId.Tough));
                break;

            case Rogue:
                listToReturn.add(new ClassPerk(ClassPerkId.LockPicker));
                listToReturn.add(new ClassPerk(ClassPerkId.Nimble));
                break;

            case Ranger:
                listToReturn.add(new ClassPerk(ClassPerkId.Survivalist));
                listToReturn.add(new ClassPerk(ClassPerkId.Rugged));
                break;
        }

        listToReturn.add(new Quirk(EnumHelper.randomEnum(PositiveQuirkId.class)));
        listToReturn.add(new Quirk(EnumHelper.randomEnum(NegativeQuirkId.class)));

        return listToReturn;
    }

    private CharacterHealth getRdnHealth() {
        return new CharacterHealth(getRndInt(45, 55));
    }
}
