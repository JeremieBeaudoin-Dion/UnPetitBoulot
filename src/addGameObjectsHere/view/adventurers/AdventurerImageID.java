package addGameObjectsHere.view.adventurers;

import addGameObjectsHere.model.characters.AdventurerClassEnum;

/**
 * @author Jérémie Beaudoin-Dion
 */
public enum AdventurerImageID {
    Ranger, Rogue, Warrior;

    public static AdventurerImageID getID(AdventurerClassEnum advClass) {
        switch (advClass) {
            case Rogue:
                return AdventurerImageID.Rogue;
            case Ranger:
                return AdventurerImageID.Ranger;
            case Fighter:
                return AdventurerImageID.Warrior;
        }

        throw new UnsupportedOperationException("The adventurer class: " + advClass.name() + " is not implemented yet.");
    }
}
