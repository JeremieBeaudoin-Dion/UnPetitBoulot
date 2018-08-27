package addGameObjectsHere.characters.fight;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class CharacterHP {

    private int maxHP;
    private int totalDamage;

    public CharacterHP(int maxHP) {
        this.maxHP = maxHP;
        this.totalDamage = 0;
    }

    public void doDamage(int dmg) {
        totalDamage += dmg;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getCurrentHP() {
        return maxHP - totalDamage;
    }

    public boolean isDead() {
        return totalDamage >= maxHP;
    }
}
