package addGameObjectsHere.model.characters.adventurers.stats;

/**
 * Base statistics that represent a character.
 */
public enum BaseStat {
    Att, Dex, Con, Hp, Hon, Cut;

    public static String getDescription(BaseStat stat) {
        switch (stat) {
            case Att:
                return "Used for fighting encounters.";
            case Dex:
                return "Used for trap encounters.";
            case Con:
                return "Used for weather encounters.";
            case Hp:
                return "Don't let it get to zero.";
            case Hon:
                return "Determines how trustworthy they are.";
            case Cut:
                return "How much money they take home (in %).";
        }

        throw new IllegalArgumentException("The stat :" + stat.toString() + " is not implemented.");
    }


}
