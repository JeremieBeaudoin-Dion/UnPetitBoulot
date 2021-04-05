package addGameObjectsHere.model.characters.adventurers.stats;

public enum PositiveQuirkId {
    Famous, Humble, Thrifty;

    public static String getDescription(PositiveQuirkId perkId) {
        switch (perkId) {
            case Famous:
                return "You gain more FAME when they succeed on a mission.";
            case Humble:
                return "Costs 50% less gold (base price).";
            case Thrifty:
                return "When equips only one item, receive its benefits twice.";
        }
        throw new IllegalArgumentException("The perk :" + perkId.toString() + " is not implemented.");
    }
}
