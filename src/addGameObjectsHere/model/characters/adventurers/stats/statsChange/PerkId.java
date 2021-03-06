package addGameObjectsHere.model.characters.adventurers.stats.statsChange;

public enum PerkId {
    Tank, LockPicker, Survivalist, Tough, Nimble, Rugged,

    Forgetful, Infamous, Miserly,

    Famous, Humble, Thrifty, Stable;

    public static PerkType getPerkType(PerkId perkID) {
        switch (perkID) {
            case Tank:
            case LockPicker:
            case Survivalist:
            case Tough:
            case Nimble:
            case Rugged:
                return PerkType.classPerk;
            case Famous:
            case Humble:
            case Thrifty:
            case Stable:
                return PerkType.positive;
            case Infamous:
            case Miserly:
            case Forgetful:
                return PerkType.negative;
        }

        throw new IllegalArgumentException("The perk :" + perkID.toString() + " is not implemented.");
    }

    public static String getDescription(PerkId perkId) {
        switch (perkId) {
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

            case Forgetful:
                return "30% to forget his items at home (act as if not equipped for the adventure).";
            case Infamous:
                return "LOSE fame when the mission is a failure.";
            case Miserly:
                return "Costs 5% more (base cut).";

            case Famous:
                return "You gain more FAME when they succeed on a mission.";
            case Humble:
                return "Costs 5% less gold (base cut).";
            case Thrifty:
                return "When equips only one item, receive its benefits twice.";
            case Stable:
                return "Has no negative quirk.";

        }
        throw new IllegalArgumentException("The perk :" + perkId.toString() + " is not implemented.");
    }

}
