package addGameObjectsHere.characters.fight;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class CharacterStats implements DisplayableCharacterStats {

    private CharacterDamage damage;
    private CharacterLine line;
    private CharacterHealth characterHealth;

    public CharacterStats(CharacterDamage damage, CharacterLine line, CharacterHealth health) {
        this.damage = damage;
        this.line = line;
        this.characterHealth = health;
    }

    public void doDamage(int damage) {
        characterHealth.doDamage(damage);
    }

    public void startFight() {
        characterHealth.startFight();
    }

    public CharacterDamage getDamage() {
        return damage;
    }

    public boolean isOut() {
        return characterHealth.isOut();
    }

    public boolean isDead() {
        return characterHealth.isDead();
    }

    /*
     * Display
     */

    public DisplayableCharacterHealth getHealth() {
        return characterHealth;
    }

    public CharacterLine getLine() {
        return line;
    }

}
