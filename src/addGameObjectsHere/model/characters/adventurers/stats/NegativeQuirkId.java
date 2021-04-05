package addGameObjectsHere.model.characters.adventurers.stats;

/**
 * A negative quirk is a small perk that impedes the adventurer in quests.
 */
public enum NegativeQuirkId {

    Forgetful, Infamous, Miserly;

    public static String getDescription(NegativeQuirkId perkId) {
        switch (perkId) {
            case Forgetful:
                return "30% to forget his items at home (act as if not equipped for the adventure).";
            case Infamous:
                return "LOSE fame when the mission is a failure.";
            case Miserly:
                return "Costs X% more.";
        }
        throw new IllegalArgumentException("The perk :" + perkId.toString() + " is not implemented.");
    }
}
