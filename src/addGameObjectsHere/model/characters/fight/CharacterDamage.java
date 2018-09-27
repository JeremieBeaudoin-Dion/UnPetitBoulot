package addGameObjectsHere.model.characters.fight;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class CharacterDamage implements Comparable<CharacterDamage> {

    private static int GLOBAL_ID = 0;

    private int amount;
    private int initiative;
    private int id;
    private boolean friendly;

    private int numberOfAffectedCharacters;

    public CharacterDamage(int amount, int initiative, boolean friendly) {
        this.id = GLOBAL_ID++;

        this.amount = amount;
        this.initiative = initiative;
        this.friendly = friendly;

        numberOfAffectedCharacters = 0;
    }

    public int getAmount() {
        return amount;
    }

    public int getInitiative() {
        return initiative;
    }

    public boolean isFriendly() {
        return friendly;
    }

    public void setNumberOfAffectedCharacters(int number) {
        numberOfAffectedCharacters = number;
    }

    public int getNumberOfAffectedCharacters() {
        return numberOfAffectedCharacters;
    }

    /**
     * Damages are sorted according to the following rules:
     *      - Highest initiative first
     *      - If initiatives are equal, highest damage is first
     *      - If both are equal, the earlier damage is done first
     */
    @Override
    public int compareTo(CharacterDamage other) {

        if (this.initiative == other.initiative) {
            if (this.amount == other.amount) {
                return id - other.id;
            }

            return other.amount - amount;
        }

        return other.initiative - initiative;
    }

    @Override
    public String toString() {
        return "CharacterDamage; ID: " + id + " Init: " + initiative + " Dmg: " + amount;
    }
}
