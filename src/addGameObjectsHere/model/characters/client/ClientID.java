package addGameObjectsHere.model.characters.client;

import addGameObjectsHere.model.characters.adventurers.AdventurerClassEnum;

/**
 * The current ID of the client - helps with description, etc.
 *
 * @author Jérémie Beaudoin-Dion
 */
public enum ClientID {
    Ranger, Rogue, Warrior, Man, SmallMan, OldWoman;

    public static ClientID getIDFromAdventurer(AdventurerClassEnum adventurerClass) {
        switch (adventurerClass) {
            case Rogue:
                return ClientID.Rogue;
            case Ranger:
                return ClientID.Ranger;
            case Warrior:
                return ClientID.Warrior;
        }

        throw new UnsupportedOperationException("The adventurer class: " + adventurerClass.name() +
                " is not implemented yet.");

    }
}
