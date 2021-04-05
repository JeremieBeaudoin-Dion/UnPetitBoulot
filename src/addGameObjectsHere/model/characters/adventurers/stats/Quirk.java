package addGameObjectsHere.model.characters.adventurers.stats;

/**
 * A quirk is a small perk that can be positive or negative.
 * All adventurers have 1 positive and 1 negative quirk.
 */
public class Quirk implements Perk {

    private PositiveQuirkId positiveQuirk;
    private NegativeQuirkId negativeQuirk;

    public Quirk(PositiveQuirkId positiveQuirk) {
        this.positiveQuirk = positiveQuirk;
    }

    public Quirk(NegativeQuirkId negativeQuirk) {
        this.negativeQuirk = negativeQuirk;
    }

    public String getName() {
        if (positiveQuirk == null) {
            return negativeQuirk.toString();
        } else {
            return positiveQuirk.toString();
        }
    }

    public String getDescription() {
        if (positiveQuirk == null) {
            return NegativeQuirkId.getDescription(negativeQuirk);
        } else {
            return PositiveQuirkId.getDescription(positiveQuirk);
        }
    }

}
