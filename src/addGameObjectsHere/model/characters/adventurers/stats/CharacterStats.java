package addGameObjectsHere.model.characters.adventurers.stats;

import java.util.LinkedList;
import java.util.List;

/**
 * Container class for statistics about an adventurer.
 *
 * @author Mia Beaudoin-Dion
 */
public class CharacterStats {

    private CharacterHealth characterHealth;
    private List<Perk> allPerks;
    private int attack;
    private int dexterity;
    private int constitution;
    private int honour;

    public CharacterStats(CharacterHealth health, int attack, int dexterity, int constitution,
                          int honour, List<Perk> allPerks) {
        this.characterHealth = health;
        this.attack = attack;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.honour = honour;
        this.allPerks = allPerks;
    }

    public void doDamage(int damage) {
        characterHealth.doDamage(damage);
    }

    public void startFight() {
        characterHealth.startFight();
    }

    public boolean isDead() {
        return characterHealth.isDead();
    }

    /*
     * Display
     */
    public int getMaxHealth() {
        return characterHealth.getMaxHp();
    }
    public int getCurrentHealth() {
        return characterHealth.getCurrentHP();
    }
    public int getAttack() {
        return attack;
    }
    public int getDexterity() {
        return dexterity;
    }
    public int getHonour() {
        return honour;
    }
    public int getConstitution() {
        return constitution;
    }
    public List<Perk> getClassPerks() {
        List<Perk> classPerks = new LinkedList<>();

        for (Perk perk : allPerks) {
            if (perk instanceof ClassPerk) {
                classPerks.add(perk);
            }
        }

        return classPerks;
    }
    public List<Perk> getQuirks() {
        List<Perk> quirks = new LinkedList<>();

        for (Perk perk : allPerks) {
            if (!(perk instanceof ClassPerk)) {
                quirks.add(perk);
            }
        }

        return quirks;
    }

}
