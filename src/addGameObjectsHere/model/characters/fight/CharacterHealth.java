package addGameObjectsHere.model.characters.fight;

/**
 * The CharacterHealth takes track of the current health of a character.
 *
 * The method startFight() must be called before each fight.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class CharacterHealth implements ImmutableCharacterHealth {

    private int maxHP;
    private int endurance;

    private int totalDamage;
    private int currentDamage;


    public CharacterHealth(int maxHP, int endurance) {
        this.maxHP = maxHP;
        this.endurance = endurance;
        this.totalDamage = 0;
        this.currentDamage = 0;
    }

    public void startFight() {
        this.currentDamage = 0;
    }

    public void doDamage(int damage) {
        totalDamage += damage;
        currentDamage += damage;
    }

    public boolean isDead() {
        return totalDamage >= maxHP;
    }

    public boolean isOut() {
        return currentDamage >= maxHP / endurance;
    }

    public int getMaxHp() {
        return maxHP;
    }

    public int getCurrentHP() {
        return maxHP - totalDamage;
    }
}
