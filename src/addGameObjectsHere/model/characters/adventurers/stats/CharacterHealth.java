package addGameObjectsHere.model.characters.adventurers.stats;

/**
 * The CharacterHealth takes track of the current health of a character.
 *
 * The method startFight() must be called before each fight.
 *
 * @author Mia Beaudoin-Dion
 */
public class CharacterHealth {

    private int maxHP;

    private int totalDamage;
    private int currentDamage;

    public CharacterHealth(int maxHP) {
        this.maxHP = maxHP;
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

    public int getMaxHp() {
        return maxHP;
    }

    public int getCurrentHP() {
        return maxHP - totalDamage;
    }
}
