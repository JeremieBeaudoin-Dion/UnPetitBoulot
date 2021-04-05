package addGameObjectsHere.model.characters.adventurers.stats;

/**
 * List of perks implemented.
 */
public enum ClassPerkId {

    Tank, LockPicker, Survivalist, Tough, Nimble, Rugged;

    public static String getDescription(ClassPerkId classPerkId) {
        switch (classPerkId) {
            case Tank:
                return "Upon success on a fight, reduces all damage taken to the party by 50%.";
            case LockPicker:
                return "Upon success on a trap, cancels damage taken by another failing adventurer.";
            case Survivalist:
                return "Upon success on a hazard, cancels despair taken by another failing adventurer.";
            case Tough:
                return "Gains 2 attack.";
            case Nimble:
                return "Gains 2 dexterity.";
            case Rugged:
                return "Gains 2 constitution.";
        }
        throw new IllegalArgumentException("The perk :" + classPerkId.toString() + " is not implemented.");
    }

}
